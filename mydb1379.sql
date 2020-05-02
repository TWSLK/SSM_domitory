/*
Navicat MySQL Data Transfer

Source Server         : 本地Mysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : mydb1379

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-02-24 00:12:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `teacher` varchar(40) NOT NULL COMMENT '指导员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('53', '三年级二班', '力量');
INSERT INTO `class` VALUES ('54', '三年级一班', '高大哥');

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `codetype` varchar(40) NOT NULL DEFAULT '' COMMENT '编码组',
  `codedesc` varchar(100) NOT NULL COMMENT '编码组用途',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', 'C001', '用户类型', '10', '管理员');
INSERT INTO `code` VALUES ('2', 'C001', '用户类型', '20', '学生');
INSERT INTO `code` VALUES ('3', 'C002', '开启/关闭', 'Y', '开启');
INSERT INTO `code` VALUES ('4', 'C002', '开启/关闭', 'N', '关闭');
INSERT INTO `code` VALUES ('6', 'C003', '是否', 'Y', '是');
INSERT INTO `code` VALUES ('7', 'C003', '是否', 'N', '否');
INSERT INTO `code` VALUES ('8', 'C004', '贷款类型', '10', '一般贷款');
INSERT INTO `code` VALUES ('9', 'C004', '贷款类型', '20', '助学贷款');
INSERT INTO `code` VALUES ('10', 'C005', '贷款状态', '10', '待审核');
INSERT INTO `code` VALUES ('11', 'C005', '贷款状态', '20', '已放款');
INSERT INTO `code` VALUES ('12', 'C005', '贷款状态', '30', '已还款');
INSERT INTO `code` VALUES ('13', 'C005', '贷款状态', '40', '审核不通过');
INSERT INTO `code` VALUES ('14', 'C006', '记录类型', '10', '新增贷款申请');
INSERT INTO `code` VALUES ('15', 'C006', '记录类型', '20', '修改贷款记录');
INSERT INTO `code` VALUES ('16', 'C006', '记录类型', '30', '审核通过并放款');
INSERT INTO `code` VALUES ('17', 'C006', '记录类型', '40', '审核不通过');
INSERT INTO `code` VALUES ('18', 'C006', '记录类型', '50', '贷款回访');
INSERT INTO `code` VALUES ('19', 'C006', '记录类型', '60', '还款提醒');
INSERT INTO `code` VALUES ('20', 'C006', '记录类型', '70', '还款');
INSERT INTO `code` VALUES ('21', 'C007', '订单状态', '10', '已支付');
INSERT INTO `code` VALUES ('22', 'C007', '订单状态', '20', '已发货');
INSERT INTO `code` VALUES ('23', 'C007', '订单状态', '30', '已收货');
INSERT INTO `code` VALUES ('24', 'C007', '订单状态', '40', '已评价');
INSERT INTO `code` VALUES ('27', 'C007', '订单状态', '90', '已取消');
INSERT INTO `code` VALUES ('30', 'TASK_STATUS', '任务状态', '10', '待完成');
INSERT INTO `code` VALUES ('31', 'TASK_STATUS', '任务状态', '20', '已完成');
INSERT INTO `code` VALUES ('32', 'COMMUNITY_STATUS', '社区状态', '10', '待审核');
INSERT INTO `code` VALUES ('33', 'COMMUNITY_STATUS', '社区状态', '20', '审核通过');
INSERT INTO `code` VALUES ('34', 'COMMUNITY_STATUS', '社区状态', '30', '审核不通过');
INSERT INTO `code` VALUES ('35', 'TIXING', '题型', '10', '单选');
INSERT INTO `code` VALUES ('36', 'TIXING', '题型', '20', '多选');
INSERT INTO `code` VALUES ('37', 'LEVEL', '等级', '10', '普通');
INSERT INTO `code` VALUES ('38', 'LEVEL', '等级', '20', '困难');
INSERT INTO `code` VALUES ('39', 'TYPE', '类型', '10', '练习');
INSERT INTO `code` VALUES ('40', 'TYPE', '类型', '20', '考试');
INSERT INTO `code` VALUES ('41', 'ACC001', '消费状态', '10', '待审核');
INSERT INTO `code` VALUES ('42', 'ACC001', '消费状态', '20', '成功');
INSERT INTO `code` VALUES ('43', 'ACC001', '消费状态', '30', '已撤销');
INSERT INTO `code` VALUES ('44', 'ACC002', '消费类型', '10', '充值');
INSERT INTO `code` VALUES ('45', 'ACC002', '消费类型', '20', '消费');
INSERT INTO `code` VALUES ('52', 'C012', '会员卡类别', '10', '月卡');
INSERT INTO `code` VALUES ('47', 'C008', '医生类型', '10', '普通医生');
INSERT INTO `code` VALUES ('48', 'C008', '医生类型', '20', '专家医生');
INSERT INTO `code` VALUES ('49', 'C009', '药剂师类型', '10', '初级执业药师');
INSERT INTO `code` VALUES ('50', 'C009', '药剂师类型', '20', '高级执业药师');
INSERT INTO `code` VALUES ('53', 'C012', '会员卡类别', '20', '季卡');
INSERT INTO `code` VALUES ('54', 'C012', '会员卡类别', '30', '年卡');
INSERT INTO `code` VALUES ('55', 'C013', '性别', '10', '男');
INSERT INTO `code` VALUES ('56', 'C013', '性别', '20', '女');
INSERT INTO `code` VALUES ('57', 'C011', '采购状态', '10', '采购中');
INSERT INTO `code` VALUES ('58', 'C011', '采购状态', '20', '采购入库');
INSERT INTO `code` VALUES ('61', 'C014', '出库状态', '10', '未出库');
INSERT INTO `code` VALUES ('60', 'C001', '用户类型', '30', '宿舍管理员');
INSERT INTO `code` VALUES ('62', 'C014', '出库状态', '20', '已出库');
INSERT INTO `code` VALUES ('63', 'C015', '入库状态', '20', '已入库');
INSERT INTO `code` VALUES ('64', 'C015', '入库状态', '10', '未入库');
INSERT INTO `code` VALUES ('65', 'C014', '出库状态', '30', '已取消');
INSERT INTO `code` VALUES ('66', 'C015', '入库状态', '30', '已取消');
INSERT INTO `code` VALUES ('67', 'C016', '缴费状态', '10', '未确认');
INSERT INTO `code` VALUES ('68', 'C016', '缴费状态', '20', '已确认');
INSERT INTO `code` VALUES ('69', 'C017', '缴费类型', '10', '水费');
INSERT INTO `code` VALUES ('70', 'C017', '缴费类型', '20', '电费');
INSERT INTO `code` VALUES ('71', 'C018', '维修状态', '10', '等待维修');
INSERT INTO `code` VALUES ('72', 'C018', '维修状态', '20', '维修完成');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL,
  `classid` varchar(40) NOT NULL,
  `admin` varchar(40) NOT NULL COMMENT '宿管',
  `bedqty` varchar(10) DEFAULT NULL COMMENT '床位',
  `waterlast` decimal(10,2) DEFAULT '0.00' COMMENT '剩余水费',
  `spotlast` decimal(10,2) DEFAULT '0.00' COMMENT '剩余电费',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('53', '8-12-001', '53', '紧急', '6', '90.00', '100.00');
INSERT INTO `house` VALUES ('54', '8-12-002', '54', '大桥', '4', '10.00', '50.00');

-- ----------------------------
-- Table structure for house_pay
-- ----------------------------
DROP TABLE IF EXISTS `house_pay`;
CREATE TABLE `house_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL COMMENT '宿舍编号',
  `paytype` varchar(10) NOT NULL COMMENT '缴费类型',
  `amt` decimal(10,2) NOT NULL COMMENT '金额',
  `status` varchar(10) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of house_pay
-- ----------------------------
INSERT INTO `house_pay` VALUES ('53', '8-12-001', '10', '50.00', '20');
INSERT INTO `house_pay` VALUES ('54', '8-12-001', '20', '100.00', '20');
INSERT INTO `house_pay` VALUES ('55', '8-12-001', '20', '100.00', '20');
INSERT INTO `house_pay` VALUES ('56', '8-12-001', '10', '10.00', '20');
INSERT INTO `house_pay` VALUES ('57', '8-12-001', '10', '22.00', '10');
INSERT INTO `house_pay` VALUES ('58', '8-12-002', '10', '10.00', '20');
INSERT INTO `house_pay` VALUES ('59', '8-12-002', '20', '50.00', '20');

-- ----------------------------
-- Table structure for house_point
-- ----------------------------
DROP TABLE IF EXISTS `house_point`;
CREATE TABLE `house_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL COMMENT '宿舍编号',
  `score` varchar(100) NOT NULL COMMENT '扣分数',
  `descr` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of house_point
-- ----------------------------
INSERT INTO `house_point` VALUES ('53', '8-12-001', '2', '不不不2');
INSERT INTO `house_point` VALUES ('54', '8-12-002', '1', '没锁门');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL,
  `gid` int(11) NOT NULL,
  `gname` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `realqty` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('51', '2020年异地重', '49', '啤酒1', '200', '180', '20');
INSERT INTO `purchase` VALUES ('52', '范德萨发是', '50', '果汁', '50', '50', '20');
INSERT INTO `purchase` VALUES ('53', 'fwfwef', '49', '啤酒1', '10', '10', '20');
INSERT INTO `purchase` VALUES ('54', '核桃露第一批采购', '51', '核桃露', '90', '90', '20');
INSERT INTO `purchase` VALUES ('55', '杏仁露首批采购', '52', '杏仁露', '90', '90', '20');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '报修内容',
  `status` varchar(10) NOT NULL COMMENT '报修状态',
  `appraise` varchar(255) DEFAULT NULL COMMENT '报修评价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('54', '8-12-001', '室内灯不亮了', '20', '啪啪啪啪1');
INSERT INTO `repair` VALUES ('55', '8-12-001', '刘医生', '20', '服务范围');
INSERT INTO `repair` VALUES ('56', '8-12-001', '厕所', '20', '好评');
INSERT INTO `repair` VALUES ('57', '8-12-002', '门锁坏了', '20', null);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `link` varchar(20) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `descr` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('12', '第一个供应商', '啊啊', '13899922200', '01087654252', '中国北京', '不不不');
INSERT INTO `supplier` VALUES ('13', '第二个供应商', '啊啊', '33232323', '2323', '3223', '2332');
INSERT INTO `supplier` VALUES ('14', '第三个供应商', '陈', '13899922888', '01087654252', '中国', '版本');
INSERT INTO `supplier` VALUES ('15', '第四个供应商', '李四', '13899922888', '23222332', '中国', '备足');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) NOT NULL COMMENT '昵称',
  `login` varchar(40) NOT NULL COMMENT '登录账号',
  `pwd` varchar(40) NOT NULL COMMENT '登陆密码',
  `utype` varchar(10) NOT NULL COMMENT '用户类型(C001)',
  `yn` char(1) DEFAULT 'Y',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像',
  `classid` varchar(10) DEFAULT NULL COMMENT '班级',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `age` varchar(10) DEFAULT NULL COMMENT '年龄',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `addr` varchar(50) DEFAULT NULL COMMENT '地址',
  `email` varchar(50) DEFAULT NULL,
  `itime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_login` (`login`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'admin', '111111', '10', 'Y', '../../upload/123.jpeg', '10', '10', null, '13899922888', '36', '3223322', null);
INSERT INTO `user` VALUES ('55', '小红', 'XS100001', '111111', '20', 'Y', '../../upload/123.jpeg', '53', '10', null, '13799990000', '中国北京', '1231@qq.com', null);
INSERT INTO `user` VALUES ('56', '李二牛', 'SG001', '111111', '30', 'Y', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('57', '小明名', 'XS100002', '111111', '20', 'Y', '../../upload/123.jpeg', '54', '10', null, '13799990000', '中国北京', '123331@qq.com', null);

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '受访人',
  `vname` varchar(50) NOT NULL COMMENT '访客',
  `rel` varchar(50) DEFAULT NULL COMMENT '关系',
  `starttime` varchar(30) DEFAULT NULL,
  `endtime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES ('54', '8-12-001', 'fsdfas', 'fds', 'fds', 'fd', 'f');
INSERT INTO `visitor` VALUES ('55', '8-12-001', 'fwfwef', 'fwfwef', 'ewf', 'few', 'ewfew');
INSERT INTO `visitor` VALUES ('56', '8-12-001', '新的受访人', '小明', '父子', '2020-02-18 10:00:00', '2020-02-18 11:00:00');
