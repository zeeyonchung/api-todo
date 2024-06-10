package com.moais.todo.member.web;

import com.moais.todo.member.service.MemberService;
import com.moais.todo.member.web.dto.LoginReq;
import com.moais.todo.member.web.dto.LoginRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionController {

    private static final Integer SESSION_EXPIRE_SECONDS = 3600;
    private final MemberService memberService;

    @PostMapping("/sessions")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq req, HttpServletRequest httpServletRequest) {
        Long id = memberService.login(req.toLoginInfo());

        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("id", id);
        session.setMaxInactiveInterval(SESSION_EXPIRE_SECONDS);

        return ResponseEntity.ok(new LoginRes(id));
    }
}
