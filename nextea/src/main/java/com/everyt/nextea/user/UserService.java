package com.everyt.nextea.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<UserVo.Response> findAllUser() {
		List<UserVo.Response> userList = new ArrayList<>();
		userRepository.findAll().forEach(user -> userList.add(new UserVo.Response(user)));
		return userList;
	}
	public UserVo.Response findOneUserByEmail(UserVo.Request.Find.Email userVo) {
		User user = userRepository.findByEmail(userVo.getEmail()).orElseThrow(() -> new IllegalArgumentException("isNotExist: "+userVo.getEmail()));
		return new UserVo.Response(user);
	}
	@Transactional
	public Long Register(UserVo.Request.Register userVo) {
		return userRepository.save(userVo.toUser()).getId();
	} //TODO: ID와 ROLE이 어떻게 저장되는지 확인해봐야 해
	@Transactional
	public Long Update(UserVo.Request.Update userVo) {
		return userVo.toUser().getId();
	}
	public Long Login(UserVo.Request.Login userVo) {
		User user = userRepository.findByEmailAndPassword(userVo.getEmail(), userVo.getPassword()).orElseThrow(() -> new IllegalArgumentException("isNotExist: "+userVo.getEmail()));
		return user.getId();
	}
}
