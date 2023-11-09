/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : whitzard

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 09/11/2023 17:19:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_scan
-- ----------------------------
DROP TABLE IF EXISTS `sys_scan`;
CREATE TABLE `sys_scan`  (
  `scan_rule_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置规则的序号',
  `scan_rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则类型',
  `single_rule_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则ID',
  `scan_taint_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '污点类型',
  `scan_namesapce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识符，命名空间',
  `scan_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类名',
  `scan_method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
  `scan_para` int(11) NOT NULL COMMENT '参数',
  PRIMARY KEY (`scan_rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
