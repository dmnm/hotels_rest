package org.example.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.HotelDao;
import org.example.rest.entity.Hotel;

@Named
public class HotelService {
    @Inject
    private HotelDao dao;

    public List<Hotel> getAll() {
        return dao.getAll();
    }

    public Hotel findById(final Long id) {
        return dao.findById(id);
    }

}
