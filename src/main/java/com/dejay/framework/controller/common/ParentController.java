package com.dejay.framework.controller.common;

import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 부모 controller
 * - 모든 controller는 상속받아 사용할 것
 */
public class ParentController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private MapUtil mapUtil;


    public CommonService getCommonService(){
        return this.commonService;
    }
    public MapUtil getMapUtil(){
        return this.mapUtil;
    }


}
