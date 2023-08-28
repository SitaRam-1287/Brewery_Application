package com.brewery.application.exception;

import com.brewery.application.dto.outputdto.ErrorDetailsDto;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalException {

   @ExceptionHandler(ElementNotFoundException.class)
   public ErrorDetailsDto handleElementNotFound(ElementNotFoundException elementNotFound){
       return new ErrorDetailsDto(HttpStatus.BAD_REQUEST,elementNotFound.getMessage());
   }
}
