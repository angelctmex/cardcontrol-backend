package com.aiti.preauthorizer.dto;

import java.util.HashMap;
import java.util.Map;

public class GenericErrorMobileResponse {

    private String status;
    private String message;
    private String path;

    public GenericErrorMobileResponse(){
        this.status = "500";
        this.message = "Internal Server Error:::";
        this.path = "";

    }

    public GenericErrorMobileResponse(String Error,String path){
        this.status = "500";
        this.message = "Internal Server Error:::"+ Error;
        this.path = path;

    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map getResponse(){
        Map rspon=new HashMap();
        rspon.put("status",this.status);
        rspon.put("message",this.message);
        rspon.put("path",this.path);
        return rspon;
    }

}
