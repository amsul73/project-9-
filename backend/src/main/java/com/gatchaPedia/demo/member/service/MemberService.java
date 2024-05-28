package com.gatchaPedia.demo.member.service;

import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.*;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {

    SignUpResponse signup(SignUpRequest signUpRequest);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);

    LogoutResponse logout(HttpServletRequest request);

    SignoutResponse signout(HttpServletRequest request);

    InvaildSessionCheckResponse sessionCheck(HttpServletRequest request);
}
