package com.everyt.nextea.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everyt.nextea.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{}
