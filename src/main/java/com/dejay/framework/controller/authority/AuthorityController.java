package com.dejay.framework.controller.authority;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObject;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.domain.user.User;
import com.dejay.framework.vo.common.CollectionPagingVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthorityController extends ParentController {

    @PostMapping(value = {"", "/"})
    public ResponseEntity selectAuth(Authentication authentication) {
        log.info("selectAuth: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("save")
    public ResponseEntity saveAuth(Authentication authentication) {
        log.info("createAuth: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("update")
    public ResponseEntity updateAuth(Authentication authentication) {
        log.info("updateAuth: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("delete")
    public ResponseEntity deleteAuth(Authentication authentication) {
        log.info("deleteAuth: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("download")
    public ResponseEntity downloadAuth(Authentication authentication) {
        log.info("downloadAuth: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }
}
