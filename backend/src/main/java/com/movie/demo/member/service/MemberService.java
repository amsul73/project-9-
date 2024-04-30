package com.movie.demo.member.service;

import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.SignUpResponse;

public interface MemberService {

    SignUpResponse signup(SignUpRequest signUpRequest);
}
