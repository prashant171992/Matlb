package com.matlb.controller;

import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.requestDomain.*;
import com.matlb.domain.responseDomain.BasePollResponse;
import com.matlb.domain.responseDomain.FriendsPresentResponse;
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
        return getUserService().createUser(user.getEmailId() , user.getAuthToken());
    }

    @RequestMapping(value = "/user/find/{id}")
    public User getUserById(@PathVariable Integer id) {
        return getUserService().findUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/user/update/mobile/{mobileNumber}")
    public UserResponse verifyMobileNumber(@PathVariable Long mobileNumber , @RequestBody User user) {
        return getUserService().updateMobileNumber(user , mobileNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/user/update/gcm")
    public UserResponse updateGCMToken(@RequestBody User user) {
        return getUserService().updateGCMToken(user);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/user/friends")
    public FriendsPresentResponse checkValidFriendsPhoneNumber(@RequestBody FriendsPresentRequest friendsPresentRequest) {
        return getUserService().verifyMobileNumbers(friendsPresentRequest);
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
        return getPollService().getPollToBeShownToUser(showPollRequest);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/create")
    public BasePollResponse createPoll(@RequestBody CreatePollRequest createPollRequest) {
        return getPollService().createPoll(createPollRequest);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/answer")
    public BasePollResponse answerPollQuestion(@RequestBody AnswerQuestionRequest answerQuestionRequest) {
        return getPollService().answerPollQuestion(answerQuestionRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poll/status/{pollId}/{pollStatus}")
    public BasePollResponse updatePollStatus(@RequestBody User user, @PathVariable Integer pollId, @PathVariable Integer pollStatus) {
        return getPollService().updatePollStatus(user, pollId, pollStatus);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poll/get/{pollId}")
    public BasePollResponse getPollDetailsById(@RequestBody User user, @PathVariable Integer pollId){
        return getPollService().getPollDetailsById(user, pollId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poll/asked/get/{pollId}")
    public BasePollResponse getPollAskedDetailsById(@RequestBody User user, @PathVariable Integer pollId) {
        return getPollService().getPollDetailsById(user, pollId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poll/cat")
    public BasePollResponse getPollCategory(@RequestBody User user){
        return getPollService().getPollCategories(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poll/report/{pollId}")
    public BasePollResponse reportPoll(@RequestBody User user,  @PathVariable Integer pollId) {
        return getPollService().reportPoll(user, pollId);
    }

    public UserService getUserService() {
        return userService;
    }

    public PollService getPollService() {
        return pollService;
    }
}
