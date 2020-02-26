/*
 Navicat Premium Data Transfer

 Source Server         : 45.40.193.214
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 45.40.193.214:3306
 Source Schema         : db_rbac

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 26/02/2020 17:43:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(40) DEFAULT NULL COMMENT 'URL路径',
  `type` int(11) DEFAULT NULL COMMENT '类型目录菜单按钮',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18640760 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES (1, '用户模块', NULL, NULL, NULL, '2020-02-26 08:00:29', '2020-02-26 08:00:29');
INSERT INTO `t_permission` VALUES (2, '查询用户', 'user:selectUser', 1, 0, '2020-02-26 09:24:53', '2020-02-26 09:24:53');
INSERT INTO `t_permission` VALUES (3, '添加用户', 'user:addUser', 1, 0, '2020-02-26 09:05:19', '2020-02-26 09:05:19');
INSERT INTO `t_permission` VALUES (4, '修改用户', 'user:updateUser', 1, 0, '2020-02-26 09:05:21', '2020-02-26 09:05:21');
INSERT INTO `t_permission` VALUES (5, '删除用户', 'user:deleteUser', 1, 0, '2020-02-26 09:05:22', '2020-02-26 09:05:22');
INSERT INTO `t_permission` VALUES (6, '权限模块', '', NULL, NULL, '2020-02-26 08:54:13', '2020-02-26 08:54:13');
INSERT INTO `t_permission` VALUES (7, '查询权限', 'permission:listPermission', 1, 0, '2020-02-26 09:26:48', '2020-02-26 09:26:48');
INSERT INTO `t_permission` VALUES (8, '查询角色', 'role:listRole', 1, 0, '2020-02-26 09:26:58', '2020-02-26 09:26:58');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(18) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(18) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) unsigned DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32223 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 'ADMIN', '超级管理员', '186407547882704900', '186407547882704900', '2020-02-26 08:00:40', '2020-02-26 08:00:40', 1);
INSERT INTO `t_role` VALUES (2, 'TEACHER', '教师', '186407547882704900', '186407547882704900', '2020-02-26 08:00:41', '2020-02-26 08:00:41', 1);
INSERT INTO `t_role` VALUES (3, 'STUDENT', '学生', '186407547882704900', '186407547882704900', '2020-02-26 08:00:42', '2020-02-26 08:00:42', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) unsigned DEFAULT NULL COMMENT '权限ID',
  `remarks` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186411053 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中间表';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES (1, 1, 2, 'ADMIN-查询用户', '2020-02-26 08:04:59', '2020-02-26 08:04:59');
INSERT INTO `t_role_permission` VALUES (2, 1, 3, 'ADMIN-修改用户', '2020-02-26 08:05:00', '2020-02-26 08:05:00');
INSERT INTO `t_role_permission` VALUES (3, 1, 1, 'ADMIN-用户模块', '2020-02-26 08:05:01', '2020-02-26 08:05:01');
INSERT INTO `t_role_permission` VALUES (4, 1, 4, 'ADMIN-添加用户', '2020-02-26 08:05:02', '2020-02-26 08:05:02');
INSERT INTO `t_role_permission` VALUES (5, 1, 5, 'ADMIN-删除用户', '2020-02-26 08:05:03', '2020-02-26 08:05:03');
INSERT INTO `t_role_permission` VALUES (6, 1, 7, 'ADMIN-查询权限', '2020-02-26 09:27:39', '2020-02-26 09:27:39');
INSERT INTO `t_role_permission` VALUES (7, 1, 8, 'ADMIN-查询角色', '2020-02-26 09:27:34', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `really_name` varchar(4) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
  `age` int(11) unsigned DEFAULT NULL COMMENT '年龄',
  `email` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1:男 2:女',
  `status` int(11) DEFAULT '1' COMMENT '状态',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否被删除,true表示用户未被删除',
  `is_account_non_expired` tinyint(1) DEFAULT '1' COMMENT '账号是否没过期,true表示账号没过期',
  `is_account_non_locked` tinyint(1) DEFAULT '1' COMMENT '账号是否没被锁定,true表示账号没被冻结',
  `is_credentials_non_expired` tinyint(1) DEFAULT '1' COMMENT '密码是否过期,true表示密码没过期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18640755 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'lisi', '111111', '李四', 'https://thirdqq.qlogo.cn/qqapp/101235792/F2407217C117BC8E13904E221D393FDC/100', '13222222222', NULL, 'lisi@qq.com', NULL, 1, 1, 1, 1, 1, 1, '2018-11-28 10:00:13', '2018-11-26 22:50:04');
INSERT INTO `t_user` VALUES (2, 'zhansgan', '111111', '张三', 'https://thirdqq.qlogo.cn/qqapp/101235792/F2407217C117BC8E13904E221D393FDC/100', '13333333333', NULL, 'zhangsan@qq.com', NULL, 1, 1, 1, 1, 1, 1, '2018-11-13 15:17:22', '2018-11-26 22:50:04');
INSERT INTO `t_user` VALUES (3, 'admin', '111111', '超级管理', 'https://q.qlogo.cn/qqapp/101235792/73EE253B488FB6033AD6A164362F26E6/100', '13222222222', NULL, 'admin@qq.com', '傻逼管理员', 1, 1, 1, 1, 1, 1, '2018-11-28 10:00:11', '2018-11-28 09:00:06');
INSERT INTO `t_user` VALUES (4, 'wangwu', '111111', '王五', 'https://thirdqq.qlogo.cn/qqapp/101235792/F2407217C117BC8E13904E221D393FDC/100', '13222222222', NULL, 'wangwu@qq.com', NULL, 2, 1, 0, 1, 1, 1, '2018-11-28 10:00:15', '2018-11-28 08:46:15');
INSERT INTO `t_user` VALUES (5, 'ceshi1', '111111', '测试1', 'https://q.qlogo.cn/qqapp/101235792/73EE253B488FB6033AD6A164362F26E6/100', '13222222222', NULL, '43@cc.cc', NULL, 2, 1, 1, 1, 1, 0, '2018-11-28 10:00:20', '2018-11-28 10:19:14');
INSERT INTO `t_user` VALUES (6, 'ceshi2', '111111', '测试2', 'https://q.qlogo.cn/qqapp/101235792/73EE253B488FB6033AD6A164362F26E6/100', '13222222222', NULL, '4321@qq.cc', NULL, 2, 1, 1, 0, 1, 1, '2018-11-28 10:00:22', '2018-11-28 10:19:26');
INSERT INTO `t_user` VALUES (7, 'zhaoliu', '111111', '赵六', 'https://thirdqq.qlogo.cn/qqapp/101235792/F2407217C117BC8E13904E221D393FDC/100', '13222222222', NULL, 'zhaoliu@qq.com', NULL, 1, 1, 1, 0, 1, 1, '2018-11-28 10:00:17', '2018-11-28 08:38:50');
INSERT INTO `t_user` VALUES (8, 'songqi', '111111', '宋七', 'https://thirdqq.qlogo.cn/qqapp/101235792/F2407217C117BC8E13904E221D393FDC/100', '13222222222', NULL, 'songqi@qq.com', NULL, 2, 1, 1, 1, 0, 1, '2018-11-28 10:00:19', '2018-11-28 08:43:56');
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(18) unsigned DEFAULT NULL COMMENT '用户ID',
  `role_id` int(18) unsigned DEFAULT NULL COMMENT '角色ID',
  `remarks` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186223 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES (1, 3, 1, 'admin-TEACHER', '2020-02-26 08:02:38', '2020-02-26 08:02:38');
INSERT INTO `t_user_role` VALUES (2, 1, 2, 'zhangsan-TEACHER', '2020-02-26 08:02:53', '2020-02-26 08:02:53');
INSERT INTO `t_user_role` VALUES (3, 2, 2, 'lisi-STUDENT', '2020-02-26 08:03:19', '2020-02-26 08:03:19');
INSERT INTO `t_user_role` VALUES (4, 6, 2, 'wangwu-STUDENT', '2020-02-26 08:03:41', '2020-02-26 08:03:41');
INSERT INTO `t_user_role` VALUES (5, 4, 3, 'admin-ADMIN', '2020-02-26 08:03:22', '2020-02-26 08:03:22');
INSERT INTO `t_user_role` VALUES (6, 5, 3, 'admin-STUDENT', '2020-02-26 08:03:23', '2020-02-26 08:03:23');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
