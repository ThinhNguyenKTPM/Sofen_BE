package com.thesis.sofen.exception;


import com.thesis.sofen.response.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//    @Nullable
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails userNotFoundException(ResourceNotFoundException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateFieldException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails resourceNullException(DuplicateFieldException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails illegalArgumentException(IllegalArgumentException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(AccountLoginException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails inactiveUserException(AccountLoginException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }


    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails datetimeException(DateTimeException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorDetails notAcceptableException(NotAcceptableException ex) {
        return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
    }
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
     public ErrorDetails unAuthorizedException(UnAuthorizedException ex) {
         return new ErrorDetails(new Date(), ex.getErrorCode(), ex.getMessage());
     }

}
