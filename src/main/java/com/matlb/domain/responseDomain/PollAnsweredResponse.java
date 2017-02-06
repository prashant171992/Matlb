package com.matlb.domain.responseDomain;

import com.matlb.domain.PollAnswer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.joda.JodaTimeFormatterRegistrar;

import java.time.format.DateTimeFormatter;

/**
 * Created by prassingh on 8/21/16.
 */
public class PollAnsweredResponse {

    public PollAnsweredResponse () {

    }

    public PollAnsweredResponse(PollAnswer pollAnswer) {
        this.answerGiven = pollAnswer.getAnswer().ordinal();
        this.genre = pollAnswer.getPoll().getGenre().name();
        if(pollAnswer.getPoll().getUserAnonymous() == 0) {
            this.askerName = pollAnswer.getPoll().getAsker().getName();
        } else {
            this.askerName = "";
        }

        DateTime dateTime = new DateTime(pollAnswer.getCreateDt());
        org.joda.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        this.date = dateTimeFormatter.print(dateTime);
    }

    private String questionText;

    private String optionAText;

    private String optionBText;

    private String optionCText;

    private String optionDText;

    private String optionEText;

    private int creditsEarned;

    private String genre;

    private int answerGiven;

    private int correctAnswer;

    private String askerName;

    private String date;

    private String questionURL;

    private String optionAURL;

    private String optionBURL;

    private String optionCURL;

    private String optionDURL;

    private String optionEURL;

    private String category;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAnswerGiven() {
        return answerGiven;
    }

    public void setAnswerGiven(int answerGiven) {
        this.answerGiven = answerGiven;
    }

    public String getAskerName() {
        return askerName;
    }

    public void setAskerName(String askerName) {
        this.askerName = askerName;
    }

    public int getCreditsEarned() {
        return creditsEarned;
    }

    public void setCreditsEarned(int creditsEarned) {
        this.creditsEarned = creditsEarned;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
