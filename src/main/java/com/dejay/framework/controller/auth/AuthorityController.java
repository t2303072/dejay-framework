package com.dejay.framework.controller.auth;

import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.authority.Authority;
import com.dejay.framework.domain.code.CommonCode;
import com.dejay.framework.vo.code.CommonCodeVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthorityController extends ParentController {

    /**
     * 개인 권한 관리 화면
     * @param mv
     * @return
     */
    @GetMapping("/individual")
    public ModelAndView findAllIndividual(ModelAndView mv) {
        mv.setViewName("auth/individual");

        // 메뉴 목록 조회
        List<CommonCodeVO> list = getCommonService().getAuthorityService().findMenuList(new String());
        mv.addObject("list", list);
        mv.addObject("code", new CommonCodeVO());
        mv.addObject("users", new ArrayList<UserVO>());

        return mv;
    }

    /**
     * 그룹 권한 관리 화면
     * @param mv
     * @return
     */
    @GetMapping("/group")
    public ModelAndView findAllGroup(ModelAndView mv) {
        mv.setViewName("auth/group");

        // 메뉴 목록 조회
        List<CommonCodeVO> list = getCommonService().getAuthorityService().findMenuList(new String());
        mv.addObject("list", list);
        // 부서 목록 조회
        List<CommonCodeVO> deptList = getCommonService().getAuthorityService().findDepartmentList();
        mv.addObject("deptList", deptList);
        mv.addObject("code", new CommonCodeVO());
        mv.addObject("users", new ArrayList<UserVO>());

        return mv;
    }

/** API **/

    /**
     * 개인 권한 저장
     * @param list
     * @return
     */
    @ResponseBody
    @PostMapping("/individual/api")
    public ResponseEntity saveIndividualAuthority(@RequestBody List<Authority> list) {
        Map<String, Object> result = getCommonService().getAuthorityService().saveIndividualAuthority(list);

        return ResponseEntity.ok(result);
    }

    /**
     * 그룹 권한 저장
     * @param list
     * @return
     */
    @ResponseBody
    @PostMapping("/group/api")
    public ResponseEntity saveGroupAuthority(@RequestBody List<Authority> list) {
        Map<String, Object> result = getCommonService().getAuthorityService().saveGroupAuthority(list);

        return ResponseEntity.ok(result);
    }

    /**
     * 사용자 조회 팝업
     * @param model
     * @param searchKeyword
     * @return
     */
    @GetMapping("/auth-popup")
    public String authPopupList(Model model, @RequestParam(value = "searchKeyword") String searchKeyword) {
        SearchVO searchVO = new SearchVO();
        searchVO.setSearchWord1(searchKeyword);
        List<UserVO> list = getCommonService().getMemberService().findAllUsers(searchVO);
        model.addAttribute("users", list);

        return "auth/individual :: #user_list_wrapper";
    }

    /**
     * 권한 레벨 목록 조회
     * @param model
     * @param deptCd
     * @return
     */
    @ResponseBody
    @GetMapping("/api/auth-level")
    public ResponseEntity authLevelList(Model model, @RequestParam(value = "deptCd") String deptCd) {
        var result = new HashMap<>();

        // 메뉴 목록 조회
        List<CommonCodeVO> list = getCommonService().getAuthorityService().findMenuList(deptCd);
        model.addAttribute("list", list);
        result.put("list", list);

        return ResponseEntity.ok(result);
    }
}
