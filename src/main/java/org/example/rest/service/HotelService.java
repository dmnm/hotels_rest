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

    public Hotel add(final Hotel hotel) {
        hotel.setId(null);
        return save(hotel);
    }

    public Hotel update(final Hotel hotel) {
        final Hotel prev = findById(hotel.getId());
        prev.name = hotel.name;
        prev.type = hotel.type;
        prev.hasPool = hotel.hasPool;
        prev.hasTennisCourt = hotel.hasTennisCourt;
        prev.hasWaterslides = hotel.hasWaterslides;

        return save(prev);
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
