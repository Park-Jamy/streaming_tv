package com.tv.streaming_tv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//scanBasePackages = {"com.tv.streaming_tv"})
//@ComponentScan(basePackages = {"com.tv.streaming_tv.service"})
public class StreamingTvApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingTvApplication.class, args);
	}

}
