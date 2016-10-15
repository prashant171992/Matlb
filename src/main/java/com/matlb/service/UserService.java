package com.matlb.service;

import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.requestDomain.FriendsPresentRequest;
import com.matlb.domain.responseDomain.FriendsPresentResponse;
import com.matlb.domain.responseDomain.UserResponse;
import org.hibernate.validator.constraints.Email;

import java.util.List;

/**
 * Created by prassingh on 3/22/16.
 */
public interface UserService {

    public List<User> findAllUsers();
    public UserResponse createUser(String email , String token);
    public UserResponse createSubscriber(String email);
    public Subscriber findSubscriberById(Integer subscriberId);
    public User findUserById(Integer userId);
    public User saveUser(User user);
    public Subscriber saveSubscriber(Subscriber subscriber);
    public User findUserByEmail(String email);
    public User findByMobileNumber(Integer phoneNumber);
    public User findUserByEmailIdAndAuthToken(String emailId, String token);
    public Subscriber findSubscriberByEmail(String email);
    public void sendMail(String emailId);
    public User authenticateSignInUser(String email , String userToken);
    public String createUserAuthToken(String token , String email);
    public UserResponse updateMobileNumber(User user , Integer mobileNumber);
    public UserResponse updateGCMToken(User user);
    public FriendsPresentResponse verifyMobileNumbers(FriendsPresentRequest friendsPresentRequest);

}
