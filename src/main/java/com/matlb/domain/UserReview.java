package com.matlb.domain;

import com.matlb.domain.requestDomain.RecordReviewRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prassingh on 2/10/17.
 */
@Entity
@Table(name = "user_review")
public class UserReview implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserReview() {

    }

    public UserReview(RecordReviewRequest recordReviewRequest) {
        this.star = recordReviewRequest.getStar();
        this.review = recordReviewRequest.getReview();
        this.user = recordReviewRequest.getUser();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "star", nullable = false)
    private int star;

    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "version", nullable = false)
    private String appVersion;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_dt" , insertable = false, updatable = false)
    private Date createDt;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "update_dt" , insertable = false, updatable = false)
    private Date updateDt;

    public int getId() {
        return id;
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

    public Date getCreateDt() {
        return createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}