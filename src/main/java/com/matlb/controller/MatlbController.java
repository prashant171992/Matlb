package com.matlb.controller;

import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.requestDomain.AnswerQuestionRequest;
import com.matlb.domain.requestDomain.CreatePollRequest;
import com.matlb.domain.requestDomain.PollEnquiryRequest;
import com.matlb.domain.requestDomain.ShowPollRequest;
import com.matlb.domain.responseDomain.BasePollResponse;
import com.matlb.domain.responseDomain.UserResponse;
import com.matlb.service.PollService;
import com.matlb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by prassingh on 3/22/16.
 */

@RestController
@RequestMapping("/matlb")
public class MatlbController {

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;


    //@RequestMapping(value = "/user/add/{email:.+}")
//    public UserResponse createUser(@PathVariable String email) {
//        return getUserService().createUser(email);
//    }

    @RequestMapping(method = RequestMethod.POST , value = "/subscriber/add")
    public UserResponse createSubscriber(@RequestBody Subscriber subscriber){
        return getUserService().createSubscriber(subscriber.getEmailId());
    }

    @RequestMapping(method = RequestMethod.POST , value = "/user/add")
    public UserResponse createUser(@RequestBody User user) {
        return getUserService().createUser(user.getEmailId() , user.getUserToken());
    }

    @RequestMapping(value = "/user/find/{id}")
    public User getUserById(@PathVariable Integer id) {
        return getUserService().findUserById(id);
    }


//    @RequestMapping(value = "/user/get/all")
//    public List<User> getAllUsers() {
//        return getUserService().findAllUsers();
//    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/find/{pageNumber}")
    public BasePollResponse findPollCreatedByUser(@RequestBody PollEnquiryRequest pollEnquiryRequest , @PathVariable Integer pageNumber) {
        return getPollService().getPollsCreatedByUser(pollEnquiryRequest , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/answered/find/{pageNumber}")
    public BasePollResponse findPollAnsweredByUser(@RequestBody PollEnquiryRequest pollEnquiryRequest, @PathVariable Integer pageNumber) {
        return getPollService().getPollAnsweredByUser(pollEnquiryRequest , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/question/answerer/find/{pollId}/{pageNumber}")
    public BasePollResponse getAnswerersByPoll(@RequestBody User user , @PathVariable Integer pageNumber , @PathVariable Integer pollId) {
        return getPollService().getPollAskedToByUser(user , pollId , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/show")
    public BasePollResponse findPollToBeShownToUser(@RequestBody ShowPollRequest showPollRequest) {
        return getPollService().getPollToBeShownByUser(showPollRequest);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/create")
    public BasePollResponse createPoll(@RequestBody CreatePollRequest createPollRequest) {
        return getPollService().createPoll(createPollRequest);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/answer")
        public BasePollResponse answerPollQuestion(@RequestBody AnswerQuestionRequest answerQuestionRequest) {
        return getPollService().answerPollQuestion(answerQuestionRequest);
    }

    public UserService getUserService() {
        return userService;
    }

    public PollService getPollService() {
        return pollService;
    }
}
