package com.dejay.framework.domain.common;

import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.member.Member;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;

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

        private Member member;
    }
}
