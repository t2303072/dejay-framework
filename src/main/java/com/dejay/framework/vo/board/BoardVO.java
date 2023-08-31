package com.dejay.framework.vo.board;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoardVO {
    // 게시판 SEQ
    private Long boardSeq;

    // 게시판 코드
    private String boardCode;

    // 제목
    @NotNull(message = "제목은 필수 값 입니다.")
    private String title;

    // 내용
    @NotNull(message = "내용은 필수 값 입니다.")
    private String contents;

    // 상단 고정 여부
    @NotNull(message = "상단 고정 여부는 필수 값 입니다.")
    private String fixYn;

    // 노출 여부
    @NotNull(message = "노출 여부는 필수 값 입니다.")
    private String displayYn;
    
    // 사용 여부
    @NotNull(message = "사용 여부는 필수 값 입니다.")
    private String useYn;

    // 처리 일시
    private Date regDttm;
    
    // 처리 ID
    private String regId;
    

}
