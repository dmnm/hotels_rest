package org.example.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Room;

@Named
public class RoomService {
    @Inject
    private RoomDao roomDao;

    public List<Room> getAll() {
        return roomDao.getAll();
    }

    public Room findById(final Long id) {
        return roomDao.findById(id);
    }

}
