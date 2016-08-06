package com.matlb;

import com.matlb.controller.CORSFilter;
import com.matlb.domain.BookmarkEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatlbApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MatlbApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MatlbApplication.class, args);
	}

	@Bean
	BookmarkEventHandler bookmarkEventHandler() {
		return new BookmarkEventHandler();
	}

	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CORSFilter());
		return registrationBean;
	}
}
