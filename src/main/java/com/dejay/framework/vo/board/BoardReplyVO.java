package com.dejay.framework.vo.board;

import com.dejay.framework.vo.file.FileVO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyVO {
    // 댓글 SEQ
    private long rippleSeq;

    // 게시판 SEQ
    private long boardSeq;

    // 댓글 번호
    private int rippleNo;

    // 댓글 내용
    private String contents;

    // 삭제 여부
    private String delYn;

    // 등록일
    private LocalDateTime regDt;
    @Setter
    private String regDtStr;

    // 등록자
    private String regId;
}
