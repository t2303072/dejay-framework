package com.dejay.framework.controller.exception;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResultVO as a return type
     * @param ex
     * @return ResultVO
     * @implNote @RequestBody, @RequestPart Exception handling
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResultVO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        this.printErrorLog(ex);
        ResultVO resultVO = this.createResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     * Map as a return type
     * @param ex
     * @return
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        this.printErrorLog(ex);
//        HashMap<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
//    }

    /**
     * ResultVO as a return type
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ResultVO> handleMethodArgumentNotValidException(BindException ex) {
        this.printErrorLog(ex);
        ResultVO resultVO = this.createResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultVO);
    }


// -----
    private <T extends BindException> List<FieldError> gatherBindingErrors(T ex) {
        List<FieldError> errList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(c -> errList.add((FieldError) c));

        return errList;
    }

    private ResultVO createResultVO(int code, String msg, List<FieldError> errList) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        resultVO.setFieldErrors(errList);

        return resultVO;
    }
    private <T extends BindException> void printErrorLog(T ex) {
        log.error("[handleMethodArgumentNotValidException] => {}", ex.getBindingResult().getAllErrors());
    }
}
