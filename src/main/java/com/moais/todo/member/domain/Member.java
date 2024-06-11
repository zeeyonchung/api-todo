package com.moais.todo.member.domain;

import com.moais.todo.common.BaseTimeEntity;
import com.moais.todo.common.error.CustomException;
import com.moais.todo.common.error.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LoginInfo loginInfo;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(LoginInfo loginInfo, String nickname) {
        this.loginInfo = loginInfo;
        this.nickname = nickname;
        this.status = MemberStatus.ACTIVATED;
    }

    public void login(LoginInfo loginInfo) {
        if (this.status != MemberStatus.ACTIVATED) {
            throw new CustomException(ErrorCode.LOGIN_WRONG_ARGUMENT, loginInfo.getLoginId());
        }

        this.loginInfo.login(loginInfo);
    }

    public void deactivate() {
        this.status = MemberStatus.DEACTIVATED;
    }
}
