package io.taskman.taskmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Dominic Ngoetjana on 2024/08/03.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the exception of type RuntimeException.
     *
     * @param e The RuntimeException that is being handled.
     * @return A ResponseEntity object with the status code HttpStatus.NOT_FOUND and the message of the exception as the response body.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
