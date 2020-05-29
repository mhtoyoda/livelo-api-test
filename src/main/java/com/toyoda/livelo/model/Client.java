package com.toyoda.livelo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cliente")
public class Client {

    @Id
    private String id;

    private String name;

    private String gender;

    private LocalDate birthDay;

    private City city;
}
