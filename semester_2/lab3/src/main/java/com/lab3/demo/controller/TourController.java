package com.lab3.demo.controller;

import com.lab3.demo.dto.TourDTO;
import com.lab3.demo.service.BookingServiceController;
import com.lab3.demo.service.TourControllerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TourController {
    private final TourControllerService tourControllerService;

    @GetMapping(value = "/tours")
    public List<TourDTO> findAll() { return tourControllerService.findAll(); }

    @GetMapping(value = "/setHot/{tourId}")
    public TourDTO changeHotness(@PathVariable(value = "tourId") Long tourId) {
        return tourControllerService.setHotness(tourId);
    }
    @PostMapping(value = "/addTour")
    public TourDTO addTour(@RequestBody TourDTO tourDTO) { return tourControllerService.addTour(tourDTO); }

}
