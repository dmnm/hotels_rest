package org.example.rest.dto;

import java.util.Date;

public class BookingRequest {
    public final Long roomId;
    public final Date startDate;
    public final Date endDate;
    public final String personName;
    public final String personPhone;

    public BookingRequest(final Long roomId, final Date startDate, final Date endDate, final String personName,
            final String personPhone) {
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personName = personName;
        this.personPhone = personPhone;
    }
}