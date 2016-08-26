package com.matlb.service;

import com.matlb.dao.*;
import com.matlb.domain.*;
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
    public BasePollResponse getPollsCreatedByUser(User user, int pageNum) {

        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((user = isValidUser(user)) != null) {

            basePollResponse = new BasePollResponse("response okay !");

            Page<Poll> poll = getPollDao().findByAskerAndFetchPollAnswersEagerly(user,pageRequest);

            List<PollResponse> pollResponseList = new ArrayList<PollResponse>();

            for(Poll poller : poll){
                PollResponse pollResponse = new PollResponse(poller);
                List<QuestionAsked> questionAskedList = getQuestionAskedDao().findByPollAndAsker(poller,user);
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
    public BasePollResponse getPollAnsweredByUser(User user, int pageNum) {

        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((user = isValidUser(user)) != null){
            basePollResponse = new BasePollResponse("response okay !");

            Page<PollAnswer> pollAnswerList = getPollAnswerDao().findByAnswerer(user , pageRequest);

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

    private User isValidUser(User tempUser) {
        tempUser = getUserDao().findByEmailId(tempUser.getEmailId());
        return tempUser;
    }

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
