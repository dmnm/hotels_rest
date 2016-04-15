package org.example.rest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.example.rest.entity.Reservation;

@Named
public class ReservationDao extends GenericDao<Reservation> {
    public List<Reservation> findByRoom(final Long roomId) {
        return repo.values().stream().filter(r -> r.roomId.equals(roomId))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
