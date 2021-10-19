package com.rmit.sept.bk_loginservices.payload;

import javax.validation.constraints.NotBlank;

public class UserDetailsRequest {

    @NotBlank(message = "token cannot be blank")
    private String token;
}