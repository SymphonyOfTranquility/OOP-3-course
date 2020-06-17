package com.lab3.demo.service;

import com.lab3.demo.converter.BookingConverter;
import com.lab3.demo.converter.ClientConverter;
import com.lab3.demo.converter.TourConverter;
import com.lab3.demo.dto.BookingDTO;
import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.dto.TourDTO;
import com.lab3.demo.entity.Booking;
import com.lab3.demo.entity.Client;
import com.lab3.demo.entity.Tour;
import com.lab3.demo.service.data.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceController {
    private final BookingService bookingService;
    private final ClientConverter clientConverter;
    private final BookingConverter bookingConverter;
    private final TourConverter tourConverter;

    public ClientDTO addBooking(BookingDTO bookingDTO){
        Client client = bookingService.addBooking(bookingDTO);
        return clientConverter.convertToDTO(client);
    }

}
