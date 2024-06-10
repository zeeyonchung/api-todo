package com.moais.todo.member.repo;

import com.moais.todo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByLoginInfo_MemberId(String memberId);
}
