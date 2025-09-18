package edu.cit.cantiller.christianjayson.campusequipmentloan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanLimitExceededException.class)
    public ResponseEntity<Map<String, Object>> handleLoanLimitExceeded(LoanLimitExceededException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Loan Limit Reached");   // 👈 custom error title
        body.put("message", ex.getMessage());      // 👈 your message


        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
