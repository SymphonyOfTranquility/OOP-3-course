package com.lab3.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookings")
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(generator = "booking_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
