package com.dejay.framework;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootApplication
public class FrameworkApplication implements ApplicationRunner {
	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
        Connection connection = dataSource.getConnection();
		log.info("DBCP: {}", dataSource.getClass());
        log.info("Url: {}", connection.getMetaData().getURL());
        log.info("UserName: {}", connection.getMetaData().getUserName());
	}
}
