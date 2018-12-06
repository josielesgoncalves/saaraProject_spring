package com.projeto.saara.exceptions;

import com.projeto.saara.helpers.Resposta;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ObjetoNaoEncontradoException.class })
    protected ResponseEntity<Object> handleObjetoNaoEcontratoException(RuntimeException ex, WebRequest request) {
    	ObjetoNaoEncontradoException exception = (ObjetoNaoEncontradoException) ex;
        Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
        return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = { ParametroInvalidoException.class })
    protected ResponseEntity<Object> handleParametroInvalidoException(RuntimeException ex, WebRequest request) {
    	ParametroInvalidoException exception = (ParametroInvalidoException) ex;
        Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
        return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest request) {
        ValidationException exception = (ValidationException) ex;
        Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
        return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}