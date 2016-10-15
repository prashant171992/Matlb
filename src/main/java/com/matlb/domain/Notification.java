package com.matlb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prassingh on 9/25/16.
 */
public class Notification {

    public Notification(){

    }

    private List<String> registration_ids = new ArrayList<String>();
    private NotificationData data;

    public List<String> getRegistrationIds() {
        return registration_ids;
    }

    public void setRegistrationIds(List<String> registrationIds) {
        this.registration_ids = registrationIds;
    }

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }
}
