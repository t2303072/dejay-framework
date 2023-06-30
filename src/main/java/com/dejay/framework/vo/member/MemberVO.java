package com.dejay.framework.vo.member;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberVO {

    private long memberSeq;
    private String memberId;
    private String memberName;
    private String email;
}