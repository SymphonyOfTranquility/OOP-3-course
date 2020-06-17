package com.lab3.demo.repository;

import com.lab3.demo.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByIsHot(Boolean isHot);
}
