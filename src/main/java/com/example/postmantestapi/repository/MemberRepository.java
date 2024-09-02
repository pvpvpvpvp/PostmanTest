package com.example.postmantestapi.repository;

import com.example.postmantestapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MemberRepository extends JpaRepository<Member, Long> {
    Member findOneById(Long id);
}
