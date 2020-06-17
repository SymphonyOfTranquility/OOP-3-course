package com.lab3.demo.converter;

import com.lab3.demo.dto.TourDTO;
import com.lab3.demo.entity.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourConverter {
    public TourDTO convertToDTO(Tour tour) {
        TourDTO tourDTO = new TourDTO();

        tourDTO.setName(tour.getName());
        tourDTO.setIsHot(tour.getIsHot());
        tourDTO.setType(tour.getType());

        return tourDTO;
    }

    public Tour convertToEntity(TourDTO tourDTO) {
        Tour tour = new Tour();

        tour.setName(tourDTO.getName());
        tour.setIsHot(tourDTO.getIsHot());
        tour.setType(tourDTO.getType());

        return tour;
    }

}
