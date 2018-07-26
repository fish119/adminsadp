/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : adminadp

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-26 14:15:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_article
-- ----------------------------
DROP TABLE IF EXISTS `article_article`;
CREATE TABLE `article_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_top` bit(1) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sub_title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `thumbnail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgamg6jq0jbdyymg1qg6c1114b` (`category_id`),
  KEY `FKmd66ia851cf819we898q4dqxp` (`author_id`),
  CONSTRAINT `FKgamg6jq0jbdyymg1qg6c1114b` FOREIGN KEY (`category_id`) REFERENCES `article_category` (`id`),
  CONSTRAINT `FKmd66ia851cf819we898q4dqxp` FOREIGN KEY (`author_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of article_article
-- ----------------------------

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hwokd4h9xg8tl23yb004m6tey` (`name`),
  KEY `FK6mogxrytxjptt5e35785ne1n` (`parent_id`),
  CONSTRAINT `FK6mogxrytxjptt5e35785ne1n` FOREIGN KEY (`parent_id`) REFERENCES `article_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES ('7', '2018-05-26 21:51:51', '2018-05-26 21:51:51', '管理制度', '0', null, '\0');
INSERT INTO `article_category` VALUES ('8', '2018-05-26 22:59:26', '2018-05-26 23:09:08', '应急管理', '96', null, '\0');
INSERT INTO `article_category` VALUES ('9', '2018-05-26 22:59:36', '2018-05-26 22:59:36', '应急预案', '0', '8', '\0');
INSERT INTO `article_category` VALUES ('10', '2018-05-26 22:59:51', '2018-05-26 22:59:51', '处置预案', '1', '8', '\0');
INSERT INTO `article_category` VALUES ('11', '2018-05-26 23:09:22', '2018-05-26 23:16:59', '安全检查', '95', null, '\0');
INSERT INTO `article_category` VALUES ('12', '2018-05-26 23:09:38', '2018-05-26 23:09:38', '检查方案', '0', '11', '\0');
INSERT INTO `article_category` VALUES ('13', '2018-05-26 23:09:49', '2018-05-26 23:09:49', '检查总结', '1', '11', '\0');
INSERT INTO `article_category` VALUES ('14', '2018-05-26 23:17:05', '2018-05-26 23:17:05', '培训管理', '94', null, '\0');
INSERT INTO `article_category` VALUES ('15', '2018-05-26 23:17:15', '2018-05-26 23:17:15', '培训计划', '0', '14', '\0');

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text COLLATE utf8_bin NOT NULL,
  `logger_name` varchar(254) COLLATE utf8_bin NOT NULL,
  `level_string` varchar(254) COLLATE utf8_bin NOT NULL,
  `thread_name` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg1` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg2` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg3` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `caller_filename` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_class` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_method` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_line` char(4) COLLATE utf8_bin NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1497 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of logging_event
-- ----------------------------
INSERT INTO `logging_event` VALUES ('1532585632195', 0x436F756C64206E6F742076616C696461746520636F6E66696775726174696F6E2061747472696275746573206173207468652053656375726974794D65746164617461536F7572636520646964206E6F742072657475726E20616E7920617474726962757465732066726F6D20676574416C6C436F6E666967417474726962757465732829, 'site.fish119.adminsadp.security.SecurityInterceptorImple', 'WARN', 'localhost-startStop-1', '0', null, null, null, null, 'AbstractSecurityInterceptor.java', 'org.springframework.security.access.intercept.AbstractSecurityInterceptor', 'afterPropertiesSet', '159', '1494');
INSERT INTO `logging_event` VALUES ('1532585632221', 0x436F756C64206E6F742076616C696461746520636F6E66696775726174696F6E2061747472696275746573206173207468652053656375726974794D65746164617461536F7572636520646964206E6F742072657475726E20616E7920617474726962757465732066726F6D20676574416C6C436F6E666967417474726962757465732829, 'site.fish119.adminsadp.security.SecurityInterceptorImple', 'WARN', 'localhost-startStop-1', '0', null, null, null, null, 'AbstractSecurityInterceptor.java', 'org.springframework.security.access.intercept.AbstractSecurityInterceptor', 'afterPropertiesSet', '159', '1495');
INSERT INTO `logging_event` VALUES ('1532585633233', 0x737072696E672E6A70612E6F70656E2D696E2D7669657720697320656E61626C65642062792064656661756C742E205468657265666F72652C2064617461626173652071756572696573206D617920626520706572666F726D656420647572696E6720766965772072656E646572696E672E204578706C696369746C7920636F6E66696775726520737072696E672E6A70612E6F70656E2D696E2D7669657720746F2064697361626C652074686973207761726E696E67, 'org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration$JpaWebConfiguration$JpaWebMvcConfiguration', 'WARN', 'main', '0', null, null, null, null, 'JpaBaseConfiguration.java', 'org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration$JpaWebConfiguration$JpaWebMvcConfiguration', 'openEntityManagerInViewInterceptor', '235', '1496');

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`event_id`,`i`) USING BTREE,
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of logging_event_exception
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) COLLATE utf8_bin NOT NULL,
  `mapped_value` text COLLATE utf8_bin,
  PRIMARY KEY (`event_id`,`mapped_key`) USING BTREE,
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of logging_event_property
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `only_sa` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jouvtrmxkx4yq25e6vrjnn28` (`parent_id`),
  CONSTRAINT `FK7jouvtrmxkx4yq25e6vrjnn28` FOREIGN KEY (`parent_id`) REFERENCES `sys_authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('1', '2018-04-13 15:39:05', '2018-04-26 14:10:51', '获取后台所需的全局数据，如用户信息、菜单等', 'GET', '全局信息', '0', '/global', '43', '\0');
INSERT INTO `sys_authority` VALUES ('2', '2018-04-14 21:21:42', '2018-04-19 19:33:34', '菜单管理', 'ALL', '菜单管理', '99', 'menu', null, '\0');
INSERT INTO `sys_authority` VALUES ('3', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '获得所有菜单列表', 'GET', '菜单列表', '0', '/setting/menus', '2', '');
INSERT INTO `sys_authority` VALUES ('4', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '保存菜单', 'POST', '保存菜单', '1', '/setting/menus', '2', '');
INSERT INTO `sys_authority` VALUES ('5', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '删除菜单', 'DELETE', '删除菜单', '2', '/setting/menu/*', '2', '');
INSERT INTO `sys_authority` VALUES ('6', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '获得所有权限列表', 'GET', '权限列表', '1', '/setting/authorities', '9', '');
INSERT INTO `sys_authority` VALUES ('7', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '保存权限', 'POST', '保存权限', '1', '/setting/authorities', '9', '');
INSERT INTO `sys_authority` VALUES ('8', '2018-04-14 21:21:42', '2018-04-14 21:21:44', '删除权限', 'DELETE', '删除权限', '1', '/setting/authority/*', '9', '');
INSERT INTO `sys_authority` VALUES ('9', '2018-04-14 21:21:42', '2018-04-19 19:33:45', '权限管理', 'ALL', '权限管理', '100', 'authority', null, '\0');
INSERT INTO `sys_authority` VALUES ('10', '2018-04-19 19:34:21', '2018-04-19 19:34:31', '部门管理', 'ALL', '部门管理', '98', 'department', null, '\0');
INSERT INTO `sys_authority` VALUES ('11', '2018-04-19 19:35:36', '2018-04-21 19:19:55', '获得所有部门列表', 'GET', '部门列表', '0', '/setting/departments', '10', '\0');
INSERT INTO `sys_authority` VALUES ('12', '2018-04-19 19:36:30', '2018-04-21 19:20:00', '保存部门（新增、修改）', 'POST', '保存部门', '1', '/setting/departments', '10', '\0');
INSERT INTO `sys_authority` VALUES ('13', '2018-04-19 19:37:02', '2018-04-21 19:20:06', '删除部门', 'DELETE', '删除部门', '2', '/setting/department/*', '10', '\0');
INSERT INTO `sys_authority` VALUES ('22', '2018-04-20 12:56:57', '2018-04-20 12:56:57', '角色管理', 'ALL', '角色管理', '100', 'role', null, '\0');
INSERT INTO `sys_authority` VALUES ('23', '2018-04-20 13:00:32', '2018-04-20 13:00:32', '获取所有角色列表', 'GET', '角色列表', '0', '/setting/roles', '22', '\0');
INSERT INTO `sys_authority` VALUES ('24', '2018-04-20 13:00:56', '2018-04-20 13:00:56', '保存角色（包括新增、编辑）', 'POST', '保存角色', '1', '/setting/roles', '22', '\0');
INSERT INTO `sys_authority` VALUES ('25', '2018-04-20 13:01:19', '2018-04-20 13:01:19', '删除角色', 'DELETE', '删除角色', '2', '/setting/role/*', '22', '\0');
INSERT INTO `sys_authority` VALUES ('26', '2018-04-22 18:59:28', '2018-04-22 18:59:28', '人员管理', 'ALL', '人员管理', '97', 'userManag', null, '\0');
INSERT INTO `sys_authority` VALUES ('27', '2018-04-22 19:00:03', '2018-04-22 19:00:03', '获得所有人员列表', 'GET', '人员列表', '0', '/setting/users', '26', '\0');
INSERT INTO `sys_authority` VALUES ('28', '2018-04-23 17:40:54', '2018-04-24 14:36:53', '检查用户名是否重复', 'GET', '用户名查重', '1', '/api/user/checkUsernameUnique', '26', '\0');
INSERT INTO `sys_authority` VALUES ('29', '2018-04-24 14:36:37', '2018-04-24 14:36:37', '保存人员，含新增和修改', 'POST', '保存人员', '2', '/setting/users', '26', '\0');
INSERT INTO `sys_authority` VALUES ('30', '2018-04-24 21:10:52', '2018-04-24 21:18:04', '删除人员', 'DELETE', '删除人员', '3', '/setting/user/*', '26', '\0');
INSERT INTO `sys_authority` VALUES ('31', '2018-04-25 10:16:25', '2018-04-25 10:16:25', '', 'POST', '重置密码', '4', '/setting/users/setDefaultPassword', '26', '\0');
INSERT INTO `sys_authority` VALUES ('32', '2018-04-25 11:42:25', '2018-04-25 11:42:44', '', 'ALL', '个人设置', '96', 'profile', null, '\0');
INSERT INTO `sys_authority` VALUES ('33', '2018-04-25 11:43:24', '2018-04-25 11:43:24', '', 'POST', '修改密码', '1', '/setting/profile/changePassword', '32', '\0');
INSERT INTO `sys_authority` VALUES ('34', '2018-04-25 19:55:34', '2018-04-25 19:55:34', '修改个人信息', 'POST', '修改个人信息', '2', '/setting/profile', '32', '\0');
INSERT INTO `sys_authority` VALUES ('35', '2018-04-25 20:29:55', '2018-04-25 20:29:55', '上传头像', 'POST', '上传头像', '3', '/setting/profile/setAvatar', '32', '\0');
INSERT INTO `sys_authority` VALUES ('36', '2018-04-25 20:29:55', '2018-04-26 14:10:56', '登录', 'POST', '登录', '3', '/auth', '43', '\0');
INSERT INTO `sys_authority` VALUES ('37', '2018-04-23 17:40:54', '2018-04-24 14:36:53', '检查昵称是否重复', 'GET', '昵称查重', '1', '/api/user/checkNicknameUnique', '26', '\0');
INSERT INTO `sys_authority` VALUES ('38', '2018-04-23 17:40:54', '2018-04-24 14:36:53', '检查Email是否重复', 'GET', 'Email查重', '1', '/api/user/checkEmailUnique', '26', '\0');
INSERT INTO `sys_authority` VALUES ('39', '2018-04-23 17:40:54', '2018-04-24 14:36:53', '检电话号码是否重复', 'GET', '电话号码查重', '1', '/api/user/checkPhoneUnique', '26', '\0');
INSERT INTO `sys_authority` VALUES ('40', '2018-04-26 13:43:57', '2018-04-26 13:43:57', '', 'ALL', '信息管理', '2', 'article', null, '\0');
INSERT INTO `sys_authority` VALUES ('41', '2018-04-26 13:44:27', '2018-04-26 13:44:27', '保存分类', 'POST', '保存分类', '0', '/article/category', '40', '\0');
INSERT INTO `sys_authority` VALUES ('42', '2018-04-26 14:09:07', '2018-04-26 14:09:15', '删除信息分类', 'DELETE', '删除分类', '1', '/article/category/*', '40', '\0');
INSERT INTO `sys_authority` VALUES ('43', '2018-04-26 14:10:44', '2018-04-26 14:10:44', '', 'ALL', '其他', '96', 'other', null, '\0');

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3c0icpjqmnbs7vsjqwg3cpff3` (`name`),
  KEY `FK5bcsdbqycjdgdvti7a55b3ht1` (`parent_id`),
  CONSTRAINT `FK5bcsdbqycjdgdvti7a55b3ht1` FOREIGN KEY (`parent_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '2018-04-19 19:59:26', '2018-04-24 20:17:07', '总部', '0', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `hide_in_menu` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`),
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '2018-04-13 15:51:57', '2018-04-20 23:45:05', 'setting', '系统设置', 'setting', '100', null, '\0');
INSERT INTO `sys_menu` VALUES ('2', '2018-04-13 15:54:57', '2018-04-21 20:18:36', 'menu-unfold', '菜单管理', 'menu', '0', '1', '\0');
INSERT INTO `sys_menu` VALUES ('3', '2018-04-13 15:54:57', '2018-04-21 20:18:46', 'lock', '权限管理', 'authority', '1', '1', '\0');
INSERT INTO `sys_menu` VALUES ('4', '2018-04-19 19:38:56', '2018-04-20 22:28:44', 'team', '人员机构', 'organization', '99', null, '\0');
INSERT INTO `sys_menu` VALUES ('5', '2018-04-19 19:42:12', '2018-04-26 09:08:00', 'bars', '机构管理', 'depart', '0', '4', '\0');
INSERT INTO `sys_menu` VALUES ('6', '2018-04-20 12:55:34', '2018-04-21 20:18:50', 'solution', '角色管理', 'role', '2', '1', '\0');
INSERT INTO `sys_menu` VALUES ('7', '2018-04-22 18:57:39', '2018-04-22 18:57:39', 'user', '人员管理', 'user', '1', '4', '\0');
INSERT INTO `sys_menu` VALUES ('8', '2018-04-25 10:20:35', '2018-04-25 10:24:24', 'user', '个人设置', 'profiles', '100', null, '\0');
INSERT INTO `sys_menu` VALUES ('9', '2018-04-25 10:23:31', '2018-04-25 10:24:30', 'idcard', '个人信息', 'profile', '0', '8', '\0');
INSERT INTO `sys_menu` VALUES ('10', '2018-04-25 10:35:01', '2018-04-25 10:35:01', 'key', '修改密码', 'changePassword', '1', '8', '\0');
INSERT INTO `sys_menu` VALUES ('11', '2018-04-26 13:12:11', '2018-05-26 18:36:09', 'copy', '信息管理', 'article', '98', null, '\0');
INSERT INTO `sys_menu` VALUES ('12', '2018-04-26 13:15:27', '2018-04-26 13:15:27', 'profile', '类别管理', 'category', '0', '11', '\0');
INSERT INTO `sys_menu` VALUES ('13', '2018-04-26 13:16:12', '2018-04-27 20:18:21', 'form', '信息维护', 'article', '1', '11', '\0');
INSERT INTO `sys_menu` VALUES ('14', '2018-05-18 16:19:29', '2018-05-18 16:19:39', null, '信息编辑', 'edit', '0', '13', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bqy406dtsr7j7d6fawi1ckyn1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2018-04-13 13:21:10', '2018-04-21 20:56:46', '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('2', '2018-04-13 13:21:10', '2018-04-21 20:56:46', '人事部管理员', '1');

-- ----------------------------
-- Table structure for sys_role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authorities`;
CREATE TABLE `sys_role_authorities` (
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `FK9lufow11qo4ioejn7im8i4qaj` (`authority_id`),
  CONSTRAINT `FK9lufow11qo4ioejn7im8i4qaj` FOREIGN KEY (`authority_id`) REFERENCES `sys_authority` (`id`),
  CONSTRAINT `FKlniymbjbgoofxh7g22ic5x3dd` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_authorities
-- ----------------------------
INSERT INTO `sys_role_authorities` VALUES ('1', '1');
INSERT INTO `sys_role_authorities` VALUES ('2', '1');
INSERT INTO `sys_role_authorities` VALUES ('1', '3');
INSERT INTO `sys_role_authorities` VALUES ('1', '4');
INSERT INTO `sys_role_authorities` VALUES ('1', '5');
INSERT INTO `sys_role_authorities` VALUES ('1', '6');
INSERT INTO `sys_role_authorities` VALUES ('1', '7');
INSERT INTO `sys_role_authorities` VALUES ('1', '8');
INSERT INTO `sys_role_authorities` VALUES ('1', '11');
INSERT INTO `sys_role_authorities` VALUES ('1', '12');
INSERT INTO `sys_role_authorities` VALUES ('1', '13');
INSERT INTO `sys_role_authorities` VALUES ('1', '23');
INSERT INTO `sys_role_authorities` VALUES ('1', '24');
INSERT INTO `sys_role_authorities` VALUES ('1', '25');
INSERT INTO `sys_role_authorities` VALUES ('1', '27');
INSERT INTO `sys_role_authorities` VALUES ('1', '28');
INSERT INTO `sys_role_authorities` VALUES ('1', '29');
INSERT INTO `sys_role_authorities` VALUES ('1', '30');
INSERT INTO `sys_role_authorities` VALUES ('1', '31');
INSERT INTO `sys_role_authorities` VALUES ('1', '33');
INSERT INTO `sys_role_authorities` VALUES ('1', '34');
INSERT INTO `sys_role_authorities` VALUES ('1', '35');
INSERT INTO `sys_role_authorities` VALUES ('1', '36');
INSERT INTO `sys_role_authorities` VALUES ('1', '41');
INSERT INTO `sys_role_authorities` VALUES ('1', '42');

-- ----------------------------
-- Table structure for sys_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menus`;
CREATE TABLE `sys_role_menus` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK6ye9n219h3egm4csthaaxt6qm` (`menu_id`),
  CONSTRAINT `FK6ye9n219h3egm4csthaaxt6qm` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKt8curcxjlubvnbrj60h8dmuts` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_menus
-- ----------------------------
INSERT INTO `sys_role_menus` VALUES ('1', '1');
INSERT INTO `sys_role_menus` VALUES ('1', '2');
INSERT INTO `sys_role_menus` VALUES ('1', '3');
INSERT INTO `sys_role_menus` VALUES ('1', '4');
INSERT INTO `sys_role_menus` VALUES ('2', '4');
INSERT INTO `sys_role_menus` VALUES ('1', '5');
INSERT INTO `sys_role_menus` VALUES ('2', '5');
INSERT INTO `sys_role_menus` VALUES ('1', '6');
INSERT INTO `sys_role_menus` VALUES ('1', '7');
INSERT INTO `sys_role_menus` VALUES ('1', '8');
INSERT INTO `sys_role_menus` VALUES ('1', '9');
INSERT INTO `sys_role_menus` VALUES ('1', '10');
INSERT INTO `sys_role_menus` VALUES ('1', '11');
INSERT INTO `sys_role_menus` VALUES ('1', '12');
INSERT INTO `sys_role_menus` VALUES ('1', '13');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `is_account_non_expired` tinyint(1) DEFAULT '1',
  `is_account_non_locked` tinyint(1) DEFAULT '1',
  `is_credentials_non_expired` tinyint(1) DEFAULT '1',
  `is_enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pulp17fvich5aby4m0kc820h6` (`phone`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`),
  KEY `FK4w939ws31adtcrmodq3varlii` (`dept_id`),
  CONSTRAINT `FK4w939ws31adtcrmodq3varlii` FOREIGN KEY (`dept_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2018-04-12 17:04:04', '2018-04-25 21:40:09', '1.png', 'a@a.com', '2018-04-25 13:30:47', '管理员', '{bcrypt}$2a$10$VmIWKjxAdPDQ1ysnJDeKh.G6AWc3xAopTi7oEBw1Qs/bEcSpkxLLi', '18888888888', 'sa', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', '2018-04-12 17:31:49', '2018-05-18 20:48:26', null, null, null, 'nickName', '{bcrypt}$2a$10$k8Zycvh0i5LzRxUqdagyme5jJ16XCApDKXK600dK8sLhOd4mxgrwa', '18888888881', 'user', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('5', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '3.png', '', '2018-04-13 17:31:00', 'nickName2', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW6e', '18888888882', 'user2', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('9', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '4.png', 'a3@a.com', '2018-04-14 17:31:00', 'nickName3', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW7e', '18888888883', 'user3', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('10', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '5.png', 'a4@a.com', '2018-04-15 17:31:00', 'nickName4', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW8e', '18888888884', 'user4', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('11', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '6.png', 'a5@a.com', '2018-04-16 17:31:00', 'nickName5', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW9e', '18888888885', 'user5', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('12', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '7.png', 'a6@a.com', '2018-04-17 17:31:00', 'nickName6', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW10e', '18888888886', 'user6', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('13', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '8.png', 'a7@a.com', '2018-04-18 17:31:00', 'nickName7', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW11e', '18888888887', 'user7', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('15', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '10.png', 'a9@a.com', '2018-04-20 17:31:00', 'nickName9', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW13e', '18888888889', 'user9', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('16', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '11.png', 'a10@a.com', '2018-04-21 17:31:00', 'nickName10', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW14e', '18888888890', 'user10', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('17', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '12.png', 'a11@a.com', '2018-04-22 17:31:00', 'nickName11', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW15e', '18888888891', 'user11', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('18', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '13.png', 'a12@a.com', '2018-04-23 17:31:00', 'nickName12', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW16e', '18888888892', 'user12', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('19', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '14.png', 'a13@a.com', '2018-04-24 17:31:00', 'nickName13', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW17e', '18888888893', 'user13', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('20', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '15.png', 'a14@a.com', '2018-04-25 17:31:00', 'nickName14', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW18e', '18888888894', 'user14', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('21', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '16.png', 'a15@a.com', '2018-04-26 17:31:00', 'nickName15', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW19e', '18888888895', 'user15', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('22', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '17.png', 'a16@a.com', '2018-04-27 17:31:00', 'nickName16', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW20e', '18888888896', 'user16', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('23', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '18.png', 'a17@a.com', '2018-04-28 17:31:00', 'nickName17', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW21e', '18888888897', 'user17', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('24', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '19.png', 'a18@a.com', '2018-04-29 17:31:00', 'nickName18', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW22e', '18888888898', 'user18', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('25', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '20.png', 'a19@a.com', '2018-04-30 17:31:00', 'nickName19', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW23e', '18888888899', 'user19', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('26', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '21.png', 'a20@a.com', '2018-05-01 17:31:00', 'nickName20', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW24e', '18888888900', 'user20', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('28', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '9.png', 'a8@a.com', '2018-04-19 17:31:00', 'nickName8', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW12e', '13888888888', 'user8', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('29', '2018-04-12 17:31:00', '2018-04-12 17:31:00', '21.png', 'a21@a.com', '2018-04-13 17:31:00', 'nickName21', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW6e', '18888888821', 'user21', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('31', '2018-04-24 14:52:12', '2018-04-24 19:59:57', null, '', null, 'test', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW6e', '13333333333', 'test', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('32', '2018-04-24 15:20:49', '2018-04-24 20:25:18', null, '', null, 'test2', '$2a$10$bdBafnS979EsOUUICa.br.nCGSd3xLhcz13vD04XXIjQBR3SvYW6e', '13333333331', 'test2', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('34', '2018-04-24 15:42:32', '2018-04-24 15:42:32', null, '', '2018-04-24 15:42:32', 'ttt', '$2a$10$.5qGndNdck0JfrUuKUb9N.VBILuRoD6JPKumueYwk3Jj8vqyDigGO', '18921921291', 'ttt', null, '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('35', '2018-04-24 17:12:51', '2018-04-24 17:12:51', null, '', '2018-04-24 17:12:51', 'rrr', '$2a$10$WZy8E9BDJgOJDfMOXorTF..vbUwSCva5HqQC2oSbI1ENzqUR61q3O', '13413413413', 'rrr', null, '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKqwiuml6b7mjmk48u5b9hmk853` (`role_id`),
  CONSTRAINT `FKp2804vh0ea810pitigxq5n6pn` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKqwiuml6b7mjmk48u5b9hmk853` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1');
INSERT INTO `sys_user_roles` VALUES ('2', '2');
INSERT INTO `sys_user_roles` VALUES ('31', '2');
INSERT INTO `sys_user_roles` VALUES ('32', '2');
