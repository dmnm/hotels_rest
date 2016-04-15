package org.example.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.ArrivalPlansDao;
import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.ArrivalPlan;
import org.example.rest.entity.Hotel;

@Named
public class HotelService {
    @Inject
    private HotelDao hotelDao;

    @Inject
    private RoomDao roomDao;

    @Inject
    private ArrivalPlansDao arrivalPlansDao;

    public ArrivalPlan getArrivalPlans(final Long hotelId) {
        return arrivalPlansDao.findByHotel(hotelId);
    }

    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    public List<Hotel> getAll(final Integer offset, final Integer limit) {
        return hotelDao.getAll(offset, limit);
    }

    public Hotel findById(final Long id) {
        return hotelDao.findById(id);
    }

    public Long add(final Hotel hotel) {
        hotel.setId(null);
        return save(hotel).getId();
    }

    public Long update(final Hotel hotel) {
        if (delete(hotel.getId())) {
            return save(hotel).getId();
        } else {
            return null;
        }
    }

    public boolean delete(final Long id) {
        final Hotel hotel = findById(id);
        if (hotel != null) {
            hotel.rooms.forEach(room -> {
                roomDao.delete(room);
            });
        }
        return hotelDao.delete(id);
    }

    private Hotel save(final Hotel hotel) {
        hotel.rooms.forEach(room -> {
            room.hotel = hotel;
            roomDao.save(room);
        });
        return hotelDao.save(hotel);
    }
}
