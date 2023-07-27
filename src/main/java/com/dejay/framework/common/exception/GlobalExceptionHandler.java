package com.dejay.framework.common.exception;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.fasterxml.jackson.core.JacksonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResultStatusVO resultStatusVO = new ResultStatusVO();

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote @RequestBody, @RequestPart Exception handling
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResultStatusVO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        this.printBindingErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getCode(), ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getMsg(), null, this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote @ModelAttribute Exception handling
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ResultStatusVO> handleBindException(BindException ex) {
        this.printBindingErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getCode(), ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getMsg(), null, this.gatherBindingErrors(ex));
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     *
     * @param ex
     * @return ResultStatusVO
     * @implNote
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResultStatusVO> handleIllegalArgumentException(IllegalArgumentException ex) {
        this.printRuntimeErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getCode(), ExceptionCodeMsgEnum.INVALID_ARGUMENT_EXISTS.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     *
     * @param ex
     * @return ResultStatusVO
     * @implNote
     */
    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<ResultStatusVO> handleNumberFormatException(NumberFormatException ex) {
        this.printRuntimeErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.INVALID_NUMBER_FORMAT.getCode(), ExceptionCodeMsgEnum.INVALID_NUMBER_FORMAT.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     *
     * @param ex
     * @return ResultStatusVO
     * @implNote
     */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ResultStatusVO> handleNullPointerException(NullPointerException ex) {
        this.printRuntimeErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.NULL_POINTER.getCode(), ExceptionCodeMsgEnum.NULL_POINTER.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     *
     * @param ex
     * @return ResultStatusVO
     * @implNote
     */
    @ExceptionHandler(ClassNotFoundException.class)
    protected ResponseEntity<ResultStatusVO> handleClassNotFoundException(ClassNotFoundException ex) {
        log.error("[handleClassNotFoundException] => {}", ex.getMessage());
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.CLASS_NOT_FOUND.getCode(), ExceptionCodeMsgEnum.CLASS_NOT_FOUND.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote Server error handling
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ResultStatusVO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("[handleHttpRequestMethodNotSupportedException] => {}", ex.getMessage());
        resultStatusVO = new ResultStatusVO(ex.getBody().getStatus(), ex.getBody().getDetail(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote Server error handling
     */
    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<ResultStatusVO> handleSQLException(SQLException ex) {
        this.printSqlErrorLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.SQL_ERROR.getCode(), ExceptionCodeMsgEnum.SQL_ERROR.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote Server error handling
     */
    @ExceptionHandler(UnsupportedEncodingException.class)
    protected ResponseEntity<ResultStatusVO> handleUnsupportedEncodingException(UnsupportedEncodingException ex) {
        this.printIOExceptionLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.UNSUPPORTED_ENCODING.getCode(), ExceptionCodeMsgEnum.UNSUPPORTED_ENCODING.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    /**
     * @param ex
     * @return ResultStatusVO
     * @implNote @RequestBody, @RequestPart Exception handling
     */
    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ResultStatusVO> handleLoginException(LoginException ex) {
        this.printGeneralSecurityExceptionLog(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.LOGIN_REQUIRED.getCode(), ExceptionCodeMsgEnum.LOGIN_REQUIRED.getMsg(), null, null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ResultStatusVO> handleAuthenticationException(AuthenticationException ex) {
        this.printAuthenticationException(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.AUTH_ERROR.getCode(), ExceptionCodeMsgEnum.AUTH_ERROR.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

    @ExceptionHandler(JacksonException.class)
    protected ResponseEntity<ResultStatusVO> handleJacksonException(JacksonException ex) {
        this.printJacksonException(ex);
        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.JSON_ERROR.getCode(), ExceptionCodeMsgEnum.JSON_ERROR.getMsg(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(resultStatusVO);
    }

//    @ExceptionHandler(RuntimeException.class)
//    protected ResponseEntity<ResultStatusVO> handleRuntimeException(RuntimeException ex) {
//        this.printRuntimeErrorLog(ex);
//        resultStatusVO = new ResultStatusVO(ExceptionCodeMsgEnum.RUNTIME_ERROR.getCode(), ExceptionCodeMsgEnum.RUNTIME_ERROR.getMsg(), ex.getMessage(), null);
//        return ResponseEntity.badRequest().body(resultStatusVO);
//    }

    // ----------------------------------------------------------------------------------------------------
    private <T extends BindException> List<FieldError> gatherBindingErrors(T ex) {
        List<FieldError> errList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(c -> errList.add((FieldError) c));
        return errList;
    }

    private <T extends BindException> void printBindingErrorLog(T ex) {
        log.error("[printBindingErrorLog] => {}", ex.getBindingResult().getAllErrors());
        log.error("GlobalErrorCount => {}", String.valueOf(ex.getBindingResult().getGlobalErrorCount()));
        log.error("ErrorCount => {}", String.valueOf(ex.getBindingResult().getErrorCount()));
        log.error("FieldErrorCount => {}", String.valueOf(ex.getBindingResult().getFieldErrorCount()));
        log.error("{}", ((MethodArgumentNotValidException) ex).getBody());
    }

    private <T extends RuntimeException> void printRuntimeErrorLog(T ex) {
        log.error("[printRuntimeErrorLog] ", ex);
    }

    private <T extends SQLException> void printSqlErrorLog(T ex) {
        log.error("[printSqlErrorLog] ", ex);
    }

    private <T extends IOException> void printIOExceptionLog(T ex) {
        log.error("[printIOExceptionLog] ", ex);
    }

    private <T extends GeneralSecurityException> void printGeneralSecurityExceptionLog(T ex) {
        log.error("[printGeneralSecurityExceptionLog] ", ex);
    }

    private <T extends AuthenticationException> void printAuthenticationException(T ex) {
        log.error("[printAuthenticationExceptionLog] ", ex);
    }

    private <T extends JacksonException> void printJacksonException(T ex) {
        log.error("[printJacksonException] ", ex);
    }
}
