package com.everyt.nextea.config.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.everyt.nextea.user.User;
import com.everyt.nextea.user.UserRepository;

@Component("userDetailsService")
public class JwtUserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        return userRepository.findOneWithRolesByEmail(email)
            .map(user -> createUser(email, user))
            .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, User user) {
        if (!user.getActivated()) {
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }
 
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
        		.map(role -> new SimpleGrantedAuthority(role.getRoletype()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
    		    passwordEncoder.encode(user.getPassword()),
                grantedAuthorities);
    }
}