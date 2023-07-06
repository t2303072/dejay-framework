package com.dejay.framework.service.common;

import com.dejay.framework.service.code.CodeService;
import com.dejay.framework.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service 공통으로 사용
 */
@RequiredArgsConstructor
@Component
public class CommonService {

    private final CodeService codeService;

    private final MemberService memberService;

    public CodeService codeService(){

        return this.codeService;
    }

    public MemberService memberService(){

        return this.memberService;
    }
}
