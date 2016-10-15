package com.matlb.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.matlb.dao.SubscriberDao;
import com.matlb.dao.UserDao;
import com.matlb.domain.MatlbStringConstants;
import com.matlb.domain.Subscriber;
import com.matlb.domain.User;
import com.matlb.domain.requestDomain.FriendsPresentRequest;
import com.matlb.domain.responseDomain.FriendsPresentResponse;
import com.matlb.domain.responseDomain.UserResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    @Value("${server_client_id}")
    private String serverClientId;

    @Value("${dummy_token}")
    private String dummyToken;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) getUserDao().findAll();
    }

    @Override
    public UserResponse createUser(String email , String token) {
        UserResponse userResponse;
        User user = authenticateSignInUser(email , token);
        if(user != null) {

            User tempUser = findUserByEmail(user.getEmailId());

            String authToken = createUserAuthToken(token,user.getEmailId());

            if(tempUser == null) {
                user.setAuthToken(authToken);
                user = saveUser(user);
                sendMail(email);
                userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_SUCCESS);
                userResponse.setUserCreated(true);
                userResponse.setUser(user);
            } else {
                user = tempUser;
                user.setAuthToken(authToken);
                user = saveUser(user);
                userResponse = new UserResponse(MatlbStringConstants.USER_REGISTER_FAILURE);
                userResponse.setUserCreated(false);
                userResponse.setUser(user);
                //sendMail(email);
            }
        } else {
            userResponse = new UserResponse(MatlbStringConstants.USER_LOGIN_FAILURE);
            userResponse.setUserCreated(false);
            userResponse.setUser(null);
        }
        return userResponse;
    }

    public User authenticateSignInUser(String email , String userToken){
        String returnVal;
        User user = new User();
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory jsonFactory = new GsonFactory();
        if(userToken != null && !userToken.equals("")){
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(serverClientId))
                    .setIssuer("https://accounts.google.com").build();

            try{
                GoogleIdToken idToken = verifier.verify(userToken);
                if (idToken != null) {
                    GoogleIdToken.Payload payload = idToken.getPayload();
                    returnVal = "User ID: " + payload.getSubject();
                    // You can also access the following properties of the payload in order
                    // for other attributes of the user. Note that these fields are only
                    // available if the user has granted the 'profile' and 'email' OAuth
                    // scopes when requested.
                     user.setEmailId(payload.getEmail());
                     //boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                     user.setName((String)payload.get("name"));
                     user.setProfilePic((String)payload.get("picture"));
//                     String locale = (String) payload.get("locale");
//                     String familyName = (String) payload.get("family_name");
//                     String givenName = (String) payload.get("given_name");
                } else {
                    user = null;
                    returnVal = "Invalid ID token.";
                }
            } catch (Exception ex){
                user = null;
                returnVal = ex.getMessage();
            }
        }
        else {
            returnVal = "Bad Token Passed In";
            user = null;
        }

        return user;
    }

    @Override
    public String createUserAuthToken(String token , String email){

        String key = UUID.randomUUID().toString().toUpperCase() +
                "|" + token +
                "|" + email ;

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(dummyToken);

        // this is the authentication token user will send in order to use the web service
        return jasypt.encrypt(key);
    }

    @Override
    public UserResponse updateMobileNumber(User user , Integer mobileNumber) {

        UserResponse userResponse;
        User user1 = findUserByEmailIdAndAuthToken(user.getEmailId() , user.getAuthToken());

        if(user1 != null) {
            user1.setPhoneNumber(mobileNumber);
            saveUser(user1);
            userResponse = new UserResponse(MatlbStringConstants.MOBILE_NUMBER_UPDATED);
        } else {
            userResponse = new UserResponse(MatlbStringConstants.NO_USER_FOUND);
        }
        userResponse.setUserCreated(false);
        userResponse.setUser(user);
        return userResponse;
    }

    @Override
    public UserResponse updateGCMToken(User user) {
        UserResponse userResponse;
        User user1 = findUserByEmailIdAndAuthToken(user.getEmailId() , user.getAuthToken());

        if(user1 != null) {
            user1.setGcmToken(user.getGcmToken());
            saveUser(user1);
            userResponse = new UserResponse(MatlbStringConstants.GCM_TOKEN_UPDATED);
        } else {
            userResponse = new UserResponse(MatlbStringConstants.NO_USER_FOUND);
        }
        userResponse.setUserCreated(false);
        userResponse.setUser(user);
        return userResponse;
    }

    @Override
    public FriendsPresentResponse verifyMobileNumbers(FriendsPresentRequest friendsPresentRequest){

        FriendsPresentResponse friendsPresentResponse ;

        User user = findUserByEmailIdAndAuthToken(friendsPresentRequest.getUser().getEmailId() , friendsPresentRequest.getUser().getAuthToken());
        List<Integer> phoneNumbersVerified = new ArrayList<>();

        if(user != null) {
            List<User> usersWithValidPhoneNumbers = getUserDao().findByPhoneNumberIn(friendsPresentRequest.getPhoneNumbers());
            if(usersWithValidPhoneNumbers != null && !usersWithValidPhoneNumbers.isEmpty()) {
                for(User user1 : usersWithValidPhoneNumbers) {
                    phoneNumbersVerified.add(user1.getPhoneNumber());
                }
            }
            friendsPresentResponse = new FriendsPresentResponse(MatlbStringConstants.FRIENDS_FOUND);
            friendsPresentResponse.setPhoneNumberList(phoneNumbersVerified);
        } else {
            friendsPresentResponse = new FriendsPresentResponse(MatlbStringConstants.NO_USER_FOUND);
            friendsPresentResponse.setUser(null);
            friendsPresentResponse.setPhoneNumberList(phoneNumbersVerified);
        }

        return friendsPresentResponse;
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
    public User findByMobileNumber(Integer phoneNumber) {
        return getUserDao().findByPhoneNumber(phoneNumber);
    }

    @Override
    public User findUserByEmailIdAndAuthToken(String emailId, String token) {
        return getUserDao().findByEmailIdAndAuthToken(emailId , token);
    }

    @Override
    public Subscriber findSubscriberByEmail(String email) {
        return getSubscriberDao().findByEmailId(email);
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
