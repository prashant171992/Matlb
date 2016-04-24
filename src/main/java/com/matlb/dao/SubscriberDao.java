package com.matlb.dao;

import com.matlb.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by prassingh on 4/10/16.
 */
public interface SubscriberDao extends JpaRepository<Subscriber,Integer> {
    Subscriber findByEmailId(String email);
}
