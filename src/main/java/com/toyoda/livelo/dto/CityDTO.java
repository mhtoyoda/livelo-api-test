package com.toyoda.livelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private String id;

    @NotNull(message = "Name is compulsory")
    @NotBlank(message = "Name is compulsory")
    private String name;

    @NotNull(message = "Name is compulsory")
    @NotBlank(message = "Name is compulsory")
    private String state;
}
