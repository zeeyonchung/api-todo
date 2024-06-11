package com.moais.todo.member.domain;

import com.moais.todo.common.error.CustomException;
import com.moais.todo.common.error.ErrorCode;
import com.moais.todo.member.util.PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {
    @Column(unique = true)
    private String loginId;
    private String encodedPassword;

    private LoginInfo(String loginId, String encodedPassword) {
        this.loginId = loginId;
        this.encodedPassword = encodedPassword;
    }

    public static LoginInfo of(String loginId, String rawPassword) {
        return new LoginInfo(loginId, PasswordEncoder.encode(rawPassword));
    }

    public void login(LoginInfo loginInfo) {
        if (!this.loginId.equals(loginInfo.getLoginId())
                || !this.encodedPassword.equals(loginInfo.getEncodedPassword())) {
            throw new CustomException(ErrorCode.LOGIN_WRONG_ARGUMENT, loginInfo.getLoginId());
        }
    }
}
