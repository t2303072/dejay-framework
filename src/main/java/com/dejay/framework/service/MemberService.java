package com.dejay.framework.service;

import com.dejay.framework.mapper.MemberMapper;
import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberService {

    private final MemberMapper memberMapper;
    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> getMemberList() {
        var member = Member.builder()
                .id(1L)
                .name("John")
                .build();
        log.info("member: {}", member);
        return memberMapper.getMemberList();
    }

    public MemberVO findMemberById(int id) {
        var member = Member.builder()
                .id(Long.valueOf(id))
                .name("John")
                .build();
        MemberVO memberVO = memberMapper.findMemberById(member);

        return memberVO;
    }

}
