package com.everyt.nextea.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everyt.nextea.Dto.MemberDto;
import com.everyt.nextea.Service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<MemberDto> findById(@PathVariable String email) {
		return ResponseEntity.ok().body(memberService.findByEmail(email));
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> update(@RequestBody MemberDto memberDto) {
		return ResponseEntity.ok().body(memberService.update(memberDto));
	}
	
	@PostMapping("/user/sign-up")
	public ResponseEntity<String> save(@RequestBody MemberDto memberDto) {
		return ResponseEntity.ok().body(memberService.save(memberDto));
	}

}
