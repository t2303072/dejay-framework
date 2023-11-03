package com.dejay.framework.service.common;

import com.dejay.framework.service.authority.AuthorityService;
import com.dejay.framework.service.board.ApproveService;
import com.dejay.framework.service.board.BoardPublicServiceImpl;
import com.dejay.framework.service.board.BoardService;
import com.dejay.framework.service.code.CodeService;
import com.dejay.framework.service.file.FilePublicServiceImpl;
import com.dejay.framework.service.mail.MailService;
import com.dejay.framework.service.member.MemberService;
import com.dejay.framework.service.menu.MenuService;
import com.dejay.framework.service.record.RecordService;
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
    private final AuthorityService authorityService;
    private final BoardService boardService;
    private final BoardPublicServiceImpl boardPublicServiceImpl;
    private final MenuService menuService;
    private final FilePublicServiceImpl fileServiceImpl;
    private final MailService mailService;
    private final RecordService recordService;
    private final ApproveService approveService;
}
