package com.matlb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prassingh on 8/19/16.
 */

@Entity
@Table(name = "poll_answer")
public class PollAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    public PollAnswer () {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ANSWERER_ID" , nullable = false)
    private User answerer;

    @ManyToOne
    @JoinColumn(name = "POLL_ID" , nullable = false)
    private Poll poll;

    @Column(name = "ANSWER" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ResultType answer;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_dt" , insertable = false, updatable = false)
    private Date createDt;

    public int getId() {
        return id;
    }

    public User getAnswerer() {
        return answerer;
    }

    public Poll getPoll() {
        return poll;
    }

    public ResultType getAnswer() {
        return answer;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setAnswerer(User answerer) {
        this.answerer = answerer;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setAnswer(ResultType answer) {
        this.answer = answer;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }
}
