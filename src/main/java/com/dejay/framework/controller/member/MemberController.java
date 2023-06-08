package com.dejay.framework.controller.member;

import com.dejay.framework.model.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = {"", "/"})
    public List<Member> memberList() {
        return memberService.getMemberList();
    }

    @GetMapping("/{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = memberService.findMemberById(id);

        return ResponseEntity.ok().body(memberVO);
    }
}
