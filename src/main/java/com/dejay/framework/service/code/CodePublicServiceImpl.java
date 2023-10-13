package com.dejay.framework.service.code;

import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CodePublicVO;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Primary
public class CodePublicServiceImpl extends ParentService implements CodeService {
    // 사이드바 메뉴 조회
    @Override
    public List<CodePublicVO> listCode() {
        return getCommonMapper().getCodeMapper().listCode();
    }
}
