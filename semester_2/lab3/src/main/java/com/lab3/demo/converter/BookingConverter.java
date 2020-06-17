package com.lab3.demo.converter;

import com.lab3.demo.dto.BookingDTO;
import com.lab3.demo.entity.Booking;
import com.lab3.demo.entity.Client;
import com.lab3.demo.entity.Tour;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setClientId(booking.getClient().getId());
        bookingDTO.setTourId(booking.getTour().getId());

        return bookingDTO;
    }

    public Booking converterToEntity(BookingDTO bookingDTO, Client client, Tour tour) {
        Booking booking = new Booking();

        booking.setId(bookingDTO.getId());
        booking.setClient(client);
        booking.setTour(tour);

        return booking;
    }

}
