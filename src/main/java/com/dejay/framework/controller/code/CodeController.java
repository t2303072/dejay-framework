package com.dejay.framework.controller.code;

import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.common.DataObject;
import com.dejay.framework.vo.common.ResultStatusVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController extends ParentController {

    @PostMapping({"", "/"})
    public ResponseEntity insertCode(@RequestBody @Valid DataObject dataObject) {
        Code inserted =  getCommonService().getCodeService().insertCode(dataObject.getDomain().getCode());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(inserted);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultStatusVO);
    }
}
