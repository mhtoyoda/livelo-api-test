package com.toyoda.livelo.dto;

import com.toyoda.livelo.validation.GenderValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String id;

    @NotNull(message = "Name is compulsory")
    @NotBlank(message = "Name is compulsory")
    private String name;

    @NotNull(message = "Gender is compulsory")
    @GenderValidation(enumClass = Gender.class)
    private String gender;

    @Past(message = "Date of Birth must be the past")
    @NotNull
    private LocalDate birthDay;

    @NotNull(message = "City is compulsory")
    @NotBlank(message = "City is compulsory")
    private CityDTO city;
}
