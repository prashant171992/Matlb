package com.matlb.domain;

/**
 * Created by prassingh on 8/21/16.
 */
public class PollAnsweredResponse {

    public PollAnsweredResponse () {

    }

    public PollAnsweredResponse(PollAnswer pollAnswer) {
        this.answerGiven = pollAnswer.getAnswer().ordinal();
        this.genre = pollAnswer.getPoll().getGenre().name();
    }

    private String questionText;

    private String optionAText;

    private String optionBText;

    private String optionCText;

    private String optionDText;

    private String optionEText;

    private String genre;

    private int answerGiven;

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
}