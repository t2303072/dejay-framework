package com.dejay.framework.service.code;

import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CodePublicVO;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.code.CommonCodeVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class CodePublicServiceImpl extends ParentService implements CodeService {
    // 사이드바 메뉴 조회
    @Override
    public List<CodePublicVO> listCode() {
        return getCommonMapper().getCodeMapper().listCode();
    }

    @Override
    public List<SelectOptionVO> commonCodeGroupList() {
        List<SelectOptionVO> list = getCommonMapper().getCodeMapper().commonCodeGroupList();
        return list;
    }

    @Override
    public List<SelectOptionVO> commonCodeCategoryList(String code) {
        List<SelectOptionVO> list = getCommonMapper().getCodeMapper().commonCodeCategoryList(code);
        return list;
    }

    @Override
    public List<CommonCodeVO> findAll(CodeSearchVO codeSearchVO) {
        List<CommonCodeVO> list = getCommonMapper().getCodeMapper().findAll(codeSearchVO);
        return list;
    }
}
