package org.example.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Room;

@Named
public class RoomService {
    @Inject
    private RoomDao roomDao;

    @Inject
    private HotelDao hotelDao;

    public List<Room> findByHotelId(final Long hotelId) {
        return hotelDao.findById(hotelId).rooms;
    }

    public Room findById(final Long id) {
        return roomDao.findById(id);
    }

}
