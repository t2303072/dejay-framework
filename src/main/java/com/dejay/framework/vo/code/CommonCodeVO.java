package com.dejay.framework.vo.code;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode(of = {"boardSeq", "boardCd"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommonCodeVO {
    private long codeSeq;
    private String codeCd;
    private String codeNm;
    private String codeDesc;
    private String codeEtc;
    private int codeOrd;
    private String useYn;
    private String delYn;
    private LocalDateTime regDt;
    private String regId;
    private LocalDateTime lastDt;
    private String lastId;
}
