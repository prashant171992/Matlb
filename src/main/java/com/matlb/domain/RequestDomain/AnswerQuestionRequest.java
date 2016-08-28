package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 8/27/16.
 */
public class AnswerQuestionRequest {

    public AnswerQuestionRequest() {

    }

    public AnswerQuestionRequest(User user, int pollId, int answer) {
        this.user = user;
        this.pollId = pollId;
        this.answer = answer;
    }

    private User user;

    private int pollId;

    private int answer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
