package com.dejay.framework.vo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PagingVO {

    private int totalCount;
    private int totalPage;
}
