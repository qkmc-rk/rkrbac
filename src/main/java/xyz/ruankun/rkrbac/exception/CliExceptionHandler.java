package xyz.ruankun.rkrbac.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ruankun.rkrbac.server.ResponseCode;
import xyz.ruankun.rkrbac.server.ServerResponse;

/**
 * @author: mrruan
 * @date: 2019-02-04 00:55
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class CliExceptionHandler {

    @ExceptionHandler(value = {AuthorizationException.class})
    public ServerResponse authorizationException(Exception e) {
        log.warn(e.getMessage());
        return ServerResponse.error(ResponseCode.UN_AUTH.getCode(), ResponseCode.UN_AUTH.getMsg());
    }
}
