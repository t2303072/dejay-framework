package com.dejay.framework.service.common;

import com.dejay.framework.service.code.CodeService;
import com.dejay.framework.service.member.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service 공통으로 사용
 */
@RequiredArgsConstructor
@Component
@Getter
public class CommonService {

    private final CodeService codeService;

    private final MemberService memberService;

}
