package com.dejay.framework.service;

import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.mapper.MemberMapper;
import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final ValidationUtil validationUtil;

//    @Autowired
    public MemberService(MemberMapper memberMapper, ValidationUtil validationUtil) {
        this.memberMapper = memberMapper;
        this.validationUtil = validationUtil;
    }

    public List<Member> getMemberList() {
        var member = Member.builder()
                .id(19L)
//                .userId("jane")
                .name("John")
                .build();

        boolean validated = validationUtil.parameterValidator(member, Member.class);
        log.info("validated => {}", String.valueOf(validated));

        log.info("member: {}", member.toString());
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
