/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80030
Source Host           : localhost:3307
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-09-25 16:17:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for active
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `people` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('1', '会议1', '2022-08-13', '08:30:00', '09:00:00', '王五');
INSERT INTO `active` VALUES ('2', '返校名单', '2022-09-08', '08:30:00', '08:45:00', '刘明宇 王嘉裕');
INSERT INTO `active` VALUES ('3', '返校名单', '2022-09-09', '08:30:00', '08:45:00', '程森 潘金明 杨玉升 孙鹤');
INSERT INTO `active` VALUES ('4', '会议1', '2022-09-30', '08:30:00', '09:00:00', '123');
INSERT INTO `active` VALUES ('5', '会议2', '2022-09-30', '08:45:00', '09:00:00', '王五，李四');

-- ----------------------------
-- Table structure for calendar
-- ----------------------------
DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `caid` int NOT NULL AUTO_INCREMENT COMMENT '日程id',
  `info` varchar(255) DEFAULT NULL COMMENT '日程内容',
  `operid` int DEFAULT NULL COMMENT '录入人id',
  `datetime` datetime DEFAULT NULL COMMENT '日程时间',
  PRIMARY KEY (`caid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of calendar
-- ----------------------------

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `cname` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `legalperson` varchar(255) DEFAULT NULL COMMENT '法人',
  `registered` varchar(255) DEFAULT NULL COMMENT '注册资金',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `category` varchar(255) DEFAULT NULL COMMENT '客户分类',
  `account` varchar(255) DEFAULT NULL COMMENT '银行账户',
  `industry` varchar(255) DEFAULT NULL COMMENT '所属行业',
  `quality` varchar(255) DEFAULT NULL COMMENT '公司属性',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '录入时间',
  `updatetime` datetime DEFAULT NULL COMMENT '最后修改日期',
  `operid` int DEFAULT NULL,
  `status` int DEFAULT '1' COMMENT '是否下架 1up 0 down',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', '唐山学院', '成泽毅', '2000000', '大学西道', '12345678910', '无', '0000000000000000000000000000000000000000000000000000000000', '教育', '无', '无', '2022-09-16 16:13:32', null, '1', '1');
INSERT INTO `client` VALUES ('2', 'asd', 'kkk', '1000', '12335456', '+8613731714984', '股份有限公司', '123456789123456', '软件', '个体工商户', '无', '2022-09-20 18:09:08', '2022-09-20 18:09:08', '1', '1');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `coid` int NOT NULL AUTO_INCREMENT,
  `cid` int DEFAULT NULL COMMENT '公司id',
  `coname` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '录入时间',
  `updatetime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `operid` int DEFAULT NULL COMMENT '录入人id',
  `status` int DEFAULT '1' COMMENT '是否下架',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '1', '毅', '唐山学院', '12345678910', '55555', '2022-09-16 16:10:28', null, '1', '1');
INSERT INTO `contact` VALUES ('2', '4', '韩123', '中国河北邯郸磁县索庄村', '+8618830059565', 'asddasd', '2022-07-29 09:43:27', '2022-08-14 21:32:57', '3', '1');

-- ----------------------------
-- Table structure for functions
-- ----------------------------
DROP TABLE IF EXISTS `functions`;
CREATE TABLE `functions` (
  `fid` int NOT NULL AUTO_INCREMENT COMMENT '功能id',
  `fname` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `fatherid` int DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of functions
-- ----------------------------
INSERT INTO `functions` VALUES ('1', '客户管理', '0');
INSERT INTO `functions` VALUES ('2', '用户管理', '0');
INSERT INTO `functions` VALUES ('4', '库存管理', '0');
INSERT INTO `functions` VALUES ('5', '系统管理员', '0');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` varchar(255) NOT NULL COMMENT '库存批次编号，根据时间生成',
  `pid` int DEFAULT NULL COMMENT '产品id',
  `numbers` int DEFAULT NULL COMMENT '进货量',
  `exist` int DEFAULT NULL COMMENT '现有量',
  `price` decimal(10,0) DEFAULT NULL COMMENT '进货价',
  `operid` int DEFAULT NULL COMMENT '记录人id',
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '仓库位置',
  `producetime` date DEFAULT NULL COMMENT '生产日期',
  `endtime` date DEFAULT NULL COMMENT '生产截止日期',
  `createtime` date DEFAULT NULL COMMENT '入库时间',
  `type` int DEFAULT '0' COMMENT '0新进，1退换',
  `state` int DEFAULT NULL COMMENT '0为正常，1为异常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('20220720231832991', '2', '1000', '0', '10', '1', 'B', '2022-07-20', '2022-08-01', '2022-07-20', '0', null);
INSERT INTO `inventory` VALUES ('20220720232102715', '1', '7000', '0', '16', '1', 'A', '2022-07-15', '2022-07-27', '2022-07-20', '0', '1');
INSERT INTO `inventory` VALUES ('20220720232303510', '2', '456', '0', '8', '1', 'B', '2022-07-18', '2022-08-17', '2022-07-20', '0', null);
INSERT INTO `inventory` VALUES ('20220723165800833', '1', '1000', '1000', '12', '1', 'B', '2022-07-23', '2022-08-17', '2022-07-23', '0', null);
INSERT INTO `inventory` VALUES ('20220724101554300', '2', '100', '0', '65', '1', 'A', '2022-07-24', '2022-08-17', '2022-07-24', '0', null);
INSERT INTO `inventory` VALUES ('20220724110201507', '2', '500', '0', '45', '1', 'A', '2022-07-15', '2022-07-24', '2022-07-24', '0', null);
INSERT INTO `inventory` VALUES ('20220724110518415', '1', '5000', '3500', '78', '1', 'A', '2022-07-24', '2022-08-16', '2022-07-24', '0', null);
INSERT INTO `inventory` VALUES ('20220724110945967', '1', '45', '45', '7', '1', 'A', '2022-07-26', '2022-09-10', '2022-07-24', '0', null);
INSERT INTO `inventory` VALUES ('20220728223556296', '2', '500', '0', '10', '1', 'B', '2022-07-28', '2022-08-31', '2022-07-28', '0', null);
INSERT INTO `inventory` VALUES ('20220729093349419', '1', '1000', '1000', '40', '1', 'A', '2022-07-29', '2022-08-29', '2022-07-29', '0', null);
INSERT INTO `inventory` VALUES ('20220729183624521', '1', '10', '10', '1000', '1', 'B', '2022-07-29', '2022-08-31', '2022-07-29', '0', null);
INSERT INTO `inventory` VALUES ('20220814210839979', '1', '45666', '45666', '12', '1', 'B', '2022-08-14', '2022-09-14', '2022-08-14', '0', null);
INSERT INTO `inventory` VALUES ('20220916161435643', '3', '20000', '20000', '5', null, 'C', '2022-09-19', '2022-09-30', '2022-09-19', '1', null);
INSERT INTO `inventory` VALUES ('20220916211419621', '3', '2000', '1900', '12', '1', 'B', '2022-09-16', '2022-10-31', '2022-09-16', '0', null);
INSERT INTO `inventory` VALUES ('20220916213346837', '3', '1000', '1000', '5', null, 'A', '2022-08-01', '2022-09-30', '2022-09-19', '1', '0');
INSERT INTO `inventory` VALUES ('20220916213422985', '2', '5000', '780', '6', null, 'C', '2022-09-01', '2022-09-30', '2022-09-19', '1', null);
INSERT INTO `inventory` VALUES ('20220916213456388', '3', '5000', '5000', '300000', null, 'B', '2022-09-16', '2022-10-31', '2022-09-16', null, null);
INSERT INTO `inventory` VALUES ('20220916213546886', '3', '2000', '2000', '998', null, 'A', '2022-09-16', '2022-10-31', '2022-09-16', '0', null);
INSERT INTO `inventory` VALUES ('20220916213700084', '2', '456123', '456123', '145644465', null, 'A', '2022-09-27', '2022-10-04', '2022-09-16', null, null);
INSERT INTO `inventory` VALUES ('20220920181739077', '2', '500', '1500', '12', null, 'B', '2022-09-27', '2022-10-04', '2022-09-20', '0', null);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `logid` int NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `oid` int DEFAULT NULL COMMENT '用户id',
  `fid` int DEFAULT NULL COMMENT '功能id',
  `createtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `oname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户名字',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `status` int NOT NULL DEFAULT '1' COMMENT '用户身份',
  `roid` int DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('1', '韩总', '12345678910', '123', '1', '1');
INSERT INTO `operator` VALUES ('2', '索总', '12345678912', '123', '1', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单编号',
  `price` decimal(10,2) DEFAULT NULL COMMENT '实际付款',
  `numbers` int DEFAULT NULL COMMENT '数量',
  `coid` int DEFAULT NULL COMMENT '客户联系人编号',
  `cid` int DEFAULT NULL COMMENT '客户公司id',
  `pid` int DEFAULT NULL COMMENT '产品id',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '下单日期',
  `deadline` timestamp NULL DEFAULT NULL COMMENT '交货日期',
  `operid` int DEFAULT NULL COMMENT '销售员id',
  `type` int DEFAULT '0' COMMENT '点单状态：0是未确认 1已确定 2已出库 3库存不足 4退货 5已完成',
  `state` int DEFAULT NULL COMMENT '1为产品类，2为服务类',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('20220722', '10.15', '800000', '2', '1', '1', '2022-07-22 11:12:28', '2022-08-01 00:00:00', '1', '5', '1');
INSERT INTO `orders` VALUES ('20220723', '11.00', '120', '1', '2', '2', '2022-07-22 21:22:46', '2022-07-23 00:00:00', '2', '2', '1');
INSERT INTO `orders` VALUES ('20220805181958110', '100000.00', '500', '1', '2', '1', '2022-08-05 18:19:00', '2022-08-05 00:00:00', '1', '1', '1');
INSERT INTO `orders` VALUES ('20220805182035262', '50000.00', '456', '1', '1', '2', '2022-08-05 18:20:00', '2022-08-05 00:00:00', '1', '5', '1');
INSERT INTO `orders` VALUES ('20220814211553816', '555555.00', '45555', '1', '1', '1', '2022-08-14 21:15:00', '2022-08-31 00:00:00', '1', '5', '1');
INSERT INTO `orders` VALUES ('20220916161435643', '200000.00', '20000', '1', '1', '3', '2022-09-16 16:14:00', '2022-10-31 16:14:00', '1', '4', '1');
INSERT INTO `orders` VALUES ('20220916213346837', '30000.00', '1000', '1', '1', '3', '2022-09-16 21:33:00', '2022-09-30 21:33:00', '1', '4', '1');
INSERT INTO `orders` VALUES ('20220916213422985', '100000.00', '5000', '1', '1', '2', '2022-09-16 21:34:00', '2022-09-30 21:34:00', '1', '2', '1');
INSERT INTO `orders` VALUES ('20220916220532465', '99.00', '100', '1', '1', '3', '2022-09-16 22:05:00', '2022-09-17 22:05:00', '1', '1', '1');

-- ----------------------------
-- Table structure for petition
-- ----------------------------
DROP TABLE IF EXISTS `petition`;
CREATE TABLE `petition` (
  `peid` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '投诉内容',
  `operid` int DEFAULT '1' COMMENT '记录人id',
  `executor` int DEFAULT NULL COMMENT '处理人id',
  `outcome` varchar(255) DEFAULT NULL COMMENT '结果反馈',
  `createtime` datetime DEFAULT NULL COMMENT '投诉时间',
  `endtime` datetime DEFAULT NULL COMMENT '处理完成时间',
  PRIMARY KEY (`peid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of petition
-- ----------------------------
INSERT INTO `petition` VALUES ('1', '6666', '1', '2', '已经处理', '2022-07-25 18:50:00', null);
INSERT INTO `petition` VALUES ('2', '功能不够完善！！！', '1', '2', '派遣人员维修', '2022-07-25 20:12:00', null);
INSERT INTO `petition` VALUES ('3', '123', '2', '1', '维修', '2022-07-26 11:28:00', null);
INSERT INTO `petition` VALUES ('4', '666666', '2', '1', '维修成功', '2022-07-29 09:35:00', null);
INSERT INTO `petition` VALUES ('5', '123', '2', '1', '465', '2022-08-05 15:12:00', null);
INSERT INTO `petition` VALUES ('6', '存在问题', '2', '1', '123', '2022-08-14 19:56:00', null);
INSERT INTO `petition` VALUES ('7', '出现问题了', '1', '2', '已经完成处理', '2022-08-14 19:58:00', null);
INSERT INTO `petition` VALUES ('8', '项目存在一些问题', '1', '2', '解决', '2022-08-14 20:02:00', null);
INSERT INTO `petition` VALUES ('9', '本项目存在一些问题', '1', '2', '789', '2022-08-14 20:07:00', null);
INSERT INTO `petition` VALUES ('10', '456', '1', '2', '9999', '2022-08-14 20:08:00', null);
INSERT INTO `petition` VALUES ('11', '456', '1', '2', '465', '2022-08-14 20:09:00', null);
INSERT INTO `petition` VALUES ('12', '789', '1', '2', '666', '2022-08-14 20:12:00', null);
INSERT INTO `petition` VALUES ('13', 'asdas', '1', '1', 'asdasd', '2022-08-14 20:14:00', null);
INSERT INTO `petition` VALUES ('14', 'asdas', '1', '2', 'asdasd', '2022-08-14 20:14:00', null);
INSERT INTO `petition` VALUES ('15', '4566', '1', '1', '123', '2022-08-14 20:16:00', null);
INSERT INTO `petition` VALUES ('16', 'asdas', '1', '1', 'asdas', '2022-08-14 20:17:00', null);
INSERT INTO `petition` VALUES ('17', '456', '1', '2', '66666', '2022-08-14 20:23:00', null);
INSERT INTO `petition` VALUES ('18', '88888', '1', '1', '啊实打实', '2022-08-14 20:26:00', null);
INSERT INTO `petition` VALUES ('19', '66666', '1', '1', '6666', '2022-08-14 20:32:00', null);
INSERT INTO `petition` VALUES ('20', 'sadas', '1', '1', '6666', '2022-08-14 20:41:00', null);
INSERT INTO `petition` VALUES ('21', 'asdasd', '1', '1', 'asdas', '2022-08-14 20:43:00', null);
INSERT INTO `petition` VALUES ('22', '555555', '1', '2', '666666', '2022-08-14 21:12:00', null);
INSERT INTO `petition` VALUES ('24', '测试完成', '1', '1', '123456', '2022-09-18 18:54:00', null);
INSERT INTO `petition` VALUES ('25', '666', '1', '2', null, '2022-09-18 18:55:00', null);
INSERT INTO `petition` VALUES ('28', '测试内容', '1', '1', '123', '2022-09-20 18:59:00', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `pname` varchar(255) DEFAULT NULL COMMENT '产品名',
  `category` varchar(255) DEFAULT NULL COMMENT '分类名',
  `price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `units` varchar(255) DEFAULT NULL COMMENT '规格单位',
  `specification` int DEFAULT NULL COMMENT '规格',
  `shelflife` varchar(255) DEFAULT NULL COMMENT '保质期',
  `marketprice` decimal(10,0) DEFAULT NULL COMMENT '市场价',
  `status` int DEFAULT '1' COMMENT '是否下架；0为下架，1为上架',
  `type` int DEFAULT NULL COMMENT '类型 1为产品 2为服务',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '雪花', '啤酒', '12.00', '瓶', '6', '28', '20', '1', '1');
INSERT INTO `product` VALUES ('2', '崂山', '啤酒', '6.00', '瓶', '5', '20', '12', '1', '1');
INSERT INTO `product` VALUES ('3', '燕京U8', '啤酒', '5.00', '瓶', '12', '30', '12', '1', '1');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `roid` int NOT NULL AUTO_INCREMENT,
  `roname` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '销售', '1');
INSERT INTO `roles` VALUES ('2', '经理', '2');
INSERT INTO `roles` VALUES ('3', '销售', '1');
INSERT INTO `roles` VALUES ('4', '实施员', null);
INSERT INTO `roles` VALUES ('5', '库管', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `oname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `roid` int DEFAULT NULL,
  `phone` varchar(0) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '1', '1', '1', null);

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `useragent` varchar(255) DEFAULT NULL,
  `behavior` longtext,
  `by1` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('58', 'admin', 'admin', '2022-08-29 15:42:28', null, '0', '\"Windows\"', '15:42:28.282尝试登录 因为账号或者密码错误登录失败 因为账号或者密码错误登录失败 因为账号或者密码错误登录失败 因为账号或者密码错误登录失败 因为账号或者密码错误登录失败 因为账号或者密码错误登录失败 登录成功 登录成功 登录成功 登录成功 登录成功', '58');
INSERT INTO `user_log` VALUES ('59', 'admin', '123', '2022-08-29 15:47:58', null, '1', '\"Windows\"', '15:47:58.143尝试登录 登录成功 登录成功 登录成功 登录成功 登录成功', '59');
INSERT INTO `user_log` VALUES ('60', 'admin', '123', '2022-08-29 15:57:51', null, '1', '\"Windows\"', '15:57:51.146尝试登录 登录成功', '60');
INSERT INTO `user_log` VALUES ('61', 'admin', '123', '2022-08-29 15:59:38', null, '1', '\"Windows\"', '15:59:38.386尝试登录 登录成功', '61');
INSERT INTO `user_log` VALUES ('62', 'admin', '123', '2022-08-29 16:01:16', null, '1', '\"Windows\"', '16:01:15.791尝试登录 登录成功', '62');
INSERT INTO `user_log` VALUES ('63', 'admin', '123', '2022-08-29 16:08:34', null, '1', '\"Windows\"', '16:08:34.425尝试登录 登录成功', '63');
INSERT INTO `user_log` VALUES ('64', 'admin', '123', '2022-08-29 16:09:21', null, '1', '\"Windows\"', '16:09:20.822尝试登录 登录成功', '64');
INSERT INTO `user_log` VALUES ('65', 'admin', '123', '2022-08-29 16:09:36', null, '1', '\"Windows\"', '16:09:36.256尝试登录 登录成功', '65');
INSERT INTO `user_log` VALUES ('66', 'admin', '123', '2022-08-29 16:10:11', null, '1', '\"Windows\"', '16:10:10.932尝试登录', '66');
INSERT INTO `user_log` VALUES ('67', '1123', '123', '2022-09-08 19:30:13', null, '0', '\"Windows\"', '19:30:12.838尝试登录 因为账号或者密码错误登录失败 查看了用户日程表 查看了2022-09-10日程表 查看了2022-09-17日程表 查看了用户日程表 查看了2022-09-15日程表 查看了2022-09-16日程表 查看了2022-09-17日程表 查看了用户日程表 查看了用户日程表 查看了2022-09-10日程表 查看了2022-09-08日程表 添加了2022-09-08日程表 查看了用户日程表 查看了2022-09-08日程表 查看了2022-09-09日程表 添加了2022-09-09日程表 查看了2022-09-09日程表 查看了用户日程表 查看了2022-09-09日程表 查看了2022-09-08日程表 查看了2022-09-09日程表 登录成功', '67');
INSERT INTO `user_log` VALUES ('68', 'admin', '123', '2022-09-10 11:38:33', null, '1', '\"Windows\"', '11:38:33.292尝试登录 查看了用户日程表 查看了2022-09-09日程表 查看了2022-09-10日程表 查看了2022-09-09日程表 查看了2022-09-08日程表 查看了2022-09-15日程表 查看了2022-09-30日程表 添加了2022-09-30日程表 查看了2022-09-30日程表 查看了2022-09-08日程表 查看了2022-09-30日程表 添加了2022-09-30日程表 查看了用户日程表 查看了2022-09-30日程表 查看了用户日程表 查看了2022-09-08日程表 查看了2022-09-08日程表 查看了2022-09-09日程表 因为账号或者密码错误登录失败', '68');
INSERT INTO `user_log` VALUES ('69', 'admin', 'admin', '2022-09-18 21:10:58', null, '0', '\"Windows\"', '21:10:57.576尝试登录 因为账号或者密码错误登录失败', '69');
INSERT INTO `user_log` VALUES ('70', 'admin', 'admin', '2022-09-18 21:12:35', null, '0', '\"Windows\"', '21:12:35.450尝试登录 登录成功', '70');
INSERT INTO `user_log` VALUES ('71', 'admin', '123', '2022-09-18 21:12:57', null, '1', '\"Windows\"', '21:12:56.964尝试登录 登录成功', '71');
INSERT INTO `user_log` VALUES ('72', 'admin', '123', '2022-09-18 21:14:26', null, '1', '\"Windows\"', '21:14:26.208尝试登录 登录成功', '72');
INSERT INTO `user_log` VALUES ('73', 'admin', '123', '2022-09-18 21:15:30', null, '1', '\"Windows\"', '21:15:30.006尝试登录 查看了用户日程表 登录成功', '73');
INSERT INTO `user_log` VALUES ('74', 'admin', '123', '2022-09-18 21:15:55', null, '1', '\"Windows\"', '21:15:55.223尝试登录 登录成功', '74');
INSERT INTO `user_log` VALUES ('75', 'admin', '123', '2022-09-18 21:18:46', null, '1', '\"Windows\"', '21:18:45.520尝试登录 登录成功', '75');
INSERT INTO `user_log` VALUES ('76', 'admin', '123', '2022-09-18 21:19:40', null, '1', '\"Windows\"', '21:19:39.846尝试登录 登录成功', '76');
INSERT INTO `user_log` VALUES ('77', 'admin', 'admin', '2022-09-18 21:20:06', null, '0', '\"Windows\"', '21:20:06.202尝试登录', '77');
INSERT INTO `user_log` VALUES ('78', 'admin', '123', '2022-09-18 21:20:09', null, '1', '\"Windows\"', '21:20:08.974尝试登录 登录成功', '78');
INSERT INTO `user_log` VALUES ('79', 'admin', '123', '2022-09-18 21:21:08', null, '1', '\"Windows\"', '21:21:07.832尝试登录 因为账号或者密码错误登录失败', '79');
INSERT INTO `user_log` VALUES ('80', '阿大声道', 'asd ', '2022-09-18 21:21:13', null, '0', '\"Windows\"', '21:21:12.626尝试登录 登录成功', '80');
INSERT INTO `user_log` VALUES ('81', 'admin', '123', '2022-09-18 21:21:24', null, '1', '\"Windows\"', '21:21:23.890尝试登录 查看了用户日程表 登录成功', '81');
INSERT INTO `user_log` VALUES ('82', 'admin', '123', '2022-09-18 21:22:42', null, '1', '\"Windows\"', '21:22:41.726尝试登录 登录成功', '82');
INSERT INTO `user_log` VALUES ('83', 'admin', '123', '2022-09-18 21:22:51', null, '1', '\"Windows\"', '21:22:50.587尝试登录 登录成功', '83');
INSERT INTO `user_log` VALUES ('84', 'admin', '123', '2022-09-18 21:32:56', null, '1', '\"Windows\"', '21:32:55.999尝试登录 登录成功', '84');
INSERT INTO `user_log` VALUES ('85', 'admin', '123', '2022-09-18 21:33:06', null, '1', '\"Windows\"', '21:33:05.555尝试登录 登录成功', '85');
INSERT INTO `user_log` VALUES ('86', 'admin', '123', '2022-09-18 21:43:00', null, '1', '\"Windows\"', '21:43:00.316尝试登录 登录成功', '86');
INSERT INTO `user_log` VALUES ('87', 'admin', '123', '2022-09-18 21:44:04', null, '1', '\"Windows\"', '21:44:03.654尝试登录 登录成功', '87');
INSERT INTO `user_log` VALUES ('88', 'admin', '123', '2022-09-18 21:44:10', null, '1', '\"Windows\"', '21:44:10.152尝试登录 登录成功', '88');
INSERT INTO `user_log` VALUES ('89', 'admin', '123', '2022-09-18 21:45:30', null, '1', '\"Windows\"', '21:45:29.517尝试登录 登录成功', '89');
INSERT INTO `user_log` VALUES ('90', 'admin', '123', '2022-09-18 21:48:38', null, '1', '\"Windows\"', '21:48:38.205尝试登录 登录成功', '90');
INSERT INTO `user_log` VALUES ('91', 'admin', '123', '2022-09-18 22:33:48', null, '1', '\"Windows\"', '22:33:47.878尝试登录 查看了用户日程表 登录成功', '91');
INSERT INTO `user_log` VALUES ('92', 'admin', '123', '2022-09-18 22:47:49', null, '1', '\"Windows\"', '22:47:49.148尝试登录 登录成功', '92');
INSERT INTO `user_log` VALUES ('93', 'admin', '123', '2022-09-18 22:48:22', null, '1', '\"Windows\"', '22:48:21.861尝试登录 登录成功', '93');
INSERT INTO `user_log` VALUES ('94', 'admin', '123', '2022-09-18 22:52:34', null, '1', '\"Windows\"', '22:52:33.911尝试登录 登录成功', '94');
INSERT INTO `user_log` VALUES ('95', 'admin', '123', '2022-09-18 23:02:29', null, '1', '\"Windows\"', '23:02:29.346尝试登录 查看了用户日程表 查看了2022-09-14日程表 查看了用户日程表 登录成功', '95');
INSERT INTO `user_log` VALUES ('96', 'admin', '123', '2022-09-19 20:23:50', null, '1', '\"Windows\"', '20:23:49.828尝试登录 查看了用户日程表 查看了2022-09-08日程表 查看了2022-09-09日程表 2022-09-24T23:10:35.362登录成功', '96');
INSERT INTO `user_log` VALUES ('97', 'admin', '123', '2022-09-24 23:10:35', null, '1', '\"Windows\"', '23:10:34.733尝试登录', '97');
INSERT INTO `user_log` VALUES ('98', 'admin', '123', '2022-09-25 14:27:14', null, '1', '\"Windows\"', '14:27:13.961尝试登录', '98');
INSERT INTO `user_log` VALUES ('99', 'admin', '123', '2022-09-25 14:32:07', null, '1', '\"Windows\"', '14:32:07.086尝试登录', '99');
INSERT INTO `user_log` VALUES ('100', 'admin', '123', '2022-09-25 14:33:32', null, '1', '\"Windows\"', '14:33:31.746尝试登录', '100');
INSERT INTO `user_log` VALUES ('101', 'admin', '123', '2022-09-25 14:35:18', null, '1', '\"Windows\"', '14:35:17.966尝试登录', '101');
INSERT INTO `user_log` VALUES ('102', 'admin', '123', '2022-09-25 14:46:41', null, '1', '\"Windows\"', '14:46:41.318尝试登录 2022-09-25T14:48:29.784登录成功', '102');
INSERT INTO `user_log` VALUES ('103', 'admin', '123', '2022-09-25 14:46:41', null, '1', '\"Windows\"', '14:46:41.318尝试登录', '103');
INSERT INTO `user_log` VALUES ('104', 'admin', '123', '2022-09-25 14:47:38', null, '1', '\"Windows\"', '14:47:37.525尝试登录', '104');
INSERT INTO `user_log` VALUES ('105', 'admin', '123', '2022-09-25 14:48:11', null, '1', '\"Windows\"', '14:48:11.208尝试登录', '105');
INSERT INTO `user_log` VALUES ('106', 'admin', '123', '2022-09-25 14:48:30', null, '1', '\"Windows\"', '14:48:29.584尝试登录', '106');
INSERT INTO `user_log` VALUES ('107', 'admin', '123', '2022-09-25 14:51:35', null, '1', '\"Windows\"', '14:51:35.353尝试登录 2022-09-25T15:01:11.020登录成功', '107');
INSERT INTO `user_log` VALUES ('108', 'admin', '123', '2022-09-25 14:58:27', null, '1', '\"Windows\"', '14:58:27.301尝试登录 2022-09-25T15:01:58.670登录成功', '108');
INSERT INTO `user_log` VALUES ('109', 'admin', '123', '2022-09-25 15:01:11', null, '1', '\"Windows\"', '15:01:10.809尝试登录 2022-09-25T15:02:43.235登录成功', '109');
INSERT INTO `user_log` VALUES ('110', 'admin', '123', '2022-09-25 15:01:58', null, '1', '\"Windows\"', '15:01:58.406尝试登录 2022-09-25T15:04:21.972查看了用户日程表', '110');
INSERT INTO `user_log` VALUES ('111', 'admin', '123', '2022-09-25 15:02:43', null, '1', '\"Windows\"', '15:02:43.051尝试登录 2022-09-25T15:02:49.655查看了用户日程表', '111');
INSERT INTO `user_log` VALUES ('112', 'admin', '123', '2022-09-25 15:06:01', null, '1', '\"Windows\"', '15:06:00.617尝试登录', '112');
INSERT INTO `user_log` VALUES ('113', 'admin', '123', '2022-09-25 15:07:23', null, '1', '\"Windows\"', '15:07:22.519尝试登录', '113');
INSERT INTO `user_log` VALUES ('114', 'admin', '123', '2022-09-25 15:08:36', null, '1', '\"Windows\"', '15:08:36.019尝试登录', '114');
INSERT INTO `user_log` VALUES ('115', 'admin', '123', '2022-09-25 15:09:31', null, '1', '\"Windows\"', '15:09:30.617尝试登录', '115');
INSERT INTO `user_log` VALUES ('116', 'admin', '123', '2022-09-25 15:22:33', null, '1', '\"Windows\"', '15:22:33.402尝试登录', '116');
INSERT INTO `user_log` VALUES ('117', 'admin', '123', '2022-09-25 15:24:29', null, '1', '\"Windows\"', '15:24:28.757尝试登录', '117');
INSERT INTO `user_log` VALUES ('118', 'admin', '123', '2022-09-25 15:25:48', null, '1', '\"Windows\"', '15:25:48.326尝试登录 2022-09-25T15:39:31.455登录成功 2022-09-25T15:52:04.790因为账号或者密码错误登录失败', null);
INSERT INTO `user_log` VALUES ('119', 'admin', '123', '2022-09-25 15:32:42', null, '1', '\"Windows\"', '15:32:41.726尝试登录 查看了2022-09-07日程表', null);
INSERT INTO `user_log` VALUES ('120', 'admin', '123', '2022-09-25 15:39:31', null, '1', '\"Windows\"', '15:39:31.289尝试登录 2022-09-25T15:39:37.633查看了用户日程表', null);
INSERT INTO `user_log` VALUES ('121', 'admin', '123', '2022-09-25 15:52:05', null, '0', '\"Windows\"', '15:52:04.582尝试登录', null);
INSERT INTO `user_log` VALUES ('122', 'admin', '123', '2022-09-25 15:52:18', null, '0', '\"Windows\"', '15:52:18.205尝试登录', null);
INSERT INTO `user_log` VALUES ('123', 'admin', '123', '2022-09-25 15:53:37', null, '0', '\"Windows\"', '15:53:37.147尝试登录 2022-09-25T15:55:02.731登录成功', null);
INSERT INTO `user_log` VALUES ('124', 'admin', '123', '2022-09-25 15:54:32', null, '0', '\"Windows\"', '15:54:32.284尝试登录', null);
INSERT INTO `user_log` VALUES ('125', 'admin', '123', '2022-09-25 15:55:03', null, '0', '\"Windows\"', '15:55:02.510尝试登录 2022-09-25T15:57:14.725登录成功 2022-09-25T15:58:16.737登录成功', null);
INSERT INTO `user_log` VALUES ('126', 'admin', '123', '2022-09-25 15:55:11', null, '0', '\"Windows\"', '15:55:10.862尝试登录 2022-09-25T15:55:44.708登录成功', null);
INSERT INTO `user_log` VALUES ('127', 'admin', '123', '2022-09-25 15:55:27', null, '0', '\"Windows\"', '15:55:27.384尝试登录', null);
INSERT INTO `user_log` VALUES ('128', 'admin', '123', '2022-09-25 15:55:45', null, '0', '\"Windows\"', '15:55:44.558尝试登录', null);
INSERT INTO `user_log` VALUES ('129', 'admin', '123', '2022-09-25 15:56:14', null, '0', '\"Windows\"', '15:56:13.861尝试登录', null);
INSERT INTO `user_log` VALUES ('130', 'admin', '123', '2022-09-25 15:56:56', null, '0', '\"Windows\"', '15:56:56.307尝试登录 2022-09-25T15:58:38.741登录成功', null);
INSERT INTO `user_log` VALUES ('131', 'admin', '123', '2022-09-25 15:57:15', null, '0', '\"Windows\"', '15:57:14.558尝试登录', null);
INSERT INTO `user_log` VALUES ('132', 'admin', '123', '2022-09-25 15:57:20', null, '0', '\"Windows\"', '15:57:19.613尝试登录', null);
INSERT INTO `user_log` VALUES ('133', 'admin', '123', '2022-09-25 15:57:31', null, '0', '\"Windows\"', '15:57:30.621尝试登录', null);
INSERT INTO `user_log` VALUES ('134', 'admin', '123', '2022-09-25 15:58:17', null, '0', '\"Windows\"', '15:58:16.598尝试登录', null);
INSERT INTO `user_log` VALUES ('135', 'admin', '123', '2022-09-25 15:58:23', null, '0', '\"Windows\"', '15:58:22.923尝试登录', null);
INSERT INTO `user_log` VALUES ('136', 'admin', '123', '2022-09-25 15:58:39', null, '0', '\"Windows\"', '15:58:38.584尝试登录', null);

-- ----------------------------
-- Table structure for visit
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `vid` int NOT NULL AUTO_INCREMENT COMMENT '拜访编号',
  `company` varchar(255) DEFAULT NULL COMMENT '所属公司',
  `client` varchar(255) DEFAULT NULL COMMENT '拜访客户',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `accompany` varchar(255) DEFAULT NULL COMMENT '陪同人员',
  `theme` varchar(255) DEFAULT NULL COMMENT '拜访主题',
  `purpose` varchar(255) DEFAULT NULL COMMENT '拜访目的',
  `ready` varchar(255) DEFAULT NULL COMMENT '拜访前准备',
  `outcome` varchar(255) DEFAULT NULL COMMENT '拜访结果',
  `attitude` varchar(255) DEFAULT NULL COMMENT '客户态度',
  `fund` varchar(255) DEFAULT NULL COMMENT '活动经费',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `operid` int DEFAULT NULL COMMENT '记录人id',
  `createtime` datetime DEFAULT NULL COMMENT '拜访时间',
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of visit
-- ----------------------------
INSERT INTO `visit` VALUES ('1', '斌洋集团', '刘总', '+8615145236581', '韩总', '交流建筑行业的智能化', '签署合同', '打印合同', '合同签署', '非常满意', '1000', '效果甚佳', null, '2022-08-08 17:02:00');
INSERT INTO `visit` VALUES ('2', '邯钢集团', '韩总', '+8615124566232', '刘总', '把钢材进出库智能化', '达成合作', '熟悉了解该企业历史', '合作成功', '较为满意', '2000', '该企业表示持续合作', null, '2022-08-11 17:40:00');
