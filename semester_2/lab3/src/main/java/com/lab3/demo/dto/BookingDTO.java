package com.lab3.demo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;

    private Long clientId;

    private Long tourId;
}
