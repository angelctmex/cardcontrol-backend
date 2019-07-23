package com.aiti.preauthorizer.dto.exceptions;

public class GenericErrorMobileResponse {
    private String status;
    private String message;
    private String code;

    public GenericErrorMobileResponse() {

    }

    public GenericErrorMobileResponse(String status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
