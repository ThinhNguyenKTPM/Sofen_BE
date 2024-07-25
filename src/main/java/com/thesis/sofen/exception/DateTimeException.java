package com.thesis.sofen.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class DateTimeException extends Exception{
    private static final long serialVersionUID = 1L;
    private String errorCode;

    public DateTimeException(String errorCode, String errorsMessage) {
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
