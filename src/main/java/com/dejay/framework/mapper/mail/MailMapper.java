package com.dejay.framework.mapper.mail;

import com.dejay.framework.vo.member.MemberVO;

public interface MailMapper {
    void insertMailAuth(MemberVO member);
    MemberVO getAuthEmailMember(MemberVO member);

    MemberVO getAuthNumber(MemberVO member);
}
