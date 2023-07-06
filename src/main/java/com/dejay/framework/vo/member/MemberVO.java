package com.dejay.framework.vo.member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class MemberVO {

    private long memberSeq;
    private String memberId;
    private String memberName;
    private String email;
}
