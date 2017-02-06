package com.matlb.dao;

import com.matlb.domain.Multimedia;
import com.matlb.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by prassingh on 1/13/17.
 */

@RepositoryRestResource
public interface MultimediaDao extends JpaRepository<Multimedia,Integer> {

    Multimedia findByPoll(Poll poll);
}
