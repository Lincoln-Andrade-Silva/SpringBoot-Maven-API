package com.api.application.core.utils;

import com.api.application.core.utils.exeption.ApplicationBusinessException;
import com.api.application.core.utils.exeption.ApplicationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @ExceptionHandler({ApplicationBusinessException.class})
    public ResponseEntity<Object> handleApplicationBusinessException(ApplicationBusinessException exception,
                                                                     HttpServletResponse servletResponse) {
        servletResponse.setStatus(exception.getHttpStatusCode());

        ApplicationErrorResponse errorResponse = ApplicationErrorResponse.setErrorResponse(exception);

        return new ResponseEntity<>(errorResponse, headers(), HttpStatus.valueOf(exception.getHttpStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        ApplicationErrorResponse errorResponse = ApplicationErrorResponse.setErrorResponse(exception);

        return new ResponseEntity<>(errorResponse, headers(), HttpStatus.valueOf(HttpServletResponse.SC_BAD_REQUEST));
    }
}
