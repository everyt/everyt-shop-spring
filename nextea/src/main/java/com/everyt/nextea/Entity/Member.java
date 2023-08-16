package com.everyt.nextea.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "member_tb")
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
    private Long id;
	
	@Column(name = "_email")
	private String email;
	
	@Column(name = "_nickname")
	private String nickname;
	
	@Column(name = "_password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Authority authority;
	
	public void setEmail(String email) {this.email = email;};
	public void setNickname(String nickname) {this.nickname = nickname;};
	public void setPassword(String password) {this.password = password;};
	
	@Builder
	public Member(Long id, String email, String nickname, String password, Authority authority) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.authority = authority;
	}
	
	public void update(String email, String nickname, String password, Authority authority) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.authority = authority;
	}
	
}
