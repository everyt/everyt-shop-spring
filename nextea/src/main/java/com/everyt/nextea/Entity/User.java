package com.everyt.nextea.Entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_tb")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private Long user_id;
	
	@Column(name = "_email", length = 50, unique = true)
	private String userEmail;
	
	@Column(name = "_nickname", length = 50)
	private String userNickname;
	
	@Column(name = "_password", length = 100)
	private String userPassword;
	
	@Column(name = "_appendtime", length = 100)
	private String userAppendTime;
	
	@Column(name = "_updatetime", length = 100)
	private String userUpdateTime;
	
	@ManyToMany
	@JoinTable(
    	name = "user_authority",
    	joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
    	inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
	private Set<Authority> authorities;
	
}
