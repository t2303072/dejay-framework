package com.dejay.framework.domain.common;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

/**
 * MyBatis Interceptor에서 테이블 처리 이력 저장을 위한 클래스 (미사용)
 */
@Setter
@Getter
@EqualsAndHashCode(of = "logId1")
public class BaseEntity {

    @NotNull(message = "엔티티 logId1은 필수 값 입니다.")
    private String logId1;
    private String logId2;
    private String remark;
    @NotNull(message = "등록ID는 필수 값 입니다.")
    private String regId;

    public BaseEntity() {}

}
