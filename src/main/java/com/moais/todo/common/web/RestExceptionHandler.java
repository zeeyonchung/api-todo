package com.moais.todo.common.web;

import com.moais.todo.common.error.CustomException;
import com.moais.todo.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e.getLocalizedMessage(), e.getErrorCode());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e.getLocalizedMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleServletRequestBindingException(ServletRequestBindingException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e.getLocalizedMessage(), ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getClass().getSimpleName(), e);
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            if (!sb.isEmpty()) sb.append(",");
            sb.append(((FieldError) error).getField());
            sb.append(" : ");
            sb.append(error.getDefaultMessage());
        });
        String message = sb.toString();
        return createResponse(message, ErrorCode.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createResponse(String message, ErrorCode errorCode) {
        return new ResponseEntity<>(new ErrorResponse(message, errorCode.getCode()), errorCode.getHttpStatus());
    }
}
