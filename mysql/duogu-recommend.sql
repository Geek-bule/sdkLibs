/*
Navicat MySQL Data Transfer

Source Server         : aliyun-47.104.67.171
Source Server Version : 50639
Source Host           : 47.104.67.171:3306
Source Database       : duogu

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-02-07 15:14:01
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
  `mobile_type` varchar(10) NOT NULL COMMENT '手机系统类型（android，ios）',
  `platform_id` varchar(50) NOT NULL COMMENT '平台id',
  `platform_url` varchar(200) NOT NULL COMMENT '平台下载地址',
  `platform_version` varchar(10) NOT NULL COMMENT '平台版本号',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='游戏表';

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', '1261639425', '连连看','ios','1261639425','https://itunes.apple.com/us/app/id1261639425?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('2', '1342462183', '点击英雄','ios','1342462183','https://itunes.apple.com/us/app/id1342462183?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('3', '1336327013', '数独','ios','1336327013','https://itunes.apple.com/us/app/id1336327013?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('4', '1279669189', '疯狂斗地主','ios','1279669189','https://itunes.apple.com/us/app/id1279669189?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('5', '1319639601', '僵尸入侵','ios','1319639601','https://itunes.apple.com/us/app/id1319639601?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('6', '1261635971', '俄罗斯方块','ios','1261635971','https://itunes.apple.com/us/app/id1261635971?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('7', '1225853471', '跑酷忍者','ios','1225853471','https://itunes.apple.com/us/app/id1225853471?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('8', '1229409097', '极品飙车','ios','1229409097','https://itunes.apple.com/us/app/id1229409097?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('9', '1204774023', '飞机大战','ios','1204774023','https://itunes.apple.com/us/app/id1204774023?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('10', '1195586792', '别踩白块儿','ios','1195586792','https://itunes.apple.com/us/app/id1195586792?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');
INSERT INTO `game` VALUES ('11', '1344649658', '僵尸射击','ios','1344649658','https://itunes.apple.com/us/app/id1344649658?l=zh&ls=1&mt=8','1.0', '2018-01-12 14:06:53', '2018-01-12 14:06:53', '0');


-- ----------------------------
-- Table structure for game_info
-- ----------------------------
DROP TABLE IF EXISTS `game_info`;
CREATE TABLE `game_info` (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
`game_id` int(20) NOT NULL COMMENT '游戏ID',
`language` varchar(50) NOT NULL COMMENT '语言代码',
`landscape_img` varchar(300) NOT NULL COMMENT '横屏图',
`portrait_img` varchar(300) NOT NULL COMMENT '竖屏图',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
PRIMARY KEY (`id`),
KEY `ind_g_l` (`game_id`,`language`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='游戏图片表';


-- ----------------------------
-- Table structure for mobile_game
-- ----------------------------
DROP TABLE IF EXISTS `mobile_game`;
CREATE TABLE `mobile_game` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
  `game_id` int(20) NOT NULL COMMENT '游戏id',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `version` varchar(50) NOT NULL COMMENT '版本号',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`game_id`),
  KEY `ind_memberId` (`dg_udid`) USING BTREE,
  KEY `ind_game_user` (`game_id`,`dg_udid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11808613 DEFAULT CHARSET=utf8 COMMENT='用户游戏表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB,
PARTITION p2 VALUES IN (2) ENGINE = InnoDB,
PARTITION p3 VALUES IN (3) ENGINE = InnoDB,
PARTITION p4 VALUES IN (4) ENGINE = InnoDB,
PARTITION p5 VALUES IN (5) ENGINE = InnoDB,
PARTITION p6 VALUES IN (6) ENGINE = InnoDB,
PARTITION p7 VALUES IN (7) ENGINE = InnoDB,
PARTITION p8 VALUES IN (8) ENGINE = InnoDB,
PARTITION p9 VALUES IN (9) ENGINE = InnoDB,
PARTITION p10 VALUES IN (10) ENGINE = InnoDB,
 PARTITION p11 VALUES IN (11) ENGINE = InnoDB) */;

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
PARTITION p2 VALUES IN (2) ENGINE = InnoDB,
PARTITION p3 VALUES IN (3) ENGINE = InnoDB,
PARTITION p4 VALUES IN (4) ENGINE = InnoDB,
PARTITION p5 VALUES IN (5) ENGINE = InnoDB,
PARTITION p6 VALUES IN (6) ENGINE = InnoDB,
PARTITION p7 VALUES IN (7) ENGINE = InnoDB,
PARTITION p8 VALUES IN (8) ENGINE = InnoDB,
PARTITION p9 VALUES IN (9) ENGINE = InnoDB,
PARTITION p10 VALUES IN (10) ENGINE = InnoDB,
 PARTITION p11 VALUES IN (11) ENGINE = InnoDB) */;

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
  `platform_account` varchar(100) DEFAULT NULL COMMENT '绑定平台账号标识',
  `mobile_type` varchar(10) NOT NULL COMMENT '手机系统类型（android，ios）',
  `nikename` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `head` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户账号表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB,
PARTITION p2 VALUES IN (2) ENGINE = InnoDB,
PARTITION p3 VALUES IN (3) ENGINE = InnoDB,
PARTITION p4 VALUES IN (4) ENGINE = InnoDB,
PARTITION p5 VALUES IN (5) ENGINE = InnoDB,
PARTITION p6 VALUES IN (6) ENGINE = InnoDB,
PARTITION p7 VALUES IN (7) ENGINE = InnoDB,
PARTITION p8 VALUES IN (8) ENGINE = InnoDB,
PARTITION p9 VALUES IN (9) ENGINE = InnoDB,
PARTITION p10 VALUES IN (10) ENGINE = InnoDB,
 PARTITION p11 VALUES IN (11) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for mobile_android
-- ----------------------------
DROP TABLE IF EXISTS `mobile_android`;
CREATE TABLE `mobile_android` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `imei` varchar(100) NOT NULL COMMENT '手机标识',
  `android_id` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `address` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_imei` (`imei`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='android手机信息表';

-- ----------------------------
-- Records of mobile_android
-- ----------------------------

-- ----------------------------
-- Table structure for mobile_ios
-- ----------------------------
DROP TABLE IF EXISTS `mobile_ios`;
CREATE TABLE `mobile_ios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dg_udid` varchar(100) NOT NULL COMMENT '本平台生成的设备唯一标识',
  `idfa` varchar(100) NOT NULL COMMENT '手机标识',
  `idfv` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `open_udid` varchar(100) DEFAULT NULL COMMENT '手机标识',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_idfa` (`idfa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='ios手机信息表';

-- ----------------------------
-- Records of mobile_ios
-- ----------------------------

-- ----------------------------
-- Table structure for push_rule
-- ----------------------------
DROP TABLE IF EXISTS `push_rule`;
CREATE TABLE `push_rule` (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
`game_code` varchar(40) NOT NULL COMMENT '游戏CODE',
`mobile_type` varchar(50) NOT NULL COMMENT '手机系统类型（android，ios）',
`push_game_code` varchar(40) NOT NULL COMMENT '推送的游戏CODE',
`percent` int(5) NOT NULL DEFAULT '0' COMMENT '百分比',
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
PRIMARY KEY (`id`),
KEY `ind_g_c_p` (`game_code`,`mobile_type`,`push_game_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='游戏推送规则表';

-- ----------------------------
-- Table structure for push_record
-- ----------------------------
DROP TABLE IF EXISTS `push_record`;
CREATE TABLE `push_record` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
  `dg_udid` varchar(100) NOT NULL COMMENT '用户ID',
  `game_id` int(20) NOT NULL COMMENT '游戏ID',
  `push_game_id` int(20) NOT NULL COMMENT '推送的游戏ID',
  `push_version` varchar(50) NOT NULL COMMENT '推送游戏的版本号',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：未激活，1：已激活）',
  `jump_times` int(10) NOT NULL DEFAULT '1' COMMENT '跳转次数',
  `last_jump_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '跳转时间',
  `activeTime` timestamp NULL DEFAULT NULL COMMENT '激活时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`game_id`),
  KEY `ind_memberId` (`dg_udid`) USING BTREE,
  KEY `ind_m_g` (`dg_udid`,`game_id`) USING BTREE,
  KEY `ind_m_g_p_l` (`dg_udid`,`push_game_id`,`push_version`,`last_jump_time`),
  KEY `ind_m_g_p_p` (`dg_udid`,`game_id`,`push_game_id`,`push_version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='游戏推送记录表'
/*!50100 PARTITION BY LIST (game_id)
(PARTITION p1 VALUES IN (1) ENGINE = InnoDB,
PARTITION p2 VALUES IN (2) ENGINE = InnoDB,
PARTITION p3 VALUES IN (3) ENGINE = InnoDB,
PARTITION p4 VALUES IN (4) ENGINE = InnoDB,
PARTITION p5 VALUES IN (5) ENGINE = InnoDB,
PARTITION p6 VALUES IN (6) ENGINE = InnoDB,
PARTITION p7 VALUES IN (7) ENGINE = InnoDB,
PARTITION p8 VALUES IN (8) ENGINE = InnoDB,
PARTITION p9 VALUES IN (9) ENGINE = InnoDB,
PARTITION p10 VALUES IN (10) ENGINE = InnoDB,
 PARTITION p11 VALUES IN (11) ENGINE = InnoDB) */;

-- ----------------------------
-- Table structure for push_statistics
-- ----------------------------
DROP TABLE IF EXISTS `push_statistics`;
CREATE TABLE `push_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(20) NOT NULL COMMENT '游戏ID',
  `push_game_id` int(20) NOT NULL COMMENT '推送的游戏ID',
  `icon_show` int(5) NOT NULL DEFAULT '0' COMMENT 'icon展示数',
  `icon_click` int(5) NOT NULL DEFAULT '0' COMMENT 'icon点击数',
  `icon_active` int(5) NOT NULL DEFAULT '0' COMMENT 'icon激活数',
  `statistics_date` int(8) NOT NULL COMMENT '统计日期',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`,`statistics_date`),
  KEY `ind_gameId` (`game_id`),
  KEY `ind_pushGameId` (`push_game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='游戏推送统计表';


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
INSERT INTO `sequence` VALUES ('dgUdid', '4', '1');

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
	return CONCAT(game_id, 'd', seqResult);  
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
	return CONCAT('d', seqResult);  
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
