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

    @Resource(name="data")
    private Domain domain;

    @Getter
    @Setter
    public static class Domain {
        @Resource(name="code")
        private Code code;

        @Resource(name="member")
        private Member member;
    }
}
