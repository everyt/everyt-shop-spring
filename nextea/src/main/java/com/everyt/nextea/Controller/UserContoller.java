package com.everyt.nextea.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everyt.nextea.Entity.User;
import com.everyt.nextea.Service.UserService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserContoller {
	
	@Autowired
	UserService userService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{userPid}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<User> getUser(@PathVariable("userPid") Long userPid) {
		Optional<User> user = userService.findById(userPid);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{userPid}", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<Void> deleteUser(@PathVariable("userPid") Long userPid) {
        userService.deleteById(userPid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
    @PutMapping(value = "/{userPid}", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<User> updateMember(@PathVariable("userPid") Long userPid, User user) {
        userService.updateById(userPid, user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    @PostMapping("/sign-up")
    public ResponseEntity<User> save(User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }

}
