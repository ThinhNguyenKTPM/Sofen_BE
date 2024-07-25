package com.thesis.sofen.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceDisabledException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorCode;

    public ResourceDisabledException(String errorCode, String errorsMessage){
        super(errorsMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
