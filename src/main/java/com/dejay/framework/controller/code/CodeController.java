package com.dejay.framework.controller.code;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.common.DataObject;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController extends ParentController {

    /**
     * 코드 저장
     * @param dataObject
     * @return
     */
    @PostMapping({"", "/"})
    public ResponseEntity insertCode(@RequestBody @Valid DataObject dataObject) {
        Code inserted =  getCommonService().getCodeService().insertCode(dataObject.getData().getCode());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted);
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    /**
     * 코드 목록 조회
     * @param searchObject
     * @return
     */
    @PostMapping("/list")
    public ResponseEntity listCode(@RequestBody @Valid SearchObject searchObject) {
        List<CodeVO> codeList = getCommonService().getCodeService().listCode(searchObject.getSearch().getCodeSearch());

        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(codeList);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.CODE_LIST.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, codeList);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 코드 한 건 조회
     * @param searchObject
     * @return
     */
    @PostMapping("/row")
    public ResponseEntity rowCode(@RequestBody @Valid SearchObject searchObject) {
        CodeVO code = getCommonService().getCodeService().rowCode(searchObject.getSearch().getCodeSearch());

        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(code);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.CODE.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, code);

        return ResponseEntity.ok(resultMap);
    }
}
