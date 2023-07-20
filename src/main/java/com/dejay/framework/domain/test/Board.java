package com.dejay.framework.domain.test;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = false)
@Getter
public class Board {
    private long id;
    @NotNull(message = "제목은 필수 값 입니다.")
    private String title;
    @NotNull(message = "내용은 필수 값 입니다.")
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    @Builder
    public Board(String title, String content, String createdBy, String modifiedBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }
}
