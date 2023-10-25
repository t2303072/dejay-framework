package com.dejay.framework.vo.board;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@EqualsAndHashCode(of = {"boardSeq", "boardCd"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardPublicVO {
    private long boardSeq;
    private String boardCd;
    private String title;
    private String contents;
    private int hits;
    private String delYn;
    private LocalDateTime regDt;
    private String regDtStr;
    private String regId;
    private LocalDateTime lastDt;
    private String lastDtStr;
    private String lastId;

    // Reply
    private List<BoardReplyVO> replyList;
}
