package com.example.postmantestapi.controller;

import com.example.postmantestapi.entity.Member;
import com.example.postmantestapi.response.Response;
import com.example.postmantestapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.postmantestapi.filter.AESCrypto.decrypt;
import static com.example.postmantestapi.filter.EncryptionFilter.decryptRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody Member member) {
        Response<String> response = memberService.login(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Response<String>> join(@RequestBody Member member) {
        Response<String> response = memberService.join(member);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/update")
    public ResponseEntity<Response<String>> update(@RequestBody Member member) {
        Response<String> response = memberService.update(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Response<String>> leave(@RequestBody Member member) {
        Response<String> response = memberService.leave(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/echo")
    public ResponseEntity<Response<String>> echo(@RequestBody String String) {
        Response<String> response = new Response<>("0000", "성공", decryptRequest(String));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
