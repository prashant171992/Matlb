package com.matlb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prassingh on 9/25/16.
 */
public class Notification {

    public Notification(){

    }

    private List<String> registrationIds = new ArrayList<String>();
    private NotificationData data;

    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(List<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }
}
