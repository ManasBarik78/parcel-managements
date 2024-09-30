package com.parcel.module.service.impl;

import com.parcel.module.modelMapper.ConvertMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.module.constants.BookingStatus;

import com.parcel.module.dto.BookingResponseDto;

import com.parcel.module.exception.BookingNotFound;
import com.parcel.module.model.Booking;
import com.parcel.module.repository.BookingRepository;
import com.parcel.module.service.BookingService;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ConvertMapper convertMapper;

    // Create booking details to database
    @Override
    public Booking createBooking(Booking booking) {

        var bookingId = UUID.randomUUID().toString();

        booking.setBookingId(bookingId);
        booking.setCreatedDate(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.ONGOING);

        bookingRepository.save(booking);
        return booking;
    }

    // Get All the Booking
    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::convertBookingReponseDto).collect(Collectors.toList());

    }

    // Find Booking by id
    @Override
    public Booking getBookingById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFound(bookingId));
        return booking;
    }

    // Edit the additional stop
    @Override
    public Booking editAdditionalStop(String bookingId, List<String> additionalStop) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFound(bookingId));
        booking.setAdditionalStop(additionalStop);
        bookingRepository.save(booking);
        return booking;

    }

    private BookingResponseDto convertBookingReponseDto(Booking booking) {
        BookingResponseDto bookingReponseDto = convertMapper.convertBookingToBookingReponseDto(booking);
        return bookingReponseDto;
    }

}
