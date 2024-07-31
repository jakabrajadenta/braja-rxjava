package com.braja.rxjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RxJavaWithSocketConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxJavaWithSocketConnectionApplication.class, args);
		log.info("APPLICATION STARTED");
	}

}
