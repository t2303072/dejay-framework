package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.vo.ResultStatusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Component
public class MapUtil {

    public <T> Map<String, Object> responseObjWrapper(ResultStatusVO resultStatusVO, List<String> mapKeyList, List dataList) {
        log.info("resultStatusVO: {}", resultStatusVO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MapKeyStringEnum.RESULT_STATUS.getKey(), resultStatusVO);

        Map<String, Object> dataMap = new HashMap<>();
        IntStream.range(0, dataList.size()).forEach(idx -> dataMap.put(mapKeyList.get(idx), dataList.get(idx)));
        resultMap.put(MapKeyStringEnum.DATA.getKey(), dataMap);

        return resultMap;
    }
}
