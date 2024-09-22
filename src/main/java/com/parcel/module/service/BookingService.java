package com.parcel.module.service;

import com.parcel.module.model.Booking;
import java.util.*;
public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(String bookingId);

    Booking editAdditionalStop(String bookingId, List<String> additionalStop);
    
}
