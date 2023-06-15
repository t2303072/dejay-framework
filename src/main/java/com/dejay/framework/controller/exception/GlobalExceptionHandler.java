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
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param ex
     * @return ResultVO
     * @implNote @RequestBody, @RequestPart Exception handling
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResultVO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        this.printBindingErrorLog(ex);
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     * @param ex
     * @return Map<String, String>
     * @implNote @RequestBody, @RequestPart Exception handling
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        this.printErrorLog(ex);
//        HashMap<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
//    }

    /**
     * @param ex
     * @return ResultVO
     * @implNote @ModelAttribute Exception handling
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ResultVO> handleBindException(BindException ex) {
        this.printBindingErrorLog(ex);
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     *
     * @param ex
     * @return ResultVO
     * @implNote
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResultVO> handleIllegalArgumentException(IllegalArgumentException ex) {
        this.printRuntimeErrorLog(ex);
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), null);
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     *
     * @param ex
     * @return ResultVO
     * @implNote
     */
    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<ResultVO> handleNumberFormatException(NumberFormatException ex) {
        this.printRuntimeErrorLog(ex);
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_NUMBER_FORMAT.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_NUMBER_FORMAT.getMsg(), null);
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     *
     * @param ex
     * @return ResultVO
     * @implNote
     */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ResultVO> handleNullPointerException(NumberFormatException ex) {
        this.printRuntimeErrorLog(ex);
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_NUMBER_FORMAT.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_NUMBER_FORMAT.getMsg(), null);
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     *
     * @param ex
     * @return ResultVO
     * @implNote
     */
    @ExceptionHandler(ClassNotFoundException.class)
    protected ResponseEntity<ResultVO> handleClassNotFoundException(ClassNotFoundException ex) {
        log.error("[handleMethodArgumentNotValidException] => {}", ex.getMessage());
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_CLASS_NOT_FOUND.getCode(), ExceptionCodeMsgEnum.ERR_CLASS_NOT_FOUND.getMsg(), null);
        return ResponseEntity.badRequest().body(resultVO);
    }

    /**
     * @param ex
     * @return ResultVO
     * @implNote Server error handling
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResultVO> handleException(Exception ex) {
        log.error("[handleMethodArgumentNotValidException] => {}", ex.getMessage());
        ResultVO resultVO = new ResultVO(ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getCode(), ExceptionCodeMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg(), null);
        return ResponseEntity.internalServerError().body(resultVO);
    }

// -----
    private <T extends BindException> List<FieldError> gatherBindingErrors(T ex) {
        List<FieldError> errList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(c -> errList.add((FieldError) c));
        return errList;
    }

    private <T extends BindException> void printBindingErrorLog(T ex) {
        log.error("[handleMethodArgumentNotValidException] => {}", ex.getBindingResult().getAllErrors());
    }

    private <T extends RuntimeException> void printRuntimeErrorLog(T ex) {
        log.error("[handleMethodArgumentNotValidException] => {}", ex.getMessage());
    }
}
