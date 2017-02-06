package com.matlb.domain;

/**
 * Created by prassingh on 10/15/16.
 */
public class NotificationData {

    public NotificationData() {

    }

    private int pollId;
    private String asker;
    private String message;
    private int askOpinion;

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAskOpinion() {
        return askOpinion;
    }

    public void setAskOpinion(int askOpinion) {
        this.askOpinion = askOpinion;
    }
}
