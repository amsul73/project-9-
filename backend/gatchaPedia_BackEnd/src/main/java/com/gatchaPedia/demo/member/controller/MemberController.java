package com.gatchaPedia.demo.member.controller;

import com.gatchaPedia.demo.member.exception.LoginInputInvalidException;
import com.gatchaPedia.demo.member.exception.SignUpInvalidInputException;
import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.LoginResponse;
import com.gatchaPedia.demo.member.response.SignUpResponse;
import com.gatchaPedia.demo.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public SignUpResponse signup(@Validated @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()){

                if(error.getField().equals("name")) errorMessages.add(error.getDefaultMessage());
                else if(error.getField().equals("username")) errorMessages.add(error.getDefaultMessage());
                else if(error.getField().equals("password")) errorMessages.add(error.getDefaultMessage());
                else if(error.getField().equals("email")) errorMessages.add(error.getDefaultMessage());
            }

            throw new SignUpInvalidInputException(errorMessages);
        }

        return memberService.signup(signUpRequest);
    }


    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()) throw new LoginInputInvalidException();

        return memberService.login(loginRequest,request);
    }




}
