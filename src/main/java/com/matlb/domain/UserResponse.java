package com.matlb.domain;

/**
 * Created by prassingh on 3/29/16.
 */
public class UserResponse extends BaseResponse {

    public UserResponse(String message) {
        super(message);
    }

    private boolean userCreated ;


    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
}
