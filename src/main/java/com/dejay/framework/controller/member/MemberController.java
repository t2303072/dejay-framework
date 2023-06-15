package com.dejay.framework.controller.member;

import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.domain.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.vo.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = {"", "/"})
    public List<Member> memberList() {
        return memberService.getMemberList();
    }

    @GetMapping("/{id}")
    public ResponseEntity findMemberById(@PathVariable int id, HttpServletResponse response) {
        MemberVO memberVO = memberService.findMemberById(id);

        return ResponseEntity.ok().body(memberVO);
    }

}
