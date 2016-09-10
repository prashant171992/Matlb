package com.matlb.service;

import com.matlb.dao.SubscriberDao;
import com.matlb.dao.UserDao;
import com.matlb.domain.MatlbStringConstants;
import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.responseDomain.UserResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prassingh on 3/22/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SubscriberDao subscriberDao;

    @Value("${api}")
    private String apiKey;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) getUserDao().findAll();
    }

    @Override
    public UserResponse createUser(String email , String token) {
        UserResponse userResponse;
        if(findUserByEmailIdAndToken(email , token) == null){
            User user = new User(email , token);
            saveUser(user);
            sendMail(email);
            userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_SUCCESS);
            userResponse.setUserCreated(true);
        } else {
            userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_FAILURE);
            userResponse.setUserCreated(false);
            //sendMail(email);
        }
        return userResponse;
    }

    @Override
    public UserResponse createSubscriber(String email) {
        UserResponse userResponse;
        if(findSubscriberByEmail(email) == null){
            Subscriber subscriber = new Subscriber(email);
            saveSubscriber(subscriber);
            userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_SUCCESS);
            userResponse.setUserCreated(true);
            sendMail(email);
        } else {
            userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_FAILURE);
            userResponse.setUserCreated(false);
            //sendMail(email);
        }
        return userResponse;
    }

    @Override
    public Subscriber findSubscriberById(Integer subscriberId) {
        return getSubscriberDao().findOne(subscriberId);
    }

    @Override
    public User findUserById(Integer userId) {
        return getUserDao().findOne(userId);
    }

    @Override
    public User saveUser(User user) {
        return getUserDao().save(user);
    }

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        return getSubscriberDao().save(subscriber);
    }

    @Override
    public User findUserByEmail(String email) {
        return getUserDao().findByEmailId(email);
    }

    @Override
    public User findUserByEmailIdAndToken(String emailId, String token) {
        return getUserDao().findByEmailIdAndUserToken(emailId , token);
    }

    @Override
    public Subscriber findSubscriberByEmail(String email) {
        return getSubscriberDao().findByEmailId(email);
    }


    @Override
    public void deleteUser(Integer userId) {
        getUserDao().delete(userId);
    }

    @Async
    public void sendMail(String emailId) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                apiKey));
        com.sun.jersey.api.client.WebResource webResource =
                client.resource("https://api.mailgun.net/v3/matlb.com/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Matlb <prashant@matlb.com>");
        formData.add("to", emailId);
        formData.add("subject" , "You found matlb !");
        formData.add("text", "Thanks for registering with matlb.com . Very soon we will be coming up with a beta version of the website . Till then don't be matlbi. And ask for matlb ;) ");
        ClientResponse clientResponse =  webResource.type(String.valueOf(MediaType.APPLICATION_FORM_URLENCODED)).
                post(ClientResponse.class, formData);
        //String output = clientResponse.getEntity(String.class);

        //UserResponse userResponse = new UserResponse("Email sent successfully : " + output);
        //return true;
    }

    public UserDao getUserDao() {
        return userDao;
    }


    public SubscriberDao getSubscriberDao() {
        return subscriberDao;
    }
}
