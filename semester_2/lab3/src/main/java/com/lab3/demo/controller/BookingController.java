package com.lab3.demo.controller;

import com.lab3.demo.dto.BookingDTO;
import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.service.BookingServiceController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class BookingController {
    private final BookingServiceController bookingServiceController;

    @GetMapping(value = "/booking")
    public ClientDTO addBooking(@RequestBody BookingDTO bookingDTO) {return bookingServiceController.addBooking(bookingDTO); }


}
