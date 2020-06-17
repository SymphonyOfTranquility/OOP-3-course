package com.lab3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(generator = "client_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "discount")
    private Long discount;
}
