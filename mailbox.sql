/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mailbox

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-09-18 17:01:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_mail_account
-- ----------------------------
DROP TABLE IF EXISTS `base_mail_account`;
CREATE TABLE `base_mail_account` (
  `id` varchar(32) NOT NULL COMMENT '系统账号ID',
  `account` varchar(50) NOT NULL COMMENT '邮箱账号',
  `password` varchar(50) NOT NULL COMMENT '邮箱密码',
  `server` varchar(30) DEFAULT NULL COMMENT '邮箱服务器',
  `status` tinyint(1) NOT NULL COMMENT '账号状态 0-不可用 1-可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT NULL COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for base_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_permission`;
CREATE TABLE `base_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `parent_name` varchar(50) DEFAULT NULL COMMENT '父级名称',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `key` varchar(50) NOT NULL COMMENT '唯一标识',
  `url` varchar(255) NOT NULL COMMENT '路径',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_name` (`name`) USING BTREE,
  UNIQUE KEY `unique_key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for base_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `base_permission_role`;
CREATE TABLE `base_permission_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `user_number` int(8) NOT NULL DEFAULT '0' COMMENT '用户数量',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for base_role_user
-- ----------------------------
DROP TABLE IF EXISTS `base_role_user`;
CREATE TABLE `base_role_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(50) NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `rolename` varchar(255) NOT NULL COMMENT '角色名称（可多个，逗号分隔）',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_bomb
-- ----------------------------
DROP TABLE IF EXISTS `mail_bomb`;
CREATE TABLE `mail_bomb` (
  `id` varchar(32) NOT NULL COMMENT '邮件轰炸ID',
  `target` varchar(50) NOT NULL COMMENT '目标邮箱',
  `email_number` int(8) NOT NULL COMMENT '邮件数量',
  `account_number` int(8) NOT NULL COMMENT '账号数量',
  `status` tinyint(1) NOT NULL COMMENT '任务状态 1-进行中 2-已完成 3-已终止',
  `send_number` int(8) DEFAULT NULL COMMENT '发送数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_category
-- ----------------------------
DROP TABLE IF EXISTS `mail_category`;
CREATE TABLE `mail_category` (
  `id` varchar(32) NOT NULL COMMENT '分类ID',
  `category` varchar(50) NOT NULL COMMENT '分类',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_category_keyword
-- ----------------------------
DROP TABLE IF EXISTS `mail_category_keyword`;
CREATE TABLE `mail_category_keyword` (
  `id` varchar(32) NOT NULL COMMENT '关键字ID',
  `category_id` varchar(32) NOT NULL COMMENT '分类ID',
  `keyword` varchar(20) NOT NULL COMMENT '关键字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_recorder
-- ----------------------------
DROP TABLE IF EXISTS `mail_recorder`;
CREATE TABLE `mail_recorder` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `email` varchar(50) NOT NULL COMMENT '目标邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `ipJson` text COMMENT 'IP记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_task
-- ----------------------------
DROP TABLE IF EXISTS `mail_task`;
CREATE TABLE `mail_task` (
  `id` varchar(32) NOT NULL COMMENT '任务ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱帐号',
  `conn_status` tinyint(1) NOT NULL COMMENT '连接状态 0-无状态 1-连接中 2-连接异常',
  `conn_exception_reason` varchar(100) DEFAULT NULL COMMENT '连接异常原因',
  `host` varchar(255) DEFAULT NULL COMMENT '服务器地址',
  `protocol_type` tinyint(1) NOT NULL COMMENT '协议类型 0-爬虫 1-imap 2-pop3 4-exchange',
  `vpn_id` varchar(32) NOT NULL COMMENT '节点ID',
  `monitoring` tinyint(1) NOT NULL COMMENT '是否监控 0-否 1-是',
  `emphasis` tinyint(1) NOT NULL COMMENT '重点关注目标 0-否 1-是',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件任务池，每个用户最多只有20条数据';

-- ----------------------------
-- Table structure for mail_trojan
-- ----------------------------
DROP TABLE IF EXISTS `mail_trojan`;
CREATE TABLE `mail_trojan` (
  `id` varchar(32) NOT NULL COMMENT '木马邮件ID',
  `source` varchar(50) NOT NULL COMMENT '发件人',
  `target` varchar(50) NOT NULL COMMENT '收件人',
  `fake_name` varchar(50) DEFAULT NULL COMMENT '仿冒名',
  `fake_email` varchar(50) DEFAULT NULL COMMENT '仿冒邮箱',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `status` tinyint(1) NOT NULL COMMENT '发送状态 1-发送中 2-发送成功 3-发送失败',
  `trojan_detail_id` varchar(32) NOT NULL COMMENT '邮箱详情ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_trojan_detail
-- ----------------------------
DROP TABLE IF EXISTS `mail_trojan_detail`;
CREATE TABLE `mail_trojan_detail` (
  `id` varchar(32) NOT NULL COMMENT '木马详情ID',
  `content` text NOT NULL COMMENT '内容',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mail_vpn
-- ----------------------------
DROP TABLE IF EXISTS `mail_vpn`;
CREATE TABLE `mail_vpn` (
  `id` varchar(32) NOT NULL COMMENT '节点id',
  `name` varchar(50) NOT NULL COMMENT '节点名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `links` varchar(500) NOT NULL COMMENT '虫洞json',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='链路表';
