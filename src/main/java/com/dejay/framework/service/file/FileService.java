package com.dejay.framework.service.file;

import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.TableNameEnum;
import com.dejay.framework.common.utils.FileUtil;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FileService extends ParentService {
    @Value("${file.tempPath}")
    private String tempPath;

    @Value("${file.realPath}")
    private String realPath;

    /**
     * 업로드 파일
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadFile(List<MultipartFile> files, String entityType) throws Exception {
        getFileUtil().fileExtensionLimit(files, entityType);
        return getFileUtil().uploadFiles(files, tempPath);
    }

    /**
     * 파일 저장
     * @param files
     * @param tableName
     * @param maxSeq
     * @return
     */
    public int saveFile(List<File> files, String tableName) throws Exception {
        Long maxSeq = getCommonMapper().getFileMapper().getMaxSeq(tableName);

        int iAffectedRows=0;

        final String tablePath = getFileUtil().targetTableFilePath(realPath,tableName);

        for (File file : files) {
            String filePath = tablePath+"\\"+file.getFileNm();

            File target = File.builder()
                    .entityNm(tableName)
                    .entitySeq(maxSeq)
                    .entityType(file.getEntityType())
                    .filePath(filePath)
                    .fileNm(file.getFileNm())
                    .orgFileNm(file.getOrgFileNm())
                    .fileType(getFileUtil().checkFileType(file.getOrgFileNm()))
                    .fileSize(file.getFileSize())
                    .build();

            iAffectedRows = getCommonMapper().getFileMapper().save(target);

            if (iAffectedRows <= 0) break;
            // temp -> real folder로 이동
            getFileUtil().moveFile(file.getFileNm(), tempPath, tablePath);
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
    public int saveFile(List<File> files, String tableName, Long seq) throws Exception {

        int iAffectedRows=0;

        final String tablePath = getFileUtil().targetTableFilePath(realPath,tableName);

        for (File file : files) {
            String filePath = tablePath+"\\"+file.getFileNm();

            File target = File.builder()
                    .entityNm(tableName)
                    .entitySeq(seq)
                    .entityType(file.getEntityType())
                    .filePath(filePath)
                    .fileNm(file.getFileNm())
                    .orgFileNm(file.getOrgFileNm())
                    .fileType(getFileUtil().checkFileType(file.getFileNm()))
                    .fileSize(file.getFileSize())
                    .build();

            iAffectedRows = getCommonMapper().getFileMapper().save(target);

            if (iAffectedRows <= 0) break;
            getFileUtil().moveFile(file.getFileNm(), tempPath, tablePath);
        }

        return iAffectedRows;
    }
    /**
     * 파일 삭제
     * @param files
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
     * @param seq
     * @return
     */
    public int updateFile(List<File> newFiles, String tableName ,Long seq) throws Exception {
        List<FileVO> originFiles = getFiles(seq);

        Map<Long,String> map = new HashMap<>();

        // 원본 FileVO를 map 안에 넣어 준다.
        for(FileVO file: originFiles){
            map.put(file.getFileSeq(), file.getFileNm());
        }


        // newFile에 해당 FileSeq값이 있을 경우, remove
        for(File file : newFiles){
            int index = 0;
            if(map.containsKey(file.getFileSeq()) ){
                Long fileSeq = file.getFileSeq();
                String FileNm = map.get(fileSeq);
                if(FileNm.equals(file.getFileNm()) ) {
                    map.remove(file.getFileSeq());
                    newFiles.remove(index);
                }
            }
            index++;
        }

        int delete = 0;
        // 수정 시 기존 파일이 없으면 삭제
        for (Long key : map.keySet()) {
            delete = deleteFile(key);
            if(delete<=0) return delete;
        }
        int insert = 0;
        if(StringUtil.isNotEmpty(newFiles))  insert = saveFile(newFiles, tableName, seq);

        return delete > 0 && insert > 0 ? 1 : 0;
    }

    /**
     * 단 건 File 조회
     * @param fileSeq
     * @return FileVO 객체
     */
    public FileVO getFile(Long fileSeq) { return getCommonMapper().getFileMapper().getFile(fileSeq); }

    /**
     * 해당 seq에 물린 File 목록 조회
     * @param entitySeq
     * @return FileVO List
     */
    public List<FileVO> getFiles(Long entitySeq){
        return getCommonMapper().getFileMapper().getFiles(entitySeq);
    }
}
