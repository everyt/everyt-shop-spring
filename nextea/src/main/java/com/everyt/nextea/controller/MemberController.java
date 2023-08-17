package com.everyt.nextea.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everyt.nextea.Dto.MemberDto;
import com.everyt.nextea.Service.MemberService;

@Controller
@RequestMapping("/api/v1")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/user")
	public ResponseEntity<List<MemberDto>> findAll() {
		return ResponseEntity.ok().body(memberService.findAll());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<MemberDto> findByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok().body(memberService.findByEmail(email));
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Long> update(@RequestBody MemberDto memberDto) {
		return ResponseEntity.ok().body(memberService.update(memberDto));
	}
	
	@PostMapping("/user/sign-up")
	public ResponseEntity<Long> save(@RequestBody MemberDto memberDto) {
		return ResponseEntity.ok().body(memberService.save(memberDto));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Long> findByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
		return ResponseEntity.ok().body(memberService.findByEmailAndPassword(email, password));
	}

}
