package com.matlb.dao;

import com.matlb.domain.User;
import com.matlb.domain.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by prassingh on 2/10/17.
 */

public interface UserReviewDao extends JpaRepository<UserReview,Integer> {

    List<UserReview> findByStar(int star);
    List<UserReview> findByUser(User user);

}
