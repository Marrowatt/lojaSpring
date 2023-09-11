//package com.example.Loja.config;
//
//import org.springframework.security.config.Customizer;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebConfig {
//	
//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(
//        		authConf -> {
//        			authConf.requestMatchers("/public").permitAll();
//        			authConf.requestMatchers("/logout").permitAll();
//        			authConf.anyRequest().authenticated();
//        		})
//        		.formLogin(Customizer.withDefaults())
//        		.build();
//    }
//}
