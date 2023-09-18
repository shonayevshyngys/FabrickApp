package com.github.controllers;

import com.github.common.dtos.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Log4j2
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> handleConflict(Exception e) {

        log.error("Failed Command", e);
        var res = ResponseMessage.builder()
                .code("400")
                .result(e.getMessage())
                .build();
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }
}
