package com.dejay.framework.controller.common;

import com.dejay.framework.common.utils.CommonUtil;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.service.common.CommonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 부모 controller
 * - 모든 controller는 상속받아 사용할 것
 */
@Getter
public class ParentController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private MapUtil mapUtil;
    @Autowired
    private CommonUtil commonUtil;

}
