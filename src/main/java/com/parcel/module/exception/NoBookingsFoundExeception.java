package com.parcel.module.exception;

public class NoBookingsFoundExeception extends RuntimeException {
    public NoBookingsFoundExeception(){
        super("No Bookings Found");
    }
}
