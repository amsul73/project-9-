package com.gatchaPedia.demo.member.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이름은 한글로만 공백없이 20자 이내로 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{1,20}$", message = "이름은 한글로만 공백없이 20자 이내로 입력해 주세요.")
    private String name;


    @NotBlank(message = "아이디는 공백이나 특수문자 없이 20자 이내로 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "아이디는 공백이나 특수문자 없이 20자 이내로 입력하세요.")
    private String username;


    @NotBlank(message = "비밀번호는 공백없이 알파벳과 특수문자로만 20자 이내로 조합해 주세요.")
    @Pattern(regexp = "^[a-zA-Z!@#$%^&*()-_=+]{1,20}$", message = "비밀번호는 공백없이 알파벳과 특수문자로만 20자 이내로 조합해 주세요.")
    private String password;


    @NotBlank(message = "유효하지 않은 이메일 형식입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

}