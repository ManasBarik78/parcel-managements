package com.parcel.module.exception;

public class BookingNotFound extends RuntimeException {
    private String userId;

    public BookingNotFound(String userId){
        super("No user found with the given ID: "+userId);
        this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }
}
