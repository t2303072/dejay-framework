package com.dejay.framework.service.file;

import com.dejay.framework.common.utils.FileUtil;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.file.FilePublicVO;
import com.dejay.framework.vo.file.FileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
@Slf4j
public class FilePublicServiceImpl extends ParentService implements FileService {

    public List<FilePublicVO> uploadFile(List<MultipartFile> files) throws Exception {
        return getFileUtil().uploadFiles(files,getPropertiesUtil().getFile().getRootDir() + getPropertiesUtil().getFile().getRealDir());
    }
    public int saveFile(List<FilePublicVO> files, Long seq, String regId) throws Exception {
        int insert=0;
        Long fileCount=1L;

        for(FilePublicVO file : files){
            FilePublicVO target;
            if(StringUtil.isEmpty(file.getFilePath())){ //빈 값인 경우
                String createFileNm = getFileUtil().createFileName(file.getFileNmOrg());
                target = FilePublicVO.builder()
                        .filePath(getPropertiesUtil().getFile().getRootDir() + getPropertiesUtil().getFile().getRealDir() +createFileNm)
                        .fileNo(fileCount)
                        .fileSize(file.getFileSize())
                        .fileNm(createFileNm)
                        .fileNmOrg(file.getFileNmOrg())
                        .boardSeq(seq)
                        .thumbnailYn("N")
                        .delYn("N")
                        .regId(regId)
                        .build();
            }else{
                target = FilePublicVO.builder()
                        .filePath(file.getFilePath())
                        .fileNo(fileCount)
                        .fileSize(file.getFileSize())
                        .fileNm(file.getFileNm())
                        .fileNmOrg(file.getFileNmOrg())
                        .boardSeq(seq)
                        .thumbnailYn("N")
                        .delYn("N")
                        .regId(regId)
                        .build();
            }
            fileCount++;
            insert = getCommonMapper().getFileMapper().save(target);
           if(insert<0) break;
        }
        return insert;
    }

    public int saveFile(List<FilePublicVO> files, Long seq) throws Exception {
        for(FilePublicVO file : files){
            getCommonMapper().getFileMapper().insert(files);
        }
        return 0;
    }

    @Override
    public int saveFile(List<File> files, String tableName, Long seq) throws Exception {

        return 0;
    }

    @Override
    public int deleteFile(String fileName) {
        return 0;
    }

    public int deleteFile(String fileName, String lastId) {
        FilePublicVO srchFile = FilePublicVO.builder()
                .fileNm(fileName)
                .lastId(lastId)
                .build();

        // 삭제할 File 조회
        FilePublicVO file = getCommonMapper().getFileMapper().getFile(srchFile);
        int iAffectedRows = 0;
        if(StringUtil.isNotEmpty(file)){
            iAffectedRows = getCommonMapper().getFileMapper().deleteFile(srchFile);
        }
        return iAffectedRows;
    }


    public int deleteFiles(String lastId, List<Integer> tgtList){
        int iAffectedRows = 0;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lastId", lastId);
        paramMap.put("seqList", tgtList);
        iAffectedRows = getCommonMapper().getFileMapper().deleteFileList(paramMap);

        return iAffectedRows;
    }

    @Override
    public int deleteFiles(List<File> files) {
        int iAffectedRows = 0;

        for(File file : files){
            iAffectedRows = deleteFile(file.getFileNm());
        }

        return iAffectedRows;
    }

    @Override
    public int updateFile(List<File> newFiles, String tableName, Long entitySeq) throws Exception {
        return 0;
    }

    /**
     * 파일 수정
     * @param files
     * @param seq
     * @param lastId
     * @return
     * @throws Exception
     */
    public int updateFile(List<FilePublicVO> files, Long seq, String lastId) throws Exception {
        List<FilePublicVO> originFiles = getFiles(seq);

        //삭제할 리스트
        List<FilePublicVO> originList = new ArrayList<>();

        // 원본 FileVO를 originList 안에 넣어 준다.
        for(FilePublicVO file: originFiles){
            originList.add(file);
        }

        List<FilePublicVO> newFileList = new ArrayList<>();
        //원본 FilePublicVO를 origin 리스트 안에 넣어 준다.
        for(FilePublicVO file : files){
            newFileList.add(file);
            for(int index = originList.size()-1; index >= 0; index--) {
                String originFileName = originList.get(index).getFileNmOrg();
                if (originFileName.equals(file.getFileNmOrg())) {
                    newFileList.remove(file); // 새로운 리스트에 원본 리스트와 같다면 삭제
                    originList.remove(index);
                }
            }
        }

        int delete = 0;
        // 수정 시 기존 파일이 없으면 삭제
        if(StringUtil.isNotEmpty(originList)){
            for(FilePublicVO file : originList){
                delete = deleteFile(file.getFileNm(), lastId);
                if(delete<=0) return delete;
            }
        }

        int insert = 0;
        if(StringUtil.isNotEmpty(newFileList)) insert = saveFile(newFileList, seq, lastId);
        return insert > 0 && delete > 0 ? 1 : 0;
    }

    @Override
    public FilePublicVO getFile(File file) {
        return null;
    }

    public List<FilePublicVO> getFiles(Long seq){
        FilePublicVO file = FilePublicVO.builder()
                                        .boardSeq(seq)
                                        .build();

        return getCommonMapper().getFileMapper().getFiles(file);
    }

    @Override
    public List<FileVO> getFiles(Long entitySeq, String tableName) {
        return null;
    }

    @Override
    public void downloadFiles(List<File> fileList, HttpServletRequest reqeust, HttpServletResponse response) throws IOException {

    }
}
