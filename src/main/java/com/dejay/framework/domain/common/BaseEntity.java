package com.dejay.framework.domain.common;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * MyBatis Interceptor에서 테이블 처리 이력 저장을 위한 클래스 (미사용)
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"tableName", "logId1", "logType"})
public class BaseEntity {

    @NotNull(message = "테이블 명은 필수 값 입니다.")
    private String tableName;
    @Setter
    @NotNull(message = "엔티티 logId1은 필수 값 입니다.")
    private String logId1;
    private String logId2;
    private String logType;
    private String logJson;
    private String remark;
    @NotNull(message = "등록ID는 필수 값 입니다.")
    private String regId;

    public BaseEntity(String tableName, String logId1, String logId2, String logType, String logJson, String remark, String regId) {
        this.tableName = tableName;
        this.logId1 = logId1;
        this.logId2 = logId2;
        this.logType = logType;
        this.logJson = logJson;
        this.remark = remark;
        this.regId = regId;
    }

}
