package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.FileEntityType;
import com.dejay.framework.common.enums.FileTypeEnum;
import com.dejay.framework.vo.file.FileVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class FileUtil {

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 리스트
     * @return  리스트 FileVO 객체 리턴
     */
    public List<FileVO> uploadFiles(final List<MultipartFile> multipartFiles, String filePath) throws Exception {
        List<FileVO> files = new ArrayList<>();
        for(MultipartFile multipartFile:multipartFiles){
            if(multipartFile.isEmpty()){
                continue;
            }
            files.add(uploadFile(multipartFile,filePath));
        }
        return files;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile
     * @return 단일 FileVO 객체 리턴
     */
    public FileVO uploadFile(MultipartFile multipartFile, String fileDir) throws Exception {
       String fileName = createFileName(multipartFile.getOriginalFilename());
       String nowDay = DateUtil.getUtcNowDateFormat("yyMM");
       String uploadPath = getUploadPath(nowDay, getOsRootDir()+fileDir) + "/" + fileName;
       log.info(uploadPath);

       File uploadFile = new File(uploadPath);

       try{
           multipartFile.transferTo(uploadFile);
       } catch(Exception e){
           log.error(e.getMessage());
       }

       return FileVO.builder()
                    .filePath(uploadPath)
                    .fileName(fileName.trim())
                    .originFileName(multipartFile.getOriginalFilename())
                    .fileSize(multipartFile.getSize())
                    .build();

    }

    /**
     * 파일 이름 생성
     * @return 파일 이름 생성
     */
    public String createFileName(String fileName) throws Exception {
        return (DateUtil.getUtcNowDateFormat("yyMMdd") + StringUtil.getRandomStringByUUID() + getFileExtension(fileName)).replaceAll(" ","");
    }

    /**
     * 파일 업로드 임시 경로 (Temp)
     * @param addPath -> 폴더 Path
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath, String filePath){
        return makeDirectories(filePath+ "/" + addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path){
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return path;
    }

    /**
     *
     * 업로드 파일 확장자 타입
     * @param fileName
     * @return String 파일 타입
     */
    public String checkFileType(final String fileName){
        String extension = fileName.substring(fileName.indexOf(".")+1).toLowerCase();
        FileTypeEnum fileType=null;
        switch (extension) {
            // 이미지 파일 확장자
            case "jpg", "jfif", "jpeg", "png", "gif" -> fileType=FileTypeEnum.IMAGE;
            // 동영상 파일 확장자
            case "mp4", "mov", "wmv", "avi" -> fileType=FileTypeEnum.VIDEO;
            //엑셀 파일 확장자
            case "xls", "xlsx" -> fileType=FileTypeEnum.EXCEL;
            //워드 파일 확장자
            case "doc", "docx" -> fileType=FileTypeEnum.WORD;
            // 한글 파일 확장자
            case "hwp" -> fileType=FileTypeEnum.HANGEUL;
            // 그외 다른 파일
            default -> fileType= FileTypeEnum.OTHER;
        }
        return fileType.getFileType();
    }

    /**
     * 다중 파일 이동
     * @param fileNames 파일 이름 리스트
     * @param fromFilePath 이동 해야 할 파일 Path
     * @param toFilePath 이동 후 파일 Path
     */
    public void moveFiles(final List<String> fileNames, String fromFilePath, String toFilePath) throws Exception {
        for(String  fileName : fileNames){
            moveFile(fileName, fromFilePath, toFilePath);
        }
    }

    /**
     * 단일 파일 이동
     * @param fileName 파일 이름
     * @param fromFilePath 이동 해야 할 파일 Path
     * @param toFilePath 이동 후 파일 Path
     */
    public void moveFile(final String fileName, String fromFilePath, String toFilePath) throws Exception {
        String nowDay = DateUtil.getUtcNowDateFormat("yyMM");
        String beforeFilePath = getUploadPath(nowDay, getOsRootDir()+ fromFilePath)+ "/" + fileName;
        String afterFilePath = makeDirectories(toFilePath)+ "/" + fileName;

        Path file = Paths.get(beforeFilePath);
        Path moveFile = Paths.get(afterFilePath);

        try{
            Path newFile = Files.move(file, moveFile);
            log.info("newFiles : { " + newFile + " }");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 단 건 Directory 파일 삭제
     * @param filePath
     */
    public void deleteFile(String filePath){
         String realPath = getOsRootDir()+filePath;
        try{
            File file = new File(realPath);

            if(file.delete()){
                log.info("파일 삭제 성공 : { " + realPath + " } ");
            } else {
                log.info("파일 삭제 실패 : { " + realPath + " } ");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 파일 확장자 이름
     * @param fileName
     * @return 파일 확장자 이름
     */
    public String getFileExtension(String fileName){
        int index = fileName.indexOf(".");
        return fileName.substring(index);
    }

    /**
     * 알맞은 파일 테이블 형식을 반환
     * @param entityName
     * @return
     */
    public Optional<FileEntityType> getFileEntityType(String entityName){
        Optional<FileEntityType> any = Arrays.stream(FileEntityType.values())
                                            .filter(v -> v.getTargetTable().equals(entityName))
                                            .findAny();
        return any;
    }

    public String getOsRootDir(){
        String os = System.getProperty("os.name").toLowerCase();
        String fileRootPath = "";
        if(os.contains("win")) {
            fileRootPath = "C:/";
        }else if(os.contains("nix") || os.contains("nux") || os.contains("aix")){
            fileRootPath = "";
        }

        return fileRootPath;
    }
}
