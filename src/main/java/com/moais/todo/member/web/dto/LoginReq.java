package com.moais.todo.member.web.dto;

import com.moais.todo.member.domain.LoginInfo;
import lombok.Getter;

@Getter
public class LoginReq {
    private String loginId;
    private String password;

    public LoginInfo toLoginInfo() {
        return LoginInfo.of(loginId, password);
    }
}
