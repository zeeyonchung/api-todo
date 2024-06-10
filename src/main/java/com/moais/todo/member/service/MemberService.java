package com.moais.todo.member.service;

import com.moais.todo.error.CustomException;
import com.moais.todo.error.ErrorCode;
import com.moais.todo.member.domain.LoginInfo;
import com.moais.todo.member.domain.Member;
import com.moais.todo.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        String memberId = member.getLoginInfo().getMemberId();
        Optional<Member> foundMember = memberRepository
                .findByLoginInfo_MemberId(memberId);

        if (foundMember.isPresent()) {
            throw new CustomException(ErrorCode.JOIN_EXISTING_MEMBER, memberId);
        }
    }

    public Long login(LoginInfo loginInfo) {
        String memberId = loginInfo.getMemberId();
        Optional<Member> foundMember = memberRepository.findByLoginInfo_MemberId(memberId);

        if (foundMember.isEmpty()) {
            throw new CustomException(ErrorCode.LOGIN_WRONG_ARGUMENT, memberId);
        }

        Member member = foundMember.get();
        member.login(loginInfo);
        return member.getId();
    }
}
