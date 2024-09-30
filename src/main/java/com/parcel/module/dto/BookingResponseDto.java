package com.parcel.module.dto;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.parcel.module.constants.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;


@Data
public class BookingResponseDto {

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
}
