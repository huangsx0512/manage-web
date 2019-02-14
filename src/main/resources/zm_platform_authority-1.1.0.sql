/*
Navicat MySQL Data Transfer

Source Server         : 47.107.185.57（aliOS）
Source Server Version : 50626
Source Host           : 47.107.185.57:3306
Source Database       : zm_platform_authority

Target Server Type    : MYSQL
Target Server Version : 50626m
File Encoding         : 65001

Date: 2019-02-14 17:46:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(16) DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(256) DEFAULT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '账号状态:0=未启用，1=已启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'test', 'E10ADC3949BA59ABBE56E057F20F883E', '测试账号', '2017-09-14 17:25:13', '2019-01-08 11:45:09', '1');
INSERT INTO `account` VALUES ('2', 'zimmur', 'CC03E747A6AFBBCBF8BE7668ACFEBEE5', 'zimmur测试账号', '2017-09-15 17:25:13', '2017-09-23 10:02:43', '1');
INSERT INTO `account` VALUES ('3', 'test1', '123', '测试', '2017-09-15 13:54:21', null, '1');

-- ----------------------------
-- Table structure for account_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_permission`;
CREATE TABLE `account_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT '链接地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `father_id` int(11) DEFAULT '0' COMMENT '父权限ID',
  `type` varchar(16) DEFAULT NULL COMMENT '根菜单=root，菜单=ment，功能=method',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_permission
-- ----------------------------

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` tinyint(4) DEFAULT '2' COMMENT '角色类型：1=管理员角色，2=普通角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES ('1', '系统管理', '1');
INSERT INTO `account_role` VALUES ('2', '应用管理', '2');
INSERT INTO `account_role` VALUES ('3', '测试角色1', '2');
INSERT INTO `account_role` VALUES ('4', '测试角色2', '2');

-- ----------------------------
-- Table structure for account_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_role_permission`;
CREATE TABLE `account_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for account_user_role
-- ----------------------------
DROP TABLE IF EXISTS `account_user_role`;
CREATE TABLE `account_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户ID',
  `account_role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_user_role
-- ----------------------------
INSERT INTO `account_user_role` VALUES ('1', '1', '1');
INSERT INTO `account_user_role` VALUES ('2', '1', '2');
INSERT INTO `account_user_role` VALUES ('3', '2', '1');
INSERT INTO `account_user_role` VALUES ('4', '2', '2');

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `app_key` varchar(256) DEFAULT NULL,
  `is_ip` tinyint(4) DEFAULT '0' COMMENT '0=不限制，1=限制',
  `ips` varchar(256) DEFAULT NULL,
  `is_enabled` tinyint(4) DEFAULT '0' COMMENT '0=未启用，1=已启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '20170906-1', 'IOSapp', 'IOS', '1', 'http://192.168.1.21', '1');
INSERT INTO `app` VALUES ('9', 'tianmaoapp', '天猫应用', '20170911', '0', 'http://192.168.12.11', '1');
INSERT INTO `app` VALUES ('10', 'jingdongapp', '京东应用', '20171001', '0', 'http://192.169.1.1', '1');
INSERT INTO `app` VALUES ('11', 'mielishuoapp', '美丽说app', '20171002', '0', 'http://', '1');
INSERT INTO `app` VALUES ('14', 'jiuxianwang', '酒仙网', '2017091203', '0', 'http://localhost', '0');
INSERT INTO `app` VALUES ('15', 'dangdangwang', '当当网', '2017091204', '0', 'http：//', '1');
INSERT INTO `app` VALUES ('16', 'suning', '苏宁app', '20170905', '0', 'http', '0');
INSERT INTO `app` VALUES ('17', 'jvhuasuan', '聚划算app', '2017091206', '0', 'http://', '1');
INSERT INTO `app` VALUES ('18', 'fenhuanwang', '凤凰网', '20170912', '0', 'http', '0');
INSERT INTO `app` VALUES ('19', 'jinritejia', '今日特价', '2017091206', '0', 'http://localhost', '0');
INSERT INTO `app` VALUES ('20', 'yamaxun', '亚马逊', '2017091207', '0', 'http://localhost', '1');
INSERT INTO `app` VALUES ('21', 'tiantaintejia', '天天特价app', '2017091208', '0', 'http://', '0');
INSERT INTO `app` VALUES ('22', 'dingdangwang', '叮当网', '20170912', '0', 'http://localhost', '1');
INSERT INTO `app` VALUES ('23', 'crmApp', 'crm客户端', '20170914', '0', 'http://localhost', '0');
INSERT INTO `app` VALUES ('24', 'wogeapp', '沃格应用app', '沃格', '0', 'http://', '1');
INSERT INTO `app` VALUES ('25', 'tuangouwangapp', '团购网', '20170920', '0', 'http://localhost', '1');
INSERT INTO `app` VALUES ('26', 'baihwangApp', '百合网', '20170920百合', '0', 'http://localhost', '0');
INSERT INTO `app` VALUES ('27', 'weipinghui', '唯品会', '20170920唯品会', '0', 'http://localhost', '0');
INSERT INTO `app` VALUES ('28', 'tejiawang', '特价网', 'tejiawang20170920', '0', 'http://', '0');
INSERT INTO `app` VALUES ('29', 'mogujie', '蘑菇街', '蘑菇街20170920', '0', 'http://', '0');
INSERT INTO `app` VALUES ('31', 'huabeiwang', '花呗网', '花呗0920', '0', 'http', '1');

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `app_role` VALUES ('1', '1', '3');
INSERT INTO `app_role` VALUES ('2', '1', '4');
INSERT INTO `app_role` VALUES ('3', '1', '5');
INSERT INTO `app_role` VALUES ('7', '22', '3');
INSERT INTO `app_role` VALUES ('8', '22', '4');
INSERT INTO `app_role` VALUES ('9', '22', '5');
INSERT INTO `app_role` VALUES ('13', '20', '3');
INSERT INTO `app_role` VALUES ('14', '20', '4');
INSERT INTO `app_role` VALUES ('18', '21', '3');
INSERT INTO `app_role` VALUES ('19', '21', '4');
INSERT INTO `app_role` VALUES ('20', '16', '3');
INSERT INTO `app_role` VALUES ('21', '16', '4');
INSERT INTO `app_role` VALUES ('22', '16', '5');
INSERT INTO `app_role` VALUES ('31', '24', '3');
INSERT INTO `app_role` VALUES ('32', '24', '4');
INSERT INTO `app_role` VALUES ('36', '25', '3');
INSERT INTO `app_role` VALUES ('37', '25', '4');
INSERT INTO `app_role` VALUES ('38', '25', '5');
INSERT INTO `app_role` VALUES ('45', '26', '3');
INSERT INTO `app_role` VALUES ('46', '26', '4');
INSERT INTO `app_role` VALUES ('47', '27', '3');
INSERT INTO `app_role` VALUES ('48', '27', '10');
INSERT INTO `app_role` VALUES ('58', '29', '8');
INSERT INTO `app_role` VALUES ('60', '28', '10');
INSERT INTO `app_role` VALUES ('65', '31', '8');
INSERT INTO `app_role` VALUES ('66', '19', '3');
INSERT INTO `app_role` VALUES ('67', '19', '4');

-- ----------------------------
-- Table structure for ment
-- ----------------------------
DROP TABLE IF EXISTS `ment`;
CREATE TABLE `ment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_server` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `url` varchar(32) DEFAULT NULL,
  `method` varchar(16) DEFAULT NULL,
  `status` smallint(6) DEFAULT '0' COMMENT '0=未启用，1=已启用，2=已废弃，3=已删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ment
-- ----------------------------
INSERT INTO `ment` VALUES ('1', 'product', '查询商品信息权限', 'http://localhost:9120', 'query', '1', '2017-09-06 14:46:28');
INSERT INTO `ment` VALUES ('3', 'queryOrderInfo', '查询商品订单信息权限', 'http://localhost:9120', 'queryOrderInfo', '1', '2017-09-07 10:51:47');
INSERT INTO `ment` VALUES ('5', 'queryPurOrder', '查询采购订单权限', 'http://localhost', 'queryPurOrder', '0', '2017-09-20 15:01:46');
INSERT INTO `ment` VALUES ('6', 'purchaseReturnServcie', '查询次品返修订单的权限', 'http://localhost', 'queryReturnInfo', '1', '2017-09-21 09:34:26');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `is_enabled` tinyint(4) DEFAULT '0' COMMENT '0=未启用，1=已启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('3', '铂金VIP应用', '1');
INSERT INTO `role` VALUES ('4', '钻石VIP应用', '1');
INSERT INTO `role` VALUES ('5', '普通应用', '1');
INSERT INTO `role` VALUES ('7', '二级会员', '1');
INSERT INTO `role` VALUES ('8', '一级会员', '1');
INSERT INTO `role` VALUES ('9', '三级会员', '1');
INSERT INTO `role` VALUES ('10', '四级会员', '1');

-- ----------------------------
-- Table structure for role_ment
-- ----------------------------
DROP TABLE IF EXISTS `role_ment`;
CREATE TABLE `role_ment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `ment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_ment
-- ----------------------------
INSERT INTO `role_ment` VALUES ('1', '3', '1');
INSERT INTO `role_ment` VALUES ('2', '3', '3');
INSERT INTO `role_ment` VALUES ('3', '4', '1');
INSERT INTO `role_ment` VALUES ('4', '4', '3');
INSERT INTO `role_ment` VALUES ('6', '5', '1');
INSERT INTO `role_ment` VALUES ('15', '7', '1');
INSERT INTO `role_ment` VALUES ('16', '7', '3');
INSERT INTO `role_ment` VALUES ('17', '8', '1');
INSERT INTO `role_ment` VALUES ('18', '8', '3');
INSERT INTO `role_ment` VALUES ('35', '9', '1');
INSERT INTO `role_ment` VALUES ('36', '9', '3');
INSERT INTO `role_ment` VALUES ('37', '9', '5');
INSERT INTO `role_ment` VALUES ('40', '10', '1');
