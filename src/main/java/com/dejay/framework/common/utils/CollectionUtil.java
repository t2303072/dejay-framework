package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.vo.ResultVO;

import java.util.List;

public class CollectionUtil {

    public static <T> ResultVO setResultVOwithListSize(List<T> list) {
        return list.size() > 0 ? new ResultVO() : new ResultVO(ResultCodeMsgEnum.NO_DATA.getCode(), ResultCodeMsgEnum.NO_DATA.getMsg());
    }
}
