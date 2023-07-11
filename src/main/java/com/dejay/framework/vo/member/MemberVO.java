package com.dejay.framework.vo.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = {"memberSeq", "memberId"})
@Getter
@Builder
public class MemberVO {

    private long memberSeq;
    private String memberId;
    private String memberName;
    private String email;
}
