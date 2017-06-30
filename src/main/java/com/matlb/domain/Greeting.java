package com.matlb.domain;

/**
 * Created by prassingh on 3/14/16.
 */
public class Greeting {
    private final Long id;
    private final String content;


    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
