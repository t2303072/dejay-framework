package com.dejay.framework.service;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.mapper.member.MemberMapper;
import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import com.dejay.framework.vo.ResultVO;
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

    public List<Member> getMemberList() {
        var member = Member.builder()
                .id(19L)
//                .userId("jane")
                .name("John")
                .build();
//        boolean validated = validationUtil.parameterValidator(member, Member.class);
        log.info("member: {}", member.toString());

        return memberMapper.getMemberList();
    }

    public MemberVO findMemberById(int id) {
        MemberVO memberVO = memberMapper.findMemberById(id);
        if(memberVO == null) {
            MemberVO resultVO = new MemberVO();
            resultVO.setResultVO(new ResultVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg()));
            return resultVO;
        }

        return memberVO;
    }

    public MemberVO insertMember(Member member) {
        var memberVO = Member.builder()
                .id(Long.valueOf(member.getId()))
                .userId(member.getUserId())
                .name(member.getName())
                .build();
        boolean validated = validationUtil.parameterValidator(memberVO, Member.class);

        return new MemberVO();
    }
}
