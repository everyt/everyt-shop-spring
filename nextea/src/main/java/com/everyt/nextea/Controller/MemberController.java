package com.everyt.nextea.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberservice;
	
	@PostMapping("/login")
	public MemberResponseDto login(@RequestBody final MemberRequestDto params) {
		MemberRequestDto entity = memberservice.findBy(params);
		return entity;
	}
}
