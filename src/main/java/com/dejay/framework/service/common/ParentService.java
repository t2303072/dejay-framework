package com.dejay.framework.service.common;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.common.utils.PropertiesUtil;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.mapper.common.CommonMapper;
import org.springframework.stereotype.Service;

/**
 * Service에서 상속받아서 사용할 것
 */
public class ParentService {

    /** Util [[ **/
    private PropertiesUtil propertiesUtil;
    private StringUtil stringUtil;
    private DateUtil dateUtil;
    private ValidationUtil validationUtil;
    /** Util ]] **/

    /** Mapper [[ **/
    private CommonMapper commonMapper;
    /** Mapper ]] **/




    public PropertiesUtil getPropertiesUtil() {
        return this.propertiesUtil;
    }

    public StringUtil getStringUtil() {
        return this.stringUtil;
    }

    public DateUtil getDateUtil() {
        return this.dateUtil;
    }

    public ValidationUtil getValidationUtil() {
        return this.validationUtil;
    }

    public CommonMapper getCommonMapper() {
        return this.commonMapper;
    }

}
