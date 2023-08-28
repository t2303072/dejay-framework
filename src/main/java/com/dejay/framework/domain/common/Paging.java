package com.dejay.framework.domain.common;

import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Paging {

    @NotNull(message = "현재 페이지 값은 필수 입니다.")
    private int currentPage; // 현재 페이지
    @NotNull(message = "노출 로우 값은 필수 입니다.")
    private int displayRow; // 한 페이지에 보여줄 로우 수
    private int totalCount; // 전체 게시물

    private int totalPage; // 전체페이지 수
    private int offset; // 검색 로우

    private int displayPage; // 한 페이지에 보여줄 페이지 수
    private int beginPage; // 시작 페이지
    private int endPage; // 종료 페이지

    public Paging() {
        setDefaultPaging(1, 0);
    }

    @Builder
    public Paging(int currentPage, int displayRow, int totalCount) {
        if(totalCount < 1) {
            setDefaultPaging(currentPage, displayRow);
        }else {
            this.currentPage = currentPage;
            this.displayRow = displayRow;
            this.totalCount = totalCount;

            setOffset();
            setTotalPage();
        }
    }

    private void setDefaultPaging(int currentPage, int displayRow) {
        this.currentPage = currentPage;
        this.displayRow = displayRow;
        this.totalCount = 0;
        this.offset = 0;
        this.totalPage = 1;
    }

    private void setOffset() {
        this.offset = (getCurrentPage() - 1) * getDisplayRow();
    }

    private void setTotalPage() {
        if(getTotalCount() == 0 || getDisplayRow() == 0) {
            this.totalPage = 1;
        }else {
            this.totalPage = (int)Math.ceil(Float.valueOf(getTotalCount()) / Float.valueOf(getDisplayRow()));
        }
    }
}
