package com.gatchaPedia.demo.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvaildSessionCheckResponse {

    private Boolean success;

    private String memberName;

    private String message;
}
