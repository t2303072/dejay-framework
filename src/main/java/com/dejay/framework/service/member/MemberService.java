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
// TODO: IJ NPE 위험코드 Optional로 변경
    private final MemberMapper memberMapper;
    private final ValidationUtil validationUtil;

    /**
     * 멤버목록 조회
     * @return
     */
    public List<MemberVO> getMemberList() {
        return memberMapper.getMemberList();
    }

    /**
     * 멤버 상세 조회
     * @param id int
     * @return
     */
    public MemberVO findMemberById(int id) {
        return memberMapper.findMemberById(id);
    }

    /**
     * 멤버 상세 조회
     * @param userName String
     * @return
     */
    public MemberVO findMemberByUserName(String userName) {
        return memberMapper.findMemberByUserName(userName);
    }

    /**
     * 멤버 등록
     * @param member {@link Member}
     * @return
     */
    public Member insertMember(Member member) {
        var target = Member.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();

        validationUtil.parameterValidator(target, Member.class);
        memberMapper.insertMember(target);

        return target;
    }
}
