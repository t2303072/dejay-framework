package com.dejay.framework.controller.member;

import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.domain.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.vo.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity memberList() {
        Map<String, Object> result = memberService.getMemberList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = memberService.findMemberById(id);
        return ResponseEntity.ok().body(memberVO);
    }

    @PostMapping("request-param-validity")
    public ResponseEntity requestParamTest(@RequestBody @Valid Member member/*, BindingResult bindingResult*/) {
//        log.info(bindingResult.toString());
        memberService.insertMember(member);
        log.info(member.toString());
        return ResponseEntity.ok(member);
    }
}
