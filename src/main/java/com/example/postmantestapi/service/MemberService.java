package com.example.postmantestapi.service;

import com.example.postmantestapi.entity.Member;
import com.example.postmantestapi.response.Response;

public interface MemberService {

    Response<String> login(Member member);

    Response<String> join(Member member);

    Response<String> update(Member member);

    Response<String> leave(Member member);
}
