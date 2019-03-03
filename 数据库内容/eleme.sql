/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : 127.168.155.1:3306
Source Database       : eleme

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-12-26 17:23:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `Cmt_Id` int(11) NOT NULL AUTO_INCREMENT,
  `S_Id` int(11) NOT NULL,
  `U_Id` int(11) NOT NULL,
  `Score` double(10,1) NOT NULL,
  `Description` text COLLATE gb2312_bin,
  `Agree` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`Cmt_Id`),
  KEY `S_Id` (`S_Id`),
  KEY `U_Id` (`U_Id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`S_Id`) REFERENCES `shop` (`S_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`U_Id`) REFERENCES `users` (`U_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `C_Id` int(11) NOT NULL AUTO_INCREMENT,
  `S_Id` int(11) DEFAULT NULL,
  `Name` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Price` double NOT NULL,
  `Description` text COLLATE gb2312_bin,
  `SalesVolume` int(10) unsigned zerofill NOT NULL,
  `Category` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Imagename` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`C_Id`),
  KEY `S_Id_key` (`S_Id`),
  CONSTRAINT `S_Id_key` FOREIGN KEY (`S_Id`) REFERENCES `shop` (`S_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '1', '张飞红汤牛肉米线', '48.9', 0x6E756C6C, '0000000867', '张飞牛肉米线', 'food0');
INSERT INTO `commodity` VALUES ('2', '1', '张飞清汤牛肉面', '48.9', 0x6E756C6C, '0000000213', '张飞牛肉面', 'food1');
INSERT INTO `commodity` VALUES ('3', '1', '张飞酸辣牛肉米线', '50.9', 0x6E756C6C, '0000000200', '张飞牛肉面', 'food2');
INSERT INTO `commodity` VALUES ('4', '1', '张飞红汤牛肉粉丝', '48.9', 0x6E756C6C, '0000000152', '张飞牛肉粉丝', 'food3');
INSERT INTO `commodity` VALUES ('5', '1', '张飞酸辣牛肉面', '50.9', 0x6E756C6C, '0000000141', '张飞牛肉面', 'food4');
INSERT INTO `commodity` VALUES ('6', '1', '张飞清汤牛肉米线', '48.9', 0x6E756C6C, '0000000124', '张飞牛肉米线', 'food5');
INSERT INTO `commodity` VALUES ('7', '1', '张飞泡椒牛肉面', '51.9', 0x6E756C6C, '0000000018', '张飞牛肉面', 'food6');
INSERT INTO `commodity` VALUES ('8', '1', '张飞酸辣牛肉粉丝', '50.9', 0x6E756C6C, '0000000028', '张飞牛肉粉丝', 'food7');
INSERT INTO `commodity` VALUES ('9', '1', '张飞清汤牛肉粉丝', '48.9', 0x6E756C6C, '0000000045', '张飞牛肉粉丝', 'food8');
INSERT INTO `commodity` VALUES ('10', '1', '康师傅冰红茶盒装', '10', 0x6E756C6C, '0000000000', '饮料', 'food9');
INSERT INTO `commodity` VALUES ('11', '1', '雪花淡爽', '4', 0x6E756C6C, '0000000005', '饮料', 'food10');
INSERT INTO `commodity` VALUES ('12', '1', '听雪碧', '3', 0x6E756C6C, '0000000019', '饮料', 'food11');
INSERT INTO `commodity` VALUES ('13', '1', '康师傅冰红茶', '4', 0x6E756C6C, '0000000011', '饮料', 'food12');
INSERT INTO `commodity` VALUES ('14', '1', '农夫山泉矿泉水', '2', 0x6E756C6C, '0000000005', '饮料', 'food13');
INSERT INTO `commodity` VALUES ('15', '1', '张飞红汤牛肉面', '48.9', 0x6E756C6C, '0000000757', '张飞牛肉面', 'food14');
INSERT INTO `commodity` VALUES ('16', '1', '张飞酸辣牛肉米线', '50.9', 0x6E756C6C, '0000000200', '张飞牛肉米线', 'food15');
INSERT INTO `commodity` VALUES ('17', '1', '张飞泡椒牛肉米线', '51.9', 0x6E756C6C, '0000000022', '张飞牛肉米线', 'food16');
INSERT INTO `commodity` VALUES ('18', '1', '张飞红汤牛肉米线', '48.9', 0x6E756C6C, '0000000867', '热销', 'food0');
INSERT INTO `commodity` VALUES ('19', '1', '张飞清汤牛肉面', '48.9', 0x6E756C6C, '0000000213', '热销', 'food1');
INSERT INTO `commodity` VALUES ('20', '1', '张飞酸辣牛肉米线', '50.9', 0x6E756C6C, '0000000200', '热销', 'food2');
INSERT INTO `commodity` VALUES ('21', '1', '张飞红汤牛肉粉丝', '48.9', 0x6E756C6C, '0000000152', '热销', 'food3');
INSERT INTO `commodity` VALUES ('22', '1', '张飞清汤牛肉米线', '48.9', 0x6E756C6C, '0000000124', '热销', 'food5');
INSERT INTO `commodity` VALUES ('23', '1', '张飞泡椒牛肉面', '51.9', 0x6E756C6C, '0000000018', '热销', 'food6');
INSERT INTO `commodity` VALUES ('24', '1', '张飞酸辣牛肉粉丝', '50.9', 0x6E756C6C, '0000000028', '热销', 'food7');
INSERT INTO `commodity` VALUES ('25', '1', '张飞清汤牛肉粉丝', '48.9', 0x6E756C6C, '0000000045', '热销', 'food8');

-- ----------------------------
-- Table structure for ord
-- ----------------------------
DROP TABLE IF EXISTS `ord`;
CREATE TABLE `ord` (
  `O_Id` int(11) NOT NULL AUTO_INCREMENT,
  `C_Id` int(11) NOT NULL,
  `U_Id` int(11) NOT NULL,
  `R_Id` int(11) NOT NULL,
  `IfAccept` int(1) unsigned zerofill NOT NULL,
  `Time` varchar(255) COLLATE gb2312_bin DEFAULT NULL,
  `Number` int(11) DEFAULT NULL,
  PRIMARY KEY (`O_Id`),
  KEY `2` (`C_Id`),
  KEY `3` (`U_Id`),
  KEY `4` (`R_Id`),
  CONSTRAINT `2` FOREIGN KEY (`C_Id`) REFERENCES `commodity` (`C_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `3` FOREIGN KEY (`U_Id`) REFERENCES `users` (`U_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `4` FOREIGN KEY (`R_Id`) REFERENCES `receiver` (`R_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of ord
-- ----------------------------
INSERT INTO `ord` VALUES ('14', '1', '1', '1', '1', '2018-12-22 16:41:30', '2');
INSERT INTO `ord` VALUES ('22', '1', '1', '1', '1', '2018-12-22 19:55:59', '2');
INSERT INTO `ord` VALUES ('23', '2', '1', '1', '1', '2018-12-22 19:55:59', '1');
INSERT INTO `ord` VALUES ('39', '2', '1', '1', '1', '2018-12-23 10:23:42', '1');
INSERT INTO `ord` VALUES ('40', '8', '1', '1', '1', '2018-12-23 20:31:56', '1');
INSERT INTO `ord` VALUES ('41', '3', '1', '1', '1', '2018-12-23 21:49:00', '0');
INSERT INTO `ord` VALUES ('42', '11', '1', '1', '1', '2018-12-23 21:49:00', '1');
INSERT INTO `ord` VALUES ('43', '10', '1', '1', '1', '2018-12-23 21:49:00', '1');
INSERT INTO `ord` VALUES ('44', '9', '1', '1', '1', '2018-12-23 21:49:01', '1');

-- ----------------------------
-- Table structure for receiver
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `R_Id` int(11) NOT NULL AUTO_INCREMENT,
  `U_Id` int(11) NOT NULL,
  `Name` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Sex` varchar(255) COLLATE gb2312_bin DEFAULT NULL,
  `Tel` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Address` varchar(255) COLLATE gb2312_bin NOT NULL,
  PRIMARY KEY (`R_Id`),
  KEY `5` (`U_Id`),
  CONSTRAINT `5` FOREIGN KEY (`U_Id`) REFERENCES `users` (`U_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of receiver
-- ----------------------------
INSERT INTO `receiver` VALUES ('1', '1', 'ysw', '男', '12345678901', 'here');
INSERT INTO `receiver` VALUES ('2', '2', '王二', '女', '12345678901', 'here1');
INSERT INTO `receiver` VALUES ('3', '3', '李三', '男', '123456789', 'here2');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `S_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Introduction` text COLLATE gb2312_bin NOT NULL,
  `StartTime` int(11) NOT NULL,
  `EndTime` int(11) NOT NULL,
  `MinPrice` int(11) NOT NULL,
  `Dprice` int(11) NOT NULL,
  `Distance` int(11) NOT NULL,
  `GetTime` int(11) NOT NULL,
  `Score` double(2,1) unsigned NOT NULL,
  `Sale` int(255) NOT NULL,
  `ImageURL` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`S_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '张飞牛肉', 0x6E756C6C, '0', '1000', '0', '2', '420', '36', '4.8', '2446', 'shop1.jpg');
INSERT INTO `shop` VALUES ('2', '东湖黑哥酸菜鱼', 0x6E756C6C, '0', '1000', '0', '4', '398', '36', '4.0', '48', 'shop2.jpg');
INSERT INTO `shop` VALUES ('3', '第一家大鸡排', 0x6E756C6C, '0', '1000', '0', '5', '1000', '50', '3.8', '25', 'shop3.jpg');
INSERT INTO `shop` VALUES ('4', '许记粥铺', 0x6E756C6C, '0', '1000', '0', '2', '1600', '44', '4.6', '2828', 'shop4.jpg');
INSERT INTO `shop` VALUES ('5', '小胖哥烧烤', 0x6E756C6C, '0', '1000', '15', '1', '1400', '44', '4.6', '1281', 'shop5.jpg');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `U_Id` int(11) NOT NULL,
  `Username` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Password` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Name` varchar(255) COLLATE gb2312_bin NOT NULL,
  `Tel` varchar(255) COLLATE gb2312_bin NOT NULL,
  `RecInfNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`U_Id`,`Username`),
  KEY `U_Id(用户)` (`U_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '15306574725', 'yswdra', 'ysw', '12345678901', '1');
INSERT INTO `users` VALUES ('2', '13626747263', 'wanger', '王二', '12345678901', '2');
INSERT INTO `users` VALUES ('3', '15306574123', 'lisan', '李三', '11122233378', '3');
