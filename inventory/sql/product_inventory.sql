/*
Navicat MySQL Data Transfer

Source Server         : 10.1.100.20
Source Server Version : 50733
Source Host           : 10.1.100.20:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-03-28 16:28:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_inventory
-- ----------------------------
DROP TABLE IF EXISTS `product_inventory`;
CREATE TABLE `product_inventory` (
  `product_id` int(11) DEFAULT NULL,
  `inventory_cnt` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_inventory
-- ----------------------------
INSERT INTO `product_inventory` VALUES ('1', '100');
