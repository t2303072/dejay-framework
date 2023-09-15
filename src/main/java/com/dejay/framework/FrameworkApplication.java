package com.dejay.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@Slf4j
@SpringBootApplication
public class FrameworkApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FrameworkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}

}
