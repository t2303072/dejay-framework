package com.dejay.framework.controller.member;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.service.TestService;
import com.dejay.framework.vo.MemberVO;
import com.dejay.framework.vo.ResultStatusVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity memberList() {
        List<MemberVO> memberList = memberService.getMemberList();
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultVO(memberList);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.TEST.getKey(), MapKeyStringEnum.MEMBER_LIST.getKey());
        var dataList = new ArrayList<Object>();
        dataList.add(testService.getTest());
        dataList.add(memberList);

        Map<String, Object> resultMap = mapUtil.responseObjWrapper(resultStatusVO, mapKeyList, dataList);

        return ResponseEntity.ok(resultMap);
    }
    @PostMapping({"", "/"})
    public ResponseEntity insertMember(@RequestBody @Valid Member member) {
        Member inserted = memberService.insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultVO(inserted);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKey());
        var dataList = Arrays.asList(inserted);
        Map<String, Object> resultMap = mapUtil.responseObjWrapper(resultStatusVO, mapKeyList, dataList);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }


    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = memberService.findMemberById(id);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultVO(memberVO);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER_LIST.getKey());
        var dataList = new ArrayList<Object>();
        dataList.add(memberVO);
        Map<String, Object> resultMap = mapUtil.responseObjWrapper(resultStatusVO, mapKeyList, dataList);

        return ResponseEntity.ok().body(resultMap);
    }

    @PostMapping("request-param-validity")
    public ResponseEntity requestParamTest(@RequestBody @Valid Member member/*, BindingResult bindingResult*/) {
                log.info(member.toString());
//        log.info(bindingResult.toString());
        memberService.insertMember(member);

        return ResponseEntity.ok(member);
    }

}
