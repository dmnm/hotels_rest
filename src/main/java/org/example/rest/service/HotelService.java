package org.example.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Hotel;

@Named
public class HotelService {
    @Inject
    private HotelDao hotelDao;

    @Inject
    private RoomDao roomDao;

    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    public Hotel findById(final Long id) {
        return hotelDao.findById(id);
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

}
