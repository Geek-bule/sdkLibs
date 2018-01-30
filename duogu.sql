/*
Navicat MySQL Data Transfer

Source Server         : localhost-MyPC
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : duogu

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-01-26 17:25:14
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='游戏记录表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB,
 PARTITION p2 VALUES IN (2) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of game_record
-- ----------------------------
INSERT INTO `game_record` VALUES ('1', '1', 'herman', '12321321321', '2018-01-17 13:49:20', '2018-01-17 13:49:20', '0');

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
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB,
 PARTITION p2 VALUES IN (2) ENGINE = InnoDB) */;

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
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current` int(20) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列表';

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('dgAccount#1', '2', '1');
INSERT INTO `sequence` VALUES ('dgAccount#2', '0', '1');
INSERT INTO `sequence` VALUES ('dgUdid', '143', '1');

-- ----------------------------
-- Procedure structure for pro_add_game
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_add_game`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_add_game`(IN game_id INT)
BEGIN
	CALL pro_add_list_partition('game_record', CONCAT('p',game_id), game_id);
	CALL pro_add_list_partition('member', CONCAT('p',game_id), game_id);
	INSERT INTO `sequence` VALUES (CONCAT('dgAccount#', game_id), '0', '1');
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

-- ----------------------------
-- Function structure for fun_get_currval
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_get_currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_get_currval`(m_name VARCHAR(50)) RETURNS int(11)
begin        
	declare value integer;         
	set value = 0;         
	select current into value  from sequence where name = m_name;   
	return value;   
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_get_dg_account
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_get_dg_account`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_get_dg_account`(game_id int(20)) RETURNS varchar(20) CHARSET utf8
begin  
	DECLARE seq INTEGER;  -- 当前序列号
	DECLARE seqResult varchar(10);  -- 当前序列号处理结果
	SELECT fun_get_nextval(CONCAT('dgAccount', '#', game_id)) INTO seq;
	IF seq<100000000 THEN
		SET seqResult = LPAD(seq, 8, 0);
	ELSE
		SET seqResult = seq;
	END IF;
	return CONCAT(game_id, 'dg', seqResult);  
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_get_dg_udid
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_get_dg_udid`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_get_dg_udid`() RETURNS varchar(20) CHARSET utf8
begin  
	DECLARE seq INTEGER;  -- 当前序列号
	DECLARE seqResult varchar(10);  -- 当前序列号处理结果
	SELECT fun_get_nextval('dgUdid') INTO seq;
	IF seq<10000000000 THEN
		SET seqResult = LPAD(seq, 10, 0);
	ELSE
		SET seqResult = seq;
	END IF;
	return CONCAT('dg', seqResult);  
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_get_nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_get_nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_get_nextval`(m_name VARCHAR(50)) RETURNS int(11)
begin  
	update sequence set current = current + increment where name = m_name;  
	return fun_get_currval(m_name);  
end
;;
DELIMITER ;
