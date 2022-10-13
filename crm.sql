/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80030
Source Host           : localhost:3307
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-10-13 19:53:38
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('1', '会议1', '2022-08-13', '08:30:00', '09:00:00', '王五');
INSERT INTO `active` VALUES ('4', '会议1', '2022-09-30', '08:30:00', '09:00:00', '123');
INSERT INTO `active` VALUES ('5', '会议2', '2022-09-30', '08:45:00', '09:00:00', '王五，李四');
INSERT INTO `active` VALUES ('6', '123', '2022-09-07', '08:30:00', '09:15:00', '123');
INSERT INTO `active` VALUES ('7', '123', '2022-09-07', '08:30:00', '09:15:00', '123');
INSERT INTO `active` VALUES ('8', '123', '2022-09-07', '08:30:00', '09:15:00', '123');
INSERT INTO `active` VALUES ('9', '123', '2022-10-06', '08:30:00', '09:30:00', '123');
INSERT INTO `active` VALUES ('10', '123', '2022-09-09', '08:30:00', '09:30:00', '213');

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
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('1', '韩总', '12345678910', '202cb962ac59075b964b07152d234b70', '1', '1', null);
INSERT INTO `operator` VALUES ('2', '索总', '12345678912', '202cb962ac59075b964b07152d234b70', '1', '1', null);

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
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `useragent` varchar(255) DEFAULT NULL,
  `behavior` longtext,
  `by1` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('145', '韩总', '2022-09-25 23:40:30', null, '1', '\"Windows\"', '23:40:30.377尝试登录 2022-09-25T23:40:34.795查看了用户日程表', null);
INSERT INTO `user_log` VALUES ('146', '韩总', '2022-09-25 23:46:38', null, '1', '\"Windows\"', '23:46:37.579尝试登录', null);
INSERT INTO `user_log` VALUES ('147', 'admin', '2022-09-25 23:51:46', null, '0', '\"Windows\"', '23:51:45.728尝试登录', null);
INSERT INTO `user_log` VALUES ('148', '韩总', '2022-09-25 23:51:58', null, '1', '\"Windows\"', '23:51:57.631尝试登录 2022-09-25T23:52:08.565查看了用户日程表', null);
INSERT INTO `user_log` VALUES ('149', '韩总', '2022-09-26 11:48:06', null, '1', '\"Windows\"', '11:48:06.219尝试登录', null);
INSERT INTO `user_log` VALUES ('150', '韩总', '2022-09-26 23:00:11', null, '1', '\"Windows\"', '23:00:11.204尝试登录', null);
INSERT INTO `user_log` VALUES ('151', '韩总', '2022-09-26 23:00:56', null, '1', '\"Windows\"', '23:00:56.281尝试登录', null);
INSERT INTO `user_log` VALUES ('152', '韩总', '2022-09-26 23:01:28', null, '1', '\"Windows\"', '23:01:27.853尝试登录', null);
INSERT INTO `user_log` VALUES ('153', '韩总', '2022-09-27 15:50:10', null, '1', '\"Windows\"', '15:50:09.944尝试登录', null);
INSERT INTO `user_log` VALUES ('154', '韩总', '2022-10-05 17:53:40', null, '1', '\"Windows\"', '17:53:40.396尝试登录', null);
INSERT INTO `user_log` VALUES ('155', '韩总', '2022-10-05 18:29:14', null, '1', '\"Windows\"', '18:29:13.766尝试登录', null);
INSERT INTO `user_log` VALUES ('156', '韩总', '2022-10-06 14:19:02', null, '1', '\"Windows\"', '14:19:02.463尝试登录', null);

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
