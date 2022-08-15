package com.dayone.web;

import com.dayone.model.Auth;
import com.dayone.model.MemberEntity;
import com.dayone.security.TokenProvider;
import com.dayone.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request){
        //회원가입을 위한 API
        var result = this.memberService.register(request);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request){
        //로그인을 위한 API
        //1. signin에서 입력받은 아이디(username)와 패스워드가 일치하는지 확인업 -> MemberService의 authenticate 메서드
        //2. TokenProvider 클래스의 generateToken 메서드로 토큰 생성해서 반환

        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());

        return ResponseEntity.ok(token);
    }

}
