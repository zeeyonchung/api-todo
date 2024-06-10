package com.moais.todo.member.service;

import com.moais.todo.member.domain.Member;
import com.moais.todo.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Member> members = memberRepository
                .findByLoginInfo_MemberId(member.getLoginInfo().getMemberId());

        if (!members.isEmpty()) {
            throw new IllegalStateException("The memberId already exists.");
        }
    }
}
