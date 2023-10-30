package com.dejay.framework.service.authority;

import com.dejay.framework.domain.authority.Authority;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.code.CommonCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityServiceImpl extends ParentService implements AuthorityService {
    @Override
    public List<CommonCodeVO> findMenuList() {
        List<CommonCodeVO> list = getCommonMapper().getMenuMapper().findMenuList();
        return list;
    }

    @Override
    public Map<String, Object> saveIndividualAuthority(List<Authority> tgt) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "권한이 저장 완료 되었습니다.");

        AtomicInteger saveCount = new AtomicInteger(0);

        if(tgt.get(0).getUserIdList().size() > 0) {
            tgt.forEach(row -> {
                row.getUserIdList().forEach(user -> {
                    row.setUserId(user);
                    if(isMenuAuthorityGranted(row)) {
                        saveCount.addAndGet(getCommonMapper().getAuthorityMapper().updateIndividualAuthority(row));
                    }else {
                        saveCount.addAndGet(getCommonMapper().getAuthorityMapper().saveIndividualAuthority(row));
                    }
                });
            });
        }else {
            result.put("code", 204);
            result.put("message", "회원을 선택 해주세요.");

            return result;
        }

        if(saveCount.get() < 1) {
            result.put("code", 204);
            result.put("message", "권한 등록에 실패했습니다.");
        }

        return result;
    }

    private boolean isMenuAuthorityGranted(Authority authority) {
        log.info(authority.toString());
        long menuSeq = getCommonMapper().getMenuMapper().findMenuSeqByUserId(authority);
        if(menuSeq < 1) return false;
        authority.setMenuSeq(menuSeq);

        return true;
    }
}
