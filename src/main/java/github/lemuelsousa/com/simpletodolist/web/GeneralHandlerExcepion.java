package github.lemuelsousa.com.simpletodolist.web;


import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import github.lemuelsousa.com.simpletodolist.exceptions.ApiErrorMessage;
import github.lemuelsousa.com.simpletodolist.exceptions.TodoNotFoundException;

@RestControllerAdvice
public class GeneralHandlerExcepion extends ResponseEntityExceptionHandler{
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest webRequest){
    
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        var apiErrorMessage = new ApiErrorMessage(statusCode, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatusCode());
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> handleTodoNotFoundException(TodoNotFoundException ex) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<Object>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatusCode());
    }
}
