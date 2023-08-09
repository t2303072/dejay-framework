package com.dejay.framework.service.code;

import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CodeService extends ParentService {

    /**
     * 코드 저장
     * @param code
     * @return
     * @throws Exception
     */
    public Code insertCode(Code code) throws Exception {

        Code target = Code.builder()
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

    /**
     * 코드 수정
     * @param code
     * @return
     * @throws Exception
     */
    public Code updateCode(Code code) throws Exception {

        Code target = Code.builder()
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
        int iAffectedRows = getCommonMapper().getCodeMapper().update(target);

        return code;
    }

    /**
     * 코드 순서 일괄변경
     * @param codeList
     * @return
     */
    public Integer updateCodeOrder(List<Code> codeList) {
        int iAffectedRows = 0;
        for (Code code : codeList) {
            iAffectedRows += getCommonMapper().getCodeMapper().updateCodeOrder(code);
        }

        //변경된 내용이 없는 경우 null return(오류 발생)
        return iAffectedRows <= 0 ? null : iAffectedRows;
    }

    /**
     * 코드 페이징 조회
     * @param search
     * @return
     */
    public List<CodeVO> pagingCode(CodeSearchVO search) {

        int totalCount = getCommonMapper().getCodeMapper().pagingCountBySearch(search);
        Paging paging = ObjectHandlingUtil.pagingOperatorBySearch(search, totalCount);
        search.setPaging(paging);

        List<CodeVO> codeList = (List<CodeVO>) getCommonMapper().getCodeMapper().pagingBySearch(search);

        return codeList;
    }

    /**
     * 코드 목록 조회
     * @param search
     * @return
     */
    public List<CodeVO> listCode(CodeSearchVO search) {
        return (List<CodeVO>) getCommonMapper().getCodeMapper().listBySearch(search);
    }

    /**
     * 코드 단 건 조회
     * @param search
     * @return
     */
    public CodeVO rowCode(CodeSearchVO search) {
        return (CodeVO) getCommonMapper().getCodeMapper().rowBySearch(search);
    }
}
