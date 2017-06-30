package com.matlb.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by prassingh on 2/16/17.
 */
@Configuration
public class ApplicationConfigAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/poller/","/poller");
        registry.addViewController("/poller").setViewName("poller/poller.html");
        registry.addViewController("/").setViewName("index");
    }
}
