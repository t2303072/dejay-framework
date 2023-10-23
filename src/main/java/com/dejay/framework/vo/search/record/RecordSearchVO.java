package com.dejay.framework.vo.search.record;

import com.dejay.framework.vo.search.SearchVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordSearchVO extends SearchVO{
    //로그 SEQ
    private Long logSeq;
    //토큰 SEQ
    private Long tokenSeq;
    //회원 SEQ
    private Long userSeq;
    //메뉴 SEQ
    private Long menuSeq;
    //처리 유형
    private String logType;
    //비고
    private String logEtc;

}
