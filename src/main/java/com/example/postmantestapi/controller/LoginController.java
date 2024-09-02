package com.example.postmantestapi.controller;

import com.example.postmantestapi.entity.Member;
import com.example.postmantestapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberservice;

    @PostMapping("/login")
    public ResponseEntity<String> login(Member member){
        Member login = memberservice.login(member);
        return new ResponseEntity<>(login.getName(),HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(Member member){
        Member result = memberservice.join(member);
        return new ResponseEntity<>(result.getId().toString(),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(Member member){
        Member result = memberservice.join(member);
        return new ResponseEntity<>(result.getId().toString(),HttpStatus.OK);
    }

    @PostMapping("/leave")
    public ResponseEntity<String> leave(Member member){
        Member result = memberservice.join(member);
        return new ResponseEntity<>(result.getId().toString(),HttpStatus.OK);
    }
}
