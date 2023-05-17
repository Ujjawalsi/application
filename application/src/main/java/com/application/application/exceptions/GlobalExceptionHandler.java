package com.application.application.exceptions;

import com.application.application.payloads.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public  ResponseEntity<Map<String,String>> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception){
        Map<String,String> resp = new HashMap<>();
        String exceptionMessage = exception.getMessage();
        Throwable cause = exception.getCause();
            resp.put(exceptionMessage, String.valueOf(cause));
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NullPointerException.class)
    public  ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException exception){
        String exceptionMessage = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(exceptionMessage,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String exceptionMessage = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(exceptionMessage,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
}

