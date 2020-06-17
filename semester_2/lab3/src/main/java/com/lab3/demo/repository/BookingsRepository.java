package com.lab3.demo.repository;

import com.lab3.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findBookingById(Long id);

    List<Booking> findBookingByClientId(Long id);
}
