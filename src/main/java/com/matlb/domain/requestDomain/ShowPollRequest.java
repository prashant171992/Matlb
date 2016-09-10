package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 9/10/16.
 */
public class ShowPollRequest {

    private User user;

    private int pageNumber;

    private int openForAll;

    private String gcmToken;

    public ShowPollRequest(){

    }

    public ShowPollRequest(User user , int pageNumber , int openForAll , String gcmToken) {
        this.user = user;
        this.pageNumber = pageNumber;
        this.openForAll = openForAll;
        this.gcmToken = gcmToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getOpenForAll() {
        return openForAll;
    }

    public void setOpenForAll(int openForAll) {
        this.openForAll = openForAll;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }
}
