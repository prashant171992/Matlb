package com.matlb.domain.requestDomain;

import com.matlb.domain.Multimedia;
import com.matlb.domain.PollCategoryEnum;
import com.matlb.domain.User;

import java.util.List;

/**
 * Created by prassingh on 8/27/16.
 */
public class CreatePollRequest {

    public CreatePollRequest(){

    }

    public CreatePollRequest(User asker, String pollQuestion, String optionAText, String optionBText, String optionCText, String optionDText, String optionEText, List<QuestionAskRequest> toBeAskedFrom, int validForMinutes, int genreType, int openForAll, int userAnonymous) {
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
        this.openForAll = openForAll;
        this.userAnonymous = userAnonymous;
        this.category = PollCategoryEnum.GENERAL.name();
    }

    public CreatePollRequest(User asker, String pollQuestion, String optionAText, String optionBText, String optionCText, String optionDText, String optionEText, List<QuestionAskRequest> toBeAskedFrom, int validForMinutes, int genreType, int openForAll, int userAnonymous, String questionURL, String optionAURL, String optionBURL, String optionCURL, String optionDURL, String optionEURL, String category) {
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
        this.openForAll = openForAll;
        this.userAnonymous = userAnonymous;
        this.questionURL = questionURL;
        this.optionAURL = optionAURL;
        this.optionBURL = optionBURL;
        this.optionCURL = optionCURL;
        this.optionDURL = optionDURL;
        this.optionEURL = optionEURL;
        this.category = category;
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

    private int openForAll;

    private int userAnonymous;

    private String questionURL;

    private String optionAURL;

    private String optionBURL;

    private String optionCURL;

    private String optionDURL;

    private String optionEURL;

    private String category;

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

    public int getOpenForAll() {
        return openForAll;
    }

    public void setOpenForAll(int openForAll) {
        this.openForAll = openForAll;
    }

    public int getUserAnonymous() {
        return userAnonymous;
    }

    public void setUserAnonymous(int userAnonymous) {
        this.userAnonymous = userAnonymous;
    }

    public String getQuestionURL() {
        return questionURL;
    }

    public void setQuestionURL(String questionURL) {
        this.questionURL = questionURL;
    }

    public String getOptionAURL() {
        return optionAURL;
    }

    public void setOptionAURL(String optionAURL) {
        this.optionAURL = optionAURL;
    }

    public String getOptionBURL() {
        return optionBURL;
    }

    public void setOptionBURL(String optionBURL) {
        this.optionBURL = optionBURL;
    }

    public String getOptionCURL() {
        return optionCURL;
    }

    public void setOptionCURL(String optionCURL) {
        this.optionCURL = optionCURL;
    }

    public String getOptionDURL() {
        return optionDURL;
    }

    public void setOptionDURL(String optionDURL) {
        this.optionDURL = optionDURL;
    }

    public String getOptionEURL() {
        return optionEURL;
    }

    public void setOptionEURL(String optionEURL) {
        this.optionEURL = optionEURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
