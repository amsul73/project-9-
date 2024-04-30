package com.movie.demo.member.controller;


import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.SignUpResponse;
import com.movie.demo.member.service.MemberService;
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


}
