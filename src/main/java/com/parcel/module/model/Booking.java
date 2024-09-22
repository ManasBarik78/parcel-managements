package com.parcel.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.parcel.module.constants.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name = "BOOKING")
public class Booking {
    @Id
    private String bookingId;
    @JsonFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdDate;
    private String senderID;
    private String receiverID;
    private String pickupLocation;
    private String dropoffLocation;
    private String parcelName;
    private int parcelWeight;
    private List<String> AdditionalStop;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}