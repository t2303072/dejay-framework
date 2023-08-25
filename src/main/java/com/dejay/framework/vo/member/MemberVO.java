package com.dejay.framework.vo.member;

import com.dejay.framework.domain.common.BaseEntity;
import lombok.*;

import java.util.Set;

@ToString
@EqualsAndHashCode(of = {"memberSeq", "memberId"})
@NoArgsConstructor
@Getter
public class MemberVO extends BaseEntity {
    private long memberSeq;
    private String memberId;
    private String password;
    private String memberName;
    private String email;
    private Set<?> authority;
    private String deptCode;

    @Builder
    public MemberVO(String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId, long memberSeq, String memberId, String password, String memberName, String email, Set<?> authority, String deptCode) {
        super(tableName, logId1, logId2, logType, logJson, remark, regId);
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.email = email;
        this.authority = authority;
        this.deptCode = deptCode;
    }
}
