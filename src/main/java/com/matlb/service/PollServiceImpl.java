package com.matlb.service;

import com.matlb.dao.*;
import com.matlb.domain.*;
import com.matlb.domain.requestDomain.AnswerQuestionRequest;
import com.matlb.domain.requestDomain.CreatePollRequest;
import com.matlb.domain.requestDomain.PollEnquiryRequest;
import com.matlb.domain.requestDomain.QuestionAskRequest;
import com.matlb.domain.responseDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prassingh on 8/22/16.
 */

@Service
@Transactional
public class PollServiceImpl implements PollService {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private PollDao pollDao;

    @Autowired
    private PollQuestionDao pollQuestionDao;

    @Autowired
    private PollAnswerDao pollAnswerDao;

    @Autowired
    private QuestionAskedDao questionAskedDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Override
    public BasePollResponse getPollsCreatedByUser(PollEnquiryRequest pollEnquiryRequest, int pageNum) {

        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((pollEnquiryRequest.user = isValidUser(pollEnquiryRequest.getUser())) != null) {

            basePollResponse = new BasePollResponse("response okay !");

            Page<Poll> poll;

            if(pollEnquiryRequest.getStatus() == StatusType.ALL.ordinal()) {
                poll = getPollDao().findByAsker(pollEnquiryRequest.getUser() , pageRequest);
            } else {
                poll = getPollDao().findByAskerAndStatus(pollEnquiryRequest.getUser() , StatusType.values()[pollEnquiryRequest.getStatus()] , pageRequest);
            }

            List<PollResponse> pollResponseList = new ArrayList<PollResponse>();

            for(Poll poller : poll){
                PollResponse pollResponse = new PollResponse(poller);
                List<QuestionAsked> questionAskedList = getQuestionAskedDao().findByPollAndAsker(poller,pollEnquiryRequest.getUser());
                pollResponse.setPeopleAsked(questionAskedList.size());

                int creditsUsed = 0;

                for(QuestionAsked question : questionAskedList) {
                    creditsUsed+=question.getCreditAlloted();  // not considering the status of questionAsked
                }

                pollResponse.setCreditsUsed(creditsUsed);
                pollResponseList.add(pollResponse);
            }

            basePollResponse.setPollResponseList(pollResponseList);

        } else {
            basePollResponse = new BasePollResponse("sorry , user not found !");
        }

        return basePollResponse;
    }



    @Override
    public BasePollResponse getPollAnsweredByUser(PollEnquiryRequest pollEnquiryRequest, int pageNum) {

        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((pollEnquiryRequest.user = isValidUser(pollEnquiryRequest.getUser())) != null){
            basePollResponse = new BasePollResponse("response okay !");


            Page<PollAnswer> pollAnswerList = getPollAnswerDao().findByAnswerer(pollEnquiryRequest.user , pageRequest);

            List<PollAnsweredResponse> pollAnsweredResponseList = new ArrayList<PollAnsweredResponse>();

            for(PollAnswer pollAnswer : pollAnswerList) {
                PollAnsweredResponse pollAnsweredResponse = new PollAnsweredResponse(pollAnswer);

                PollQuestion pollQuestion = getPollQuestionDao().findByPoll(pollAnswer.getPoll());

                pollAnsweredResponse.setQuestionText(pollQuestion.getQuestionText());
                pollAnsweredResponse.setOptionAText(pollQuestion.getOptionA());
                pollAnsweredResponse.setOptionBText(pollQuestion.getOptionB());
                pollAnsweredResponse.setOptionCText(pollQuestion.getOptionC());
                pollAnsweredResponse.setOptionDText(pollQuestion.getOptionD());
                pollAnsweredResponse.setOptionEText(pollQuestion.getOptionE());

                pollAnsweredResponseList.add(pollAnsweredResponse);

            }
            basePollResponse.setPollAnsweredResponses(pollAnsweredResponseList);

        } else {
            basePollResponse = new BasePollResponse("sorry , user not found !");
        }

        return basePollResponse;
    }

    @Override
    public BasePollResponse getPollAskedToByUser(User user, int pollId, int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((user = isValidUser(user)) != null) {
            basePollResponse = new BasePollResponse("response okay !");

            Poll poll = getPollDao().findById(pollId);

            Page<QuestionAsked> questionAskedList = getQuestionAskedDao().findByPollAndAsker(poll , user , pageRequest);

            List<PollQuestionAskedToResponse> pollQuestionAskedToResponseList = new ArrayList<PollQuestionAskedToResponse>();

            for(QuestionAsked questionAsked : questionAskedList) {
                PollQuestionAskedToResponse pollQuestionAskedToResponse = new PollQuestionAskedToResponse(questionAsked);
                pollQuestionAskedToResponseList.add(pollQuestionAskedToResponse);
            }

            basePollResponse.setPollQuestionAskedToResponses(pollQuestionAskedToResponseList);

        } else {
            basePollResponse = new BasePollResponse("sorry , user not found !");
        }

        return basePollResponse;
    }

    @Override
    public BasePollResponse getPollToBeShownByUser(User user,  int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((user = isValidUser(user)) != null) {
            basePollResponse = new BasePollResponse("response okay !");

            Page<QuestionAsked> questionAskedList = getQuestionAskedDao().findByAnswererAndStatus(user , StatusType.PENDING , pageRequest);

            List<PollForUserResponse> pollForUserResponseList = new ArrayList<PollForUserResponse>();

            for(QuestionAsked questionAsked : questionAskedList) {
                PollForUserResponse pollForUserResponse = new PollForUserResponse(questionAsked.getPoll().getPollQuestion());
                pollForUserResponseList.add(pollForUserResponse);
            }

            basePollResponse.setPollForUserResponses(pollForUserResponseList);

        } else {
            basePollResponse = new BasePollResponse("sorry , user not found !");
        }
        return basePollResponse;
    }

    @Override
    public BasePollResponse createPoll(CreatePollRequest createPollRequest) {

        BasePollResponse basePollResponse = new BasePollResponse();

        User asker = isValidUser(createPollRequest.getAsker());

        if(asker == null) {
            basePollResponse.setMessage("Poll create request not from a valid user");
            return basePollResponse;
        }

        for(QuestionAskRequest questionAskRequest : createPollRequest.getToBeAskedFrom()) {
            User askedTo = isValidUser(questionAskRequest.getAskedTo());
            if(askedTo == null) {
                basePollResponse.setMessage("Poll question asked not to a valid user");
                return basePollResponse;
            } else {
                questionAskRequest.setAskedTo(askedTo);
            }
        }

        Poll poll = new Poll();
        poll.setAsker(asker);
        poll.setGenre(GenreType.values()[createPollRequest.getGenreType()]);
        poll = getPollDao().save(poll);

        PollQuestion pollQuestion = new PollQuestion();
        pollQuestion.setQuestionText(createPollRequest.getPollQuestion());
        pollQuestion.setOptionA(createPollRequest.getOptionAText());
        pollQuestion.setOptionB(createPollRequest.getOptionBText());
        pollQuestion.setOptionC(createPollRequest.getOptionCText());
        pollQuestion.setOptionD(createPollRequest.getOptionDText());
        pollQuestion.setOptionE(createPollRequest.getOptionEText());
        pollQuestion.setPoll(poll);

        pollQuestion = getPollQuestionDao().save(pollQuestion);

        for(QuestionAskRequest questionAskRequest : createPollRequest.getToBeAskedFrom()) {
            QuestionAsked questionAsked = new QuestionAsked();
            questionAsked.setAnswerer(questionAskRequest.getAskedTo());
            questionAsked.setAsker(asker);
            questionAsked.setCreditAlloted(questionAsked.getCreditAlloted());
            questionAsked.setPoll(poll);
            getQuestionAskedDao().save(questionAsked);
        }

        asker.setQuestionCount(asker.getQuestionCount() + 1);
        getUserDao().save(asker);

        basePollResponse.setMessage("poll created successfully!");
        return basePollResponse;

    }

    @Override
    public BasePollResponse answerPollQuestion(AnswerQuestionRequest answerQuestionRequest) {

        BasePollResponse basePollResponse = new BasePollResponse();

        User answerer = isValidUser(answerQuestionRequest.getUser());

        Poll poll = getPollDao().findById(answerQuestionRequest.getPollId());

        if(answerer == null || poll == null) {
            basePollResponse.setMessage("Invalid answerer or poll");
            return basePollResponse;
        }

        QuestionAsked questionAsked = getQuestionAskedDao().findByPollAndAnswererAndStatus(poll , answerer , StatusType.PENDING);

        if(questionAsked == null) {
            basePollResponse.setMessage("Invalid answerer/poll/status combination");
            return basePollResponse;
        }

        if(ResultType.REJECT.ordinal() == answerQuestionRequest.getAnswer()) {
            questionAsked.setStatus(StatusType.REJECTED);
            getQuestionAskedDao().save(questionAsked);

            basePollResponse.setMessage("Poll rejected successfully !");

        } else {
            PollAnswer pollAnswer = new PollAnswer();
            pollAnswer.setAnswer(ResultType.values()[answerQuestionRequest.getAnswer()]);
            pollAnswer.setAnswerer(answerer);
            pollAnswer.setPoll(poll);

            getPollAnswerDao().save(pollAnswer);

            questionAsked.setStatus(StatusType.ANSWERED);
            getQuestionAskedDao().save(questionAsked);

            int credits = answerer.getCredits();
            answerer.setCredits(credits + questionAsked.getCreditAlloted());
            answerer.setAnswerCount(answerer.getAnswerCount() + 1);

            getUserDao().save(answerer);

            basePollResponse.setMessage("Credits received " + questionAsked.getCreditAlloted());
        }

        return basePollResponse;

    }

    private User isValidUser(User tempUser) {
        tempUser = getUserDao().findByEmailId(tempUser.getEmailId());
        return tempUser;
    }

    // TODO : add a method to update poll status as COMPLETED if all rows in question asked are in status other than PENDING state .Also , take care of valid upto case .

    public UserService getUserService() {
        return userService;
    }

    public PollDao getPollDao() {
        return pollDao;
    }

    public PollQuestionDao getPollQuestionDao() {
        return pollQuestionDao;
    }

    public PollAnswerDao getPollAnswerDao() {
        return pollAnswerDao;
    }

    public QuestionAskedDao getQuestionAskedDao() {
        return questionAskedDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
