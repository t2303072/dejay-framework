package com.dejay.framework.controller.member;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObject;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.domain.user.User;
import com.dejay.framework.vo.common.CollectionPagingVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController extends ParentController {

    @GetMapping(value = {"", "/"})
    public ResponseEntity memberList(HttpServletRequest request, @RequestBody @Valid SearchObject searchObject, Authentication authentication) {
        TokenVO tokenVO = ObjectHandlingUtil.extractTokenInfo(request); log.info(tokenVO.toString());
        MemberVO loginVO = ObjectHandlingUtil.extractLoginInfo(request); log.info(loginVO.toString());

        CollectionPagingVO collectionPagingVO = getCommonService().getMemberService().getMemberList(searchObject);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(collectionPagingVO.getObjects().stream().toList());
        
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString(), MapKeyStringEnum.MEMBER_LIST.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, collectionPagingVO.getPaging(), collectionPagingVO.getObjects());

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping({"", "/"})
    public ResponseEntity insertMember(@RequestBody @Valid Member member) {
        Member inserted = getCommonService().getMemberService().insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, inserted);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    @PostMapping(value = "insert")
    public ResponseEntity insertMember(@RequestBody @Valid User user) {
        User inserted = getCommonService().getMemberService().insertUser(user);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.USER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, inserted);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    @PostMapping("sign-up")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        TokenObject tokenObject = getCommonService().getMemberService().signUp(signUpRequest);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(tokenObject, RequestTypeEnum.CREATE);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.TOKEN_OBJECT.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, tokenObject);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = getCommonService().getMemberService().findMemberById(id);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(memberVO);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, memberVO);

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("request-param-validity")
    public ResponseEntity requestParamTest(@RequestBody @Valid Member member) {
        getCommonService().getMemberService().insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(member);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, member);

        return ResponseEntity.ok(resultMap);
    }

}
