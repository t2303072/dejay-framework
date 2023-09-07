package com.dejay.framework.service.file;


import com.dejay.framework.common.enums.FileEntityType;
import com.dejay.framework.common.exception.CustomFileException;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.file.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@Slf4j
@Service
public class FileService extends ParentService {


    /**
     * 업로드 파일
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadFile(List<MultipartFile> files, String entityName, String entityType) throws Exception {
        List<FileVO> fileList= getFileUtil().uploadFiles(files, getPropertiesUtil().getFile().getTempPath());
        saveTempFile(fileList, entityName, entityType);
        return fileList;
    }

    public int saveTempFile(List<FileVO> files, String entityName, String entityType) throws CustomFileException {
        int iAffectedRows = 0;

        FileEntityType fileEntityType = getFileUtil().getFileEntityType(entityName).get();
        // 테이블 별 "01", "02" , "03" 형식 확인
        boolean correctType = false;
        for(String type : fileEntityType.getEntityType()){
            if(StringUtil.equals(type, entityType)) {
                correctType = true;
                break;
            }
        }
        if(!correctType) {
            throw new CustomFileException();
        }


        for(FileVO file: files){
            File target = File.builder()
                            .fileNm(file.getFileNm())
                            .entityNm(entityName)
                            .entityType(entityType)
                            .orgFileNm(file.getOrgFileNm())
                            .fileSize(file.getFileSize())
                            .build();
            iAffectedRows = getCommonMapper().getFileMapper().saveTempFile(target);

            if(iAffectedRows<=0) break;
        }
        return iAffectedRows;
    }

    /**
     * 파일 저장 (파일 업데이트 신규 파일 인 경우 사용)
     * @param files
     * @param tableName
     * @param seq
     * @return
     */
    public int saveFile(List<File> files, String tableName,Long seq) throws Exception {
        int iAffectedRows=0;

        for (File file : files) {
            // temp db 조회
            FileVO tempFile = getTempFile(file.getFileNm());

            FileEntityType fileEntityType = getFileUtil().getFileEntityType(tempFile.getEntityNm()).get();
            String realPath = getPropertiesUtil().getFile().getRealPath() + fileEntityType.getEntityDir();
            log.info(realPath);
            File target = File.builder()
                    .entityNm(tableName)
                    .entitySeq(seq)
                    .entityType(tempFile.getEntityType())
                    .filePath(realPath)
                    .fileNm(tempFile.getFileNm())
                    .orgFileNm(tempFile.getOrgFileNm())
                    .fileType(getFileUtil().checkFileType(file.getFileNm()))
                    .fileSize(tempFile.getFileSize())
                    .build();

            iAffectedRows = getCommonMapper().getFileMapper().save(target);

            if (iAffectedRows <= 0) break;
            getFileUtil().moveFile(file.getFileNm(), getPropertiesUtil().getFile().getTempPath() , realPath);
        }

        return iAffectedRows;
    }
    /**
     * 파일 삭제
     * @param fileSeq
     * @return
     */
    public int deleteFile(Long fileSeq){
        // 삭제할 File 조회
        FileVO file = getCommonMapper().getFileMapper().getFile(fileSeq);
        int iAffectedRows = getCommonMapper().getFileMapper().deleteFile(fileSeq);

        // 실제 Directory 삭제
        if(StringUtil.isNotEmpty(file)) {
            getFileUtil().deleteFile(file.getFilePath());
        }
        return iAffectedRows;
    }

    /**
     * 파일 리스트 삭제
     * @param files
     * @return
     */
    public int deleteFiles(List<File> files){
        int iAffectedRows = 0;

        for(File file : files){
           iAffectedRows = deleteFile(file.getFileSeq());
        }

        return iAffectedRows;
    }

    /**
     * 파일 업데이트
     * @param newFiles
     * @param tableName
     * @param entitySeq
     * @return
     * @throws Exception
     */
    public int updateFile(List<File> newFiles, String tableName ,Long entitySeq) throws Exception {
        List<FileVO> originFiles = getFiles(entitySeq, tableName);

        // 제거할 파일 리스트
        Map<String,FileVO> originMap = new HashMap<>();

        // 원본 FileVO를 map 안에 넣어 준다.
        for(FileVO file: originFiles){
            originMap.put(file.getFileNm(),file);
        }

        for(File file : newFiles){
            // 새로운 파일리스트에 있는 경우 originList에서 제거
            if(originMap.containsKey(file.getFileNm())){
                originMap.remove(file.getFileNm());
                newFiles.remove(file);
            }
        }

        Iterator<Map.Entry<String,FileVO>> itr = originMap.entrySet().iterator();

        int delete = 0;
        // 수정 시 기존 파일이 없으면 삭제
        while (itr.hasNext()) {
            Map.Entry<String,FileVO> entry = itr.next();
            Long key = entry.getValue().getFileSeq();
            delete = deleteFile(key);
            if(delete<=0) return delete;
        }
        int insert = 0;
        if(StringUtil.isNotEmpty(newFiles))  insert = saveFile(newFiles, tableName, entitySeq);

        return delete > 0 && insert > 0 ? 1 : 0;
    }

    /**
     * 단 건 File 조회
     * @param file
     * @return FileVO 객체
     */
    public FileVO getFile(File file) { return getCommonMapper().getFileMapper().getFile(file); }

    /**
     * 단 건 TempFile 조회
     */
    public FileVO getTempFile(String fileNm) {return getCommonMapper().getFileMapper().getTempFile(fileNm);}

    /**
     * 해당 seq에 물린 File 목록 조회
     * @param entitySeq
     * @return FileVO List
     */
    public List<FileVO> getFiles(Long entitySeq, String tableName){
        File file = File.builder()
                        .entitySeq(entitySeq)
                        .entityNm(tableName)
                        .build();

        return getCommonMapper().getFileMapper().getFiles(file);
    }

}
