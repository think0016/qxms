/*
Navicat MySQL Data Transfer

Source Server         : 192.168.12.106
Source Server Version : 50516
Source Host           : 192.168.12.106:3306
Source Database       : qx

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2017-02-06 17:50:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qx_department
-- ----------------------------
DROP TABLE IF EXISTS `qx_department`;
CREATE TABLE `qx_department` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `parent_did` int(11) NOT NULL COMMENT '父级编号',
  `parent_dids` varchar(255) NOT NULL DEFAULT '' COMMENT '所有父级编号',
  `dname` varchar(255) NOT NULL COMMENT '部门名称',
  `manager` varchar(100) DEFAULT NULL COMMENT '负责人',
  `sort` int(11) NOT NULL DEFAULT '30' COMMENT '排序',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记（0:正常1:删除）',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_department
-- ----------------------------
INSERT INTO `qx_department` VALUES ('10001', '0', '0', '软信公司', 'LQ', '30', '1', '2016-12-13 17:13:16', '', '0');
INSERT INTO `qx_department` VALUES ('10002', '10001', '0,10001', '开发部', 'LQ', '30', '1', '2016-12-08 15:51:48', '', '0');
INSERT INTO `qx_department` VALUES ('10003', '10001', '0,10001', '市场部', 'LQ', '30', '1', '2016-12-08 15:53:12', '', '0');
INSERT INTO `qx_department` VALUES ('10004', '10001', '0,10001', '产品部', 'LQ', '30', '1', '2016-12-13 16:12:53', '', '0');
INSERT INTO `qx_department` VALUES ('10008', '10001', '0,10001', '综合部', '张三', '50', '1', '2016-12-16 17:46:13', 'fff', '0');
INSERT INTO `qx_department` VALUES ('20001', '10001', '0,10001', '财务部', 'aaa', '30', '1', '2017-01-17 10:41:21', 'gbgbg', '1');
INSERT INTO `qx_department` VALUES ('20002', '10001', '0,10001', 'CDC', 'aaa', '30', '1', '2017-01-17 17:04:38', '', '1');

-- ----------------------------
-- Table structure for qx_menu
-- ----------------------------
DROP TABLE IF EXISTS `qx_menu`;
CREATE TABLE `qx_menu` (
  `menuid` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `parent_ids` varchar(255) NOT NULL,
  `mname` varchar(255) NOT NULL,
  `sort` tinyint(4) NOT NULL DEFAULT '30' COMMENT '排序',
  `href` varchar(255) NOT NULL DEFAULT '' COMMENT '链接',
  `icon` varchar(255) DEFAULT '' COMMENT '图标',
  `mtype` tinyint(4) DEFAULT '0' COMMENT '菜单类型(0菜单1按钮)',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_menu
-- ----------------------------
INSERT INTO `qx_menu` VALUES ('10001', '0', '0', '顶级菜单', '30', '', '', '10', null, null, null, '0');
INSERT INTO `qx_menu` VALUES ('10002', '10001', '0,10001', '系统设置', '30', '', '', '0', null, null, '', '0');
INSERT INTO `qx_menu` VALUES ('10003', '10002', '0,10001,10002', '用户中心', '31', '', '', '0', null, null, '', '0');
INSERT INTO `qx_menu` VALUES ('10004', '10002', '0,10001,10002', '角色管理', '30', '/role/list', '', '0', null, null, null, '0');
INSERT INTO `qx_menu` VALUES ('10008', '10002', '0,10001,10002', '组织机构', '30', '/department/list', '', '0', '1', '2016-12-19 17:31:27', '', '0');
INSERT INTO `qx_menu` VALUES ('10009', '10002', '0,10001,10002', '菜单管理', '30', '/menu/list', '', '0', '1', '2016-12-19 17:33:29', '', '0');
INSERT INTO `qx_menu` VALUES ('10011', '10003', '0,10001,10002,10003', '用户管理', '30', '/user/list', '', '0', '1', '2016-12-27 14:45:52', '', '0');
INSERT INTO `qx_menu` VALUES ('10012', '10003', '0,10001,10002,10003', '密码重置', '30', '/user/pwform', '', '0', '1', '2016-12-27 14:46:21', '', '0');
INSERT INTO `qx_menu` VALUES ('10013', '10011', '0,10001,10002,10003,10011', '添加/修改', '30', '/user/form', '', '1', '1', '2016-12-28 09:28:12', '', '0');
INSERT INTO `qx_menu` VALUES ('10014', '10004', '0,10001,10002,10004', '添加/修改', '30', '/role/form', '', '1', '1', '2016-12-28 09:34:39', '', '0');
INSERT INTO `qx_menu` VALUES ('10015', '10008', '0,10001,10002,10008', '添加/修改', '30', '/department/form', '', '1', '1', '2016-12-28 09:35:00', '', '0');
INSERT INTO `qx_menu` VALUES ('10016', '10009', '0,10001,10002,10009', '添加/修改', '30', '/menu/form', '', '1', '1', '2016-12-28 09:35:13', '', '0');
INSERT INTO `qx_menu` VALUES ('10017', '10004', '0,10001,10002,10004', '功能授权', '30', '/role/grantform', '', '1', '1', '2016-12-28 16:48:27', '', '0');

-- ----------------------------
-- Table structure for qx_role
-- ----------------------------
DROP TABLE IF EXISTS `qx_role`;
CREATE TABLE `qx_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL COMMENT '角色名称',
  `role_type` varchar(255) DEFAULT '' COMMENT '角色类型',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_role
-- ----------------------------
INSERT INTO `qx_role` VALUES ('1', '超级管理员', 'global', '1', '2016-12-08 16:00:22', 'ddd', '0');
INSERT INTO `qx_role` VALUES ('2', '普通用户', '', '1', '2016-12-12 10:11:27', 'zxc', '0');
INSERT INTO `qx_role` VALUES ('3', '微信管理员', '', '1', '2016-12-28 18:06:45', '', '0');
INSERT INTO `qx_role` VALUES ('4', '测试角色fff', '', '1', '2017-01-13 10:25:48', '', '1');
INSERT INTO `qx_role` VALUES ('5', '测试角色', '', '1', '2017-01-13 10:30:48', 'YUYUYU', '1');
INSERT INTO `qx_role` VALUES ('6', 'cdcdc', '', '1', '2017-01-17 16:51:47', 'cdcdc', '1');
INSERT INTO `qx_role` VALUES ('7', '测试角色', '', '1', '2017-01-18 10:46:55', 'ddd', '1');

-- ----------------------------
-- Table structure for qx_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `qx_role_menu`;
CREATE TABLE `qx_role_menu` (
  `rmid` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`rmid`),
  KEY `rid_mid1` (`role_id`),
  KEY `rid_mid2` (`menu_id`),
  CONSTRAINT `rid_mid1` FOREIGN KEY (`role_id`) REFERENCES `qx_role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rid_mid2` FOREIGN KEY (`menu_id`) REFERENCES `qx_menu` (`menuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_role_menu
-- ----------------------------
INSERT INTO `qx_role_menu` VALUES ('41', '2', '10015');
INSERT INTO `qx_role_menu` VALUES ('42', '2', '10008');
INSERT INTO `qx_role_menu` VALUES ('43', '2', '10012');
INSERT INTO `qx_role_menu` VALUES ('44', '2', '10003');
INSERT INTO `qx_role_menu` VALUES ('45', '2', '10002');
INSERT INTO `qx_role_menu` VALUES ('46', '2', '10001');
INSERT INTO `qx_role_menu` VALUES ('53', '4', '10016');
INSERT INTO `qx_role_menu` VALUES ('54', '4', '10009');
INSERT INTO `qx_role_menu` VALUES ('55', '4', '10015');
INSERT INTO `qx_role_menu` VALUES ('56', '4', '10008');
INSERT INTO `qx_role_menu` VALUES ('57', '4', '10017');
INSERT INTO `qx_role_menu` VALUES ('58', '4', '10014');
INSERT INTO `qx_role_menu` VALUES ('59', '4', '10004');
INSERT INTO `qx_role_menu` VALUES ('60', '4', '10012');
INSERT INTO `qx_role_menu` VALUES ('61', '4', '10013');
INSERT INTO `qx_role_menu` VALUES ('62', '4', '10011');
INSERT INTO `qx_role_menu` VALUES ('63', '4', '10003');
INSERT INTO `qx_role_menu` VALUES ('64', '4', '10002');
INSERT INTO `qx_role_menu` VALUES ('65', '4', '10001');
INSERT INTO `qx_role_menu` VALUES ('87', '3', '10012');
INSERT INTO `qx_role_menu` VALUES ('88', '3', '10013');
INSERT INTO `qx_role_menu` VALUES ('89', '3', '10011');
INSERT INTO `qx_role_menu` VALUES ('90', '3', '10003');
INSERT INTO `qx_role_menu` VALUES ('91', '3', '10002');
INSERT INTO `qx_role_menu` VALUES ('92', '3', '10001');

-- ----------------------------
-- Table structure for qx_user
-- ----------------------------
DROP TABLE IF EXISTS `qx_user`;
CREATE TABLE `qx_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT '' COMMENT '昵称',
  `turename` varchar(100) DEFAULT '',
  `did` int(11) DEFAULT NULL COMMENT '部门编号',
  `gender` enum('女','男') DEFAULT '男',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT '' COMMENT '手机号',
  `registerdate` datetime DEFAULT NULL,
  `logindate` datetime DEFAULT NULL,
  `status` tinyint(255) DEFAULT '1' COMMENT '状态（1正常0冻结）',
  `headphoto` varchar(255) DEFAULT '',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`login_name`),
  KEY `uid_did` (`did`),
  CONSTRAINT `uid_did` FOREIGN KEY (`did`) REFERENCES `qx_department` (`did`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_user
-- ----------------------------
INSERT INTO `qx_user` VALUES ('1', 'admin', 'ad20a56c5c52a265756c0fef3c3c6734abc82966663146cd4a44e710', '零壹', '零壹', '10001', '男', 'qqqm@qq.com', '', '2016-11-23 09:24:36', '2017-02-06 10:49:07', '1', '/upload/headphoto/1_1486349485165.jpg', 'vvvvvvvvv');
INSERT INTO `qx_user` VALUES ('2', 'cs123', '9d5afcecb08de742e4e7734cecc199abaf4c867ec9bfa6041dce8817', 'DCDC', 'DCDC', '10002', '男', 'dasd', '', '2016-12-20 17:55:43', '2016-12-21 15:08:06', '0', '', 'dfff');
INSERT INTO `qx_user` VALUES ('3', 'cacac', '399e334c4bb21a960c5174d814381471d51ade929d3209e39a6f3067', 'ddd', 'ddd', '10004', '男', 'fas@qq.com', '', '2016-12-27 16:19:28', null, '0', '', 'fff');
INSERT INTO `qx_user` VALUES ('4', 'think0016', '95a73f98cbc5a4f81c872616462ffdca11404afa4174b5070aca5498', '王伟', '王伟', '10001', '男', 'asdasd@qq.com', '', '2016-12-28 15:28:26', '2016-12-28 16:50:20', '1', '', 'tt');
INSERT INTO `qx_user` VALUES ('5', 'think0017', '7596e23357513af73fbf98412f27ea88f7bb088414ed7e4757294278', '刘伟', '刘伟', '10002', '女', '5656@q.com', '', '2016-12-28 17:06:04', '2017-02-06 10:55:55', '1', '', '');
INSERT INTO `qx_user` VALUES ('6', 'think0018', 'a6f0470358a3d88c8afaf5710bd9b918a851e276ba2b56d32a074770', '慕容垂', '慕容垂', '10001', '女', 'eqwe@qq.com', '', '2016-12-28 18:02:11', '2017-01-18 10:58:46', '1', '', '');
INSERT INTO `qx_user` VALUES ('10', 'think0019', '558dd8279820aca84852acf3e15b4dbb11fd31a603afc225180ecb3f', 'Miss', '', '10001', '女', 'asdasd@qq.com', '', '2017-01-13 09:47:49', '2017-01-18 10:47:23', '0', '', 'ffffff');
INSERT INTO `qx_user` VALUES ('100001', 'cs111', '81f9fdde2de0e615d9bc59d1583cefd61e9bd6f82963ea82a4f7b4e4', 'pdd', '', '10001', '男', 'bthth@qq.com', '', '2017-01-17 16:35:09', null, '0', '', '');

-- ----------------------------
-- Table structure for qx_user_role
-- ----------------------------
DROP TABLE IF EXISTS `qx_user_role`;
CREATE TABLE `qx_user_role` (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`urid`),
  KEY `uid_rid1` (`user_id`),
  KEY `uid_rid2` (`role_id`),
  CONSTRAINT `uid_rid1` FOREIGN KEY (`user_id`) REFERENCES `qx_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid_rid2` FOREIGN KEY (`role_id`) REFERENCES `qx_role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qx_user_role
-- ----------------------------
INSERT INTO `qx_user_role` VALUES ('11', '1', '1');
INSERT INTO `qx_user_role` VALUES ('13', '3', '2');
INSERT INTO `qx_user_role` VALUES ('15', '2', '2');
INSERT INTO `qx_user_role` VALUES ('17', '5', '2');
INSERT INTO `qx_user_role` VALUES ('19', '4', '3');
INSERT INTO `qx_user_role` VALUES ('29', '10', '3');
INSERT INTO `qx_user_role` VALUES ('34', '100001', '3');
INSERT INTO `qx_user_role` VALUES ('35', '6', '7');
