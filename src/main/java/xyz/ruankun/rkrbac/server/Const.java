package xyz.ruankun.rkrbac.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: mrruan
 * @date: 2018/7/16 14:52
 * @description:
 */
public class Const {

    /**
     * 密码盐值
     */
    public static final String SALT = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 超级管理员
     */
    public static final String ADMIN_USERNAME = "admin";

    @AllArgsConstructor
    @Getter
    public enum PermissionType {
        /**
         * 目录
         */
        DIRECTORY(0, "目录"),
        /**
         * 菜单
         */
        MENU(1, "菜单"),
        /**
         * 按钮
         */
        BUTTON(2, "按钮");
        private int type;
        private String description;
    }
}
