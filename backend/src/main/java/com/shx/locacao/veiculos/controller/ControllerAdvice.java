package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.exception.BusinessException;
import com.shx.locacao.veiculos.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError objectNotFound(ObjectNotFoundException e){
        return new ApiError(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError businessErrors(BusinessException e){
        return new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    private static class ApiError {

        private final String error;
        private final Long timestamp;
        private final Integer statuscode;

        public ApiError(String error, Integer statuscode) {
            this.error = error;
            this.statuscode = statuscode;
            timestamp = System.currentTimeMillis();
        }

        public String getError() {
            return error;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public Integer getStatuscode() {
            return statuscode;
        }
    }
}
