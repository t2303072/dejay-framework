package com.dejay.framework.vo.search.menu;

import com.dejay.framework.vo.search.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuSearchVO extends SearchVO {
    // 메뉴 Seq
    private int menuSeq;
    // 메뉴 ID
    private String menuId;

    // 메뉴 Title
    private String menuTitle;

    // 메뉴 URL
    private String menuUrl;



}
