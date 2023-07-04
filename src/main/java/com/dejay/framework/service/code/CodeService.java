package com.dejay.framework.service.code;

import com.dejay.framework.domain.code.Code;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CodeService extends ParentService {

    public Code insertCode(Code code) {

        var target = Code.builder()
                .code(code.getCode())
                .codeName(code.getCodeName())
                .remark1(code.getRemark1())
                .value1(code.getValue1())
                .remark2(code.getRemark2())
                .value2(code.getValue2())
                .codeOrd(code.getCodeOrd())
                .useYn(code.getUseYn())
                .build();
        boolean isValidated = getValidationUtil().parameterValidator(target, Code.class);
        int iAffectedRows = getCommonMapper().getCodeMapper().insert(target);

        return code;
    }

    public List<CodeVO> listCode(CodeSearchVO search) {
        return (List<CodeVO>) getCommonMapper().getCodeMapper().listBySearch(search);
    }

    public CodeVO rowCode(CodeSearchVO search) {
        return (CodeVO) getCommonMapper().getCodeMapper().rowBySearch(search);
    }
}
