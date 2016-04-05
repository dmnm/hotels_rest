package org.example.rest.service;

import javax.inject.Named;

import org.example.rest.dto.BookingRequest;
import org.example.rest.dto.BookingResponse;
import org.example.rest.dto.BookingSearchRequest;
import org.example.rest.dto.BookingSearchResponse;

@Named
public class RoomBookingService {
    public BookingResponse bookingRoom(final BookingRequest request) {
        return null;
    }

    public BookingSearchResponse searchRooms(final BookingSearchRequest request) {
        return null;
    }
}
