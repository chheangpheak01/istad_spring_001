package com.sopheak.restfulapi001.exception;
import com.sopheak.restfulapi001.exception.customexception.CustomException;
import com.sopheak.restfulapi001.utils.AIPErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AIPErrorResponse> validateHandling (MethodArgumentNotValidException exception){
        AIPErrorResponse aipErrorResponse = AIPErrorResponse
                .builder()
                .status(exception.getStatusCode().toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getFieldError().getDefaultMessage())
                .build();
        return  ResponseEntity.badRequest().body(aipErrorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<AIPErrorResponse> handleCustomException (CustomException exception){
        AIPErrorResponse aipErrorResponse = AIPErrorResponse
                .builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aipErrorResponse);
    }
}
