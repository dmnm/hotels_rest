package org.example.rest.dao;

import javax.inject.Named;

import org.example.rest.entity.ArrivalPlan;

@Named
public class ArrivalPlansDao extends GenericDao<ArrivalPlan> {
    public ArrivalPlan findByHotel(final Long hotelId) {
        return repo.values().stream().filter(p -> p.hotel != null && p.hotel.getId().equals(hotelId)).findFirst()
                .orElse(null);
    }
}
