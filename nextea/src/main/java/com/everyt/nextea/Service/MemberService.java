package com.everyt.nextea.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everyt.nextea.Dto.MemberDto;
import com.everyt.nextea.Entity.Member;
import com.everyt.nextea.Repository.MemberRepository;

//DTO -> Entity 변환 로직은 Service, 비지니스 로직에서 수행되어야 합니다.
//Controller에서는 직접적인 값을 클라이언트에게서 입력받고, 그 값을 Service가 입력받아서 DTO로 변환해줘야 합니다.
@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<MemberDto> findAll() {
		List<MemberDto> members = new ArrayList<>();
		memberRepository.findAll().forEach(member -> members.add(new MemberDto(member)));
		return members;
	}
	
	public MemberDto findByEmail(String email) {
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. email: "+email));
		return new MemberDto(member);
	}
	
	public String update(MemberDto memberDto) {
		String email = memberDto.getEmail();
		Member member = memberRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. email: "+email));
		member.update(memberDto.getEmail(), memberDto.getNickname(), memberDto.getPassword(), memberDto.getAuthority());
		return email;
	}
	
	public String save(MemberDto memberDto) {
		return memberRepository.save(memberDto.toEntity()).getEmail();
	}
}
