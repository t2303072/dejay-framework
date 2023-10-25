package com.dejay.framework.mapper.file;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.file.FilePublicVO;
import com.dejay.framework.vo.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper extends GeneralMapper {
    List<FilePublicVO> getFiles(Object obj);
    FilePublicVO getFile(Object obj);
    FileVO getTempFile(String fileName);
    int saveTempFile(Object obj);
    int save(Object obj);
    int deleteFile(Object obj);

    int deleteFileList(Map<String, Object> list);
}
