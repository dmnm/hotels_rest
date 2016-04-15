package org.example.rest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.example.rest.entity.Hotel;

@Named
public class HotelDao extends GenericDao<Hotel> {
    public List<Hotel> findByParams(final boolean hasPool, final boolean hasWaterslides, final boolean hasTennisCourt) {
        return repo.values().stream().filter(
                h -> h.hasPool == hasPool && h.hasWaterslides == hasWaterslides && h.hasTennisCourt == hasTennisCourt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
