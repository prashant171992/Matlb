package com.matlb.domain.responseDomain;

import com.matlb.domain.Poll;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by prassingh on 8/21/16.
 */
public class PollResponse {

    public PollResponse(){

    }

    public PollResponse(Poll poll) {
        this.genre = poll.getGenre().name();
        this.optACount = poll.getOptACount();
        this.optBCount = poll.getOptBCount();
        this.optCCount = poll.getOptCCount();
        this.optDCount = poll.getOptDCount();
        this.optECount = poll.getOptECount();
        this.validTill = poll.getValidUpto();
        this.questionText = poll.getPollQuestion().getQuestionText();
        this.optionAText = poll.getPollQuestion().getOptionA();
        this.optionBText = poll.getPollQuestion().getOptionB();
        this.optionCText = poll.getPollQuestion().getOptionC();
        this.optionDText = poll.getPollQuestion().getOptionD();
        this.optionEText = poll.getPollQuestion().getOptionE();
        this.creditsUsed = 0;
        this.userAnonymous = poll.getUserAnonymous();
        this.pollOpenForAll = poll.getPollOpenForAll();
        this.questionStatus = poll.getStatus().ordinal();

        DateTime dateTime = new DateTime(poll.getCreateDt());
        org.joda.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        this.date = dateTimeFormatter.print(dateTime);
    }

    private String questionText;

    private String optionAText;

    private String optionBText;

    private String optionCText;

    private String optionDText;

    private String optionEText;

    private String genre;

    private int optACount;

    private int optBCount;

    private int optCCount;

    private int optDCount;

    private int optECount;

    private Date validTill;

    private List<PeopleAnsweredOrNot> peopleAnsweredOrNotList;

    private int creditsUsed;

    private int pollOpenForAll;

    private int userAnonymous;

    private int questionStatus;

    private String date;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getOptACount() {
        return optACount;
    }

    public void setOptACount(int optACount) {
        this.optACount = optACount;
    }

    public int getOptBCount() {
        return optBCount;
    }

    public void setOptBCount(int optBCount) {
        this.optBCount = optBCount;
    }

    public int getOptCCount() {
        return optCCount;
    }

    public void setOptCCount(int optCCount) {
        this.optCCount = optCCount;
    }

    public int getOptDCount() {
        return optDCount;
    }

    public void setOptDCount(int optDCount) {
        this.optDCount = optDCount;
    }

    public int getOptECount() {
        return optECount;
    }

    public void setOptECount(int optECount) {
        this.optECount = optECount;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public int getCreditsUsed() {
        return creditsUsed;
    }

    public void setCreditsUsed(int creditsUsed) {
        this.creditsUsed = creditsUsed;
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

    public int getPollOpenForAll() {
        return pollOpenForAll;
    }

    public void setPollOpenForAll(int pollOpenForAll) {
        this.pollOpenForAll = pollOpenForAll;
    }

    public int getUserAnonymous() {
        return userAnonymous;
    }

    public void setUserAnonymous(int userAnonymous) {
        this.userAnonymous = userAnonymous;
    }

    public int getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(int questionStatus) {
        this.questionStatus = questionStatus;
    }

    public List<PeopleAnsweredOrNot> getPeopleAnsweredOrNotList() {
        return peopleAnsweredOrNotList;
    }

    public void setPeopleAnsweredOrNotList(List<PeopleAnsweredOrNot> peopleAnsweredOrNotList) {
        this.peopleAnsweredOrNotList = peopleAnsweredOrNotList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
