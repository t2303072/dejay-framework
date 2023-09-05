package com.dejay.framework.controller.File;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.enums.TableNameEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.DataObject;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController extends ParentController {

    @PostMapping("/upload")
    public ResponseEntity uploadFile(List<MultipartFile> files,String entityType) throws Exception {
        List<FileVO> fileList = getCommonService().getFileService().uploadFile(files, entityType);
         ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(fileList, ResultCodeMsgEnum.NO_DATA);
         var mapKeyString = Arrays.asList( MapKeyStringEnum.FILE_LIST.getKeyString());

         var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyString, fileList);

         return ResponseEntity.ok(resultMap);
    }

    // 임시 테스트를 위한 Controller (추후 사용 X)
    // File을 저장해야하는 해당 Contoller에서 파일 저장
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid DataObject dataObject) throws Exception {
        int inserted = getCommonService().getFileService().saveFile(dataObject.getData().getFileList(), TableNameEnum.BOARD.name());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(inserted,  RequestTypeEnum.CREATE);

        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    // 임시 테스트를 위한 Update Controller (추후 사용 X)
    // File을 수정해야하는 해당 Contoller에서 파일 수정
    @PostMapping("/update")
    public ResponseEntity update(){
        List<FileVO> fileList = getCommonService().getFileService().getFiles(10L);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(fileList, ResultCodeMsgEnum.NO_DATA);
        var mapKeyString = Arrays.asList(MapKeyStringEnum.FILE_LIST.getKeyString());
        var resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyString, fileList);

        return ResponseEntity.ok(resultMap);
    }


    @PostMapping("/delete")
    public ResponseEntity deleteFile(@RequestBody DataObject dataObject) {
        int deleted = getCommonService().getFileService().deleteFile(dataObject.getData().getFile().getFileSeq());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(deleted, RequestTypeEnum.DELETE);

        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteFiles(@RequestBody DataObject dataObject){
        int deleted = getCommonService().getFileService().deleteFiles(dataObject.getData().getFileList());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(deleted, RequestTypeEnum.DELETE);

        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }


}
