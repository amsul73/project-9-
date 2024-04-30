package com.gatchaPedia.demo.member.controller;


import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.LoginResponse;
import com.gatchaPedia.demo.member.response.SignUpResponse;
import com.gatchaPedia.demo.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public SignUpResponse signup(@RequestBody SignUpRequest signUpRequest){

        return memberService.signup(signUpRequest);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest,
                               HttpServletRequest request){


        return memberService.login(loginRequest,request);
    }

}
