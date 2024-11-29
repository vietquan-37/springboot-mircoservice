package com.vietquan37.ecommerce.advice;



import com.vietquan37.ecommerce.payload.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<APIResponse> handleEntityNotFoundException(Exception ex){
//
//        APIResponse response = APIResponse.builder()
//                .message(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.builder()
                .error(errors)
                .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGeneralException(Exception ex) {
        APIResponse response = APIResponse.builder()
                .message("An unexpected error occurred: " + ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}