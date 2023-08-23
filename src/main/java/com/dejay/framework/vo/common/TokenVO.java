package com.dejay.framework.vo.common;

import com.dejay.framework.common.enums.AuthorityEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
public class TokenVO extends BaseEntity {
    private String sub;
    private String userName;
    private String iss;
    private long iat;
    private long exp;
    private Set<AuthorityEnum> authority;

    public TokenVO(String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId) {
        super(tableName, logId1, logId2, logType, logJson, remark, regId);
    }

    @Builder
    public TokenVO(String sub, String userName, String iss, long iat, long exp, Set<AuthorityEnum> authority, String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId) {
        super(tableName, logId1, logId2, logType, logJson, remark, regId);
        this.sub = sub;
        this.userName = userName;
        this.iss = iss;
        this.iat = iat;
        this.exp = exp;
        this.authority = authority;
    }
}
