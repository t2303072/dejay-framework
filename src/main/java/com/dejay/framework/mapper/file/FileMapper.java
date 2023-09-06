package com.dejay.framework.mapper.file;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper extends GeneralMapper {
    List<FileVO> getFiles(Object obj);
    FileVO getFile(Long fileSeq);
    FileVO getTempFile(String fileNm);
    int saveTempFile(Object obj);
    int save(Object obj);
    int deleteFile(Object obj);
}
