package com.everyt.nextea.Repository;

//import io.hypersistence.utils.spring.repository.HibernateRepository; save 메소드가 아니라 update 메소드를 사용할 수 있다. extends에 추가하면.
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everyt.nextea.Entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
