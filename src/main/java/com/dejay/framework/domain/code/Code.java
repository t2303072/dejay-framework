package com.dejay.framework.domain.code;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = false)
@Getter
@Builder
public class Code {
    @NotNull(message = "코드는 필수값 입니다.")
    private String code;
    @NotNull(message = "코드명은 필수값 입니다.")
    private String codeName;
    private String remark1;
    private Integer value1;
    private String remark2;
    private Integer value2;
    @NotNull(message = "코드 순서는 필수값 입니다.")
    private Integer codeOrd;
    @NotNull(message = "사용여부는 필수값 입니다.")
    private String useYn;

}
