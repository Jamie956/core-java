package com.jamie.component;

import com.jamie.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


	@Bean
	public User user() {
		return new User();
	}
}
