package com.vti.blogapp.exception;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        var message = getMessage("MethodArgumentNotValidException.message");
        var errors = new HashMap<String, String>();
        for (var error : exception.getFieldErrors()) {
            var key = error.getField();
            var value = error.getDefaultMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(message, errors);
        return new ResponseEntity<>(response, headers, status);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleContraintViolationException(ConstraintViolationException exception,
                                                                    WebRequest request) {
        var message = getMessage("ConstraintViolationException.message");
        var errors = new HashMap<String, String>();
        for (var error : exception.getConstraintViolations()) {
            var key = error.getPropertyPath().toString();
            var value = error.getMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(message, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = getMessage(
                "HttpRequestMethodNotSupportedException.message",
                exception.getMethod()
        );
        var response = new ErrorResponse(message);
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = getMessage(
                "MissingServletRequestParameterException.message",
                exception.getParameterName(),
                exception.getParameterType()
        );
        var response = new ErrorResponse(message);
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = getMessage(
                "NoHandlerFoundException.message",
                exception.getHttpMethod(), exception.getRequestURL()
        );
        var response = new ErrorResponse(message);
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (exception instanceof MethodArgumentTypeMismatchException e) {
            var requiredType = e.getRequiredType();
            var message = getMessage(
                    "MethodArgumentTypeMismatchException.message",
                    e.getName(), requiredType == null ? "null" : requiredType.getName()
            );
            var response = new ErrorResponse(message);
            return new ResponseEntity<>(response, headers, status);
        }
        return super.handleTypeMismatch(exception, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception exception) {
        var message = getMessage("Exception.message");
        var response = new ErrorResponse(message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
