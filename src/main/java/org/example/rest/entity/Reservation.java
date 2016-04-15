package org.example.rest.entity;

import java.util.Date;

import org.example.rest.validation.ValidateReservation;

@ValidateReservation
public class Reservation extends Entity {
    private static final long serialVersionUID = -8823467673156439404L;

    public Long roomId;
    public Date startDate;
    public Date endDate;
    public String personName;
    public String personPhone;

    public Reservation() {}

    public Reservation(final Long roomId, final Date startDate, final Date endDate, final String personName,
            final String personPhone) {
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personName = personName;
        this.personPhone = personPhone;
    }
}