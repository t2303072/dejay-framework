package com.dejay.framework.domain.board;

import com.dejay.framework.domain.common.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
public class BoardPublic {
    // 게시판 SEQ
    private Long boardSeq;

    // 게시판 코드
    @NotNull(message = "게시판 코드는 필수값 입니다.")
    private String boardCd;

    // 제목
    @NotNull(message="제목은 필수값 입니다.")
    private String title;

    // 내용
    private String contents;

    // 조회수
    private String hits;

    // 삭제 여부
    private String delYn;

    // 등록자 ID
    private String regId;

    // 수정자 ID
    private String lastId;

    @Builder
    public BoardPublic(Long boardSeq, String boardCd, @NotNull(message = "제목은 필수값 입니다.") String title, String contents, String hits, String delYn, String regId, String lastId) {
        this.boardSeq = boardSeq;
        this.boardCd = boardCd;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
        this.delYn = delYn;
        this.regId = regId;
        this.lastId = lastId;
    }
}
