package com.gatchaPedia.demo.handler;


import com.gatchaPedia.demo.member.exception.LoginInputInvalidException;
import com.gatchaPedia.demo.member.exception.MemberAuthException;
import com.gatchaPedia.demo.member.exception.MemberUsernameNotExistException;
import com.gatchaPedia.demo.member.exception.PasswordMissMatchException;
import com.gatchaPedia.demo.member.response.LoginFailResponse;
import com.gatchaPedia.demo.member.response.MemberAuthFailResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = Controller.class)
public class LoginExcepitonManager {



    @ExceptionHandler(MemberUsernameNotExistException.class)
    public LoginFailResponse memberUsernameNotExistExceptionHandle(){
        return new LoginFailResponse(false,1004L,"아이디와 비밀번호를 다시 확인해 주세요");
    }



    @ExceptionHandler(PasswordMissMatchException.class)
    public LoginFailResponse passwordMissmatchExceptionHandle(){
        return new LoginFailResponse(false,1004L,"아이디와 비밀번호를 다시 확인해 주세요");
    }
    
    
    @ExceptionHandler(LoginInputInvalidException.class)
    public LoginFailResponse loginInputInvalidExceptionHandle(){
        return new LoginFailResponse(false,1004L,"아이디와 비밀번호를 다시 확인해 주세요");
    }

    @ExceptionHandler(MemberAuthException.class)
    public MemberAuthFailResponse memberAuthExceptionHandle(){

        return new MemberAuthFailResponse(false,1000L,"로그인이 필요한 요청입니다.");
    }


}
