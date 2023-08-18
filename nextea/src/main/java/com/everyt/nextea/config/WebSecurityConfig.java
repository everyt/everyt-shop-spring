package com.everyt.nextea.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final CorsFilter corsFilter;
	
	public WebSecurityConfig(CorsFilter corsFilter) {
		this.corsFilter = corsFilter;
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		http
			.csrf(csrf -> csrf.disable())
			
			// CORS 규칙 필터 추가
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			
			// Http 요청에 대한 권한 설정
			.authorizeHttpRequests(requests -> requests
			.requestMatchers(
			                 mvcMatcherBuilder.pattern("/api/v1/hello"),
					         mvcMatcherBuilder.pattern("/api/v1/auth"),
					         mvcMatcherBuilder.pattern("/api/v1/user/signup"),
					         PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated())
			// JWT 필터
			// .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			// enable h2-console
			.headers(headers -> headers.frameOptions(options -> options.sameOrigin())
			);
		
		return http.build();
	}
}
