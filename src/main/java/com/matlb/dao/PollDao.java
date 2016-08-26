package com.matlb.dao;

import com.matlb.domain.GenreType;
import com.matlb.domain.Poll;
import com.matlb.domain.StatusType;
import com.matlb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by prassingh on 8/19/16.
 */
public interface PollDao extends JpaRepository<Poll, Integer> {

    @Query("SELECT poll from Poll poll JOIN poll.pollAnswers where poll.asker = :user")
    Page<Poll> findByAskerAndFetchPollAnswersEagerly(@Param("user") User asker , Pageable pageRequest);

    Page<Poll> findByAskerAndGenre(User asker , GenreType genre , Pageable pageRequest);

    Page<Poll> findByAskerAndStatus(User asker , StatusType status, Pageable pageRequest);

    Poll findById(int pollId);

    //Page<Poll> findByAskerAndStillValid(User asker, Pageable pageRequest);

}
