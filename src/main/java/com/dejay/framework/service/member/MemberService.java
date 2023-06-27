package com.dejay.framework.service.member;

import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.mapper.member.MemberMapper;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.vo.member.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final ValidationUtil validationUtil;

    public List<MemberVO> getMemberList() {
//        var member = Member.builder()
//                .memberSeq(19L)
//                .memberId("jane")
//                .memberName("John")
//                .build();
//        boolean validated = validationUtil.parameterValidator(member, Member.class);
//        log.info("member: {}", member.toString());

        return memberMapper.getMemberList();
    }

    public MemberVO findMemberById(int id) {
        return memberMapper.findMemberById(id);
    }

    public Member insertMember(Member member) {
        var target = Member.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();
        boolean validated = validationUtil.parameterValidator(target, Member.class);
        memberMapper.insertMember(target);

        return target;
    }
}
