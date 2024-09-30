package com.parcel.module.dto;
import java.util.List;
import com.parcel.module.constants.BookingStatus;
import lombok.Data;


@Data
public class BookingResponseDto {

    private String bookingId;
    private String senderID;
    private String receiverID;
    
    private String dropoffLocation;
    private String parcelName;
    
    private List<String> AdditionalStop;
    private BookingStatus bookingStatus;


    
}
