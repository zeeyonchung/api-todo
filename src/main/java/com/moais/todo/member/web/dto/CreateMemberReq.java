package com.moais.todo.member.web.dto;

import com.moais.todo.member.domain.LoginInfo;
import com.moais.todo.member.domain.Member;
import lombok.Getter;

@Getter
public class CreateMemberReq {
    private String loginId;
    private String password;
    private String nickname;

    public Member toMember() {
        return new Member(LoginInfo.of(loginId, password), nickname);
    }
}
