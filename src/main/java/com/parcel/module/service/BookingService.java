package com.parcel.module.service;

import com.parcel.module.dto.BookingResponseDto;
import com.parcel.module.model.Booking;
import java.util.*;
public interface BookingService {

    BookingResponseDto createBooking(Booking booking);

    List<BookingResponseDto> getAllBookings();

    BookingResponseDto getBookingById(String bookingId);

    BookingResponseDto editAdditionalStop(String bookingId, List<String> additionalStop);
    
}
