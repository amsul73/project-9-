package com.gatchaPedia.demo.handler;


import com.gatchaPedia.demo.member.exception.MemberDuplicateByEmailException;
import com.gatchaPedia.demo.member.exception.MemberDuplicateByNameException;
import com.gatchaPedia.demo.member.exception.MemberDuplicateByUsernameException;
import com.gatchaPedia.demo.member.response.SignUpFailResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = Controller.class)
public class ExceptionManager {


    @ExceptionHandler(MemberDuplicateByNameException.class)
    public SignUpFailResponse memberDuplicateByNameExceptionHandle(){

        return new SignUpFailResponse(false, "동일한 이름의 사용자가 있습니다.");
    }


    @ExceptionHandler(MemberDuplicateByUsernameException.class)
    public SignUpFailResponse memberDuplicateByUsernameExceptionHandle(){

        return new SignUpFailResponse(false,"동일한 아이디의 사용자가 있습니다.");
    }


    @ExceptionHandler(MemberDuplicateByEmailException.class)
    public SignUpFailResponse memberDuplicateByEmailExceptionHandle(){

        return new SignUpFailResponse(false, "동일한 이메일로 가입한 사용자가 있습니다.");

    }
}
