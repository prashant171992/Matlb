package com.matlb.domain.requestDomain;

import com.matlb.domain.User;

/**
 * Created by prassingh on 2/10/17.
 */
public class RecordReviewRequest {

    private User user;
    private int star;
    private String review;
    private String appVersion;

    public RecordReviewRequest(User user, int star, String review, String appVersion) {
        this.user = user;
        this.star = star;
        this.review = review;
        this.appVersion = appVersion;
    }

    public RecordReviewRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
