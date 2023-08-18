package com.everyt.nextea.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everyt.nextea.config.util.SecurityUtil;
import com.everyt.nextea.exception.CustomException;
import com.everyt.nextea.exception.ErrorCode;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<UserVo.Response> findAllUser() {
		List<UserVo.Response> userList = new ArrayList<>();
		userRepository.findAll().forEach(user -> userList.add(new UserVo.Response(user)));
		return userList;
	}
	
	@Transactional
	public UserVo.Response signUp(UserVo.Request.SignUp userVo) {
		if (userRepository.existsByEmail(userVo.getEmail())) {
			throw new CustomException(ErrorCode.ALREADY_EXIST_USER);
		}
		
		Role role = Role.builder()
				.roletype("ROLE_USER")
				.build();
		
		User user = User.builder()
				.email(userVo.getEmail())
				.nickname(userVo.getNickname())
				.password(userVo.getPassword())
				.roles(Collections.singleton(role))
				.activated(true)
				.build();
		
		return new UserVo.Response(userRepository.save(user));
	}

    @Transactional(readOnly = true)
    public UserVo.Response getUserWithRoles(String email) {
        return new UserVo.Response(userRepository.findOneWithRolesByEmail(email).orElse(null));
    }
    
    @Transactional(readOnly = true)
    public UserVo.Response getMyUserWithRoles() {
        return new UserVo.Response(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithRolesByEmail)
                        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND))
        );
    }
}
