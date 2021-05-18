/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : mcy_book

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-09-20 14:03:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `isbn` varchar(64) DEFAULT NULL,
  `loan_number` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `press` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `kind_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ehpdfjpu1jm3hijhj4mm0hx9h` (`isbn`),
  KEY `FKt3jo8988fcydhfq87cdhqb44m` (`kind_id`),
  CONSTRAINT `FKt3jo8988fcydhfq87cdhqb44m` FOREIGN KEY (`kind_id`) REFERENCES `kind` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '克里斯托弗·斯坦纳 ', 'QQ图片20190920125745.jpg', '123456', '1', '算法帝国', '2', '长江出版社', '992be331-b7bc-4d4d-bb45-9342e418f43f', '1');
INSERT INTO `book` VALUES ('2', '张三', 'QQ图片20190920125751.jpg', '111111', '0', 'c语言', '5', '清华出版社', '7b4f3948-84bf-4635-bd8a-b458fb089e03', '2');
INSERT INTO `book` VALUES ('3', '李四', 'QQ图片20190920125755.jpg', '222222', '1', '人工智能', '4', '长江出版社', '4f78bede-e773-478c-bdee-fa50123dc161', '1');
INSERT INTO `book` VALUES ('4', '张三', 'QQ图片20190920125758.jpg', '12345645', '1', '设计模式', '2', '长江出版社', '0dcfa5c1-e533-49f0-bc45-f36001b69841', '1');
INSERT INTO `book` VALUES ('5', 'admin', 'QQ图片20190920125801.jpg', '456', '0', '计算机', '5', '未来出版社', '661c6a80-d92d-4e4c-98cc-d6604e37e4e8', '2');
INSERT INTO `book` VALUES ('6', '1234', 'QQ图片20190920125804.jpg', '1234131', '0', 'ps', '23', '长江', '04ab4e26-8dab-406e-815a-005dcc826f46', '1');
INSERT INTO `book` VALUES ('7', '·123', 'QQ图片20190920125810.jpg', '123123', '0', 'Python', '5', '长江', 'dbf8af84-0055-4f36-b188-71d9e445bd05', '1');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ao06wunawb79tv8k153deuffs` (`name`),
  KEY `FK5bwmuwbv32l2bg3b9ia7dbj9p` (`dept_id`),
  KEY `FKe7uuxj9ix1ek5yaq1fp4rg8tv` (`teacher_id`),
  CONSTRAINT `FK5bwmuwbv32l2bg3b9ia7dbj9p` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
  CONSTRAINT `FKe7uuxj9ix1ek5yaq1fp4rg8tv` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '某某大学', null, '3');
INSERT INTO `dept` VALUES ('2', '电子信息学院', '1', '3');
INSERT INTO `dept` VALUES ('3', '软件一班', '2', '3');
INSERT INTO `dept` VALUES ('4', '经济管理学院', '1', '3');
INSERT INTO `dept` VALUES ('5', '会计一班', '4', '3');

-- ----------------------------
-- Table structure for journal
-- ----------------------------
DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(255) DEFAULT NULL,
  `reamark` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal
-- ----------------------------
INSERT INTO `journal` VALUES ('1', '登录', '系统管理员', '2019-05-21 18:23:06', 'system');
INSERT INTO `journal` VALUES ('2', '退出', '系统管理员', '2019-05-21 18:23:19', 'system');
INSERT INTO `journal` VALUES ('3', '登录', '图书管理员', '2019-05-21 18:23:27', 'admin');
INSERT INTO `journal` VALUES ('4', '添加老师', '图书管理员', '2019-05-21 18:25:18', 'admin');
INSERT INTO `journal` VALUES ('5', '添加学院或班级', '图书管理员', '2019-05-21 18:25:37', 'admin');
INSERT INTO `journal` VALUES ('6', '添加学院或班级', '图书管理员', '2019-05-21 18:25:48', 'admin');
INSERT INTO `journal` VALUES ('7', '添加学院或班级', '图书管理员', '2019-05-21 18:26:10', 'admin');
INSERT INTO `journal` VALUES ('8', '添加学院或班级', '图书管理员', '2019-05-21 18:26:21', 'admin');
INSERT INTO `journal` VALUES ('9', '添加学院或班级', '图书管理员', '2019-05-21 18:34:29', 'admin');
INSERT INTO `journal` VALUES ('10', '登录', '系统管理员', '2019-09-20 12:51:48', 'system');
INSERT INTO `journal` VALUES ('11', '退出', '系统管理员', '2019-09-20 12:52:18', 'system');
INSERT INTO `journal` VALUES ('12', '登录', '系统管理员', '2019-09-20 12:52:29', 'system');
INSERT INTO `journal` VALUES ('13', '退出', '系统管理员', '2019-09-20 12:52:37', 'system');
INSERT INTO `journal` VALUES ('14', '登录', '图书管理员', '2019-09-20 12:52:44', 'admin');
INSERT INTO `journal` VALUES ('15', '添加图书种类', '图书管理员', '2019-09-20 12:53:01', 'admin');
INSERT INTO `journal` VALUES ('16', '添加图书种类', '图书管理员', '2019-09-20 12:53:07', 'admin');
INSERT INTO `journal` VALUES ('17', '添加图书', '图书管理员', '2019-09-20 12:59:36', 'admin');
INSERT INTO `journal` VALUES ('18', '添加图书', '图书管理员', '2019-09-20 13:00:26', 'admin');
INSERT INTO `journal` VALUES ('19', '添加图书', '图书管理员', '2019-09-20 13:01:10', 'admin');
INSERT INTO `journal` VALUES ('20', '添加学生', '图书管理员', '2019-09-20 13:02:45', 'admin');
INSERT INTO `journal` VALUES ('21', '添加学生', '图书管理员', '2019-09-20 13:02:59', 'admin');
INSERT INTO `journal` VALUES ('22', '添加学生', '图书管理员', '2019-09-20 13:03:21', 'admin');
INSERT INTO `journal` VALUES ('23', '添加图书', '图书管理员', '2019-09-20 13:03:52', 'admin');
INSERT INTO `journal` VALUES ('24', '添加图书', '图书管理员', '2019-09-20 13:04:23', 'admin');
INSERT INTO `journal` VALUES ('25', '添加图书', '图书管理员', '2019-09-20 13:04:52', 'admin');
INSERT INTO `journal` VALUES ('26', '添加图书', '图书管理员', '2019-09-20 13:05:14', 'admin');
INSERT INTO `journal` VALUES ('27', '退出', '图书管理员', '2019-09-20 13:09:24', 'admin');
INSERT INTO `journal` VALUES ('28', '登录', '用户', '2019-09-20 13:09:48', '123');
INSERT INTO `journal` VALUES ('29', '退出', '用户', '2019-09-20 13:10:11', '123');
INSERT INTO `journal` VALUES ('30', '登录', '图书管理员', '2019-09-20 13:10:21', 'admin');
INSERT INTO `journal` VALUES ('31', '退出', '图书管理员', '2019-09-20 13:24:20', 'admin');
INSERT INTO `journal` VALUES ('32', '登录', '图书管理员', '2019-09-20 13:24:56', 'admin');
INSERT INTO `journal` VALUES ('33', '退出', '图书管理员', '2019-09-20 13:30:36', 'admin');
INSERT INTO `journal` VALUES ('34', '登录', '系统管理员', '2019-09-20 13:30:46', 'system');
INSERT INTO `journal` VALUES ('35', '退出', '系统管理员', '2019-09-20 13:34:35', 'system');
INSERT INTO `journal` VALUES ('36', '登录', '图书管理员', '2019-09-20 13:35:22', 'admin');
INSERT INTO `journal` VALUES ('37', '退出', '图书管理员', '2019-09-20 13:37:04', 'admin');
INSERT INTO `journal` VALUES ('38', '登录', '用户', '2019-09-20 13:37:10', '123');

-- ----------------------------
-- Table structure for kind
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nkmto30tewosx0blmj8fteqqj` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('1', '39', '计算机类');
INSERT INTO `kind` VALUES ('2', '10', '艺术类');

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` varchar(255) DEFAULT NULL,
  `reamark` varchar(255) DEFAULT NULL,
  `return_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtn0iin8slxpqpos9r97qwsoa7` (`book_id`),
  KEY `FK3tg2gh1akmkwwbx8hor9txfr9` (`user_id`),
  CONSTRAINT `FK3tg2gh1akmkwwbx8hor9txfr9` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKtn0iin8slxpqpos9r97qwsoa7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES ('1', '2019-11-20 13:05:22', '图书管理员', null, '2019-09-20 13:05:22', '未还', '1', '2');
INSERT INTO `records` VALUES ('2', '2019-11-20 13:09:57', '用户', null, '2019-09-20 13:09:57', '未还', '4', '4');
INSERT INTO `records` VALUES ('3', '2019-11-20 13:10:00', '用户', null, '2019-09-20 13:10:00', '待审核', '3', '4');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK51vi5k0vbdvve6mrxlme8x0vo` (`dept_id`),
  CONSTRAINT `FK51vi5k0vbdvve6mrxlme8x0vo` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
  CONSTRAINT `FKt1enxfujglcm8pio0xa8qbty5` FOREIGN KEY (`id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('123456', '男', '4', '3');
INSERT INTO `student` VALUES ('1111', '男', '5', '3');
INSERT INTO `student` VALUES ('123145', '男', '6', '5');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `remark` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKs0p52er5hge04dv8bdb67rii3` FOREIGN KEY (`id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (null, '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_SYSTEM', '系统管理员');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_ADMIN', '图书管理员');
INSERT INTO `sys_role` VALUES ('3', 'ROLE_USER', '学生');
INSERT INTO `sys_role` VALUES ('4', 'ROLE_TEACHER', '老师');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '系统管理员', '0', '$2a$10$l3QL.WdVpFHTdXGxulWc2.OQEKJBjA35eDnZsVmxLtFtWPlQ4eSo2', 'system');
INSERT INTO `sys_user` VALUES ('2', '图书管理员', '1', '$2a$10$RXiC6sUiq44rLksuAunOZ.Z0tebJlNz614Xr7Dst0HLrcE9iGA6ta', 'admin');
INSERT INTO `sys_user` VALUES ('3', '张三', '0', '$2a$10$ONgTg.n7.KGiiF778Hcunu7RWInQPBUO4SELXyzOjcUi2wrY.YfGi', '123455');
INSERT INTO `sys_user` VALUES ('4', '张三', '2', '$2a$10$NG73eVcStccBLlcLOBHp6excP3sj21zLJBlpIsbRnCuwKMojg3jzq', '123');
INSERT INTO `sys_user` VALUES ('5', '李四', '0', '$2a$10$0E1k1JkYxGXgxsR3gLoVSexoIFZEDJkNb50ij2CJYRP2xtik26VIe', '123456');
INSERT INTO `sys_user` VALUES ('6', '张三', '0', '$2a$10$mfMfSn2TGx82jcMct0C/Yuo40x7YJEKR7sWBtxmMPQkCgLmFVjMme', '122341132');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKb40xxfch70f5qnyfw8yme1n1s` (`user_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '4');
INSERT INTO `sys_user_role` VALUES ('4', '3');
INSERT INTO `sys_user_role` VALUES ('5', '3');
INSERT INTO `sys_user_role` VALUES ('6', '3');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `remark` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKf133j4v6w5thwqt8shi88o0ic` FOREIGN KEY (`id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('asdf', '3');
