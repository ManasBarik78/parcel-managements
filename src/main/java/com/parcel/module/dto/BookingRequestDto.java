package com.parcel.module.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDto {
    private String senderID;
    private String receiverID;
    private String pickupLocation;
    private String dropoffLocation;
    private String parcelName;
    private int parcelWeight;
    private List<String> AdditionalStop;
}
