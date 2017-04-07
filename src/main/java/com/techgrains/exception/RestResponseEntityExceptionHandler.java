package com.techgrains.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { TGConflictException.class, TGForbiddenException.class, TGNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest request) {
        String error = "Internal Server Error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(e instanceof TGConflictException) {
            error = "Conflict";
            status = HttpStatus.CONFLICT;
        } else if(e instanceof TGForbiddenException) {
            error = "Forbidden";
            status = HttpStatus.FORBIDDEN;
        } else if(e instanceof TGNotFoundException) {
            error = "Not Found";
            status = HttpStatus.NOT_FOUND;
        }

        RestErrorResponse response = new RestErrorResponse(status, error, e.getMessage());
        return handleExceptionInternal(e, response, headers, status, request);
    }
}
