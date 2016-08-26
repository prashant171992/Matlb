package com.matlb.controller;

import com.matlb.domain.*;
import com.matlb.service.PollService;
import com.matlb.service.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
        return getUserService().createUser(user.getEmailId());
    }

    @RequestMapping(value = "/user/find/{id}")
    public User getUserById(@PathVariable Integer id) {
        return getUserService().findUserById(id);
    }


    @RequestMapping(value = "/user/get/all")
    public List<User> getAllUsers() {
        return getUserService().findAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/create/find/{pageNumber}")
    public BasePollResponse findPollCreatedByUser(@RequestBody User user , @PathVariable Integer pageNumber) {
        return getPollService().getPollsCreatedByUser(user , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/answered/find/{pageNumber}")
    public BasePollResponse findPollAnsweredByUser(@RequestBody User user , @PathVariable Integer pageNumber) {
        return getPollService().getPollAnsweredByUser(user , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/question/answerer/find/{pollId}/{pageNumber}")
    public BasePollResponse getAnswersByPoll(@RequestBody User user , @PathVariable Integer pageNumber , @PathVariable Integer pollId) {
        return getPollService().getPollAskedToByUser(user , pollId , pageNumber);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/poll/show/{pageNumber}")
    public BasePollResponse findPollToBeShownToUser(@RequestBody User user , @PathVariable Integer pageNumber) {
        return getPollService().getPollToBeShownByUser(user , pageNumber);
    }

    public UserService getUserService() {
        return userService;
    }

    public PollService getPollService() {
        return pollService;
    }
}
