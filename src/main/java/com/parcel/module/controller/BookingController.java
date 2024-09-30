package com.parcel.module.controller;

import com.parcel.module.dto.BookingRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcel.module.dto.BookingResponseDto;
import com.parcel.module.dto.BookingResponseDto;
import com.parcel.module.exception.NoBookingsFoundExeception;
import com.parcel.module.model.Booking;
import com.parcel.module.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
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
    public BookingResponseDto createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        var booking = new Booking();
        BeanUtils.copyProperties(bookingRequestDto,booking);
        return bookingService.createBooking(booking);
    }


     // Get All the Booking
     @GetMapping("/bookings")
     @Operation(summary = "Get all booking list", description = "")
     public List<BookingResponseDto> getBookings() {
         List<BookingResponseDto> bookings = bookingService.getAllBookings();
 
         if(bookings.isEmpty()){
             throw new NoBookingsFoundExeception();
         }
         return bookings;
     }

    // Find Booking by id
    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Get booking by ID", description = "")
    public BookingResponseDto getBookingById(@PathVariable String bookingId) {

        return bookingService.getBookingById(bookingId);

    }

    // Edit the additional stop
    @PutMapping("/booking/addstop/{bookingId}")
    @Operation(summary = "Add additional stop to existing booking", description = "")
    public BookingResponseDto addAdditionalStop(@PathVariable String bookingId, @RequestBody List<String> additionalStop) {
        return bookingService.editAdditionalStop(bookingId, additionalStop);
    }
}
