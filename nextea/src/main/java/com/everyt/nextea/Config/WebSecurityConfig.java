package com.everyt.nextea.Config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc
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
			//csrf 토큰 보유시 통과, /api/csrf로 토큰 발급을 요구 가능
			//.csrf(csrf -> {
			//	CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
			//	cookieCsrfTokenRepository.setCookieCustomizer(cookie -> {
			//		cookie.sameSite("None");
			//		cookie.secure(true);
			//		cookie.httpOnly(false);
			//	});
			//	csrf.csrfTokenRepository(cookieCsrfTokenRepository);
			//})
			
			// CORS 규칙 필터 추가
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			
			// Http 요청에 대한 권한 설정
			.authorizeHttpRequests(requests -> requests
			.requestMatchers(mvcMatcherBuilder.pattern("/api/csrf"),
					         mvcMatcherBuilder.pattern("/api/user/**")).permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()
			);
		
		return http.build();
	}
}
