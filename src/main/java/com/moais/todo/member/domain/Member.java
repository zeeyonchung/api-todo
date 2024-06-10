package com.moais.todo.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LoginInfo loginInfo;

    private String nickname;

    public Member(LoginInfo loginInfo, String nickname) {
        this.loginInfo = loginInfo;
        this.nickname = nickname;
    }

    public void login(LoginInfo loginInfo) {
        this.loginInfo.login(loginInfo);
    }
}
