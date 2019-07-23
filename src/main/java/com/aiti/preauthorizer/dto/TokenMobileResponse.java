package com.aiti.preauthorizer.dto;


/*
* @(#)TokenMobileResponse.java
*
* Copyright 2016 - All rights reserved.
* Objeto que se encarga de devolver una respuesta JSON
* @author Angel Contreras
* @version 1.0
* @date 29/09/2016
*/
public class TokenMobileResponse {

    private long   id;
    private int    responseCode;
    private String responseCodeDescription;
    private String seed;
    private String maskName;
    private String maskPan;

    public TokenMobileResponse(){}
    public TokenMobileResponse(long id, int responseCode, String encryptedToken){
        this.id = id;
        this.seed = encryptedToken;
        this.responseCode = responseCode;
        this.responseCodeDescription = "OK";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getMaskName() {
        return maskName;
    }

    public void setMaskName(String maskName) {
        this.maskName = maskName;
    }

    public String getMaskPan() {
        return maskPan;
    }

    public void setMaskPan(String maskPan) {
        this.maskPan = maskPan;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCodeDescription() {
        return responseCodeDescription;
    }

    public void setResponseCodeDescription(String responseCodeDescription) {
        this.responseCodeDescription = responseCodeDescription;
    }
}
