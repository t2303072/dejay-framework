package com.dejay.framework.vo.code;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode(of = {"codeSeq", "codeCd"}, callSuper = false)
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

    // [권한] 대상자 아이디(코드)
    private String userId;
    // [권한] 메뉴 노출 여부
    private String displayUseYn;
    private boolean displayable;
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
    // 상위 메뉴 코드
    private String parentCodeCd;
    // 상위 메뉴 명
    private String parentCodeNm;

    public CommonCodeVO() {
        this.displayable = false;
        this.displayUseYn = "N";
        this.authC = "N";
        this.authR = "N";
        this.authU = "N";
        this.authD = "N";
        this.authF = "N";
    }

    public int isDisplayable() {
        this.displayable = this.displayUseYn == "Y" ? true : false;
        return 0;
    }
}
