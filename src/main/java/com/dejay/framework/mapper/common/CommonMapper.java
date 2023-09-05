package com.dejay.framework.mapper.common;


import com.dejay.framework.mapper.board.BoardMapper;
import com.dejay.framework.mapper.code.CodeMapper;
import com.dejay.framework.mapper.file.FileMapper;
import com.dejay.framework.mapper.member.MemberMapper;
import com.dejay.framework.mapper.menu.MenuMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    private final BoardMapper boardMapper;

    private final MenuMapper menuMapper;

    private final FileMapper fileMapper;
}