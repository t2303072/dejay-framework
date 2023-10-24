package com.dejay.framework.mapper.menu;

import com.dejay.framework.domain.menu.Menu;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.menu.MenuVO;
import com.dejay.framework.vo.search.menu.MenuSearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends GeneralMapper {
    // Menu Ord 최대 순서 조회
    Long getOrdMax(Long pMenuSeq);

    // 대메뉴 리스트 조회
    List<MenuVO> getLgMenuList();

    // 소메뉴 리스트 조회
    List<MenuVO> getSmMenuList(MenuSearchVO searchVO);

    // 메뉴 순서 변경
    //@EntityLog
    int updateOrd(Menu menu);

    // [공통 코드] 메뉴 1 depth 조회
    List<MenuVO> findCommonMenuCodeList();
}
