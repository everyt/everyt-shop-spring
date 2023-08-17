package com.everyt.nextea.Dto;

import com.everyt.nextea.Entity.Authority;
import com.everyt.nextea.Entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private long id;
	private String email;
	private String nickname;
	private String password;
	private Authority authority;
	
	@Builder
	public MemberDto(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.password = member.getPassword();
		this.authority = member.getAuthority();
	}
	
	public Member toEntity() {
		return Member.builder().email(email).nickname(nickname).password(password).authority(authority).build();
	}

}
