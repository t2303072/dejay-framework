package com.dejay.framework.vo.member;

import com.dejay.framework.domain.common.BaseEntity;
import lombok.*;

import java.util.Set;

@ToString
@EqualsAndHashCode(of = {"memberSeq", "memberId"}, callSuper = false)
@NoArgsConstructor
@Setter
@Getter
public class MemberVO extends BaseEntity {
    private long memberSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String userTel;
    private String userEmail;
    private Set<?> authority;
    private String deptCode;
    private String appointCode;
    private String positionCode;
    @Builder
    public MemberVO(String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId, long memberSeq, String userId, String userPwd, String userName, String userEmail, Set<?> authority, String deptCode, String appointCode, String positionCode, String userTel) {
        super(tableName, logId1, logId2, logType, logJson, remark, regId);
        this.memberSeq = memberSeq;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userTel = userTel;
        this.userEmail = userEmail;
        this.authority = authority;
        this.deptCode = deptCode;
        this.appointCode = appointCode;
        this.positionCode = positionCode;
    }
}
