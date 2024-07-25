package com.thesis.sofen.util;

import com.thesis.sofen.exception.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;

public class ArgumentNotValid {
    public static void handleInvalidArgument(BindingResult result ) throws MethodArgumentNotValidException {
        if(result.hasErrors()) {
            String errorMessage = result.getFieldError().getDefaultMessage();
            throw new MethodArgumentNotValidException("argument_invalid", errorMessage);
        }
    }
}
