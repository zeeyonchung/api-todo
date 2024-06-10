package com.moais.todo.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e, ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e, e.getErrorCode());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleServletRequestBindingException(ServletRequestBindingException e) {
        log.error(e.getClass().getSimpleName(), e);
        return createResponse(e, ErrorCode.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createResponse(Exception e, ErrorCode errorCode) {
        ErrorResponse errorResponse = ErrorResponse.of(e, errorCode.getCode());
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
