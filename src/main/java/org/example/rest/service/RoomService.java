package org.example.rest.service;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Hotel;
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

    public boolean delete(final Long id) {
        final Hotel hotel = findById(id).hotel;

        final Iterator<Room> i = hotel.rooms.iterator();
        while (i.hasNext()) {
            if (id.equals(i.next().getId())) {
                i.remove();
            }
        }

        return roomDao.delete(id);
    }

    public Room add(final Long hotelId, final Room room) {
        final Hotel hotel = hotelDao.findById(hotelId);
        room.hotel = hotel;
        hotel.rooms.add(roomDao.save(room));
        return room;
    }

    public Room update(final Room room) {
        final Room prev = findById(room.getId());

        prev.hasAirConditioning = room.hasAirConditioning;
        prev.hasBalcony = room.hasBalcony;
        prev.hasTV = room.hasTV;
        prev.type = room.type;
        prev.view = room.view;

        return prev;
    }

}
