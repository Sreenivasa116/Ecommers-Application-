package com.application.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse>
    handleHttpMessageNotReadableException
            (HttpMessageNotReadableException ex,
             HttpServletRequest request){
      Map<String,String> details = new LinkedHashMap<>();
      ExceptionResponse exceptionResponse = null;
      Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife &&
                ife.getTargetType().isEnum()) {

            String field = ife.getPath().get(0).getFieldName();
            String allowed = Arrays.toString(ife.getTargetType().getEnumConstants());

            details.put(field, "Invalid value. Allowed: " + allowed);

            exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                    400,
                    "Invalid value for enum field",
                    request.getRequestURI(),
                    details);
        }
      return ResponseEntity.ok().body(exceptionResponse);
    }
}
