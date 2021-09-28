/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.10.161
 Source Server Type    : MySQL
 Source Server Version : 50628
 Source Host           : 172.16.10.161:3306
 Source Schema         : mailbox

 Target Server Type    : MySQL
 Target Server Version : 50628
 File Encoding         : 65001

 Date: 28/09/2021 15:43:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yxmq_mail_bomb
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_bomb`;
CREATE TABLE `yxmq_mail_bomb`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮件轰炸ID',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标邮箱',
  `email_number` int(8) NOT NULL COMMENT '邮件数量',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标邮箱',
  `account_number` int(8) NOT NULL COMMENT '账号数量',
  `status` tinyint(1) NOT NULL COMMENT '任务状态 1-进行中 2-已完成 3-已终止',
  `exception_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常原因',
  `send_number` int(8) NULL DEFAULT NULL COMMENT '发送数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_category
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_category`;
CREATE TABLE `yxmq_mail_category`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类ID',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_category_keyword
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_category_keyword`;
CREATE TABLE `yxmq_mail_category_keyword`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关键字ID',
  `category_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类ID',
  `keyword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关键字',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_recorder
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_recorder`;
CREATE TABLE `yxmq_mail_recorder`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `ipJson` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'IP记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_task
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_task`;
CREATE TABLE `yxmq_mail_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱密码',
  `conn_status` tinyint(1) NOT NULL COMMENT '连接状态 0-无状态 1-连接中 2-连接异常',
  `conn_exception_reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接异常原因',
  `port` int(11) NULL DEFAULT NULL COMMENT '服务器端口',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器地址',
  `has_ssl` tinyint(1) NOT NULL COMMENT '是否支持SSL 0-否 1-是',
  `protocol_type` tinyint(1) NOT NULL COMMENT '协议类型 0-爬虫 1-imap 2-pop3 4-exchange',
  `vpn_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点ID',
  `has_monitoring` tinyint(1) NOT NULL COMMENT '是否监控 0-否 1-是',
  `has_emphasis` tinyint(1) NOT NULL COMMENT '重点关注目标 0-否 1-是',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `read_number` int(11) NULL DEFAULT NULL COMMENT '已读邮件数',
  `un_read_number` int(11) NULL DEFAULT NULL COMMENT '未读邮件数',
  `sensitive_number` int(11) NULL DEFAULT NULL COMMENT '敏感邮件数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件任务池，每个用户最多只有20条数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_trojan
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_trojan`;
CREATE TABLE `yxmq_mail_trojan`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '木马邮件ID',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '发件账号ID',
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发件人',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收件人',
  `mail_sploit` int(11) NULL DEFAULT NULL COMMENT '是否开启仿冒邮箱',
  `mail_sploit_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仿冒名',
  `mail_sploit_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仿冒邮箱',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `vpn_id` bigint(20) NULL DEFAULT NULL COMMENT '安全链路',
  `status` tinyint(1) NOT NULL COMMENT '发送状态 1-发送中 2-发送成功 3-发送失败',
  `trojan_detail_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱详情ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_trojan_detail
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_trojan_detail`;
CREATE TABLE `yxmq_mail_trojan_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '木马详情ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_mail_vpn
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_mail_vpn`;
CREATE TABLE `yxmq_mail_vpn`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `node_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点组合',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `links` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '虫洞json',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '链路表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yxmq_mail_vpn
-- ----------------------------
INSERT INTO `yxmq_mail_vpn` VALUES (1, '新加坡节点链路', '新加坡1>>新加坡2>>新加坡3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', 0, '[\"asset://149.28.155.23:65530/socks5\",\"asset://45.32.118.33:65530/socks5\",\"asset://45.76.145.54:65530/socks5\"]', '0', NULL);
INSERT INTO `yxmq_mail_vpn` VALUES (2, '香港节点链路', '香港1>>香港2>>香港3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', 0, '[\"asset://134.122.130.112:65530/socks5\",\"asset://134.122.130.170:65530/socks5\",\"asset://134.122.130.175:65530/socks5\"]', '0', NULL);
INSERT INTO `yxmq_mail_vpn` VALUES (3, '日本节点链路', '日本1>>日本2>>日本3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', 0, '[\"asset://149.28.23.34:65530/socks5\",\"asset://167.179.110.74:65530/socks5\",\"asset://198.13.57.51:65530/socks5\"]', '0', NULL);
INSERT INTO `yxmq_mail_vpn` VALUES (4, '美国节点链路', '美国1>>美国2>>美国3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', 0, '[\"asset://104.207.153.212:65530/socks5\",\"asset://107.191.55.81:65530/socks5\",\"asset://144.202.24.242:65530/socks5\"]', '0', NULL);
INSERT INTO `yxmq_mail_vpn` VALUES (5, '韩国节点链路', '韩国1>>韩国2>>韩国3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', 0, '[\"asset://27.102.113.130:65530/socks5\",\"asset://27.102.113.141:65530/socks5\",\"asset://27.102.113.171:65530/socks5\"]', '0', NULL);

-- ----------------------------
-- Table structure for yxmq_system_mail_account
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_mail_account`;
CREATE TABLE `yxmq_system_mail_account`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统账号ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱密码',
  `server` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱服务器',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'STMP服务器地址',
  `port` int(11) NULL DEFAULT NULL COMMENT 'STMP服务器端口',
  `status` tinyint(1) NOT NULL COMMENT '账号状态 0-不可用 1-可用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_system_permission
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_permission`;
CREATE TABLE `yxmq_system_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `parent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级名称',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_name`(`name`) USING BTREE,
  UNIQUE INDEX `unique_key`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_system_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_permission_role`;
CREATE TABLE `yxmq_system_permission_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) UNSIGNED NOT NULL,
  `permission_id` bigint(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_system_role
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_role`;
CREATE TABLE `yxmq_system_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `user_number` int(8) NOT NULL DEFAULT 0 COMMENT '用户数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yxmq_system_role
-- ----------------------------
INSERT INTO `yxmq_system_role` VALUES (1, '管理员', 0, '1', '2019-06-12 18:34:40', NULL, 0, 1, NULL);
INSERT INTO `yxmq_system_role` VALUES (2, '普通用户', 0, '1', '2019-06-12 18:35:19', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for yxmq_system_role_user
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_role_user`;
CREATE TABLE `yxmq_system_role_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `role_id` bigint(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yxmq_system_user
-- ----------------------------
DROP TABLE IF EXISTS `yxmq_system_user`;
CREATE TABLE `yxmq_system_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `rolename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称（可多个，逗号分隔）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yxmq_system_user
-- ----------------------------
INSERT INTO `yxmq_system_user` VALUES (1, 'secxun2019', '12ac32573ecfeb245fe84c631646a835', 'Administrator', NULL, 'Administrator', NULL, '1998-12-31 16:00:00', NULL, 0, 0, 'Administrator');

SET FOREIGN_KEY_CHECKS = 1;
