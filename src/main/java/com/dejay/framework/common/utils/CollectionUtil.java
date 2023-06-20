package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.vo.ResultStatusVO;

import java.util.List;

public class CollectionUtil {

    public static <T> ResultStatusVO setResultVOwithListSize(List<T> list) {
        return list.size() > 0 ? new ResultStatusVO() : new ResultStatusVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
    }
}
