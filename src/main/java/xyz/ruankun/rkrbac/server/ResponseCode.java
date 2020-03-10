package xyz.ruankun.rkrbac.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: mrruan
 * @description:
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 业务错误
     */
    ERROR(1000, "业务错误"),

    /**
     * 用户未登录
     */
    UN_LOGIN(1001, "用户未登录"),

    /**
     * 用户无权限
     */
    UN_AUTH(1002, "用户无权限");

    private final int code;
    private final String msg;
}
