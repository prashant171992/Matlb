package com.matlb.dao;

import com.matlb.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by prassingh on 8/19/16.
 */
public interface PollDao extends JpaRepository<Poll, Integer> {

    @Query("SELECT poll from Poll poll JOIN poll.pollAnswers where poll.asker = :user")
    Page<Poll> findByAskerAndFetchPollAnswersEagerly(@Param("user") User asker , Pageable pageRequest);

    Page<Poll> findByAskerAndGenreOrderByCreateDtDesc(User asker , GenreType genre , Pageable pageRequest);

    Page<Poll> findByAskerAndStatus(User asker , StatusType status, Pageable pageRequest);

    Page<Poll> findByAsker(User asker, Pageable pageRequest);

    @Query(value = "SELECT poll FROM Poll poll WHERE poll.pollOpenForAll = :pollOpenForAll AND poll.status = :status",
            countQuery = "SELECT count(poll) FROM Poll poll WHERE poll.pollOpenForAll = :pollOpenForAll AND poll.status = :status",
            nativeQuery = true)
    Page<Poll> findByPollOpenForAllAndStatus(int pollOpenForAll , StatusType status , Pageable pageRequest);

    @Query(value = "SELECT poll FROM Poll poll WHERE poll.pollOpenForAll = :pollOpenForAll AND poll.status = :status AND poll.pollCategory = :pollCategoryEnum",
            countQuery = "SELECT count(poll) FROM Poll poll WHERE poll.pollOpenForAll = :pollOpenForAll AND poll.status = :status AND poll.pollCategory = :pollCategoryEnum",
            nativeQuery = true)
    Page<Poll> findByPollOpenForAllAndStatusAndPollCategory(int pollOpenForAll , StatusType status , PollCategoryEnum pollCategoryEnum,  Pageable pageRequest);

    //Page<Poll> findByAskerAndStillValid(User asker, Pageable pageRequest);

}
