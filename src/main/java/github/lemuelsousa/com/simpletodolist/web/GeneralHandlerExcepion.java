package github.lemuelsousa.com.simpletodolist.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import github.lemuelsousa.com.simpletodolist.exceptions.BadRequestException;

@ControllerAdvice
public class GeneralHandlerExcepion {
    
    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<Object> handlerBadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(exception.getMessage());
    }

}
