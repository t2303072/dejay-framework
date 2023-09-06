package com.dejay.framework.service.common;

import com.dejay.framework.common.utils.*;
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
    @Autowired
    private FileUtil fileUtil;
    /** Util ]] **/

    /** Mapper [[ **/
    @Autowired
    private CommonMapper commonMapper;
    /** Mapper ]] **/

}
