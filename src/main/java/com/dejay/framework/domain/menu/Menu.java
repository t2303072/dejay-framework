package com.dejay.framework.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    // 메뉴 SEQ
    @JsonProperty("menuSeq")
    private Long menuSeq;
    
    // 부모 메뉴 SEQ
    @JsonProperty("pMenuSeq")
    private Long pMenuSeq;

    //메뉴 유형 01:ADMIN , 02:USER
    private String menuType;

    // 메뉴 ID
    private String menuId;

    // 메뉴 DEPTH
    private Long menuDepth;

    // 메뉴 타이틀
    private String menuTitle;

    // 메뉴 URL
    private String menuUrl;

    // 메뉴 순서
    private Long menuOrd;

    // 노출 여부
    private String displayYn;

    // 사용 여부
    private String useYn;

}
