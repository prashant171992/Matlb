package com.matlb.domain.responseDomain;

/**
 * Created by prassingh on 3/29/16.
 */
public class BaseResponse {

    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
