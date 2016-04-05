package org.example.rest.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Room;
import org.example.rest.service.RoomService;

@Named
public class RoomServiceImpl implements RoomService {
    @Inject
    private RoomDao roomDao;

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public Room findById(final Long id) {
        return roomDao.findById(id);
    }

}
