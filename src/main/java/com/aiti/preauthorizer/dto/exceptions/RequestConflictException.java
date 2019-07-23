package com.aiti.preauthorizer.dto.exceptions;

public class RequestConflictException extends RuntimeException {
    private String codeError;

    public RequestConflictException() {
        super();
    }

    public RequestConflictException(final String message, String codeError) {
        super(message);
        this.codeError = codeError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
}
