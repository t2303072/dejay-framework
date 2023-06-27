package com.dejay.framework.controller.test;

import com.dejay.framework.common.config.JasyptConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

