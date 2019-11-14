package top.liyf.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.liyf.admin.exception.BusinessException;
import top.liyf.admin.result.ResultBean;
import top.liyf.admin.result.ResultCode;

import java.security.Principal;

/**
 * 统一异常处理
 *
 * @author liyf
 * Created in 2019-11-14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResultBean businessExceptionHandler(BusinessException e) {
        return new ResultBean(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultBean runtimeExceptionHandler(RuntimeException e, Principal principal) {
        if (principal != null) {
            log.error("用户:" + principal.getName() );
        }
        log.error("系统异常", e);
        return new ResultBean(ResultCode.UNKNOWN_ERROR);
    }
}
