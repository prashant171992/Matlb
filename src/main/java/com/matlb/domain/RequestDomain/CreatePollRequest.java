package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

import java.util.List;

/**
 * Created by prassingh on 8/27/16.
 */
public class CreatePollRequest {

    public CreatePollRequest(){

    }

    public CreatePollRequest(User asker, String pollQuestion, String optionAText, String optionBText, String optionCText, String optionDText, String optionEText, List<QuestionAskRequest> toBeAskedFrom, int validForMinutes, int genreType) {
        this.asker = asker;
        this.pollQuestion = pollQuestion;
        this.optionAText = optionAText;
        this.optionBText = optionBText;
        this.optionCText = optionCText;
        this.optionDText = optionDText;
        this.optionEText = optionEText;
        this.toBeAskedFrom = toBeAskedFrom;
        this.validForMinutes = validForMinutes;
        this.genreType = genreType;
    }

    private User asker;

    private String pollQuestion;

    private String optionAText;

    private String optionBText;

    private String optionCText;

    private String optionDText;

    private String optionEText;

    private List<QuestionAskRequest> toBeAskedFrom;

    private int validForMinutes;

    private int genreType;

    public User getAsker() {
        return asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
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

    public List<QuestionAskRequest> getToBeAskedFrom() {
        return toBeAskedFrom;
    }

    public void setToBeAskedFrom(List<QuestionAskRequest> toBeAskedFrom) {
        this.toBeAskedFrom = toBeAskedFrom;
    }

    public int getValidForMinutes() {
        return validForMinutes;
    }

    public void setValidForMinutes(int validForMinutes) {
        this.validForMinutes = validForMinutes;
    }

    public int getGenreType() {
        return genreType;
    }

    public void setGenreType(int genreType) {
        this.genreType = genreType;
    }
}
