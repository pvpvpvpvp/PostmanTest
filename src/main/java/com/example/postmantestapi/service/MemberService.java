package com.example.postmantestapi.service;

import com.example.postmantestapi.entity.Member;

public interface MemberService {

    public Member login(Member id);

    public Member join(Member member);

    public Member update(Member member);

    public Member leave(Member member);
}
