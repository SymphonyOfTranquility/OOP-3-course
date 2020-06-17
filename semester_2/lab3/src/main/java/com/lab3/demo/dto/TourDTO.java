package com.lab3.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDTO {
    @NotNull(message = "Tour type required")
    private String type;

    @NotNull(message = "Hotness required")
    private Boolean isHot;

    private String name;
}
