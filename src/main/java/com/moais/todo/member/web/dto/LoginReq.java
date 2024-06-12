package com.moais.todo.member.web.dto;

import com.moais.todo.member.domain.LoginInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginReq {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public LoginInfo toLoginInfo() {
        return LoginInfo.of(loginId, password);
    }
}
