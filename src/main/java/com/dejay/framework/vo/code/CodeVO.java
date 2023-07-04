package com.dejay.framework.vo.code;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CodeVO {
    private String code;
    private String codeName;
    private String remark1;
    private Integer value1;
    private String remark2;
    private Integer value2;
    private Integer codeOrd;
    private String useYn;
}
