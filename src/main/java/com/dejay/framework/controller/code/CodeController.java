package com.dejay.framework.controller.code;


import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.code.CodePublicVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController extends ParentController {
    @PostMapping("/list")
    public List<CodePublicVO> codeList(){
        return getCommonService().getCodeService().listCode();
    }
}
