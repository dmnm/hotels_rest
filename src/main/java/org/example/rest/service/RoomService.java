package org.example.rest.service;

import java.util.List;

import org.example.rest.entity.Room;

public interface RoomService {
    List<Room> getAll();

    Room findById(Long id);
}
