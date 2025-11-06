package com.leleo.mytasks.infra;

import com.leleo.mytasks.exceptions.NotFoundException;
import com.leleo.mytasks.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> handleTaskNotFound(NotFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<ValidationErrorMessage> handleValidationError(ValidationException exception) {
        ValidationErrorMessage errorResponse = new ValidationErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getFields());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
