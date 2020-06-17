package com.lab3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tours")
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(generator = "tour_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tour_id_seq", sequenceName = "tour_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "is_hot")
    private Boolean isHot;
}
