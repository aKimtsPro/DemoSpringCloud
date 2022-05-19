package bstorm.akimts.film.controller;

import bstorm.akimts.film.exceptions.NotFoundException;
import bstorm.akimts.film.exceptions.ServiceUnreachableException;
import feign.FeignException;
//import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import feign.Retryer;
//import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {

//    @ExceptionHandler(CallNotPermittedException.class)
//    public ResponseEntity<String> handle(CallNotPermittedException ex){
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("problem circuit OPEN");
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handle(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("resources not found");
    }

    @ExceptionHandler(ServiceUnreachableException.class)
    public ResponseEntity<String> handle(ServiceUnreachableException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Access to service temporaly suspended");
    }

}
