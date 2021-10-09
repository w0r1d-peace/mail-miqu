/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mailbox

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-10-09 09:38:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_log
-- ----------------------------
DROP TABLE IF EXISTS `base_log`;
CREATE TABLE `base_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT NULL COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `log` varchar(255) DEFAULT NULL COMMENT '日志信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of base_log
-- ----------------------------
INSERT INTO `base_log` VALUES ('1', '2021-09-30 07:56:47', '2021-09-30 07:56:47', '0', '2', 'secxun2019', '用户 secxun2019 操作了【新增用户】功能');
INSERT INTO `base_log` VALUES ('2', '2021-09-30 08:01:38', '2021-09-30 08:01:38', '0', '2', 'secxun2019', '用户 secxun2019 操作了【新增用户】功能');
INSERT INTO `base_log` VALUES ('3', '2021-09-30 08:04:02', '2021-09-30 08:04:02', '0', '2', 'secxun2019', '用户 secxun2019 操作了【新增用户】功能');

-- ----------------------------
-- Table structure for base_mail_account
-- ----------------------------
DROP TABLE IF EXISTS `base_mail_account`;
CREATE TABLE `base_mail_account` (
  `id` varchar(32) NOT NULL COMMENT '系统账号ID',
  `account` varchar(50) NOT NULL COMMENT '邮箱账号',
  `password` varchar(50) NOT NULL COMMENT '邮箱密码',
  `server` varchar(30) DEFAULT NULL COMMENT '邮箱服务器',
  `host` varchar(255) DEFAULT NULL COMMENT 'STMP服务器地址',
  `port` int(11) DEFAULT NULL COMMENT 'STMP服务器端口',
  `status` tinyint(1) NOT NULL COMMENT '账号状态 0-不可用 1-可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT NULL COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of base_mail_account
-- ----------------------------

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
-- Records of base_permission
-- ----------------------------

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
-- Records of base_permission_role
-- ----------------------------

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
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', '管理员', '0', '1', '2019-06-12 18:34:40', null, '0', '1', null);
INSERT INTO `base_role` VALUES ('2', '普通用户', '0', '1', '2019-06-12 18:35:19', null, '0', '1', null);

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
-- Records of base_role_user
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'secxun2019', '12ac32573ecfeb245fe84c631646a835', 'Administrator', null, 'Administrator', null, '1998-12-31 16:00:00', null, '0', '0', 'Administrator');

-- ----------------------------
-- Table structure for mail_bomb
-- ----------------------------
DROP TABLE IF EXISTS `mail_bomb`;
CREATE TABLE `mail_bomb` (
  `id` varchar(32) NOT NULL COMMENT '邮件轰炸ID',
  `target` varchar(50) NOT NULL COMMENT '目标邮箱',
  `email_number` int(8) NOT NULL COMMENT '邮件数量',
  `account` varchar(255) DEFAULT NULL COMMENT '目标邮箱',
  `account_number` int(8) NOT NULL COMMENT '账号数量',
  `status` tinyint(1) NOT NULL COMMENT '任务状态 1-进行中 2-已完成 3-已终止',
  `exception_reason` varchar(255) DEFAULT NULL COMMENT '异常原因',
  `send_number` int(8) DEFAULT NULL COMMENT '发送数量',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mail_bomb
-- ----------------------------

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
-- Records of mail_category
-- ----------------------------

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
-- Records of mail_category_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for mail_header
-- ----------------------------
DROP TABLE IF EXISTS `mail_header`;
CREATE TABLE `mail_header` (
  `email_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `uid` varchar(180) DEFAULT NULL COMMENT '邮件唯一id，相对于账户的唯一id',
  `fromer` varchar(255) DEFAULT NULL COMMENT '发件人',
  `receiver` varchar(3000) DEFAULT NULL COMMENT '收件人JSON，按name=>value,email=>value的键值对形式存入',
  `bcc` varchar(3000) DEFAULT NULL COMMENT '密送人JSON，格式和收件人一样',
  `cc` varchar(3000) DEFAULT NULL COMMENT '抄送人JSON，格式和收件人一样',
  `folder` varchar(100) DEFAULT NULL COMMENT '所属文件夹',
  `has_read` tinyint(1) DEFAULT NULL COMMENT '是否已读 1 表示是，0 表示否',
  `has_attachment` tinyint(1) DEFAULT NULL COMMENT '是否包含附件 1 表示是，0 表示否',
  `send_date` datetime DEFAULT NULL COMMENT '发送日期',
  `email` varchar(50) DEFAULT NULL COMMENT '所属邮箱',
  `title` varchar(1000) DEFAULT NULL COMMENT '邮箱标题',
  `eml_path` varchar(2000) DEFAULT NULL COMMENT '原始邮件存储路径',
  `has_top` tinyint(1) DEFAULT NULL COMMENT '是否为置顶文件 1表示是，0表示否',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间，冗余字段',
  `user_id` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `creator` varchar(255) DEFAULT NULL COMMENT '所属用户名，冗余字段，提高查询性能',
  `removed` tinyint(2) DEFAULT NULL COMMENT '逻辑删除 1-是 0-否',
  PRIMARY KEY (`email_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件头';

-- ----------------------------
-- Records of mail_header
-- ----------------------------

-- ----------------------------
-- Table structure for mail_host
-- ----------------------------
DROP TABLE IF EXISTS `mail_host`;
CREATE TABLE `mail_host` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain` varchar(100) DEFAULT NULL COMMENT '域名',
  `pop_host` varchar(255) DEFAULT NULL COMMENT 'pop3服务器地址',
  `imap_host` varchar(255) DEFAULT NULL COMMENT 'imap服务器地址',
  `exchange_host` varchar(255) DEFAULT NULL COMMENT 'exchange服务器地址，exchange有自动识别的方法，此字段作为备用字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `smtp_host` varchar(255) DEFAULT NULL COMMENT 'smtp服务器地址',
  `imap_port` int(11) DEFAULT NULL COMMENT 'imap端口号',
  `pop_port` int(11) DEFAULT NULL COMMENT 'pop端口号',
  `smtp_port` int(11) DEFAULT NULL COMMENT 'smtp端口号',
  `exchange_port` int(11) DEFAULT '-1' COMMENT '冗余字段',
  `custom` varchar(255) DEFAULT NULL COMMENT '自定义协议顺序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `domain` (`domain`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件服务器查询表，后期做成远程更新，供查询服务器主机和端口。exchange无需设置，有自动寻找的功能。不再使用nslookup去对比查询。';

-- ----------------------------
-- Records of mail_host
-- ----------------------------
INSERT INTO `mail_host` VALUES ('1', 'vip.qq.com', 'pop.qq.com', 'imap.qq.com', '', '2018-12-12 04:11:45', '2018-12-12 04:11:45', '0', 'smtp.qq.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('2', 'oxfam.org.hk', '', '', '', '2019-03-01 12:25:06', '2019-03-01 12:25:06', '0', '', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('4', 'gmail.com', 'pop.gmail.com', 'imap.gmail.com', '', '2018-12-12 04:13:55', '2018-12-12 04:13:55', '0', 'smtp.gmail.com', '993', '995', '587', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('5', 'yeah.net', 'pop.yeah.net', 'imap.yeah.net', '', '2018-12-12 02:33:17', '2018-12-12 02:33:17', '0', 'smtp.yeah.net', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('6', 'tom.com', 'pop.tom.com', '', '', '2018-12-12 04:18:07', '2018-12-12 04:18:07', '0', 'smtp.tom.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('7', '21cn.com', 'pop.21cn.com', 'imap.21cn.com', '', '2018-12-12 04:25:04', '2018-12-12 04:25:04', '0', 'smtp.21cn.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('8', 'outlook.com', 'pop-mail.outlook.com', 'imap-mail.outlook.com', '', '2018-12-12 04:16:22', '2018-12-12 04:16:22', '0', 'smtp-mail.outlook.com', '993', '995', '465', '-1', '3,2,1');
INSERT INTO `mail_host` VALUES ('9', 'hotmail.com', 'pop-mail.outlook.com', 'imap-mail.outlook.com', '', '2018-12-12 04:16:22', '2018-12-12 04:16:22', '0', 'smtp-mail.outlook.com', '993', '995', '465', '-1', '3,2,1');
INSERT INTO `mail_host` VALUES ('10', 'qq.com', 'pop.qq.com', 'imap.qq.com', '', '2018-12-12 02:36:55', '2018-12-12 02:36:55', '0', 'smtp.qq.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('11', '139.com', 'pop.139.com', 'imap.139.com', '', '2018-12-12 04:04:07', '2018-12-12 04:04:07', '0', 'smtp.139.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('12', 'sina.com', 'pop.sina.com', 'imap.sina.com', '', '2018-12-12 02:37:28', '2018-12-12 02:37:28', '0', 'smtp.sina.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('13', 'sohu.com', 'pop3.sohu.com', '', '', '2018-12-12 03:51:33', '2018-12-12 03:51:33', '0', 'smtp.sohu.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('14', 'yahoo.com', 'pop.mail.yahoo.com', '', '', '2018-12-12 04:27:39', '2018-12-12 04:27:39', '0', 'smtp.mail.yahoo.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('15', '163.com', 'pop.163.com', 'imap.163.com', '', '2018-12-12 02:26:46', '2018-12-12 02:26:46', '0', 'smtp.163.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('16', '126.com', 'pop3.126.com', 'imap.126.com', '', '2018-12-12 02:27:21', '2018-12-12 02:27:21', '0', 'smtp.126.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('17', '189.cn', 'pop.189.cn', 'imap.189.cn', '', '2018-12-12 04:05:51', '2018-12-12 04:05:51', '0', 'smtp.189.cn', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('18', 'foxmail.com', 'pop.qq.com', 'imap.qq.com', '', '2018-12-12 04:12:52', '2018-12-12 04:12:52', '0', 'smtp.qq.com', '993', '995', '465', '-1', '2,1');
INSERT INTO `mail_host` VALUES ('19', 'chinaren.com', 'pop.chinaren.com', '', '', '2018-12-12 04:30:12', '2018-12-12 04:30:12', '0', '', '993', '995', '465', '-1', '2,1');

-- ----------------------------
-- Table structure for mail_information
-- ----------------------------
DROP TABLE IF EXISTS `mail_information`;
CREATE TABLE `mail_information` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `email_id` bigint(20) NOT NULL COMMENT '所属邮件ID',
  `fromer` varchar(50) DEFAULT NULL COMMENT '发件人',
  `email` varchar(50) NOT NULL COMMENT '目标邮箱',
  `has_read` tinyint(1) DEFAULT NULL COMMENT '是否已读 1 表示是，0 表示否',
  `has_top` tinyint(1) DEFAULT NULL COMMENT '是否为置顶文件 1表示是，0表示否',
  `has_emphasis` tinyint(1) DEFAULT NULL COMMENT '重点关注目标 0-否 1-是',
  `title` varchar(255) DEFAULT NULL COMMENT '主题',
  `send_date` datetime DEFAULT NULL COMMENT '发送时间',
  `has_match_title` tinyint(1) DEFAULT '0' COMMENT '匹配关键字在主题 0-不匹配 1-匹配',
  `has_match_content` tinyint(1) DEFAULT '0' COMMENT '匹配关键字在内容 0-不匹配 1-匹配',
  `has_match_attachment` tinyint(1) DEFAULT '0' COMMENT '匹配关键字在附件 0-不匹配 1-匹配',
  `match_category` varchar(255) DEFAULT NULL COMMENT '匹配分类',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mail_information
-- ----------------------------

-- ----------------------------
-- Table structure for mail_location
-- ----------------------------
DROP TABLE IF EXISTS `mail_location`;
CREATE TABLE `mail_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uuid` varchar(255) DEFAULT NULL COMMENT 'UUID',
  `account_id` bigint(20) DEFAULT NULL COMMENT '发送邮箱账号ID',
  `source` varchar(255) DEFAULT NULL COMMENT '发送邮箱账号',
  `target` varchar(255) DEFAULT NULL COMMENT '目标邮箱账号',
  `status` tinyint(4) DEFAULT NULL COMMENT '定位状态 0-未定位 1-已定位',
  `vpn_id` bigint(20) DEFAULT NULL COMMENT '安全链路',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` bigint(2) DEFAULT NULL COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `ipJson` varchar(255) DEFAULT NULL COMMENT 'IP记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of mail_location
-- ----------------------------

-- ----------------------------
-- Table structure for mail_message
-- ----------------------------
DROP TABLE IF EXISTS `mail_message`;
CREATE TABLE `mail_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) NOT NULL COMMENT '信息内容',
  `type` tinyint(1) NOT NULL COMMENT '消息类型 1-邮件拉取 2-舆情监控 3-邮件定位 4-邮件轰炸',
  `has_read` tinyint(1) NOT NULL COMMENT '是否已读 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mail_message
-- ----------------------------

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
-- Records of mail_recorder
-- ----------------------------

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
  `password` varchar(255) DEFAULT NULL COMMENT '邮箱密码',
  `conn_status` tinyint(1) NOT NULL COMMENT '连接状态 0-无状态 1-连接中 2-连接异常',
  `conn_exception_reason` varchar(100) DEFAULT NULL COMMENT '连接异常原因',
  `port` int(11) DEFAULT NULL COMMENT '服务器端口',
  `host` varchar(255) DEFAULT NULL COMMENT '服务器地址',
  `has_ssl` tinyint(1) NOT NULL COMMENT '是否支持SSL 0-否 1-是',
  `protocol_type` tinyint(1) NOT NULL COMMENT '协议类型 0-爬虫 1-imap 2-pop3 4-exchange',
  `vpn_id` varchar(32) NOT NULL COMMENT '节点ID',
  `has_monitoring` tinyint(1) NOT NULL COMMENT '是否监控 0-否 1-是',
  `has_emphasis` tinyint(1) NOT NULL COMMENT '重点关注目标 0-否 1-是',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `read_number` int(11) DEFAULT NULL COMMENT '已读邮件数',
  `un_read_number` int(11) DEFAULT NULL COMMENT '未读邮件数',
  `sensitive_number` int(11) DEFAULT NULL COMMENT '敏感邮件数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件任务池，每个用户最多只有20条数据';

-- ----------------------------
-- Records of mail_task
-- ----------------------------

-- ----------------------------
-- Table structure for mail_trojan
-- ----------------------------
DROP TABLE IF EXISTS `mail_trojan`;
CREATE TABLE `mail_trojan` (
  `id` varchar(32) NOT NULL COMMENT '木马邮件ID',
  `account_id` bigint(20) DEFAULT NULL COMMENT '发件账号ID',
  `source` varchar(50) NOT NULL COMMENT '发件人',
  `target` varchar(50) NOT NULL COMMENT '收件人',
  `mail_sploit` int(11) DEFAULT NULL COMMENT '是否开启仿冒邮箱',
  `mail_sploit_name` varchar(50) DEFAULT NULL COMMENT '仿冒名',
  `mail_sploit_email` varchar(50) DEFAULT NULL COMMENT '仿冒邮箱',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `vpn_id` bigint(20) DEFAULT NULL COMMENT '安全链路',
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
-- Records of mail_trojan
-- ----------------------------

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
-- Records of mail_trojan_detail
-- ----------------------------

-- ----------------------------
-- Table structure for mail_vpn
-- ----------------------------
DROP TABLE IF EXISTS `mail_vpn`;
CREATE TABLE `mail_vpn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `name` varchar(50) NOT NULL COMMENT '节点名称',
  `node_group` varchar(255) DEFAULT NULL COMMENT '节点组合',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
  `links` varchar(500) NOT NULL COMMENT '虫洞json',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='链路表';

-- ----------------------------
-- Records of mail_vpn
-- ----------------------------
INSERT INTO `mail_vpn` VALUES ('1', '新加坡节点链路', '新加坡1>>新加坡2>>新加坡3', '2021-09-28 03:09:31', '2021-09-28 03:09:31', '0', '[\"asset://149.28.155.23:65530/socks5\",\"asset://45.32.118.33:65530/socks5\",\"asset://45.76.145.54:65530/socks5\"]', '0', null);
