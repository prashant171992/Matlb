package com.matlb.dao;

import com.matlb.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by prassingh on 6/29/16.
 */

@RepositoryRestResource
public interface BookmarkDao extends JpaRepository<Bookmark,Integer> {

    @RestResource(path = "url")
    List<Bookmark> findByUrl(@Param("text") String url);
}
