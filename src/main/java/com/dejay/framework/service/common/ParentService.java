package com.dejay.framework.service.common;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.common.utils.PropertiesUtil;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.mapper.common.CommonMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service에서 상속받아서 사용할 것
 */
@Getter
public class ParentService {

    /** Util [[ **/
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private StringUtil stringUtil;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private ValidationUtil validationUtil;
    /** Util ]] **/

    /** Mapper [[ **/
    @Autowired
    private CommonMapper commonMapper;
    /** Mapper ]] **/



}
