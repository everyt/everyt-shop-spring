package com.everyt.nextea.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserVo {
	@Getter
	public static class Response {
		private Long id;
		private String email;
		private String nickname;
		private String password;
		private Boolean enabled;
		private List<Role> roles;
		public Response(User user) {
			this.id = user.getId();
			this.email = user.getEmail();
			this.nickname = user.getNickname();
			this.password = user.getPassword();
			this.enabled = user.getEnabled();
			this.roles = user.getRoles();
		}
	}
	public static class Request {
		public static class Find {
			@Getter
			@AllArgsConstructor
			public static class Email {
				private String email;
			}
			@Getter
			@AllArgsConstructor
			public static class Password {
				private String password;
			}
		}
		@Getter
		@AllArgsConstructor
		public static class Login {
			private String email;
			private String password;
		}
		@Getter
		@AllArgsConstructor
		public static class Register {
			private String email;
			private String nickname;
			private String password;
			public User toUser() {
				return User.builder().email(email).nickname(nickname).password(password).enabled(true).build();
			}
		}
		@Getter
		@AllArgsConstructor
		public static class Update {
			private Long id;
			private String email;
			private String nickname;
			private String password;
			public User toUser() {
				return User.builder().id(id).email(email).nickname(nickname).password(password).enabled(true).build();
			}
		}
	}
}
