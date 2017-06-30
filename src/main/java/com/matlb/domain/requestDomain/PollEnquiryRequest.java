package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 8/28/16.
 */
public class PollEnquiryRequest {

    public User user;

    private int status;

    public PollEnquiryRequest() {
    }

    public PollEnquiryRequest(User user, int status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
