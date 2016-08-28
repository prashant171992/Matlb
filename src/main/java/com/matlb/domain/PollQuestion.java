package com.matlb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prassingh on 8/19/16.
 */

@Entity
@Table(name = "poll_question")
public class PollQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    public PollQuestion() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "POLL_ID" , nullable = false)
    private Poll poll;

    @Column(name = "question_text" , nullable = false)
    private String questionText;

    @Column(name = "option_A")
    private String optionA;

    @Column(name = "option_B")
    private String optionB;

    @Column(name = "option_C")
    private String optionC;

    @Column(name = "option_D")
    private String optionD;

    @Column(name = "option_E")
    private String optionE;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_dt" , insertable = false, updatable = false)
    private Date createDt;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "update_dt" , insertable = false, updatable = false)
    private Date updateDt;

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
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
}
