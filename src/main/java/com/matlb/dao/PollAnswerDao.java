package com.matlb.dao;

import com.matlb.domain.Poll;
import com.matlb.domain.PollAnswer;
import com.matlb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by prassingh on 8/19/16.
 */
public interface PollAnswerDao extends JpaRepository<PollAnswer, Integer> {

    PollAnswer findByPoll(Poll poll);
    Page<PollAnswer> findByAnswerer(User user , Pageable pageRequest);

}
