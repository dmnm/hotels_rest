package org.example.rest.service;

import static org.apache.commons.lang3.time.DateUtils.isSameDay;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.ArrivalPlansDao;
import org.example.rest.dao.ReservationDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.ArrivalPlan;
import org.example.rest.entity.ArrivalPlan.StartEndDates;
import org.example.rest.entity.Hotel;
import org.example.rest.entity.HotelType;
import org.example.rest.entity.Reservation;

@Named
public class ReservationService {
    @Inject
    private RoomDao roomDao;

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private ArrivalPlansDao arrivalPlansDao; 

    public boolean checkAvailability(final Long roomId, final Date startDate, final Date endDate) {
        final Hotel hotel = roomDao.findById(roomId).hotel;
        if (hotel.type == HotelType.Sanitarium && !checkArrivalPlans(hotel.getId(), startDate, endDate)) {
            return false;
        }

        return checkReservations(roomId, startDate, endDate);
    }

    public void reserve(final Reservation data) {
        reservationDao.save(data);
    }

    private boolean checkArrivalPlans(final Long hotelId, final Date startDate, final Date endDate) {
        final ArrivalPlan plan = arrivalPlansDao.findByHotel(hotelId);
        for (final StartEndDates r : plan.ranges) {
            if (isSameDay(startDate, r.startDate) && isSameDay(endDate, r.endDate)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkReservations(final Long roomId, final Date startDate, final Date endDate) {
        final List<Reservation> reservations = reservationDao.findByRoom(roomId);
        for (final Reservation r : reservations) {
            if ((startDate.after(r.startDate) && startDate.before(r.endDate))
                    || (endDate.after(r.startDate) && endDate.before(r.endDate))) {
                return false;
            } else if (startDate.equals(r.startDate) || startDate.equals(r.endDate) || endDate.equals(r.startDate)
                    || endDate.equals(r.endDate)) {
                return false;
            } else if (r.startDate.after(startDate) && r.startDate.before(endDate)) {
                return false;
            }
        }
        return true;
    }
}
