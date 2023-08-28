package com.brewery.application.dto.outputdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDto{

    public HttpStatus httpStatus;

    public String message;

}
