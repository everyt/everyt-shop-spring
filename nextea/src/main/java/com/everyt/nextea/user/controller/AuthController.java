package com.everyt.nextea.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everyt.nextea.config.jwt.JwtFilter;
import com.everyt.nextea.config.jwt.JwtTokenProvider;
import com.everyt.nextea.config.jwt.JwtTokenVo;
import com.everyt.nextea.user.UserVo;

@Controller
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@PostMapping("/auth")
	public ResponseEntity<JwtTokenVo> authorize(@RequestBody UserVo.Request.Login userVo) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userVo.getEmail(), userVo.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtTokenVo(jwt), httpHeaders, HttpStatus.OK);
	}

}
