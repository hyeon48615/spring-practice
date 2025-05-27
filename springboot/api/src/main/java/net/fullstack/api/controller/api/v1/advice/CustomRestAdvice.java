package net.fullstack.api.controller.api.v1.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class CustomRestAdvice {
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error("BindException : {}", e);

        Map<String, String> errMap = new HashMap<>();
        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        }
        return ResponseEntity.badRequest().body(errMap);
    }
}