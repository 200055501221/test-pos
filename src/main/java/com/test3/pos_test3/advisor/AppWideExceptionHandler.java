package com.test3.pos_test3.advisor;

import com.test3.pos_test3.exception.NotFoundException;
import com.test3.pos_test3.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> NotFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error coming",e.getMessage()+"Hi"),
                HttpStatus.NOT_FOUND
        );
    }
}
