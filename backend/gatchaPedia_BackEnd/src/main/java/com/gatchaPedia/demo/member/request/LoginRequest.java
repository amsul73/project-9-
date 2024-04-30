package com.gatchaPedia.demo.member.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$")
    private String username;


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z!@#$%^&*()-_=+]{1,20}$")
    private String password;

}
