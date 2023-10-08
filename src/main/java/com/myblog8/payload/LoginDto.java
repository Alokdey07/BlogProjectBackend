package com.myblog8.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginDto {
    private String usernameOrEmail;
    private String password;

}
