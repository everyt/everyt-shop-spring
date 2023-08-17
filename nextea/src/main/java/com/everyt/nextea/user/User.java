package com.everyt.nextea.user;

import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "EMAIL", length = 50, unique = true)
	@Nonnull
	private String email;
	
	@Column(name = "NICKNAME")
	@Nonnull
	private String nickname;
	
	@Column(name = "PASSWORD")
	@Nonnull
	private String password;
	
	@Column(name = "ENABLED")
	@Nonnull
	private Boolean enabled;
	
	@Nonnull
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "USER_ROLE",
			joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
			inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
	private List<Role> roles;
}
