package com.aiti.preauthorizer.dto.exceptions;

public class RequestBadException extends RuntimeException {

    private static final String DESCRIPTION = "Validation of parameters not successful :: ";
    private String codeError;

    public RequestBadException() {
        super();
    }

    public RequestBadException(final String message, String codeError) {
        super(DESCRIPTION + message);
        this.codeError = codeError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
}
