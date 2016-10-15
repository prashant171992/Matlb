package com.matlb.domain.responseDomain;

import com.matlb.domain.PollQuestion;

import java.util.Date;

/**
 * Created by prassingh on 8/25/16.
 */
public class PollForUserResponse {

    public PollForUserResponse () {

    }

    public PollForUserResponse (PollQuestion pollQuestion) {
        this.questionText = pollQuestion.getQuestionText();
        this.optionAText = pollQuestion.getOptionA();
        this.optionBText = pollQuestion.getOptionB();
        this.optionCText = pollQuestion.getOptionC();
        this.optionDText = pollQuestion.getOptionD();
        this.optionEText = pollQuestion.getOptionE();
        this.validUpto = pollQuestion.getPoll().getValidUpto();
        this.pollId = pollQuestion.getId();
    }

    private int pollId;

    private String questionText;

    private String optionAText;

    private String optionBText;

    private String optionCText;

    private String optionDText;

    private String optionEText;

    private int creditToBeEarned;

    private Date validUpto;

    private String askerName;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionAText() {
        return optionAText;
    }

    public void setOptionAText(String optionAText) {
        this.optionAText = optionAText;
    }

    public String getOptionBText() {
        return optionBText;
    }

    public void setOptionBText(String optionBText) {
        this.optionBText = optionBText;
    }

    public String getOptionCText() {
        return optionCText;
    }

    public void setOptionCText(String optionCText) {
        this.optionCText = optionCText;
    }

    public String getOptionDText() {
        return optionDText;
    }

    public void setOptionDText(String optionDText) {
        this.optionDText = optionDText;
    }

    public String getOptionEText() {
        return optionEText;
    }

    public void setOptionEText(String optionEText) {
        this.optionEText = optionEText;
    }

    public int getCreditToBeEarned() {
        return creditToBeEarned;
    }

    public void setCreditToBeEarned(int creditToBeEarned) {
        this.creditToBeEarned = creditToBeEarned;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(Date validUpto) {
        this.validUpto = validUpto;
    }

    public String getAskerName() {
        return askerName;
    }

    public void setAskerName(String askerName) {
        this.askerName = askerName;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }
}
