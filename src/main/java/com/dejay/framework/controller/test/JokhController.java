package com.dejay.framework.controller.test;

import com.dejay.framework.common.config.JasyptConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/jokh")
public class JokhController {

    @Autowired
    JasyptConfig jasyptConfig;

    @PostMapping("/encryption")
    public ResponseEntity jasypt() {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("url", jasyptConfig.jasyptEncrypt("jdbc:log4jdbc:mariadb://10.34.220.168:3306/dejay_public"));
        resultMap.put("url", jasyptConfig.jasyptEncrypt("dejay"));
        resultMap.put("url", jasyptConfig.jasyptEncrypt("dejay@1234"));

        return ResponseEntity.ok(resultMap.toString());
    }

}

