package com.dejay.framework.domain.common;

import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 모델 공통 관리
 */
@Getter
@Setter
public class DataObject {

    private Data data;

    @Getter
    @Setter
    public static class Data {
        private Code code;

        private List<Code> codeList;

        private Member member;

        private Board board;

        private List<Board> boardList;
    }
}
