package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ObjectHandlingUtil {

    /**
     * 단건 객체에 대한 결과 객체 생성
     * @param obj T 대상 객체
     * @param resultCodeMsgEnum {@link ResultCodeMsgEnum} 응답 에러코드
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setSingleObjResultStatusVO(T obj, ResultCodeMsgEnum resultCodeMsgEnum) {
        return obj == null ? new ResultStatusVO(resultCodeMsgEnum.getCode(), resultCodeMsgEnum.getMsg()) : new ResultStatusVO();
    }

    /**
     * 다건 객체에 대한 결과 객체 생성
     * @param list {@link List}
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setListResultStatusVO(List<T> list, ResultCodeMsgEnum resultCodeMsgEnum) {
        return list.size() > 0 ? new ResultStatusVO() : new ResultStatusVO(resultCodeMsgEnum.getCode(), resultCodeMsgEnum.getMsg());
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

    /**
     * 데이터 관련 처리 결과 객체 생성
     * @param obj T
     * @param key {@link RequestTypeEnum}
     * @return
     * @param <T>
     */
    public static <T> ResultStatusVO setDataManipulationResultStatusVO(T obj, RequestTypeEnum key) {
        if(obj != null) {
            return new ResultStatusVO();
        }

        var result = switch(key) {
            case CREATE -> new ResultStatusVO(ResultCodeMsgEnum.CREATE_DATA_FAIL.getCode(), ResultCodeMsgEnum.CREATE_DATA_FAIL.getMsg());
            case UPDATE -> new ResultStatusVO(ResultCodeMsgEnum.UPDATE_DATA_FAIL.getCode(), ResultCodeMsgEnum.UPDATE_DATA_FAIL.getMsg());
            case DELETE -> new ResultStatusVO(ResultCodeMsgEnum.DELETE_DATA_FAIL.getCode(), ResultCodeMsgEnum.DELETE_DATA_FAIL.getMsg());
            default -> new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
        };

        return result;
    }

    /**
     * Paging 연산기
     * @param searchObject {@link SearchObject}
     * @param totalCount {@link Integer}
     * @return
     */
    public static Paging pagingOperator(SearchObject searchObject, int totalCount) {
        return Paging.builder()
                .totalCount(totalCount)
                .currentPage(searchObject.getSearch().getPaging().getCurrentPage())
                .displayRow(searchObject.getSearch().getPaging().getDisplayRow())
                .build();
    }


    /**
     * Paging 연산기
     * @param search {@link SearchVO}
     * @param totalCount {@link Integer}
     * @return
     */
    public static Paging pagingOperatorBySearch(SearchVO search, int totalCount) {
        return Paging.builder()
                .totalCount(totalCount)
                .currentPage(search.getPaging().getCurrentPage())
                .displayRow(search.getPaging().getDisplayRow())
                .build();
    }


}
