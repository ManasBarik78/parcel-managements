package com.parcel.module.dto;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.parcel.module.constants.BookingStatus;
import com.parcel.module.model.Booking;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingReponseDto {

    private String bookingId;
    private LocalDateTime createdDate;
    private String senderID;
    private String receiverID;
    private String pickupLocation;
    private String dropoffLocation;
    private String parcelName;
    private int parcelWeight;
    private List<String> AdditionalStop;
    private BookingStatus bookingStatus;

    public BookingReponseDto(String bookingId, LocalDateTime createdDate, String pickupLocation, String dropoffLocation,
            String parcelName, int parcelWeight, List<String> additionalStop, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.createdDate = createdDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.parcelName = parcelName;
        this.parcelWeight = parcelWeight;
        AdditionalStop = additionalStop;
        this.bookingStatus = bookingStatus;
    }

    public BookingReponseDto(int i, List<BookingReponseDto> bookingReponseDtos) {
        //TODO Auto-generated constructor stub
    }


    
}
