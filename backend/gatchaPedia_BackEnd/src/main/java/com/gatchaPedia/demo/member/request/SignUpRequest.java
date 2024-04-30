package com.gatchaPedia.demo.member.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @
    private String name;

    private String username;

    private String password;

    private String email;
}