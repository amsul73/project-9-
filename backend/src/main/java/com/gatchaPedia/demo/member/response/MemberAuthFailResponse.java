package com.gatchaPedia.demo.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberAuthFailResponse {

    private Boolean success;

    private Long ERRCode;

    private String message;
}
