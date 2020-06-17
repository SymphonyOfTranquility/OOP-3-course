package com.lab3.demo.service.data;

import com.lab3.demo.converter.BookingConverter;
import com.lab3.demo.dto.BookingDTO;
import com.lab3.demo.entity.Booking;
import com.lab3.demo.entity.Client;
import com.lab3.demo.entity.Tour;
import com.lab3.demo.exception.ClientNotExists;
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
public class BookingService {
    private final BookingsRepository bookingsRepository;
    private final ClientRepository clientRepository;
    private final TourRepository tourRepository;
    private final BookingConverter bookingConverter;

    @Transactional
    public Client addBooking(BookingDTO bookingDTO) {
        Optional<Tour> tourOpt = tourRepository.findById(bookingDTO.getClientId());
        Optional<Client> clientOpt = clientRepository.findById(bookingDTO.getClientId());

        Tour tour = tourOpt.orElseThrow(() -> new TourNotExists("Tour with id " + bookingDTO.getTourId() + " not exists."));
        Client client = clientOpt.orElseThrow(() -> new ClientNotExists("Client with id " + bookingDTO.getClientId() + " not exists."));

        bookingsRepository.save(bookingConverter.converterToEntity(bookingDTO, client, tour));
        return client;
    }

}
