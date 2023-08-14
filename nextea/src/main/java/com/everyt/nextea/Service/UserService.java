package com.everyt.nextea.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everyt.nextea.Entity.User;
import com.everyt.nextea.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(user -> users.add(user));
		return users;
	}
	
	public Optional<User> findById(Long userPid) {
		Optional<User> user = userRepository.findById(userPid);
		return user;
	}
	
	public void deleteById(Long userPid) {
		userRepository.deleteById(userPid);
	}
	
	public User save(User user) {
		userRepository.save(user);
		return user;
	}
	
	public void updateById(Long userPid, User updatedUser) {
		Optional<User> user = userRepository.findById(userPid);
		if (user.isPresent()) {
			user.get().setUserEmail(updatedUser.getUserEmail());
			user.get().setUserNickname(updatedUser.getUserNickname());
			user.get().setUserPassword(updatedUser.getUserPassword());
			user.get().setUserUpdateTime(updatedUser.getUserUpdateTime());
			userRepository.save(user.get());
		}
		
	}
}
