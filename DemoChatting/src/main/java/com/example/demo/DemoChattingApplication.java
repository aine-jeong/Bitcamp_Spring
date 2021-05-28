package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration  //추가하기
public class DemoChattingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoChattingApplication.class, args);
	}

}
