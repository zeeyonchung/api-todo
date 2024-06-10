package com.moais.todo.member.web;

import com.moais.todo.member.domain.Member;
import com.moais.todo.member.service.MemberService;
import com.moais.todo.member.web.dto.CreateMemberReq;
import com.moais.todo.member.web.dto.CreateMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<CreateMemberRes> create(@RequestBody CreateMemberReq req) {
        Member member = req.toMember();
        Long id = memberService.join(member);
        return ResponseEntity.ok(new CreateMemberRes(id));
    }
}
