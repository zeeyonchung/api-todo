package com.moais.todo.member.web.dto;

import com.moais.todo.member.domain.LoginInfo;
import com.moais.todo.member.domain.Member;
import lombok.Getter;

@Getter
public class CreateMemberReq {
    private String memberId;
    private String password;
    private String nickname;

    public Member toMember() {
        return new Member(LoginInfo.of(memberId, password), nickname);
    }
}
