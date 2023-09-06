package com.dejay.framework.vo.file;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileVO {
    // 파일 SEQ
    private Long fileSeq;
    
    // 테이블 명
    private String entityNm;

    // 테이블 SEQ
    private Long entitySeq;

    // 테이블 TYPE
    private String entityType;

    // 파일 경로
    private String filePath;
    
    // 파일 이름
    private String fileNm;

    // 기존 파일 명
    private String orgFileNm;

    // 파일 유형 01:이미지, 02:비디오, 03:엑셀, 04:워드, 05:한글, 06: 그 외 문
    private String fileType;

    // 파일 사이즈
    private Long fileSize;

}
