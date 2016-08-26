package com.matlb.service;

import com.matlb.domain.*;

import java.util.List;

/**
 * Created by prassingh on 8/21/16.
 */
public interface PollService {

    public BasePollResponse getPollsCreatedByUser(User userParam , int pageNum);

    public BasePollResponse getPollAnsweredByUser(User user , int pageNum);

    public BasePollResponse getPollAskedToByUser(User user , int pollId , int pageNum);

    public BasePollResponse getPollToBeShownByUser(User user , int pageNum);

}
