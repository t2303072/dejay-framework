package com.dejay.framework.controller.common;

import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.service.common.CommonService;
import lombok.RequiredArgsConstructor;

/**
 * 부모 controller
 * - 모든 controller는 상속받아 사용할 것
 */
public class ParentController {

    private CommonService commonService;
    private MapUtil mapUtil;


    public CommonService getCommonService(){
        return this.commonService;
    }
    public MapUtil getMapUtil(){
        return this.mapUtil;
    }


}
