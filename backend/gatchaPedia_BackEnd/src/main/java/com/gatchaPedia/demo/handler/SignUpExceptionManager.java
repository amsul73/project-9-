package com.gatchaPedia.demo.handler;


import com.gatchaPedia.demo.member.exception.DuplicateMemberByEmailException;
import com.gatchaPedia.demo.member.exception.DuplicateMemberByUsernameException;
import com.gatchaPedia.demo.member.exception.SignUpInvalidInputException;
import com.gatchaPedia.demo.member.response.SignUpFailResponse;
import com.gatchaPedia.demo.member.response.SignUpInputFailResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = Controller.class)
public class SignUpExceptionManager {


    @ExceptionHandler(DuplicateMemberByUsernameException.class)
    public SignUpFailResponse memberDuplicateByUsernameExceptionHandle(){

        return new SignUpFailResponse(false,"동일한 아이디의 사용자가 있습니다.");
    }


    @ExceptionHandler(DuplicateMemberByEmailException.class)
    public SignUpFailResponse memberDuplicateByEmailExceptionHandle(){

        return new SignUpFailResponse(false, "동일한 이메일로 가입한 사용자가 있습니다.");
    }



    @ExceptionHandler(SignUpInvalidInputException.class)
    public SignUpInputFailResponse signUpInvaldInputExceptionHandle(SignUpInvalidInputException ex){

        return new SignUpInputFailResponse(false,  ex.getErrorMessages());
    }




}
