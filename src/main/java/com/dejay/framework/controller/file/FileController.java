package com.dejay.framework.controller.file;

import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.file.FilePublicVO;
import com.dejay.framework.vo.file.FileVO;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController extends ParentController {
    @GetMapping("/download/{fileSeq}")
    public ResponseEntity download(@PathVariable String fileSeq, HttpServletRequest request, HttpServletResponse response) throws IOException {
      FilePublicVO file = getCommonService().getFileServiceImpl().getFile(Long.parseLong(fileSeq));
      getCommonService().getFileServiceImpl().downloadFile(file, request, response);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/board/detail/"+fileSeq));
      return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
