package com.example.postmantestapi.repository;

import com.example.postmantestapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findOneByNickName(String NickName);

    Member findOneById(Long NickName);
    boolean existsByNickName(String name);
}
