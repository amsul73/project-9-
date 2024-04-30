package com.gatchaPedia.demo.member.service;

import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.LoginResponse;
import com.gatchaPedia.demo.member.response.LogoutResponse;
import com.gatchaPedia.demo.member.response.SignUpResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {

    SignUpResponse signup(SignUpRequest signUpRequest);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);

    LogoutResponse logout(HttpServletRequest request);
}
