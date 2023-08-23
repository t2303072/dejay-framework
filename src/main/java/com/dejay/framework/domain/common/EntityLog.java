package com.dejay.framework.domain.common;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * MyBatis Interceptor에서 테이블 처리 이력 저장을 위한 클래스 (미사용)
 */
@Setter
@Getter
@EqualsAndHashCode(of = {"tableName", "logId1"})
public class EntityLog {

    @NotNull(message = "테이블 명은 필수 값 입니다.")
    private String tableName;
    @NotNull(message = "엔티티 logId1은 필수 값 입니다.")
    private String logId1;
    private String logId2;
    private String logType;
    private String remark;
    @NotNull(message = "등록ID는 필수 값 입니다.")
    private String regId;

    @Builder
    public EntityLog(String tableName, String logId1, String logId2, String logType, String remark, String regId) {
        this.tableName = tableName;
        this.logId1 = logId1;
        this.logId2 = logId2;
        this.logType = logType;
        this.remark = remark;
        this.regId = regId;
    }
}
