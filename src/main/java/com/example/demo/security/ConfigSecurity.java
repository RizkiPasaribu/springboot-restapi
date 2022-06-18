package com.example.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ConfigSecurity {
	//configuration untuk encode password
	//https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-configuration
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// anotsi order bertujuan untuk pendefinisian lebih awal atau bisa dibilang
	// bean yang pertama dibuat adalah ini
	//https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#_multiple_httpsecurity
	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterDosen(HttpSecurity http) throws Exception {
		// disini saya menambahkan authentikasi terhadap routing
		// dosen dan matakuliah
		http
		.authorizeRequests(authorize -> authorize
		.mvcMatchers("/user/**").permitAll()
			.mvcMatchers("/student/**").hasRole("USER")
			.anyRequest().hasRole("ADMIN")
		)
		.formLogin()
		.and()
		.httpBasic()
		.and()
		.csrf(csrf -> csrf.disable());
		return http.build();
		// disini saya mematikan csrf karena saya memakai insomnia dan tidak bisa passing kode csrf ke form json nya
	}

	// create custom user detail service karena disini kita menggunakan auth yang terintegrasi dengan database
	// bukan auth yang disimpan di dalam memori (inmomori)
	// https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html#servlet-authentication-userdetailsservice
	@Bean
	CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
}