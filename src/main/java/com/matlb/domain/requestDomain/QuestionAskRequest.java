package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 8/27/16.
 */
public class QuestionAskRequest {

    public QuestionAskRequest() {

    }

    public QuestionAskRequest(Long phoneNumber, int creditAsBait) {
        this.phoneNumber = phoneNumber;
        this.creditAsBait = creditAsBait;
    }


    private Long phoneNumber;
    private int creditAsBait;

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCreditAsBait() {
        return creditAsBait;
    }

    public void setCreditAsBait(int creditAsBait) {
        this.creditAsBait = creditAsBait;
    }
}
