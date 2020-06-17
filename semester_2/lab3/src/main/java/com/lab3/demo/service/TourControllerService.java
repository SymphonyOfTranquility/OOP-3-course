package com.lab3.demo.service;

import com.lab3.demo.converter.TourConverter;
import com.lab3.demo.dto.BookingDTO;
import com.lab3.demo.dto.TourDTO;
import com.lab3.demo.entity.Booking;
import com.lab3.demo.entity.Tour;
import com.lab3.demo.exception.TourHotnessNotNullException;
import com.lab3.demo.service.data.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourControllerService {
    private final TourService tourService;
    private final TourConverter tourConverter;

    public TourDTO addTour(TourDTO tourDTO) {
        Tour tour = tourConverter.convertToEntity(tourDTO);
        Tour newTour = tourService.addTour(tour);
        return tourConverter.convertToDTO(newTour);
    }

    public TourDTO setHotness(Long tourId) {
        if (tourId == null)
            throw new TourHotnessNotNullException();

        return tourConverter.convertToDTO(tourService.changeHot(tourId));
    }

    public List<TourDTO> findAllClientsTours(Long clientId) {
        List<Tour> tours = tourService.findAllClientTours(clientId);
        List<TourDTO> tourDTOS = new ArrayList<>();
        for (int i = 0;i < tours.size(); ++i)
            tourDTOS.add(tourConverter.convertToDTO(tours.get(i)));
        return tourDTOS;
    }

    public List<TourDTO> findAll() {
        List<Tour> tours = tourService.findAll();
        List<TourDTO> tourDTOS = new ArrayList<>();
        for (int i = 0;i < tours.size(); ++i)
            tourDTOS.add(tourConverter.convertToDTO(tours.get(i)));
        return tourDTOS;
    }

}
