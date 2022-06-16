package com.example.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ConfigSecurity {
	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterDosen(HttpSecurity http) throws Exception {
		// disini saya menambahkan authentikasi terhadap routing
		// dosen dan matakuliah
		http
		.authorizeRequests(authorize -> authorize
			.mvcMatchers("/student/**").permitAll()
			.anyRequest().hasRole("ADMIN")
		)
		.formLogin()
		.and()
		.httpBasic();
		return http.build();
	}

	@Bean
	CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
}