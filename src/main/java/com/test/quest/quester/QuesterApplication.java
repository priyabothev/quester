package com.test.quest.quester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan({"com.test.quest"})
@EnableJpaRepositories("com.test.quest")
@EntityScan("com.test.quest")
public class QuesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesterApplication.class, args);
	}

}
