package com.dejay.framework.mapper.common;


import com.dejay.framework.mapper.code.CodeMapper;
import com.dejay.framework.mapper.member.MemberMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapper 공통으로 사용
 */
@RequiredArgsConstructor
@Component
@Getter
public class CommonMapper {

    private final CodeMapper codeMapper;

    private final MemberMapper memberMapper;

}