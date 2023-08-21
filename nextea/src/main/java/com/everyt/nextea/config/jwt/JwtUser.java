package com.everyt.nextea.config.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {
	
	private static final long serialVersionUID = 5286916723768180336L;
	
	private final Long id;
	private final String email;
	private final String nickname;
	private final String password;
	private final Boolean activated;
	private final Collection<? extends GrantedAuthority> roles;
	
	public JwtUser(
			Long id,
			String email,
			String nickname,
			String password,
			Boolean activated,
			Collection<? extends GrantedAuthority> roles
	) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.activated = activated;
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	@Override
	public boolean isAccountNonExpired() {return activated;}
	@Override
	public boolean isAccountNonLocked() {return activated;}
	@Override
	public boolean isCredentialsNonExpired() {return activated;}
	@Override
	public boolean isEnabled() {return activated;}
	@Override
	public String getUsername() {return email;}
}
