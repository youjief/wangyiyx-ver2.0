/*
 Navicat Premium Data Transfer

 Source Server         : foreign
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 192.168.1.139:3306
 Source Schema         : wyyx

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 17/10/2019 12:02:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_info
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info`  (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详细',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_numbers` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_kind` int(255) NULL DEFAULT NULL COMMENT '1居家生活，2服饰鞋包，3美食酒水，4个护清洁，5数码家电',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `goods_stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品库存',
  `goods_praise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品好评率',
  `goods_is_seckill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是秒杀商品',
  `goods_sales` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品销量',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `delivery_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `order_channel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `goods_freight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运费',
  `goods_scores` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品积分',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '是否删除',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_level` int(1) NULL DEFAULT NULL COMMENT '0普通用户，1一级，2二级，3三级，4会员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `headimgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `privilege` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0未设置，1男，2女',
  `exp_value` int(11) NULL DEFAULT NULL COMMENT '经验值',
  `user_scores` int(11) NULL DEFAULT NULL COMMENT '用户积分',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的IP地址',
  `vip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0非VIP，1是VIP（超级会员）',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `birth_is_change` int(255) NULL DEFAULT NULL COMMENT '0未设置生日，1设置过了，就不让改了',
  `user_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户收货地址',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
