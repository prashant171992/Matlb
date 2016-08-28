package com.matlb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prassingh on 8/19/16.
 */

@Entity
@Table(name = "question_asked")
public class QuestionAsked implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "asked_by" , nullable = false)
    private User asker;

    @ManyToOne
    @JoinColumn(name = "asked_to" , nullable = false)
    private User answerer;

    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusType status = StatusType.PENDING;

    @Column(name = "credit_alloted" , nullable = false)
    private int creditAlloted = 0;

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

    public Poll getPoll() {
        return poll;
    }

    public User getAsker() {
        return asker;
    }

    public User getAnswerer() {
        return answerer;
    }

    public StatusType getStatus() {
        return status;
    }

    public int getCreditAlloted() {
        return creditAlloted;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public void setAnswerer(User answerer) {
        this.answerer = answerer;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public void setCreditAlloted(int creditAlloted) {
        this.creditAlloted = creditAlloted;
    }
}
