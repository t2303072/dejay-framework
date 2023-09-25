/*
package com.dejay.framework.restController.member;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.restController.common.ParentController;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.vo.common.CollectionPagingVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

*/
/**
 * 회원 API
 *//*

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class RestMemberController extends ParentController {

    */
/**
     * 회원 목록 조회 API
     * @param request
     * @param searchObject
     * @return {@link ResponseEntity}
     *//*

    @GetMapping(value = {"", "/"})
    public ResponseEntity memberList(HttpServletRequest request, @RequestBody @Valid SearchObject searchObject) {
        CollectionPagingVO collectionPagingVO = getCommonService().getMemberService().getMemberList(searchObject);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(collectionPagingVO.getObjects().stream().toList(), ResultCodeMsgEnum.NO_DATA);
        
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString(), MapKeyStringEnum.MEMBER_LIST.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, collectionPagingVO.getPaging(), collectionPagingVO.getObjects());

        return ResponseEntity.ok(resultMap);
    }

    */
/**
     * 회원가입 API
     * @param signUpRequest
     * @return {@link ResponseEntity}
     *//*

    @PostMapping("/api/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        TokenObjectVO tokenObjectVO = getCommonService().getMemberService().signUp(signUpRequest);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(tokenObjectVO, RequestTypeEnum.CREATE);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.TOKEN_OBJECT.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, tokenObjectVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    */
/**
     * 회원 상세 조회 API
     * @param id
     * @return {@link ResponseEntity}
     *//*

    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable int id) {
        MemberVO memberVO = getCommonService().getMemberService().findMemberById(id);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(memberVO, ResultCodeMsgEnum.NO_DATA);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, memberVO);

        return ResponseEntity.ok(resultMap);
    }


//    -----
    @Deprecated
    @PostMapping({"", "/"})
    public ResponseEntity insertMember(@RequestBody @Valid Member member) {
        Member inserted = getCommonService().getMemberService().insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted, ResultCodeMsgEnum.NO_DATA);

        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, inserted);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    @Deprecated
    @PostMapping("request-param-validity")
    public ResponseEntity requestParamTest(@RequestBody @Valid Member member) {
        getCommonService().getMemberService().insertMember(member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(member, ResultCodeMsgEnum.NO_DATA);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.MEMBER.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, member);

        return ResponseEntity.ok(resultMap);
    }

}
*/
