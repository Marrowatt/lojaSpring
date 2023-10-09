package com.example.Loja.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig {
	
	@Autowired
	private UserDetailsService uds;
	
//	@Autowired
//	private LoginSuccessHandler successHandler;
	
	@Bean
	AuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider prov = new DaoAuthenticationProvider();
		
		prov.setUserDetailsService(uds);
		
		prov.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return prov;
	}

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(
				authConf -> {
					authConf.requestMatchers("/login").permitAll();
					authConf.requestMatchers("/").permitAll();
        			authConf.requestMatchers("/produto").hasAuthority("admin");
        			authConf.anyRequest().authenticated();
        		})
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/marca"))
				.logout(logout -> logout.logoutSuccessUrl("/"))
				.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer () throws Exception {

		return (web) -> web.ignoring().requestMatchers("/img/**", "/js/**", "/css/**", "/vendor/**");
	}
}
