package com.moais.todo.member.domain;

import com.moais.todo.error.CustomException;
import com.moais.todo.error.ErrorCode;
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
    private String memberId;
    private String encodedPassword;

    private LoginInfo(String memberId, String encodedPassword) {
        this.memberId = memberId;
        this.encodedPassword = encodedPassword;
    }

    public static LoginInfo of(String memberId, String rawPassword) {
        return new LoginInfo(memberId, PasswordEncoder.encode(rawPassword));
    }

    public void login(LoginInfo loginInfo) {
        if (!this.memberId.equals(loginInfo.getMemberId())
                || !this.encodedPassword.equals(loginInfo.getEncodedPassword())) {
            throw new CustomException(ErrorCode.LOGIN_WRONG_ARGUMENT, loginInfo.getMemberId());
        }
    }
}
