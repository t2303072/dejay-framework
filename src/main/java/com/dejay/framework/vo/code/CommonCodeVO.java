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
}
