/*
Navicat MySQL Data Transfer

Source Server         : localhost-MyPC
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : duogu

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-01-17 08:37:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '游戏代码',
  `name` varchar(100) NOT NULL COMMENT '游戏名称',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='游戏表';

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', 'test', '测试', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');

-- ----------------------------
-- Table structure for game_record
-- ----------------------------
DROP TABLE IF EXISTS `game_record`;
CREATE TABLE `game_record` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `game_id` int(20) NOT NULL COMMENT '游戏id',
  `dg_account` varchar(100) NOT NULL COMMENT '账号内部识别标识',
  `record` longtext NOT NULL COMMENT '游戏记录',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏记录表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of game_record
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `game_id` int(20) NOT NULL COMMENT '游戏id',
  `dg_account` varchar(100) NOT NULL COMMENT '账号内部识别标识',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `platform_type` varchar(100) NOT NULL COMMENT '绑定平台账号类型',
  `platform_account` varchar(100) NOT NULL COMMENT '绑定平台账号标识',
  `mobile_type` varchar(10) NOT NULL COMMENT '手机系统类型（android，ios）',
  `nikename` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `head` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for mobile_android
-- ----------------------------
DROP TABLE IF EXISTS `mobile_android`;
CREATE TABLE `mobile_android` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `imei` varchar(100) NOT NULL COMMENT '手机标识',
  `android_id` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `address` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='android手机信息表';

-- ----------------------------
-- Records of mobile_android
-- ----------------------------

-- ----------------------------
-- Table structure for mobile_ios
-- ----------------------------
DROP TABLE IF EXISTS `mobile_ios`;
CREATE TABLE `mobile_ios` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `idfa` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `idfv` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `open_udid` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ios手机信息表';

-- ----------------------------
-- Records of mobile_ios
-- ----------------------------

-- ----------------------------
-- Procedure structure for pro_add_game_partition
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_add_game_partition`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_add_game_partition`(
	IN game_id INT
)
BEGIN
	CALL pro_add_list_partition('game_record', CONCAT('p',game_id), game_id);
	CALL pro_add_list_partition('member', CONCAT('p',game_id), game_id);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_add_list_partition
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_add_list_partition`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_add_list_partition`(
	IN `table_name` VARCHAR (100),
	IN `partition_name` VARCHAR (100),
	IN partition_value INT
)
BEGIN
	DECLARE m_sql VARCHAR (1000) ;
	SET m_sql = CONCAT('ALTER TABLE ', table_name, ' ADD PARTITION ( PARTITION ',partition_name, ' VALUES IN (', partition_value,'))'); 
	SET @m_stmt=m_sql;
	PREPARE stmt FROM @m_stmt;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
END
;;
DELIMITER ;
