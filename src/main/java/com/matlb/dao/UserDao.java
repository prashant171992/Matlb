package com.matlb.dao;

/**
 * Created by prassingh on 3/21/16.
 */

import com.matlb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer>{

    User findByEmailId(String email);
}
