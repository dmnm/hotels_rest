package org.example.rest.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArrivalPlan extends Entity {
    private static final long serialVersionUID = -2886427927279519363L;

    @JsonIgnore
    public final Hotel hotel;
    public final List<StartEndDates> ranges;

    public ArrivalPlan(final Hotel hotel, final StartEndDates... ranges) {
        this.hotel = hotel;
        this.ranges = Arrays.asList(ranges);
    }

    public static class StartEndDates {
        public StartEndDates(final Date startDate, final Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public final Date startDate;
        public final Date endDate;
    }
}
