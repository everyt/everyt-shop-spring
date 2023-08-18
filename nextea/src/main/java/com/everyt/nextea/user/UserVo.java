package com.everyt.nextea.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserVo {
	
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private Long id;
		private String email;
		private String nickname;
		private String password;
		private Boolean activated;
		private Set<Role> roles;
		
		public Response(User user) {
			this.id = user.getId();
			this.email = user.getEmail();
			this.nickname = user.getNickname();
			this.password = user.getPassword();
			this.activated = user.getActivated();
			this.roles = user.getRoles();
		}
	}
	public static class Request {
		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Login {
			private String email;
			private String password;
		}
		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class SignUp {
			private String email;
			private String nickname;
			private String password;
			public User toUser() {
				return User.builder().email(email).nickname(nickname).password(password).activated(true).build();
			}
		}
	}
}
