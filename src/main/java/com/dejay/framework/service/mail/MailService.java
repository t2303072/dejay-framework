package com.dejay.framework.service.mail;

import com.dejay.framework.vo.member.MemberVO;

import java.util.Map;

public interface MailService {
    // 이메일 인증 메일을 보냄
    void mailAuthSend(MemberVO member);
    MemberVO getEmail(MemberVO member);
    Map<String,Object> getAuthNumber(MemberVO member) throws Exception;
}
