package com.everyt.nextea.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everyt.nextea.user.UserService;
import com.everyt.nextea.user.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }
	
	@PostMapping("/user/signup")
	public ResponseEntity<UserVo.Response> signUp(@RequestBody UserVo.Request.SignUp userVo) {
		return ResponseEntity.ok().body(userService.signUp(userVo));
	}
	
	@GetMapping("/user/list")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UserVo.Response>> showUserList() {
		return ResponseEntity.ok().body(userService.findAllUser());
	}
	
	@GetMapping("/user")
	@PreAuthorize("hanAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserVo.Response> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithRoles());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserVo.Response> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserWithRoles(email));
    }
}