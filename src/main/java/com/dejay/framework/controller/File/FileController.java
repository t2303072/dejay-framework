package com.dejay.framework.controller.File;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.DataObject;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.file.FileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController extends ParentController {
    /**
     * 파일 업로드
     * @param files
     * @return 
     * @throws Exception
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFile(List<MultipartFile> files,@RequestParam(required = true) String entityId,@RequestParam(required = true) String entityType) throws Exception {
        List<FileVO> fileList = getCommonService().getFileService().uploadFile(files, entityId, entityType);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(fileList, ResultCodeMsgEnum.NO_DATA);
        var mapKeyString = Arrays.asList( MapKeyStringEnum.FILE_LIST.getKeyString());

        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyString, fileList);

        return ResponseEntity.ok(resultMap);
    }


    /**
     * 파일 삭제 단 건
     * @param dataObject
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity deleteFile(@RequestBody DataObject dataObject) {
        int deleted = getCommonService().getFileService().deleteFile(dataObject.getData().getFile().getFileName());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(deleted, RequestTypeEnum.DELETE);

        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 파일 리스트 삭제
     * @param dataObject
     * @return
     */
    @PostMapping("/deleteList")
    public ResponseEntity deleteFiles(@RequestBody DataObject dataObject){
        int deleted = getCommonService().getFileService().deleteFiles(dataObject.getData().getFileList());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(deleted, RequestTypeEnum.DELETE);

        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 파일 다운로드
     * @param dataObject
     */
    @PostMapping(value = "/download")
    public ResponseEntity download(@RequestBody DataObject dataObject, HttpServletRequest request) throws IOException {
        return getCommonService().getFileService().downloadFiles(dataObject.getData().getFileList(),  request);
    }

}
