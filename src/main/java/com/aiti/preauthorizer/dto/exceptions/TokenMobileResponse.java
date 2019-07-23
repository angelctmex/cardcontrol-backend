package com.aiti.preauthorizer.dto.exceptions;

public class TokenMobileResponse {

    private String status;
    private String seed;
    private Integer ccvtimelife;

    public TokenMobileResponse(){}
    public TokenMobileResponse(String encryptedToken, Integer ccvtimelife){
        this.status = "OK";
        this.seed = encryptedToken;
        this.ccvtimelife = ccvtimelife;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getCcvtimelife() {
        return ccvtimelife;
    }

    public void setCcvtimelife(Integer ccvtimelife) {
        this.ccvtimelife = ccvtimelife;
    }
}

