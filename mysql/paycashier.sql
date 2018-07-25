/*
 Navicat Premium Data Transfer

 Source Server         : syduogu.com
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : syduogu.com
 Source Database       : duogu

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 03/19/2018 13:35:08 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `paycashier`
-- ----------------------------
DROP TABLE IF EXISTS `pay_cashier`;
CREATE TABLE `pay_cashier` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dg_udid` varchar(100) NOT NULL COMMENT '用户代码',
  `game_id` varchar(100) NOT NULL COMMENT '游戏代码',
  `pay_code` varchar(100) NOT NULL COMMENT '支付记录代码',
  `partner_code` varchar(32) NOT NULL COMMENT '商户号',
  `product_name` varchar(50) NOT NULL COMMENT '商品名',
  `order_id` varchar(32) NOT NULL COMMENT '商品订单id',
  `user_email` varchar(100) NOT NULL COMMENT '用户邮箱',
  `currency_code` varchar(10) NOT NULL COMMENT '币种符号',
  `settle_amount` float(10,2) NOT NULL COMMENT '支付金额',
  `transaction_id` varchar(32) NOT NULL COMMENT '交易流水号',
  `pay_url` varchar(200) NOT NULL COMMENT '用户支付地址',
  `pay_status` varchar(50) NOT NULL COMMENT '支付状态',
  `pay_time` varchar(200) NOT NULL COMMENT '用户支付时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='游戏表';

