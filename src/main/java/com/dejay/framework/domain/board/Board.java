package com.dejay.framework.domain.board;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class Board {
    // 게시판 SEQ
    private Long boardSeq;

    // 게시판 코드
    private String boardCd;

    // 제목
    @NotNull(message="제목은 필수값 입니다.")
    private String title;

    // 내용
    @NotNull(message="내용은 필수값 입니다.")
    private String contents;

    // 상단 고정 여부
    @NotNull(message="상단 고정 여부는 필수값 입니다.")
    private String fixYn;

    // 노출 여부
    @NotNull(message="노출 여부는 필수값 입니다.")
    private String displayYn;

    // 사용 여부
    @NotNull(message="사용 여부는 필수값 입니다.")
    private String useYn;
}
