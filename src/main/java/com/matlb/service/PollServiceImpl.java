package com.matlb.service;

import com.matlb.NetworkCall;
import com.matlb.dao.*;
import com.matlb.domain.*;
import com.matlb.domain.requestDomain.*;
import com.matlb.domain.responseDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Value("${notification_url}")
    private String url;

    @Value("${authorization_key}")
    private String authorizationKey;


    @Override
    public BasePollResponse getPollsCreatedByUser(PollEnquiryRequest pollEnquiryRequest, int pageNum) {

        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((pollEnquiryRequest.user = isValidUser(pollEnquiryRequest.getUser())) != null) {

            basePollResponse = new BasePollResponse(MatlbStringConstants.RESPONSE_OKAY);

            Page<Poll> polls;

            if(pollEnquiryRequest.getStatus() == StatusType.ALL.ordinal()) {
                polls = getPollDao().findByAsker(pollEnquiryRequest.getUser() , pageRequest);
            } else {
                polls = getPollDao().findByAskerAndStatus(pollEnquiryRequest.getUser() , StatusType.values()[pollEnquiryRequest.getStatus()] , pageRequest);
            }

            List<PollResponse> pollResponseList = new ArrayList<PollResponse>();

            for (Poll poller : polls.getContent()) {
                PollResponse pollResponse = new PollResponse(poller);
                List<QuestionAsked> questionAskedList = getQuestionAskedDao().findByPollAndAsker(poller, pollEnquiryRequest.getUser());
                List<PeopleAnsweredOrNot> peopleAnsweredOrNotList = new ArrayList<PeopleAnsweredOrNot>();

                int creditsUsed = 0;

                for (QuestionAsked question : questionAskedList) {
                    PeopleAnsweredOrNot peopleAnsweredOrNot = new PeopleAnsweredOrNot(question);
                    peopleAnsweredOrNotList.add(peopleAnsweredOrNot);
                    creditsUsed += question.getCreditAlloted();  // not considering the status of questionAsked
                }

                pollResponse.setCreditsUsed(creditsUsed);
                pollResponse.setPeopleAnsweredOrNotList(peopleAnsweredOrNotList);
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

        if((pollEnquiryRequest.user = isValidUser(pollEnquiryRequest.getUser())) != null) {

            basePollResponse = new BasePollResponse(MatlbStringConstants.RESPONSE_OKAY);

            Page<PollAnswer> pollAnswerList = getPollAnswerDao().findByAnswerer(pollEnquiryRequest.user , pageRequest);

            List<PollAnsweredResponse> pollAnsweredResponseList = new ArrayList<PollAnsweredResponse>();

            for(PollAnswer pollAnswer : pollAnswerList) {
                PollAnsweredResponse pollAnsweredResponse = new PollAnsweredResponse(pollAnswer);

                // for fetching credits , if any
                QuestionAsked questionAsked = getQuestionAskedDao().findByPollAndAnswerer(pollAnswer.getPoll(), pollEnquiryRequest.getUser());
                if(questionAsked != null) {
                    pollAnsweredResponse.setCreditsEarned(questionAsked.getCreditAlloted());
                } else {
                    pollAnsweredResponse.setCreditsEarned(0);
                }

                pollAnsweredResponse.setCorrectAnswer(findPollAnswer(pollAnswer.getPoll()));

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
    public BasePollResponse getPollAskedToByUser(User user, Integer pollId, int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum , PAGE_SIZE);

        BasePollResponse basePollResponse ;

        if((user = isValidUser(user)) != null) {
            basePollResponse = new BasePollResponse("response okay !");

            Poll poll = getPollDao().findOne(pollId);

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
    public BasePollResponse getPollToBeShownToUser(ShowPollRequest showPollRequest) {
        PageRequest pageRequest = new PageRequest(showPollRequest.getPageNumber() , PAGE_SIZE);

        BasePollResponse basePollResponse ;
        User user = showPollRequest.getUser();

        if((user = isValidUser(user)) != null) {
            getUserService().saveUser(user);
            basePollResponse = new BasePollResponse(MatlbStringConstants.RESPONSE_OKAY);

            List<PollForUserResponse> pollForUserResponseList = new ArrayList<PollForUserResponse>();

            if(showPollRequest.getOpenForAll() == 1){

                Page<Poll> polls = getPollDao().findByPollOpenForAllOrderByUpdateDtDesc(1 , pageRequest);
                for(Poll poll : polls.getContent()) {
                    if(getPollAnswerDao().findByPollAndAnswerer(poll,user) == null && getQuestionAskedDao().findByPollAndAnswerer(poll, user) == null) {
                        PollForUserResponse pollForUserResponse = new PollForUserResponse(poll.getPollQuestion());
                        if(poll.getUserAnonymous() == 0) {
                            pollForUserResponse.setAskerName(poll.getAsker().getName());
                        } else {
                            pollForUserResponse.setAskerName(null);
                        }
                        pollForUserResponseList.add(pollForUserResponse);
                    }
                }

            } else {

                Page<QuestionAsked> questionAskedList = getQuestionAskedDao().findByAnswererAndStatus(user , StatusType.PENDING , pageRequest);
                for(QuestionAsked questionAsked : questionAskedList) {
                    PollForUserResponse pollForUserResponse = new PollForUserResponse(questionAsked.getPoll().getPollQuestion());
                    if(questionAsked.getPoll().getUserAnonymous() == 0) {
                        pollForUserResponse.setAskerName(questionAsked.getPoll().getAsker().getName());
                    } else {
                        pollForUserResponse.setAskerName(null);
                    }
                    pollForUserResponseList.add(pollForUserResponse);
                }
            }

            basePollResponse.setPollForUserResponses(pollForUserResponseList);

        } else {
            basePollResponse = new BasePollResponse(MatlbStringConstants.NO_USER_FOUND);
        }
        return basePollResponse;
    }

    @Override
    public BasePollResponse createPoll(CreatePollRequest createPollRequest) {

        BasePollResponse basePollResponse = new BasePollResponse();

        User asker = isValidUser(createPollRequest.getAsker());

        if(asker == null) {
            basePollResponse.setMessage(MatlbStringConstants.POLL_REQUEST_INVALID_USER);
            return basePollResponse;
        }

        if(createPollRequest.getToBeAskedFrom() != null) {
            for(QuestionAskRequest questionAskRequest : createPollRequest.getToBeAskedFrom()) {
                User askedTo = getUserService().findByMobileNumber(questionAskRequest.getPhoneNumber());
                if(askedTo == null) {
                    basePollResponse.setMessage(MatlbStringConstants.POLL_REQUEST_NOT_TO_VALID_USER);
                    return basePollResponse;
                }
            }
        }


        Poll poll = new Poll();
        poll.setAsker(asker);
        poll.setGenre(GenreType.values()[createPollRequest.getGenreType()]);
        poll.setPollOpenForAll(createPollRequest.getOpenForAll());
        poll.setUserAnonymous(createPollRequest.getUserAnonymous());
        poll = getPollDao().save(poll);

        PollQuestion pollQuestion = new PollQuestion();
        pollQuestion.setQuestionText(createPollRequest.getPollQuestion());
        pollQuestion.setOptionA(createPollRequest.getOptionAText());
        pollQuestion.setOptionB(createPollRequest.getOptionBText());
        pollQuestion.setOptionC(createPollRequest.getOptionCText());
        pollQuestion.setOptionD(createPollRequest.getOptionDText());
        pollQuestion.setOptionE(createPollRequest.getOptionEText());
        pollQuestion.setPoll(poll);

        getPollQuestionDao().save(pollQuestion);

        NotificationData notificationData = new NotificationData();
        if (createPollRequest.getUserAnonymous() == 1) {
            notificationData.setAsker(MatlbStringConstants.POLL_ASKED_ANONYMOUS);
        } else {
            notificationData.setAsker(asker.getName() + MatlbStringConstants.POLL_ASKED_NON_ANONYMOUS);
        }
        notificationData.setMessage(pollQuestion.getQuestionText());
        notificationData.setPollId(poll.getId());

        Notification notification = new Notification();
        notification.setData(notificationData);
        List<String> gcmIds = new ArrayList<>();

        if(createPollRequest.getToBeAskedFrom() != null) {
            for(QuestionAskRequest questionAskRequest : createPollRequest.getToBeAskedFrom()) {
                QuestionAsked questionAsked = new QuestionAsked();
                User answerer = getUserService().findByMobileNumber(questionAskRequest.getPhoneNumber());
                questionAsked.setAnswerer(answerer);
                questionAsked.setStatus(StatusType.PENDING);
                questionAsked.setAsker(asker);
                questionAsked.setCreditAlloted(questionAsked.getCreditAlloted());
                questionAsked.setPoll(poll);
                gcmIds.add(answerer.getGcmToken());
                getQuestionAskedDao().save(questionAsked);
            }
        }

        notification.setRegistrationIds(gcmIds);
        notification.setData(notificationData);

        try {
            NetworkCall.makePostRequest(notification, url, authorizationKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        asker.setQuestionCount(asker.getQuestionCount() + 1);
        getUserDao().save(asker);

        basePollResponse.setMessage(MatlbStringConstants.POLL_CREATED_SUCCESSFULLY);
        return basePollResponse;

    }

    @Override
    public BasePollResponse answerPollQuestion(AnswerQuestionRequest answerQuestionRequest) {

        BasePollResponse basePollResponse = new BasePollResponse();

        User answerer = isValidUser(answerQuestionRequest.getUser());

        Poll poll = getPollDao().findOne(answerQuestionRequest.getPollId());

        if(answerer == null || poll == null) {
            basePollResponse.setMessage("Invalid answerer or poll");
            return basePollResponse;
        }

        QuestionAsked questionAsked = getQuestionAskedDao().findByPollAndAnswererAndStatus(poll , answerer , StatusType.PENDING);

        if (questionAsked == null) {
            // Checked if user can give answer to openForAll question or he has already answered it
            if(poll.getPollOpenForAll() == 1 && poll.getStatus().ordinal() != StatusType.COMPLETED.ordinal()
                    && poll.getStatus().ordinal() != StatusType.EXPIRED.ordinal()
                    && getPollAnswerDao().findByPollAndAnswerer(poll, answerer) == null) {

                PollAnswer pollAnswer = new PollAnswer();
                pollAnswer.setAnswer(ResultType.values()[answerQuestionRequest.getAnswer()]);
                pollAnswer.setAnswerer(answerer);
                pollAnswer.setPoll(poll);

                getPollAnswerDao().save(pollAnswer);

                switch (ResultType.values()[answerQuestionRequest.getAnswer()]) {
                    case OPTION_A:
                        poll.setOptACount(poll.getOptACount() + 1);
                        break;
                    case OPTION_B:
                        poll.setOptBCount(poll.getOptBCount() + 1);
                        break;
                    case OPTION_C:
                        poll.setOptCCount(poll.getOptCCount() + 1);
                        break;
                    case OPTION_D:
                        poll.setOptDCount(poll.getOptDCount() + 1);
                        break;
                    case OPTION_E:
                        poll.setOptECount(poll.getOptECount() + 1);
                        break;
                }

                answerer.setAnswerCount(answerer.getAnswerCount() + 1);
                getUserDao().save(answerer);

                getPollDao().save(poll);

                basePollResponse.setMessage(MatlbStringConstants.POLL_FOR_ALL_ANSWERED_SUCCESSFULLY);
            } else {
                basePollResponse.setMessage("Invalid answerer/poll/status combination");
            }
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

            switch (ResultType.values()[answerQuestionRequest.getAnswer()]) {
                case OPTION_A:
                    poll.setOptACount(poll.getOptACount() + 1);
                    break;
                case OPTION_B:
                    poll.setOptBCount(poll.getOptBCount() + 1);
                    break;
                case OPTION_C:
                    poll.setOptCCount(poll.getOptCCount() + 1);
                    break;
                case OPTION_D:
                    poll.setOptDCount(poll.getOptDCount() + 1);
                    break;
                case OPTION_E:
                    poll.setOptECount(poll.getOptECount() + 1);
                    break;
            }

            getPollDao().save(poll);

            int credits = answerer.getCredits();
            answerer.setCredits(credits + questionAsked.getCreditAlloted());
            answerer.setAnswerCount(answerer.getAnswerCount() + 1);

            getUserDao().save(answerer);

            basePollResponse.setMessage(MatlbStringConstants.CREDITS_RECEIVED + questionAsked.getCreditAlloted());
        }

        return basePollResponse;

    }

    @Override
    public BasePollResponse updatePollStatus(User user, Integer pollId, int status) {
        BasePollResponse basePollResponse = new BasePollResponse();

        User answerer = isValidUser(user);

        Poll poll = getPollDao().findOne(pollId);

        if(answerer == null || poll == null) {
            basePollResponse.setMessage("Invalid user or poll");
            return basePollResponse;
        }

        poll.setStatus(StatusType.values()[status]);

        getPollDao().save(poll);

        basePollResponse.setMessage(MatlbStringConstants.POLL_STATUS_UPDATE_SUCCESSFUL);
        return basePollResponse;
    }

    @Override
    public BasePollResponse getPollDetailsById(User user, Integer pollId) {
        BasePollResponse basePollResponse = new BasePollResponse();

        User answerer = isValidUser(user);

        Poll poll = getPollDao().findOne(pollId);

        if(answerer == null || poll == null) {
            basePollResponse.setMessage("Invalid user or poll");
            return basePollResponse;
        }

        List<PollForUserResponse> pollForUserResponses = new ArrayList<>();
        PollForUserResponse pollForUserResponse = new PollForUserResponse(poll.getPollQuestion());
        QuestionAsked questionAsked = getQuestionAskedDao().findByPollAndAnswerer(poll, answerer);
        if(questionAsked!= null) {
            pollForUserResponse.setCreditToBeEarned(questionAsked.getCreditAlloted());
        } else {
            pollForUserResponse.setCreditToBeEarned(0);
        }

        if(poll.getUserAnonymous() == 1) {
            pollForUserResponse.setAskerName(null);
        } else {
            pollForUserResponse.setAskerName(poll.getAsker().getName());
        }

        pollForUserResponses.add(pollForUserResponse);

        basePollResponse.setMessage(MatlbStringConstants.RESPONSE_OKAY);

        basePollResponse.setPollForUserResponses(pollForUserResponses);
        return basePollResponse;
    }


    private User isValidUser(User tempUser) {
        return getUserService().findUserByEmailIdAndAuthToken(tempUser.getEmailId() , tempUser.getAuthToken());
    }

    private int findPollAnswer(Poll poll) {
        if (poll.getOptACount() >= poll.getOptBCount() && poll.getOptACount() >= poll.getOptCCount() && poll.getOptACount() >= poll.getOptDCount() && poll.getOptACount() >= poll.getOptECount()){
            return 0;
        }
        if (poll.getOptBCount() >= poll.getOptACount() && poll.getOptBCount() >= poll.getOptCCount() && poll.getOptBCount() >= poll.getOptDCount() && poll.getOptBCount() >= poll.getOptECount()){
            return 1;
        }
        if (poll.getOptCCount() >= poll.getOptACount() && poll.getOptCCount() >= poll.getOptBCount() && poll.getOptCCount() >= poll.getOptDCount() && poll.getOptCCount() >= poll.getOptECount()){
            return 2;
        }
        if (poll.getOptDCount() >= poll.getOptACount() && poll.getOptDCount() >= poll.getOptBCount() && poll.getOptDCount() >= poll.getOptCCount() && poll.getOptDCount() >= poll.getOptECount()){
            return 3;
        }
        if (poll.getOptECount() >= poll.getOptACount() && poll.getOptECount() >= poll.getOptBCount() && poll.getOptECount() >= poll.getOptCCount() && poll.getOptECount() >= poll.getOptDCount()){
            return 4;
        }
        return -1;
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
