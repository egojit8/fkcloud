package com.egojit.cloud.common.exception;

import com.egojit.cloud.common.base.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@ControllerAdvice
public class AdminExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AdminExceptionHandler.class);

    /**
     * exception处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult javaExceptionHandler(Exception ex) {//APIResponse是项目中对外统一的出口封装，可以根据自身项目的需求做相应更改
        logger.error("捕获到Exception异常", ex);
        //异常日志入库
        return new BaseResult(BaseResult.REST_RESULT.ERROR);
    }

    /**
     *
     * @param cve
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResult handleConstraintViolationException(ConstraintViolationException cve){
        Set<ConstraintViolation<?>> cves = cve.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : cves) {
            System.out.println(constraintViolation.getMessage());
            logger.error(constraintViolation.getMessage());
        }
        return new BaseResult(BaseResult.REST_RESULT.ERROR);
    }
}
