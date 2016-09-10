package com.matlb.domain.responseDomain;

import java.util.List;

/**
 * Created by prassingh on 8/23/16.
 */
public class BasePollResponse extends BaseResponse {

    public BasePollResponse() {

    }

    public BasePollResponse(String message) {
        super(message);
    }

    private List<PollResponse> pollResponseList;

    private List<PollAnsweredResponse> pollAnsweredResponses;

    private List<PollQuestionAskedToResponse> pollQuestionAskedToResponses;

    private List<PollForUserResponse> pollForUserResponses;

    public List<PollResponse> getPollResponseList() {
        return pollResponseList;
    }

    public void setPollResponseList(List<PollResponse> pollResponseList) {
        this.pollResponseList = pollResponseList;
    }

    public List<PollAnsweredResponse> getPollAnsweredResponses() {
        return pollAnsweredResponses;
    }

    public void setPollAnsweredResponses(List<PollAnsweredResponse> pollAnsweredResponses) {
        this.pollAnsweredResponses = pollAnsweredResponses;
    }

    public List<PollQuestionAskedToResponse> getPollQuestionAskedToResponses() {
        return pollQuestionAskedToResponses;
    }

    public void setPollQuestionAskedToResponses(List<PollQuestionAskedToResponse> pollQuestionAskedToResponses) {
        this.pollQuestionAskedToResponses = pollQuestionAskedToResponses;
    }

    public List<PollForUserResponse> getPollForUserResponses() {
        return pollForUserResponses;
    }

    public void setPollForUserResponses(List<PollForUserResponse> pollForUserResponses) {
        this.pollForUserResponses = pollForUserResponses;
    }
}
