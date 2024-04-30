package com.gatchaPedia.demo.member.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class SignUpInvalidInputException extends RuntimeException{

    private List<String> errorMessages;

    public SignUpInvalidInputException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
