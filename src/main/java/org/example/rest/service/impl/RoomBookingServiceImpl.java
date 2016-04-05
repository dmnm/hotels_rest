package org.example.rest.service.impl;

import javax.inject.Named;

import org.example.rest.dto.BookingRequest;
import org.example.rest.dto.BookingResponse;
import org.example.rest.dto.BookingSearchRequest;
import org.example.rest.dto.BookingSearchResponse;
import org.example.rest.service.RoomBookingService;

@Named
public class RoomBookingServiceImpl implements RoomBookingService {
    @Override
    public BookingResponse bookingRoom(final BookingRequest request) {
        return null;
    }

    @Override
    public BookingSearchResponse searchRooms(final BookingSearchRequest request) {
        return null;
    }
}
