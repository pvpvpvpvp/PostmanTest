package com.example.postmantestapi.service;

import com.example.postmantestapi.entity.Member;
import com.example.postmantestapi.repository.MemberRepository;
import com.example.postmantestapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Response<String> login(Member member) {
        Member foundMember = memberRepository.findOneByNickName(member.getNickName());
        if (foundMember == null) {
            return new Response<>("0001", "회원 정보가 없습니다.", null);
        }
        if (!member.getPassword().equals(foundMember.getPassword())) {
            return new Response<>("0002", "비밀번호가 맞지 않습니다.", null);
        }
        return new Response<>("0000", "로그인 성공", foundMember.getName());
    }

    @Override
    public Response<String> join(Member member) {
        if (memberRepository.existsByNickName(member.getNickName())) {
            return new Response<>("0003", "닉네임이 이미 존재합니다.", null);
        }
        memberRepository.save(member);
        return new Response<>("0000", "회원가입 성공", null);
    }

    @Override
    public Response<String> update(Member member) {
        Member foundMember = memberRepository.findOneById(member.getId());
        if (foundMember == null) {
            return new Response<>("0001", "회원 정보가 없습니다.", null);
        }
        if (memberRepository.existsByNickName(member.getNickName())) {
            return new Response<>("0003", "닉네임이 이미 존재합니다.", null);
        }
        foundMember.setName(member.getName());
        foundMember.setPassword(member.getPassword());
        foundMember.setNickName(member.getNickName());
        memberRepository.save(foundMember);
        return new Response<>("0000", "비밀번호 변경 성공", null);
    }

    @Override
    public Response<String> leave(Member member) {
        Member foundMember = memberRepository.findOneById(member.getId());
        if (foundMember == null) {
            return new Response<>("0001", "회원 정보가 없습니다.", null);
        }
        memberRepository.deleteById(member.getId());
        return new Response<>("0000", "회원 탈퇴 성공", null);
    }
}
