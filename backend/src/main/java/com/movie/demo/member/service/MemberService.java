package com.movie.demo.member.service;

import com.movie.demo.member.entity.Member;
import com.movie.demo.member.request.LoginRequest;
import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.LoginResponse;
import com.movie.demo.member.response.SignUpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

    SignUpResponse signup(SignUpRequest signUpRequest);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);
}
