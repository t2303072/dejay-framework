package com.dejay.framework.vo.search.board;

import com.dejay.framework.vo.search.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchVO extends SearchVO{ 
    //게시판 SEQ
    private Long boardSeq;
    //게시판 boardCode
    private String boardCode;
    //제목
    private String title;
    //내용
    private String contents;
    //상단 고정 여부
    private String fixYn;
    // 노출 여부
    private String displayYn;
}
