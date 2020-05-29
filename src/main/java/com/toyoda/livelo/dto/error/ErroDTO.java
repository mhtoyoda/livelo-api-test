package com.toyoda.livelo.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {

    private String field;
    private String message;

}
