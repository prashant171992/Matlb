package com.matlb.domain.responseDomain;

import com.matlb.domain.QuestionAsked;
import com.matlb.domain.StatusType;

/**
 * Created by prassingh on 10/12/16.
 */
public class PeopleAnsweredOrNot {

    public PeopleAnsweredOrNot(){

    }

    public PeopleAnsweredOrNot(QuestionAsked questionAsked) {
        this.name = questionAsked.getAnswerer().getName();
        this.answerGiven = questionAsked.getStatus().equals(StatusType.ANSWERED);
    }

    private String name;

    private boolean answerGiven;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnswerGiven() {
        return answerGiven;
    }

    public void setAnswerGiven(boolean answerGiven) {
        this.answerGiven = answerGiven;
    }
}
