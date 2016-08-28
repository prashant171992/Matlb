package com.matlb.dao;

import com.matlb.domain.Poll;
import com.matlb.domain.QuestionAsked;
import com.matlb.domain.StatusType;
import com.matlb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by prassingh on 8/19/16.
 */
public interface QuestionAskedDao extends JpaRepository<QuestionAsked , Integer> {

    List<QuestionAsked> findByPollAndAsker(Poll poll , User asker);
    Page<QuestionAsked> findByPollAndAsker(Poll poll , User asker , Pageable pageRequest);
    Page<QuestionAsked> findByAnswererAndStatus (User user , StatusType status, Pageable pageRequest);
    List<QuestionAsked> findByPollAndAskerAndStatus(Poll poll , User asker , int status);
    QuestionAsked findByPollAndAnswererAndStatus(Poll poll , User answerer , StatusType status);

}
