package com.xpto.toggle.Exceptions.Advice;

import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Error> notFound(ResourceNotFoundException ex) {
        return new ResponseEntity<Error>(ex.getError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BedRequestExcpetion.class)
    public final ResponseEntity<Error> bedRequestExcpetion(BedRequestExcpetion ex) {
        return new ResponseEntity<Error>(ex.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Error> genricError(Throwable ex) {
        Error error = new Error();
        error.setCode("INTRNAL-SERVER-ERROR");
        error.setDetails(ex.getMessage());
        return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
