package az.keytd.expensetracker.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.exceptions.BadRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handle(NotFoundException ex){
        Response trs = new Response(404, ex.getMessage());
        return new ResponseEntity<>(trs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handle(BadRequestException ex){
        Response trs = new Response(400, ex.getMessage());
        return new ResponseEntity<>(trs, HttpStatus.BAD_REQUEST);
    }
}
