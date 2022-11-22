package com.dd.alcohol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.persistence.EntityListeners;


@EnableJpaAuditing
@SpringBootApplication
public class AlcoholApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlcoholApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
