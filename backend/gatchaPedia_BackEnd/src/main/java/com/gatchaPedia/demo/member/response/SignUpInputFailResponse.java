package com.gatchaPedia.demo.member.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInputFailResponse {

    private Boolean success;

    private List<String> errorMessages;
}
