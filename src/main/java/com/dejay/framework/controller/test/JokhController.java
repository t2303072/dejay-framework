package com.dejay.framework.controller.test;

import com.dejay.framework.common.config.JasyptConfig;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.service.TestService;
import com.dejay.framework.vo.MemberVO;
import com.dejay.framework.vo.ResultStatusVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/jokh")
public class JokhController {

    @Autowired
    JasyptConfig jasyptConfig;

    @GetMapping(value = {"encryption"})
    public ResponseEntity jasypt() {

        log.info("url: {}", jasyptConfig.jasyptEncrypt("jdbc:log4jdbc:mariadb://10.34.220.168:3306/dejay_public"));
        log.info("id: {}", jasyptConfig.jasyptEncrypt("dejay_was"));
        log.info("password: {}", jasyptConfig.jasyptEncrypt("22@dejay"));

        return ResponseEntity.ok(null);
    }

}

