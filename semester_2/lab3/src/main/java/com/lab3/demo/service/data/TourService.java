package com.lab3.demo.service.data;


import com.lab3.demo.dto.TourDTO;
import com.lab3.demo.entity.Booking;
import com.lab3.demo.entity.Client;
import com.lab3.demo.entity.Tour;
import com.lab3.demo.exception.ClientNotExists;
import com.lab3.demo.exception.TourAlreadyExists;
import com.lab3.demo.exception.TourNotExists;
import com.lab3.demo.repository.BookingsRepository;
import com.lab3.demo.repository.ClientRepository;
import com.lab3.demo.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final BookingsRepository bookingsRepository;

    public List<Tour> findAll() { return tourRepository.findAll(); }

    public List<Tour> findAllHot() { return tourRepository.findByIsHot(true); }

    public List<Tour> findAllClientTours(Long clientId) {
        List<Booking> bookings = bookingsRepository.findBookingByClientId(clientId);

        List<Tour> tours = new ArrayList<>();
        for (int i = 0;i < bookings.size(); ++i)
            tours.add(bookings.get(i).getTour());
        return tours;
    }

    @Transactional
    public Tour addTour(Tour tour) {
        Optional<Tour> oldTour = tourRepository.findById(tour.getId());
        if (oldTour.isPresent()) {
            throw new TourAlreadyExists("Tour with id " + tour.getId() + " already exists.");
        }
        return tourRepository.save(tour);
    }

    @Transactional
    public Tour changeHot(Long tourId) {
        Optional<Tour> oldTour = tourRepository.findById(tourId);
        Tour tour = oldTour.orElseThrow(() -> new TourNotExists("Tour with id " + tourId + " not exists"));
        tour.setIsHot(!tour.getIsHot());

        return tourRepository.findById(tourId).orElseThrow(() -> new TourNotExists("Tour with id " + tourId + " not exists"));
    }

    @Transactional
    public void deleteTour(Long tourId) {
        Optional<Tour> oldTour = tourRepository.findById(tourId);
        if (!oldTour.isPresent()) {
            throw new TourNotExists("Tour with id " + tourId + " not exists");
        }
        tourRepository.deleteById(tourId);
    }
}
