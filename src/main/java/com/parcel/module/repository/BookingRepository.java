package com.parcel.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcel.module.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,String>{
    
}
