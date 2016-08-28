package com.matlb.domain.responseDomain;

import com.matlb.domain.QuestionAsked;

/**
 * Created by prassingh on 8/21/16.
 */
public class PollQuestionAskedToResponse {

    public PollQuestionAskedToResponse(){

    }


    public PollQuestionAskedToResponse(QuestionAsked questionAsked) {
        this.userName = questionAsked.getAnswerer().getName();
        this.creditsGiven = questionAsked.getCreditAlloted();
        this.questionStatus = questionAsked.getStatus().ordinal();
    }


    private String userName;

    private int questionStatus;

    private int creditsGiven;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCreditsGiven() {
        return creditsGiven;
    }

    public void setCreditsGiven(int creditsGiven) {
        this.creditsGiven = creditsGiven;
    }

    public int getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(int questionStatus) {
        this.questionStatus = questionStatus;
    }
}
