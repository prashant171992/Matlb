package com.matlb.dao;

import com.matlb.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * Created by prassingh on 8/19/16.
 */
public interface PollDao extends JpaRepository<Poll, Integer> {

    @Query("SELECT p from Poll p JOIN p.pollAnswers where p.asker = :user")
    Page<Poll> findByAskerAndFetchPollAnswersEagerly(@Param("user") User asker , Pageable pageRequest);

    Page<Poll> findByAskerAndGenreOrderByCreateDtDesc(User asker , GenreType genre , Pageable pageRequest);

    Page<Poll> findByAskerAndStatus(User asker , StatusType status, Pageable pageRequest);

    Page<Poll> findByAsker(User asker, Pageable pageRequest);

    @Query(value = "SELECT p FROM Poll p WHERE p.pollOpenForAll = :pollOpenForAll AND p.status = :status \n#pageable\n",
            countQuery = "SELECT count(p) FROM Poll p WHERE p.pollOpenForAll = :pollOpenForAll AND p.status = :status")
    Page<Poll> findByPollOpenForAllAndStatus(@Param("pollOpenForAll") int pollOpenForAll , @Param("status") StatusType status , Pageable pageRequest);

    @Query(value = "SELECT p FROM Poll p WHERE p.pollOpenForAll = :pollOpenForAll AND p.status = :status AND p.pollCategory = :pollCategoryEnum \n#pageable\n",
            countQuery = "SELECT count(p) FROM Poll p WHERE p.pollOpenForAll = :pollOpenForAll AND p.status = :status AND p.pollCategory = :pollCategoryEnum")
    Page<Poll> findByPollOpenForAllAndStatusAndPollCategory(@Param("pollOpenForAll") int pollOpenForAll , @Param("status") StatusType status , @Param("pollCategoryEnum") PollCategoryEnum pollCategoryEnum,  Pageable pageRequest);

    //Page<Poll> findByAskerAndStillValid(User asker, Pageable pageRequest);

}
