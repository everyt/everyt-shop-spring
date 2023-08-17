package com.everyt.nextea.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/list")
	public ResponseEntity<List<UserVo.Response>> showUserList() {
		return ResponseEntity.ok().body(userService.findAllUser());
	}

}
