package com.matlb.service;

import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.responseDomain.UserResponse;

import java.util.List;

/**
 * Created by prassingh on 3/22/16.
 */
public interface UserService {

    public List<User> findAllUsers();
    public UserResponse createUser(String email , String token , String userName);
    public UserResponse createSubscriber(String email);
    public Subscriber findSubscriberById(Integer subscriberId);
    public User findUserById(Integer userId);
    public User saveUser(User user);
    public Subscriber saveSubscriber(Subscriber subscriber);
    public User findUserByEmail(String email);
    public User findUserByEmailIdAndToken(String emailId, String token);
    public Subscriber findSubscriberByEmail(String email);
    public void deleteUser(Integer userId);
    public void sendMail(String emailId);

}
