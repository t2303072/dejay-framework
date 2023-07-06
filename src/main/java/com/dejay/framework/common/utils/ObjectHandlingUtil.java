package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.login.LoginVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ObjectHandlingUtil {

    /**
     * 단건 객체에 대한 결과 객체 생성
     * @param obj T
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setSingleObjResultStatusVO(T obj) {
        return obj == null ? new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg()) : new ResultStatusVO();
    }

    /**
     * 다건 객체에 대한 결과 객체 생성
     * @param list {@link List}
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setListResultStatusVO(List<T> list) {
        return list.size() > 0 ? new ResultStatusVO() : new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
    }

    /**
     * API 요청: HttpServletRequest 유효 토큰 정보 추출
     * @param request
     * @return
     */
    public static TokenVO extractTokenInfo(HttpServletRequest request) {
        return (TokenVO) request.getAttribute(MapKeyStringEnum.TOKEN_VO.getKeyString());
    }

    /**
     * API 요청: HttpServletRequest 로그인 정보 추출
     * @param request
     * @return
     */
    public static MemberVO extractLoginInfo(HttpServletRequest request) {
        return (MemberVO) request.getAttribute(MapKeyStringEnum.MEMBER_VO.getKeyString());
    }
}
