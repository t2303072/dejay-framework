package com.dejay.framework.controller.member;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.service.member.MemberService;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.login.LoginVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final TestService testService;
    private final MemberService memberService;
    private final MapUtil mapUtil;

    @GetMapping(value = {"", "/"})
    public ResponseEntity memberList(HttpServletRequest request) {
        // TODO: IJ 로그인 정보
        TokenVO tokenVO = ObjectHandlingUtil.extractTokenInfo(request); log.info(tokenVO.toString());
        MemberVO loginVO = ObjectHandlingUtil.extractLoginInfo(request); log.info(loginVO.toString());

        List<MemberVO> memberList = memberService.getMemberList();
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(memberList);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.TEST.getKeyString(), MapKeyStringEnum.MEMBER_LIST.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(resultStatusVO, mapKeyList, testService.getTest(), memberList);

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping({"", "/"})
    public ResponseEntity insertMember(@RequestBody @Valid Member member) {
        Member inserted = memberService.insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(resultStatusVO, mapKeyList, inserted);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = memberService.findMemberById(id);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(memberVO);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(resultStatusVO, mapKeyList, memberVO);

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("request-param-validity")
    public ResponseEntity requestParamTest(@RequestBody @Valid Member member) {
        log.info(member.toString());
        memberService.insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(member);
        List<String> mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(resultStatusVO, mapKeyList, member);

        return ResponseEntity.ok(resultMap);
    }

}
