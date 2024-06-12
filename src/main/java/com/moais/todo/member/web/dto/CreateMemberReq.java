package com.moais.todo.member.web.dto;

import com.moais.todo.member.domain.LoginInfo;
import com.moais.todo.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMemberReq {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;

    public Member toMember() {
        return new Member(LoginInfo.of(loginId, password), nickname);
    }
}
