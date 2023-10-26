package com.dejay.framework.domain.code;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode(of = {"codeSeq", "codeCd"}, callSuper = false)
@NoArgsConstructor
@Getter
public class CommonCode {
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
    private int rowNum;
    // [권한] 저장
    private String authC;
    // [권한] 조회
    private String authR;
    // [권한] 수정
    private String authU;
    // [권한] 삭제
    private String authD;
    // [권한] 파일
    private String authF;
    // 공통 코드 신규 등록 여부
    private String newlyAdded;

    @Builder
    public CommonCode(long codeSeq, String codeCd, String codeNm, String codeDesc, String codeEtc, int codeOrd, String useYn, String delYn, LocalDateTime regDt, String regId, LocalDateTime lastDt, String lastId, int rowNum, String authC, String authR, String authU, String authD, String authF, String newlyAdded) {
        this.codeSeq = codeSeq;
        this.codeCd = codeCd;
        this.codeNm = codeNm;
        this.codeDesc = codeDesc;
        this.codeEtc = codeEtc;
        this.codeOrd = codeOrd;
        this.useYn = useYn;
        this.delYn = delYn;
        this.regDt = regDt;
        this.regId = regId;
        this.lastDt = lastDt;
        this.lastId = lastId;
        this.rowNum = rowNum;
        this.authC = authC;
        this.authR = authR;
        this.authU = authU;
        this.authD = authD;
        this.authF = authF;
        this.newlyAdded = newlyAdded;
    }
}
