package com.parcel.module.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcel.module.model.Booking;
import com.parcel.module.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Booking controller", description = "Manage Parcel booking")
@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create booking details to database
    @PostMapping("/booking")
    @Operation(summary = "create a new booking", description = "")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    //Get All the Booking 
    @GetMapping("/bookings")
    @Operation(summary = "Get all booking list", description = "")
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }
    
    //Find Booking by id
    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Get booking by ID", description = "")
    public Booking getBookingById(@PathVariable String bookingId) {
       return bookingService.getBookingById(bookingId);
    }

    //Edit the additional stop
    @PutMapping("/booking/addstop/{bookingId}")
    @Operation(summary = "Add additional stop to existing booking", description = "")
    public Booking addAdditionalStop(@PathVariable String bookingId, @RequestBody List<String> additionalStop) {
        return bookingService.editAdditionalStop(bookingId,additionalStop);
    }
}
