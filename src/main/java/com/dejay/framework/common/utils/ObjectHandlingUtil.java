package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.vo.ResultStatusVO;

import java.util.List;

public class ObjectHandlingUtil {

    public static <T> ResultStatusVO setSingleObjResultVO(T obj) {
        return obj == null ? new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg()) : new ResultStatusVO();
    }

    public static <T> ResultStatusVO setListResultVO(List<T> list) {
        return list.size() > 0 ? new ResultStatusVO() : new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
    }
}
