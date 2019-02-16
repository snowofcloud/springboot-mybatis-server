package com.q8888.springboot.mybatis.server.base;


import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.q8888.springboot.mybatis.server.base.JsonResult.State.SYSTEM_ERROR;

/**
 * <p>集中处理应用异常</p>
 * <p>因应用使用前后端分离方式开发,故所有异常将以json格式返回给调用方;</p>
 * <pre>
 *     1.controller 中的参数验证异常通过IllegalArgumentException捕获;
 *     2.404/500/常规异常/前端校验异常
 *     方式:日志输出
 *     TODO:输出到elk
 * </pre>
 *
 * @author konbluesky
 * @date 2018-06-06
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionController extends AbstractErrorController {

    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * 用来接收前端校验的异常信息
     * 状态码使用200,使用ApiResult Code作为约束;
     */
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, MissingServletRequestParameterException.class,IllegalArgumentException.class})
    public ResponseEntity frontVerify(Exception e) {
        log.warn("参数校验异常|{}", e.getMessage());
        String message = null;
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException temp = (MethodArgumentNotValidException)e;
            message = temp.getBindingResult().getAllErrors().stream().map((cv) -> cv == null ? null : cv.getDefaultMessage()).collect(Collectors.joining(";"));//返回全部错误
            /*Iterator var2 = temp.getBindingResult().getAllErrors().iterator();//只返回一个错误
             message = var2.hasNext()?((ObjectError)var2.next()).getDefaultMessage():null;**/
        }else if(e instanceof ConstraintViolationException){
            ConstraintViolationException temp = (ConstraintViolationException)e;
            Optional<ConstraintViolation<?>> op = temp.getConstraintViolations().stream().findFirst();
            if(op.isPresent())
                message = op.get().getMessage();//只返回一个错误
            //message = e.getMessage();//返回全部错误
        }else if(e instanceof MissingServletRequestParameterException){
            MissingServletRequestParameterException temp = (MissingServletRequestParameterException)e;
            message = "缺少参数错误：" + " " + temp.getParameterName();
        } else if(e instanceof  IllegalArgumentException){
            IllegalArgumentException ex = (IllegalArgumentException)e;
            message = ex.getMessage();
        }
        String errorData=Throwables.getStackTraceAsString(e);
        JsonResult result =  JsonResult.failed(message,errorData);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    /**
     * Exception大范围异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        return wrapperException(e, SYSTEM_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity exceptionHandler(HttpRequestMethodNotSupportedException e) {
        return wrapperException(e, SYSTEM_ERROR);
    }

    private ResponseEntity wrapperException(Exception e, JsonResult.State state) {
        log.error("系统异常:", e);
        JsonResult result = new JsonResult(state);
        result.setMessage(e.getMessage());
        result.setData(Throwables.getStackTraceAsString(e));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

