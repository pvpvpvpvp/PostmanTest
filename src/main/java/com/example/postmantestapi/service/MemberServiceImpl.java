package com.example.postmantestapi.service;

import com.example.postmantestapi.entity.Member;
import com.example.postmantestapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(Member member) {
        Long id = member.getId();
        Member byId = memberRepository.findOneById(id);
        return byId;
    }

    @Override
    public Member join(Member member) {
        Member save = memberRepository.save(member);
        return save;
    }

    @Override
    public Member update(Member member) {
        Member byId = memberRepository.findOneById(member.getId());
        return memberRepository.save(byId);
    }

    @Override
    public Member leave(Member member) {
        memberRepository.deleteById(member.getId());
        return member;
    }

}
