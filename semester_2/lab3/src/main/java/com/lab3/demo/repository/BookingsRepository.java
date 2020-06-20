package com.lab3.demo.repository;

import com.lab3.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByClientId(Long id);
}
