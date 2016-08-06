package com.matlb.rabbitMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by prassingh on 6/27/16.
 */
@Component
public class TaskProducer {
    @Autowired
    private TaskProducerConfiguration taskProducerConfiguration;

    public void sendNewTask(TaskMessage taskMessage)
    {
        taskProducerConfiguration.rabbitTemplate().convertAndSend(taskProducerConfiguration.tasksQueue, taskMessage);
    }
}
