package com.dejay.framework.vo.member;

import lombok.*;

@ToString
@EqualsAndHashCode(of = {"memberSeq", "memberId"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberVO {

    private long memberSeq;
    private String memberId;
    private String password;
    private String memberName;
    private String email;
    private String[] authority;

}
