package com.dejay.framework.service.authority;

import com.dejay.framework.mapper.authority.AuthorityMapper;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CommonCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityServiceImpl extends ParentService implements AuthorityService {
    @Override
    public List<CommonCodeVO> findMenuList() {
        List<CommonCodeVO> list = getCommonMapper().getMenuMapper().findMenuList();
        return list;
    }
}
