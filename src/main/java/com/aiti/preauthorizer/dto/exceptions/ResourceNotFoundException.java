package com.aiti.preauthorizer.dto.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -9079454849611061074L;
    private String codeError;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, String codeError) {
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
