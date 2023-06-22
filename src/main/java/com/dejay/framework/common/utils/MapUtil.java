package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
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

    /**
     * ResponseEntity 응답데이터 Wrapper
     * @param resultStatusVO 응답상태 객체
     * @param mapKeyList     Map 키값 List 컬렉션
     * @param dataList       데이터 객체  List 컬렉션
     * @return
     * @param <T>
     */
    public <T> Map<String, Object> responseObjWrapper(ResultStatusVO resultStatusVO, List<String> mapKeyList, List dataList) {

        if(mapKeyList.size() != dataList.size()) {
            throw new IllegalArgumentException(ExceptionCodeMsgEnum.NOT_EQUAL_OBJECT_SIZE.getMsg());
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MapKeyStringEnum.RESULT_STATUS.getKey(), resultStatusVO);

        Map<String, Object> dataMap = new HashMap<>();
        IntStream.range(0, dataList.size()).forEach(idx -> dataMap.put(mapKeyList.get(idx), dataList.get(idx)));
        resultMap.put(MapKeyStringEnum.DATA.getKey(), dataMap);

        return resultMap;
    }
}
