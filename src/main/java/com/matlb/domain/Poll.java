package com.matlb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by prassingh on 8/17/16.
 */

@Entity
@Table(name = "poll")
public class Poll implements Serializable {

    private static final long serialVersionUID = 1L;

    public Poll(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ASKER_ID" , nullable = false)
    private User asker;

    @Column(name = "OPT_A_COUNT")
    private int optACount = 0;

    @Column(name = "OPT_B_COUNT" , nullable = false)
    private int optBCount = 0;

    @Column(name = "OPT_C_COUNT" , nullable = false)
    private int optCCount = 0;


    @Column(name = "OPT_D_COUNT" , nullable = false)
    private int optDCount = 0;

    @Column(name = "OPT_E_COUNT" , nullable = false)
    private int optECount = 0;

    @Column(name = "STATUS" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusType status = StatusType.RUNNING;

    @Column(name = "RESULT" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ResultType result = ResultType.NON_DECISIVE;

    @Column(name = "GENRE" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GenreType genre = GenreType.NONE;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_dt" , insertable = false, updatable = false)
    private Date createDt;

    @Basic(optional = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "update_dt" , insertable = false, updatable = false)
    private Date updateDt;

    @Column(name = "valid_upto")
    private Date validUpto;

    @Column(name = "open")
    private int pollOpenForAll = 1;

    @Column(name = "anonymous")
    private int userAnonymous = 1;

    @Column(name = "category" , nullable = false)
    @Enumerated(EnumType.STRING)
    private PollCategoryEnum pollCategory = PollCategoryEnum.GENERAL;

    @Column(name = "report_count")
    private int reportCount = 0;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "poll" , cascade = CascadeType.ALL)
    private List<PollAnswer> pollAnswers;

    @OneToOne(fetch = FetchType.LAZY , mappedBy = "poll" , cascade = CascadeType.ALL)
    private PollQuestion pollQuestion;

    @OneToOne(fetch = FetchType.LAZY , mappedBy = "poll" , cascade = CascadeType.ALL)
    private Multimedia multimedia;


    public int getId() {
        return id;
    }

    public User getAsker() {
        return asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public int getOptACount() {
        return optACount;
    }

    public void setOptACount(int optACount) {
        this.optACount = optACount;
    }

    public int getOptBCount() {
        return optBCount;
    }

    public void setOptBCount(int optBCount) {
        this.optBCount = optBCount;
    }

    public int getOptCCount() {
        return optCCount;
    }

    public void setOptCCount(int optCCount) {
        this.optCCount = optCCount;
    }

    public int getOptDCount() {
        return optDCount;
    }

    public void setOptDCount(int optDCount) {
        this.optDCount = optDCount;
    }

    public int getOptECount() {
        return optECount;
    }

    public void setOptECount(int optECount) {
        this.optECount = optECount;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(Date validUpto) {
        this.validUpto = validUpto;
    }

    public List<PollAnswer> getPollAnswers() {
        return pollAnswers;
    }

    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setPollAnswers(List<PollAnswer> pollAnswers) {
        this.pollAnswers = pollAnswers;
    }

    public void setPollQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public int getPollOpenForAll() {
        return pollOpenForAll;
    }

    public void setPollOpenForAll(int pollOpenForAll) {
        this.pollOpenForAll = pollOpenForAll;
    }

    public int getUserAnonymous() {
        return userAnonymous;
    }

    public void setUserAnonymous(int userAnonymous) {
        this.userAnonymous = userAnonymous;
    }

    public PollCategoryEnum getPollCategory() {
        return pollCategory;
    }

    public void setPollCategory(PollCategoryEnum pollCategory) {
        this.pollCategory = pollCategory;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
