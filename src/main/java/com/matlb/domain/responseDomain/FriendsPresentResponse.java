package com.matlb.domain.responseDomain;

import com.matlb.domain.User;

import java.util.List;

/**
 * Created by prassingh on 9/25/16.
 */
public class FriendsPresentResponse extends BaseResponse {

    public FriendsPresentResponse(String message) {
        super(message);
    }

    private User user;

    private List<Long> phoneNumberList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<Long> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
