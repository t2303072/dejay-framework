package com.dejay.framework.service.menu;

import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.domain.menu.Menu;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.menu.MenuVO;
import com.dejay.framework.vo.search.menu.MenuSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService extends ParentService {
    /**
     * 메뉴 삽입
     * @param menu
     * @return 삽입 성공 1, 삽입 실패 0
     */
    public int insertMenu(Menu menu){
        Menu target = Menu.builder()
                .pMenuSeq(menu.getPMenuSeq())
                .menuId(menu.getMenuId())
                .menuType(menu.getMenuType())
                .menuDepth(menu.getMenuDepth())
                .menuTitle(menu.getMenuTitle())
                .menuUrl(menu.getMenuUrl())
                .menuOrd(getOrdMax(menu.getPMenuSeq()))
                .displayYn(menu.getDisplayYn())
                .useYn(menu.getUseYn())
                .build();

        int iAffectedRows = getCommonMapper().getMenuMapper().insert(target);

        return iAffectedRows;
    }


    /**
     * 메뉴 최대 ORD +1 값 조회
     * @param pMenuSeq
     * @return 최대 순서
     */
    public Long getOrdMax(Long pMenuSeq) {
        return getCommonMapper().getMenuMapper().getOrdMax(pMenuSeq);
    }

    /**
     * 대메뉴 리스트 조회
     * @return 대메뉴 List
     */
    public List<MenuVO> getLgMenuList(){
        List<MenuVO> menuList = getCommonMapper().getMenuMapper().getLgMenuList();
        return menuList;
    }

    /**
     * 소메뉴 리스트 조회
     * @param searchVO
     * @return 소메뉴 List
     */
    public List<MenuVO> getSmMenuList(MenuSearchVO searchVO){
        List<MenuVO> menuList = getCommonMapper().getMenuMapper().getSmMenuList(searchVO);

        return menuList;
    }


    /**
     * 메뉴 단 건 조회
     * @param searchVO
     * @return Menu Object
     */
    public MenuVO rowMenu(MenuSearchVO searchVO) {
        return (MenuVO) getCommonMapper().getMenuMapper().rowBySearch(searchVO);
    }

    /**
     * 메뉴 내용 수정
     * @param menu
     * @return 수정 성공 1, 수정 실패 0
     */
    public int updateMenu(Menu menu) {
         Menu target = Menu.builder()
                            .menuTitle(menu.getMenuTitle())
                            .menuId(menu.getMenuId())
                            .menuUrl(menu.getMenuUrl())
                            .displayYn(menu.getDisplayYn())
                            .build();

         int iAffectedRows = getCommonMapper().getMenuMapper().update(target);
         
         return iAffectedRows;
    }

    /**
     * 메뉴 순서 변경
     * @param menuList
     * @return 변경 성공 1 , 변경 실패 0
     */
    public Integer updateOrd(List<Menu> menuList){

        Iterator<Menu> iter = menuList.iterator();

        int iAffectedRows=0;
        while(iter.hasNext()) {
            Menu menu = iter.next();

            iAffectedRows = getCommonMapper().getMenuMapper().updateOrd(menu);
            if(iAffectedRows<=0) break;
        }

        /* int iAffectedRows  = getCommonMapper().getMenuMapper().updateOrd(menuList); */

        return iAffectedRows;
    }
}