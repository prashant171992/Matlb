package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 8/27/16.
 */
public class QuestionAskRequest {

    public QuestionAskRequest() {

    }

    public QuestionAskRequest(User askedTo, int creditAsBait) {
        this.askedTo = askedTo;
        this.creditAsBait = creditAsBait;
    }

    private User askedTo;

    private int creditAsBait;

    public User getAskedTo() {
        return askedTo;
    }

    public void setAskedTo(User askedTo) {
        this.askedTo = askedTo;
    }

    public int getCreditAsBait() {
        return creditAsBait;
    }

    public void setCreditAsBait(int creditAsBait) {
        this.creditAsBait = creditAsBait;
    }
}
