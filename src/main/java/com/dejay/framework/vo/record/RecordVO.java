package com.dejay.framework.vo.record;

import com.dejay.framework.vo.board.BoardReplyVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@EqualsAndHashCode(of = {"logSeq", "tokenSeq", "userSeq", "menuSeq"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecordVO {
    private long logSeq;
    private long tokenSeq;
    private long userSeq;
    private long menuSeq;
    private String codeCd;
    private String codeNm;
    @Setter
    private String menuNm;
    private String logType;
    @Setter
    private String logTypeKoreanStr;
    private String logEtc;
    private LocalDateTime regDt;
    @Setter
    private String regDtStr;
    private String regId;
}
