package com.everyt.nextea.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
	
	@Autowired
	private CorsFilter corsFilter;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		http
			.csrf(csrf -> {
				CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
				cookieCsrfTokenRepository.setCookieCustomizer(cookie -> {
					cookie.sameSite("None");
					cookie.secure(true);
					cookie.httpOnly(false);
				});
				csrf.csrfTokenRepository(cookieCsrfTokenRepository);
			})
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
			.requestMatchers(
					mvcMatcherBuilder.pattern("/api/csrf"),
					mvcMatcherBuilder.pattern("/api/user/**")
					).permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()
			)
            .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .headers(headers ->
	            headers.frameOptions(options ->
	                options.sameOrigin()
	            )
            );
		
		return http.build();
	}
}
