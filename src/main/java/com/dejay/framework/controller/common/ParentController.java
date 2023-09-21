package com.dejay.framework.controller.common;


import com.dejay.framework.common.utils.CommonUtil;
import com.dejay.framework.common.utils.FileUtil;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.service.common.CommonService;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ParentController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private MapUtil mapUtil;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileUtil fileUtil;

    /**
     * 토근 관련 VO
     * @return
     */
    public TokenVO getTokenVO() {
        return ObjectHandlingUtil.extractTokenInfo(request);
    }

    /**
     * 회원 관련 VO
     * @return
     */
    public MemberVO getLoginVO() {
        return ObjectHandlingUtil.extractLoginInfo(request);
    }
}
