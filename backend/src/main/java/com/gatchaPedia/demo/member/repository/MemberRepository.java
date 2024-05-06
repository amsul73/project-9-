package com.gatchaPedia.demo.member.repository;

import com.gatchaPedia.demo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


    boolean existsByName(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Member findByUsername(String LoginId);
}