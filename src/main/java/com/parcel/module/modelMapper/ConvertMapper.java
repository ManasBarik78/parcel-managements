package com.parcel.module.modelMapper;

import com.parcel.module.dto.BookingResponseDto;
import com.parcel.module.model.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ConvertMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BookingResponseDto convertBookingReponseDto(Booking booking) {
        BookingResponseDto bookingReponseDto = this.modelMapper.map(booking, BookingResponseDto.class);
        return bookingReponseDto;
    }
}
