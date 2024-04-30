package com.movie.demo.member.controller;


import com.movie.demo.member.request.LoginRequest;
import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.LoginResponse;
import com.movie.demo.member.response.SignUpResponse;
import com.movie.demo.member.service.MemberService;
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
