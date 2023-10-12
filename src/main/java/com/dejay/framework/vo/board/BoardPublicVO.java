package com.dejay.framework.vo.board;

import com.dejay.framework.domain.common.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode(of = {"boardSeq", "boardCd"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardPublicVO {
    private long boardSeq;
    private String boardCd;
    private String title;
    private String contents;
    private int hits;
    private String delYn;
    private LocalDateTime regDt;
    private String regDtStr;
    private String regId;
    private LocalDateTime lastDt;
    private String lastDtStr;
    private String lastId;
//    @Builder
//    public BoardPublicVO(String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId, long memberSeq, String userType, String userId, String userPwd, String userName, String userEmail, String authority, String deptCode, String appointCode, String positionCode, String userTel) {
//        super(tableName, logId1, logId2, logType, logJson, remark, regId);
//        this.memberSeq = memberSeq;
//        this.userId = userId;
//        this.userPwd = userPwd;
//        this.userType = userType;
//        this.userName = userName;
//        this.userTel = userTel;
//        this.userEmail = userEmail;
//        this.authority = authority;
//        this.deptCode = deptCode;
//        this.appointCode = appointCode;
//        this.positionCode = positionCode;
//    }
}
