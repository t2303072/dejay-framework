package com.dejay.framework.mapper.file;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper extends GeneralMapper {
    List<FileVO> getFiles(Long entitySeq);
    FileVO getFile(Long fileSeq);
    Long getMaxSeq(String tableName);
    int save(Object obj);
    int deleteFile(Object obj);
}
