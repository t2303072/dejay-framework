package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.vo.ResultStatusVO;

import java.util.List;

public class ObjectHandlingUtil {

    /**
     * @desc 단건 객체에 대한 결과 객체 생성
     * @param obj T
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setSingleObjResultStatusVO(T obj) {
        return obj == null ? new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg()) : new ResultStatusVO();
    }

    /**
     * @desc 다건 객체에 대한 결과 객체 생성
     * @param list {@link List}
     * @return ResultStatusVO
     * @param <T>
     */
    public static <T> ResultStatusVO setListResultStatusVO(List<T> list) {
        return list.size() > 0 ? new ResultStatusVO() : new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
    }
}
