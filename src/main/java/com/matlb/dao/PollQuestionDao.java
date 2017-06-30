package com.matlb.dao;

import com.matlb.domain.Poll;
import com.matlb.domain.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by prassingh on 8/19/16.
 */
public interface PollQuestionDao extends JpaRepository<PollQuestion, Integer> {

    PollQuestion findByPoll(Poll poll);

}
