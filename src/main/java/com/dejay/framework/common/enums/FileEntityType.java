package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FileEntityType {

    BASIC("01", new FileTypeEnum[]{FileTypeEnum.IMAGE, FileTypeEnum.VIDEO, FileTypeEnum.EXCEL, FileTypeEnum.WORD})
    ,VIDEO("02", new FileTypeEnum[]{FileTypeEnum.VIDEO})
    ,TEXT("03", new FileTypeEnum[]{FileTypeEnum.EXCEL, FileTypeEnum.WORD, FileTypeEnum.HANGEUL});

    private String entityType;
    private FileTypeEnum[] fileTypes;

}
