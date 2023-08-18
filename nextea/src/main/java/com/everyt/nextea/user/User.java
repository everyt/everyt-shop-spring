package com.everyt.nextea.user;

import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class User {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "EMAIL", length = 50, unique = true)
	@Nonnull
	private String email;
	
	@Column(name = "NICKNAME", length = 50, unique = false)
	@Nonnull
	private String nickname;
	
	@Column(name = "PASSWORD", length = 50, unique = false)
	@Nonnull
	private String password;
	
	@Column(name = "ACTIVATED", length = 1, unique = false)
	@Nonnull
	private Boolean activated;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "USER_ROLE",
			joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
			inverseJoinColumns = {@JoinColumn(name = "ROLE_TYPE", referencedColumnName = "ROLE_TYPE")})
	private Set<Role> roles;
}
