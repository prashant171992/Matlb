package com.matlb.domain;

import com.matlb.domain.requestDomain.CreatePollRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prassingh on 1/13/17.
 */

@Entity
@Table(name = "multimedia")
public class Multimedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "poll_id" , nullable = false)
    private Poll poll;

    @Column(name = "question_url")
    private String questionURL;

    @Column(name = "option_a")
    private String optionAURL;

    @Column(name = "option_b")
    private String optionBURL;

    @Column(name = "option_c")
    private String optionCURL;

    @Column(name = "option_d")
    private String optionDURL;

    @Column(name = "option_e")
    private String optionEURL;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_dt" , insertable = false, updatable = false)
    private Date createDt;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "update_dt" , insertable = false, updatable = false)
    private Date updateDt;

    public Multimedia() {

    }

    public Multimedia(Poll poll, CreatePollRequest createPollRequest) {
        this.poll = poll;
        if (createPollRequest.getQuestionURL() != null) {
            this.questionURL = createPollRequest.getQuestionURL();
        }
        if (createPollRequest.getOptionAURL() != null) {
            this.optionAURL = createPollRequest.getOptionAURL();
        }
        if (createPollRequest.getOptionBURL() != null) {
            this.optionBURL = createPollRequest.getOptionBURL();
        }
        if (createPollRequest.getOptionCURL() != null) {
            this.optionCURL = createPollRequest.getOptionCURL();
        }
        if (createPollRequest.getOptionDURL() != null) {
            this.optionDURL = createPollRequest.getOptionDURL();
        }
        if (createPollRequest.getOptionEURL() != null) {
            this.optionEURL = createPollRequest.getOptionEURL();
        }
    }

    public int getId() {
        return id;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
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
}
