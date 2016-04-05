package org.example.rest.service;

import org.example.rest.dto.BookingRequest;
import org.example.rest.dto.BookingResponse;
import org.example.rest.dto.BookingSearchRequest;
import org.example.rest.dto.BookingSearchResponse;

public interface RoomBookingService {
    BookingResponse bookingRoom(BookingRequest request);

    BookingSearchResponse searchRooms(BookingSearchRequest request);
}
