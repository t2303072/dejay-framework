package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FileEntityType {

      BOARD_NOTICE("BOARD",new String[] {"01", "02", "03"}, "BOARD_NOTICE") // CODE "003001" , 01 : 기본 , 02: 그 외
    , BOARD_FAQ("BOARD", new String[]{"01", "02"}, "BOARD_FAQ"); // CODE "003002" , 01 : 기본 , 02: 그 외

    private String targetTable;
    private String[] entityType;
    private String entityId;

}
