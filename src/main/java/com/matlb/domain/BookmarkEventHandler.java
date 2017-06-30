package com.matlb.domain;

import com.matlb.rabbitMq.TaskMessage;
import com.matlb.rabbitMq.TaskProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Date;

/**
 * Created by prassingh on 6/29/16.
 */

@RepositoryEventHandler(Bookmark.class)
public class BookmarkEventHandler {

    @Autowired
    private TaskProducer taskProducer;

    @HandleBeforeCreate
    public void handleBookmarkCreate(Bookmark bookmark)
    {
        bookmark.setCreateDt(new Date());
    }

    @HandleAfterCreate
    public void handleAfterBookmarkCreate(Bookmark bookmark)
    {
        final TaskMessage taskMessage = new TaskMessage();
        taskMessage.setUrl(bookmark.getUrl());
        taskProducer.sendNewTask(taskMessage);
    }


}
