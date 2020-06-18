/*
 Navicat Premium Data Transfer

 Source Server         : 39.105.159.66
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 39.105.159.66:3306
 Source Schema         : BLOG_DB

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 17/06/2020 8:10:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_article
-- ----------------------------
DROP TABLE IF EXISTS `biz_article`;
CREATE TABLE `biz_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章封面图片',
  `qrcode_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章专属二维码地址',
  `is_markdown` tinyint(3) UNSIGNED NULL DEFAULT 1,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  `content_md` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'markdown版的文章内容',
  `top` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否置顶',
  `category_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '类型',
  `status` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '状态',
  `recommended` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否推荐',
  `slider` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否轮播',
  `slider_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图地址',
  `original` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '是否原创',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章简介，最多200字',
  `keywords` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章关键字，优化搜索',
  `comment` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '是否开启评论',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_article
-- ----------------------------
INSERT INTO `biz_article` VALUES (34, '示例文章', '1', 'Suk', '', NULL, 1, '<p>示例文章</p>', NULL, 1, 1, 1, 1, 0, '', 1, '示例文章', '示例文章', 1, '2020-06-17 09:43:47', '2020-06-17 17:51:10');

-- ----------------------------
-- Table structure for biz_article_look
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_look`;
CREATE TABLE `biz_article_look`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` int(10) UNSIGNED NOT NULL COMMENT '文章ID',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已登录用户ID',
  `user_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户IP',
  `look_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '浏览时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章浏览记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_article_look
-- ----------------------------
INSERT INTO `biz_article_look` VALUES (9, 1, NULL, '0:0:0:0:0:0:0:1', '2019-10-18 17:25:04', '2019-10-18 17:25:04', '2019-10-18 17:25:04');
INSERT INTO `biz_article_look` VALUES (10, 12, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:07:32', '2019-10-21 16:07:32', '2019-10-21 16:07:32');
INSERT INTO `biz_article_look` VALUES (11, 3, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:09:09', '2019-10-21 16:09:09', '2019-10-21 16:09:09');
INSERT INTO `biz_article_look` VALUES (12, 19, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:11:13', '2019-10-21 16:11:13', '2019-10-21 16:11:13');
INSERT INTO `biz_article_look` VALUES (13, 2, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:15:24', '2019-10-21 16:15:24', '2019-10-21 16:15:24');
INSERT INTO `biz_article_look` VALUES (14, 4, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:16:23', '2019-10-21 16:16:23', '2019-10-21 16:16:23');
INSERT INTO `biz_article_look` VALUES (15, 5, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:17:22', '2019-10-21 16:17:22', '2019-10-21 16:17:22');
INSERT INTO `biz_article_look` VALUES (16, 11, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:19:23', '2019-10-21 16:19:23', '2019-10-21 16:19:23');
INSERT INTO `biz_article_look` VALUES (17, 16, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:21:47', '2019-10-21 16:21:47', '2019-10-21 16:21:47');
INSERT INTO `biz_article_look` VALUES (18, 15, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:23:59', '2019-10-21 16:23:59', '2019-10-21 16:23:59');
INSERT INTO `biz_article_look` VALUES (19, 20, NULL, '0:0:0:0:0:0:0:1', '2019-10-21 16:28:21', '2019-10-21 16:28:21', '2019-10-21 16:28:21');
INSERT INTO `biz_article_look` VALUES (20, 11, NULL, '111.8.254.114', '2020-06-12 13:09:24', '2020-06-12 13:09:24', '2020-06-12 13:09:24');
INSERT INTO `biz_article_look` VALUES (21, 30, NULL, '111.8.254.114', '2020-06-12 13:59:00', '2020-06-12 13:59:00', '2020-06-12 13:59:00');
INSERT INTO `biz_article_look` VALUES (22, 30, NULL, '223.88.46.161', '2020-06-12 14:32:55', '2020-06-12 14:32:55', '2020-06-12 14:32:55');
INSERT INTO `biz_article_look` VALUES (23, 30, NULL, '114.105.31.250', '2020-06-12 14:33:05', '2020-06-12 14:33:05', '2020-06-12 14:33:05');
INSERT INTO `biz_article_look` VALUES (24, 30, NULL, '220.114.193.118', '2020-06-12 14:34:22', '2020-06-12 14:34:22', '2020-06-12 14:34:22');
INSERT INTO `biz_article_look` VALUES (25, 30, NULL, '120.219.13.8', '2020-06-12 14:34:25', '2020-06-12 14:34:25', '2020-06-12 14:34:25');
INSERT INTO `biz_article_look` VALUES (26, 30, NULL, '112.2.77.82', '2020-06-12 22:43:49', '2020-06-12 22:43:49', '2020-06-12 22:43:49');
INSERT INTO `biz_article_look` VALUES (27, 30, NULL, '0:0:0:0:0:0:0:1', '2020-06-13 12:58:33', '2020-06-13 12:58:33', '2020-06-13 12:58:33');
INSERT INTO `biz_article_look` VALUES (28, 30, NULL, '192.168.137.1', '2020-06-13 19:02:08', '2020-06-13 19:02:08', '2020-06-13 19:02:08');
INSERT INTO `biz_article_look` VALUES (29, 30, NULL, '111.8.254.53', '2020-06-15 07:49:19', '2020-06-15 07:49:19', '2020-06-15 07:49:19');
INSERT INTO `biz_article_look` VALUES (30, 30, NULL, '113.119.66.11', '2020-06-15 08:44:53', '2020-06-15 08:44:53', '2020-06-15 08:44:53');
INSERT INTO `biz_article_look` VALUES (31, 30, NULL, '59.35.85.242', '2020-06-15 08:44:57', '2020-06-15 08:44:57', '2020-06-15 08:44:57');
INSERT INTO `biz_article_look` VALUES (32, 30, NULL, '222.87.39.85', '2020-06-15 08:47:26', '2020-06-15 08:47:26', '2020-06-15 08:47:26');
INSERT INTO `biz_article_look` VALUES (33, 30, NULL, '113.242.16.236', '2020-06-15 08:47:42', '2020-06-15 08:47:42', '2020-06-15 08:47:42');
INSERT INTO `biz_article_look` VALUES (34, 30, NULL, '113.69.194.136', '2020-06-15 08:47:43', '2020-06-15 08:47:43', '2020-06-15 08:47:43');
INSERT INTO `biz_article_look` VALUES (35, 30, NULL, '113.117.173.102', '2020-06-15 08:47:44', '2020-06-15 08:47:44', '2020-06-15 08:47:44');
INSERT INTO `biz_article_look` VALUES (36, 30, NULL, '113.65.204.255', '2020-06-15 08:48:07', '2020-06-15 08:48:07', '2020-06-15 08:48:07');
INSERT INTO `biz_article_look` VALUES (37, 30, NULL, '116.7.250.174', '2020-06-15 08:49:57', '2020-06-15 08:49:57', '2020-06-15 08:49:57');
INSERT INTO `biz_article_look` VALUES (38, 32, NULL, '113.119.66.11', '2020-06-15 08:51:14', '2020-06-15 08:51:14', '2020-06-15 08:51:14');
INSERT INTO `biz_article_look` VALUES (39, 30, NULL, '119.130.207.66', '2020-06-15 08:53:38', '2020-06-15 08:53:38', '2020-06-15 08:53:38');
INSERT INTO `biz_article_look` VALUES (40, 30, NULL, '112.96.70.221', '2020-06-15 08:55:33', '2020-06-15 08:55:33', '2020-06-15 08:55:33');
INSERT INTO `biz_article_look` VALUES (41, 30, NULL, '117.152.0.165', '2020-06-15 09:20:30', '2020-06-15 09:20:30', '2020-06-15 09:20:30');
INSERT INTO `biz_article_look` VALUES (42, 30, NULL, '113.201.240.223', '2020-06-15 09:21:33', '2020-06-15 09:21:33', '2020-06-15 09:21:33');
INSERT INTO `biz_article_look` VALUES (43, 30, NULL, '171.122.123.204', '2020-06-15 09:22:35', '2020-06-15 09:22:35', '2020-06-15 09:22:35');
INSERT INTO `biz_article_look` VALUES (44, 30, NULL, '117.132.197.52', '2020-06-15 09:26:40', '2020-06-15 09:26:40', '2020-06-15 09:26:40');
INSERT INTO `biz_article_look` VALUES (45, 30, NULL, '60.209.38.110', '2020-06-15 09:26:46', '2020-06-15 09:26:46', '2020-06-15 09:26:46');
INSERT INTO `biz_article_look` VALUES (46, 30, NULL, '14.108.201.51', '2020-06-15 09:26:46', '2020-06-15 09:26:46', '2020-06-15 09:26:46');
INSERT INTO `biz_article_look` VALUES (47, 30, NULL, '223.104.9.128', '2020-06-15 09:26:47', '2020-06-15 09:26:47', '2020-06-15 09:26:47');
INSERT INTO `biz_article_look` VALUES (48, 30, NULL, '120.229.76.118', '2020-06-15 09:26:48', '2020-06-15 09:26:48', '2020-06-15 09:26:48');
INSERT INTO `biz_article_look` VALUES (49, 32, NULL, '14.108.201.51', '2020-06-15 09:26:58', '2020-06-15 09:26:58', '2020-06-15 09:26:58');
INSERT INTO `biz_article_look` VALUES (50, 30, NULL, '117.152.221.163', '2020-06-15 09:27:55', '2020-06-15 09:27:55', '2020-06-15 09:27:55');
INSERT INTO `biz_article_look` VALUES (51, 30, NULL, '14.28.25.43', '2020-06-15 09:28:30', '2020-06-15 09:28:30', '2020-06-15 09:28:30');
INSERT INTO `biz_article_look` VALUES (52, 30, NULL, '14.114.39.247', '2020-06-15 09:28:38', '2020-06-15 09:28:38', '2020-06-15 09:28:38');
INSERT INTO `biz_article_look` VALUES (53, 30, NULL, '122.224.64.243', '2020-06-15 09:30:46', '2020-06-15 09:30:46', '2020-06-15 09:30:46');
INSERT INTO `biz_article_look` VALUES (54, 30, NULL, '183.48.125.151', '2020-06-15 09:30:58', '2020-06-15 09:30:58', '2020-06-15 09:30:58');
INSERT INTO `biz_article_look` VALUES (55, 30, NULL, '42.86.162.87', '2020-06-15 09:31:21', '2020-06-15 09:31:21', '2020-06-15 09:31:21');
INSERT INTO `biz_article_look` VALUES (56, 30, NULL, '119.39.248.63', '2020-06-15 09:31:26', '2020-06-15 09:31:26', '2020-06-15 09:31:26');
INSERT INTO `biz_article_look` VALUES (57, 30, NULL, '114.135.241.64', '2020-06-15 09:32:50', '2020-06-15 09:32:50', '2020-06-15 09:32:50');
INSERT INTO `biz_article_look` VALUES (58, 30, NULL, '223.104.170.165', '2020-06-15 09:35:44', '2020-06-15 09:35:44', '2020-06-15 09:35:44');
INSERT INTO `biz_article_look` VALUES (59, 30, NULL, '106.121.158.195', '2020-06-15 09:37:00', '2020-06-15 09:37:00', '2020-06-15 09:37:00');
INSERT INTO `biz_article_look` VALUES (60, 32, NULL, '117.152.0.165', '2020-06-15 09:37:56', '2020-06-15 09:37:56', '2020-06-15 09:37:56');
INSERT INTO `biz_article_look` VALUES (61, 32, NULL, '183.67.63.105', '2020-06-15 09:38:05', '2020-06-15 09:38:05', '2020-06-15 09:38:05');
INSERT INTO `biz_article_look` VALUES (62, 32, NULL, '223.104.170.165', '2020-06-15 09:44:53', '2020-06-15 09:44:53', '2020-06-15 09:44:53');
INSERT INTO `biz_article_look` VALUES (63, 30, NULL, '117.136.81.169', '2020-06-15 09:58:45', '2020-06-15 09:58:45', '2020-06-15 09:58:45');
INSERT INTO `biz_article_look` VALUES (64, 30, NULL, '223.104.170.82', '2020-06-15 12:50:43', '2020-06-15 12:50:43', '2020-06-15 12:50:43');
INSERT INTO `biz_article_look` VALUES (65, 32, NULL, '137.116.170.133', '2020-06-15 16:50:54', '2020-06-15 16:50:54', '2020-06-15 16:50:54');
INSERT INTO `biz_article_look` VALUES (66, 30, NULL, '111.8.255.14', '2020-06-17 07:46:31', '2020-06-17 07:46:31', '2020-06-17 07:46:31');
INSERT INTO `biz_article_look` VALUES (67, 32, NULL, '111.8.255.14', '2020-06-17 07:47:41', '2020-06-17 07:47:41', '2020-06-17 07:47:41');
INSERT INTO `biz_article_look` VALUES (68, 34, NULL, '0:0:0:0:0:0:0:1', '2020-06-17 09:55:39', '2020-06-17 09:55:39', '2020-06-17 09:55:39');

-- ----------------------------
-- Table structure for biz_article_tags
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_tags`;
CREATE TABLE `biz_article_tags`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_id` int(10) UNSIGNED NOT NULL COMMENT '标签表主键',
  `article_id` int(10) UNSIGNED NOT NULL COMMENT '文章ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 150 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表标签' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_article_tags
-- ----------------------------
INSERT INTO `biz_article_tags` VALUES (87, 5, 6, '2019-10-18 17:23:51', '2019-10-18 17:23:51');
INSERT INTO `biz_article_tags` VALUES (88, 5, 7, '2019-10-18 17:23:55', '2019-10-18 17:23:55');
INSERT INTO `biz_article_tags` VALUES (89, 5, 8, '2019-10-18 17:23:58', '2019-10-18 17:23:58');
INSERT INTO `biz_article_tags` VALUES (90, 5, 9, '2019-10-18 17:24:02', '2019-10-18 17:24:02');
INSERT INTO `biz_article_tags` VALUES (91, 5, 10, '2019-10-18 17:24:05', '2019-10-18 17:24:05');
INSERT INTO `biz_article_tags` VALUES (94, 5, 13, '2019-10-18 17:24:17', '2019-10-18 17:24:17');
INSERT INTO `biz_article_tags` VALUES (95, 5, 14, '2019-10-18 17:24:21', '2019-10-18 17:24:21');
INSERT INTO `biz_article_tags` VALUES (98, 5, 17, '2019-10-18 17:24:35', '2019-10-18 17:24:35');
INSERT INTO `biz_article_tags` VALUES (101, 5, 1, '2019-10-21 16:01:41', '2019-10-21 16:01:41');
INSERT INTO `biz_article_tags` VALUES (104, 5, 3, '2019-10-21 16:10:33', '2019-10-21 16:10:33');
INSERT INTO `biz_article_tags` VALUES (107, 5, 19, '2019-10-21 16:14:50', '2019-10-21 16:14:50');
INSERT INTO `biz_article_tags` VALUES (108, 5, 18, '2019-10-21 16:15:01', '2019-10-21 16:15:01');
INSERT INTO `biz_article_tags` VALUES (110, 5, 2, '2019-10-21 16:16:03', '2019-10-21 16:16:03');
INSERT INTO `biz_article_tags` VALUES (111, 5, 4, '2019-10-21 16:16:54', '2019-10-21 16:16:54');
INSERT INTO `biz_article_tags` VALUES (113, 5, 5, '2019-10-21 16:18:20', '2019-10-21 16:18:20');
INSERT INTO `biz_article_tags` VALUES (114, 5, 12, '2019-10-21 16:19:03', '2019-10-21 16:19:03');
INSERT INTO `biz_article_tags` VALUES (115, 2, 11, '2019-10-21 16:19:51', '2019-10-21 16:19:51');
INSERT INTO `biz_article_tags` VALUES (116, 3, 11, '2019-10-21 16:19:51', '2019-10-21 16:19:51');
INSERT INTO `biz_article_tags` VALUES (117, 4, 11, '2019-10-21 16:19:51', '2019-10-21 16:19:51');
INSERT INTO `biz_article_tags` VALUES (121, 4, 16, '2019-10-21 16:22:59', '2019-10-21 16:22:59');
INSERT INTO `biz_article_tags` VALUES (122, 5, 15, '2019-10-21 16:24:06', '2019-10-21 16:24:06');
INSERT INTO `biz_article_tags` VALUES (124, 5, 20, '2019-10-21 16:28:13', '2019-10-21 16:28:13');
INSERT INTO `biz_article_tags` VALUES (125, 5, 21, '2019-10-21 16:52:52', '2019-10-21 16:52:52');
INSERT INTO `biz_article_tags` VALUES (126, 5, 22, '2019-10-21 16:52:52', '2019-10-21 16:52:52');
INSERT INTO `biz_article_tags` VALUES (127, 5, 23, '2019-10-21 16:52:52', '2019-10-21 16:52:52');
INSERT INTO `biz_article_tags` VALUES (128, 5, 24, '2019-10-21 16:52:52', '2019-10-21 16:52:52');
INSERT INTO `biz_article_tags` VALUES (129, 5, 25, '2019-10-21 16:52:52', '2019-10-21 16:52:52');
INSERT INTO `biz_article_tags` VALUES (130, 1, 29, '2020-06-12 08:35:27', '2020-06-12 08:35:27');
INSERT INTO `biz_article_tags` VALUES (132, 5, 30, '2020-06-12 13:58:47', '2020-06-12 13:58:47');
INSERT INTO `biz_article_tags` VALUES (144, 2, 32, '2020-06-17 07:59:49', '2020-06-17 07:59:49');
INSERT INTO `biz_article_tags` VALUES (145, 4, 32, '2020-06-17 07:59:49', '2020-06-17 07:59:49');
INSERT INTO `biz_article_tags` VALUES (146, 24, 32, '2020-06-17 07:59:49', '2020-06-17 07:59:49');
INSERT INTO `biz_article_tags` VALUES (149, 12, 33, '2020-06-17 08:01:07', '2020-06-17 08:01:07');
INSERT INTO `biz_article_tags` VALUES (151, 5, 34, '2020-06-17 09:51:11', '2020-06-17 09:51:11');

-- ----------------------------
-- Table structure for biz_category
-- ----------------------------
DROP TABLE IF EXISTS `biz_category`;
CREATE TABLE `biz_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pid` int(10) UNSIGNED NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章类型名',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型介绍',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '是否可用',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_category
-- ----------------------------
INSERT INTO `biz_category` VALUES (1, 0, '前端技术', '前端技术', 1, 'fa fa-css3', 1, '2018-01-14 21:34:54', '2018-07-25 17:57:50');

-- ----------------------------
-- Table structure for biz_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_comment`;
CREATE TABLE `biz_comment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL COMMENT '被评论的文章或者页面的ID(-1:留言板)',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的ID',
  `pid` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '父级评论的id',
  `qq` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的QQ（未登录用户）',
  `nickname` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的昵称（未登录用户）',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的头像地址',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的邮箱地址（未登录用户）',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人的网站地址（未登录用户）',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '评论的状态',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的ip',
  `lng` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的地址',
  `os` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的系统类型',
  `os_short_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的系统的简称',
  `browser` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的浏览器类型',
  `browser_short_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的浏览器的简称',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论的内容',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注（审核不通过时添加）',
  `support` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '支持（赞）',
  `oppose` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '反对（踩）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for biz_link
-- ----------------------------
DROP TABLE IF EXISTS `biz_link`;
CREATE TABLE `biz_link`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接名',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接介绍',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链图片地址',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链站长邮箱',
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链站长qq',
  `status` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '状态',
  `origin` int(11) NULL DEFAULT NULL COMMENT '1-管理员添加 2-自助申请',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友情链接' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for biz_love
-- ----------------------------
DROP TABLE IF EXISTS `biz_love`;
CREATE TABLE `biz_love`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `biz_id` int(10) UNSIGNED NOT NULL COMMENT '业务ID',
  `biz_type` tinyint(1) NULL DEFAULT NULL COMMENT '业务类型：1.文章，2.评论',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已登录用户ID',
  `user_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户IP',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'biz_love' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_love
-- ----------------------------
INSERT INTO `biz_love` VALUES (7, 2, 1, NULL, '0:0:0:0:0:0:0:1', 1, '2019-10-21 16:33:58', '2019-10-21 16:33:58');
INSERT INTO `biz_love` VALUES (8, 30, 1, NULL, '111.8.254.114', 1, '2020-06-12 14:26:36', '2020-06-12 14:26:36');
INSERT INTO `biz_love` VALUES (9, 30, 1, NULL, '120.219.13.8', 1, '2020-06-12 14:34:30', '2020-06-12 14:34:30');
INSERT INTO `biz_love` VALUES (10, 30, 1, NULL, '111.8.254.53', 1, '2020-06-15 08:45:19', '2020-06-15 08:45:19');
INSERT INTO `biz_love` VALUES (11, 30, 1, NULL, '113.69.194.136', 1, '2020-06-15 08:47:44', '2020-06-15 08:47:44');
INSERT INTO `biz_love` VALUES (12, 30, 1, NULL, '222.87.39.85', 1, '2020-06-15 08:48:04', '2020-06-15 08:48:04');
INSERT INTO `biz_love` VALUES (13, 30, 1, NULL, '113.119.66.11', 1, '2020-06-15 08:50:59', '2020-06-15 08:50:59');
INSERT INTO `biz_love` VALUES (14, 30, 1, NULL, '116.7.250.174', 1, '2020-06-15 08:53:27', '2020-06-15 08:53:27');
INSERT INTO `biz_love` VALUES (15, 30, 1, NULL, '119.130.207.66', 1, '2020-06-15 08:53:40', '2020-06-15 08:53:40');
INSERT INTO `biz_love` VALUES (16, 30, 1, NULL, '183.48.125.151', 1, '2020-06-15 09:34:04', '2020-06-15 09:34:04');
INSERT INTO `biz_love` VALUES (17, 30, 1, NULL, '223.104.170.165', 1, '2020-06-15 09:45:22', '2020-06-15 09:45:22');
INSERT INTO `biz_love` VALUES (18, 32, 1, NULL, '137.116.170.133', 1, '2020-06-15 16:51:06', '2020-06-15 16:51:06');

-- ----------------------------
-- Table structure for biz_site_config
-- ----------------------------
DROP TABLE IF EXISTS `biz_site_config`;
CREATE TABLE `biz_site_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key',
  `sys_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key`(`sys_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_site_config
-- ----------------------------
INSERT INTO `biz_site_config` VALUES (1, 'CLOUD_STORAGE_CONFIG', '{\"type\":1,\"qiniuDomain\":\"http://cdn.nbclass.com\",\"qiniuPrefix\":\"img/blog\",\"qiniuAccessKey\":\"dGKQzLej_0__Xd9kElc7IK-FLWwyGPBBXFT-hBka\",\"qiniuSecretKey\":\"8sZZbdmEDS4yVx0DCj7lGxIFJqaPc-y-_-DD1bkk\",\"qiniuBucketName\":\"blog\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"qcloudRegion\":\"\"}', 1, '云存储配置信息');
INSERT INTO `biz_site_config` VALUES (5, 'SITE_NAME', '阿硕的博客|Suk', 1, '网站名称');
INSERT INTO `biz_site_config` VALUES (6, 'SITE_KWD', '阿硕的博客|Suk,个人博客', 1, '网站关键字');
INSERT INTO `biz_site_config` VALUES (7, 'SITE_DESC', '阿硕的博客|Suk,个人博客', 1, '网站描述');
INSERT INTO `biz_site_config` VALUES (8, 'SITE_PERSON_PIC', 'http://images.susuk.com.cn/images/d52ed47a49ef4068b3c0709f47472c62.jpg', 1, '站长头像');
INSERT INTO `biz_site_config` VALUES (9, 'SITE_PERSON_NAME', '阿硕的博客|Suk', 1, '站长名称');
INSERT INTO `biz_site_config` VALUES (10, 'SITE_PERSON_DESC', '一枚90后Coder,全栈(淦)工程师, Stay Hungry,Stay Foolish,just do it.', 1, '站长描述');
INSERT INTO `biz_site_config` VALUES (11, 'BAIDU_PUSH_URL', '', 1, '百度推送地址');

-- ----------------------------
-- Table structure for biz_site_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_site_info`;
CREATE TABLE `biz_site_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `site_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `site_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站配置信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for biz_tags
-- ----------------------------
DROP TABLE IF EXISTS `biz_tags`;
CREATE TABLE `biz_tags`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书签名',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书签' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_tags
-- ----------------------------
INSERT INTO `biz_tags` VALUES (5, '其他', '其他', '2018-07-25 18:55:06', '2018-07-25 18:55:06');

-- ----------------------------
-- Table structure for biz_theme
-- ----------------------------
DROP TABLE IF EXISTS `biz_theme`;
CREATE TABLE `biz_theme`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题名（路径前缀）',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题描述',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题预览图url',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '0-未启用 1-启用',
  `create_time` datetime(0) NOT NULL,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站主题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_theme
-- ----------------------------
INSERT INTO `biz_theme` VALUES (1, 'zblog', 'zb 官方主题', 'https://gitee.com/supperzh/zb-blog/raw/master/docs/img/blog-index.png?v=1.0', 1, '2018-09-19 15:50:45', '2019-09-30 15:44:09');
INSERT INTO `biz_theme` VALUES (2, 'lblogone', 'lblogone', 'https://gitee.com/supperzh/zb-blog/raw/master/docs/img/lblogone.png?v=1.0', 0, '2018-09-19 16:35:02', '2018-09-19 16:35:02');

-- ----------------------------
-- Table structure for pay_customer
-- ----------------------------
DROP TABLE IF EXISTS `pay_customer`;
CREATE TABLE `pay_customer`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_job_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_pay_money` bigint(20) NOT NULL,
  `customer_face_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_reward_date` date NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_customer
-- ----------------------------
INSERT INTO `pay_customer` VALUES (7, '海豚', 'phper', 10, 'http://q1.qlogo.cn/g?b=qq&nk=764646262&s=640', '2020-06-15');
INSERT INTO `pay_customer` VALUES (8, '偶然', 'coder', 10, 'http://q1.qlogo.cn/g?b=qq&nk=542454534&s=640', '2020-06-15');
INSERT INTO `pay_customer` VALUES (9, '小夜君', '切图仔', 10, 'http://q1.qlogo.cn/g?b=qq&nk=952841085&s=640', '2020-06-15');

-- ----------------------------
-- Table structure for pay_items
-- ----------------------------
DROP TABLE IF EXISTS `pay_items`;
CREATE TABLE `pay_items`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pay_money` bigint(20) NOT NULL,
  `pay_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_items
-- ----------------------------
INSERT INTO `pay_items` VALUES (1, 10, '蚊子再小也是肉');
INSERT INTO `pay_items` VALUES (2, 100, '尽一点绵薄之力');
INSERT INTO `pay_items` VALUES (3, 500, '力挺UP');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(11) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '性别男d');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (24, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (27, 1, '下载资源', '0', 'busi_consume_type', '', '', 'Y', '0', 'admin', '2019-07-17 11:06:36', 'admin', NULL, '下载扣费');
INSERT INTO `sys_dict_data` VALUES (28, 1, '手动扣费', '1', 'busi_consume_type', '', '', 'Y', '0', 'admin', '2019-07-17 11:06:59', 'admin', NULL, '其它扣费');
INSERT INTO `sys_dict_data` VALUES (29, 1, '正常充值', '1', 'busi_recharge_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:24:38', 'admin', NULL, '正常充值');
INSERT INTO `sys_dict_data` VALUES (30, 2, '赠送积分', '2', 'busi_recharge_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:24:57', 'admin', NULL, '赠送积分');
INSERT INTO `sys_dict_data` VALUES (31, 1, '微信支付', '0', 'busi_pay_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:25:54', 'admin', NULL, '微信支付');
INSERT INTO `sys_dict_data` VALUES (32, 1, '支付宝支付', '1', 'busi_pay_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:26:13', 'admin', NULL, '支付宝支付');
INSERT INTO `sys_dict_data` VALUES (33, 2, '银联支付', '2', 'busi_pay_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:26:32', 'admin', NULL, '银联支付');
INSERT INTO `sys_dict_data` VALUES (34, 3, '赠送', '3', 'busi_pay_type', '', '', 'Y', '0', 'admin', '2019-07-17 15:26:48', 'admin', NULL, '赠送');
INSERT INTO `sys_dict_data` VALUES (35, 1, '未支付', '0', 'busi_pay_status', '', '', 'Y', '0', 'admin', '2019-07-17 15:27:57', 'admin', NULL, '未支付');
INSERT INTO `sys_dict_data` VALUES (36, 2, '已支付', '1', 'busi_pay_status', '', '', 'Y', '0', 'admin', '2019-07-17 15:28:10', 'admin', NULL, '已支付');
INSERT INTO `sys_dict_data` VALUES (37, 3, '支付失败', '2', 'busi_pay_status', '', '', 'Y', '0', 'admin', '2019-07-17 15:28:43', 'admin', NULL, '支付失败');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型（0.网站用户  1.系统用户）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 664 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (668, 'Suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-17 09:49:04', '2020-06-17 17:49:04', '1');
INSERT INTO `sys_logininfor` VALUES (669, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 09:50:16', '2020-06-17 17:50:16', '1');
INSERT INTO `sys_logininfor` VALUES (670, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-06-17 13:19:58', '2020-06-17 21:19:58', '1');
INSERT INTO `sys_logininfor` VALUES (671, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-06-17 13:20:00', '2020-06-17 21:20:00', '1');
INSERT INTO `sys_logininfor` VALUES (672, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-06-17 13:20:03', '2020-06-17 21:20:02', '1');
INSERT INTO `sys_logininfor` VALUES (673, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-06-17 13:20:07', '2020-06-17 21:20:06', '1');
INSERT INTO `sys_logininfor` VALUES (674, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 13:20:13', '2020-06-17 21:20:12', '1');
INSERT INTO `sys_logininfor` VALUES (675, 'Suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-17 13:20:16', '2020-06-17 21:20:16', '1');
INSERT INTO `sys_logininfor` VALUES (676, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 13:27:47', '2020-06-17 21:27:47', '1');
INSERT INTO `sys_logininfor` VALUES (677, 'Suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-17 13:27:50', '2020-06-17 21:27:49', '1');
INSERT INTO `sys_logininfor` VALUES (678, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-06-17 13:59:12', '2020-06-17 21:59:13', '1');
INSERT INTO `sys_logininfor` VALUES (679, 'suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 14:07:33', '2020-06-17 22:07:33', '1');
INSERT INTO `sys_logininfor` VALUES (680, 'Suk', '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-17 14:07:36', '2020-06-17 22:07:36', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级ID',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序顺序',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1078 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '', 'fa fa-gear', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', 'menuItem', 'M', '0', '', 'fa fa-video-camera', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'xiaofeng', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', 'menuItem', 'M', '1', '', 'fa fa-bars', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'xiaofeng', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', 'system:user:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', 'system:role:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', 'system:config:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/system/operlog', '', 'C', '0', 'monitor:operlog:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/system/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', '2019-07-13 12:40:51', '2018-03-16 11:33:00', 'admin', 'admin', '');
INSERT INTO `sys_menu` VALUES (1035, '配置管理', 1049, 1, '/spider/config', 'menuItem', 'C', '1', '', '', NULL, '2019-08-29 11:29:36', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1036, '文章管理', 1, 2, '/content', 'menuItem', 'C', '1', '', '', NULL, '2019-08-29 11:30:53', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1037, '文章抓取', 1049, 3, '/spider/crawl/index', 'menuItem', 'C', '0', '', '', NULL, '2019-08-29 11:32:37', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1038, '网站管理', 0, 2, '', 'menuItem', 'M', '0', '', 'fa fa-cogs', NULL, '2019-09-27 20:06:06', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1039, '文章管理', 0, 2, '', 'menuItem', 'M', '0', '', 'fa fa-file-code-o', NULL, '2019-09-27 20:06:49', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1040, '上传管理', 0, 5, '', 'menuItem', 'M', '0', '', 'fa fa-exchange', NULL, '2019-09-27 20:08:15', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1041, '基础信息', 1038, 1, '/blog/siteConfig', 'menuItem', 'C', '0', '', '', NULL, '2019-09-28 11:30:01', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1042, '主题管理', 1038, 2, '/blog/theme', 'menuItem', 'C', '0', 'blog:theme:view', '', NULL, '2019-09-28 11:30:35', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1043, '友链管理', 1038, 3, '/blog/link', 'menuItem', 'C', '0', 'blog:link:view', '', NULL, '2019-09-28 11:30:54', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1044, '评论管理', 1038, 4, '/blog/comment', 'menuItem', 'C', '0', 'blog:comment:view', '', NULL, '2019-09-28 11:31:12', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1045, '发布文章', 1039, 1, '', 'menuItem', 'C', '1', '', '', NULL, '2019-09-28 11:31:53', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1046, '文章列表', 1039, 2, '/blog/articleCopy', 'menuItem', 'C', '0', 'blog:articleCopy:view', '', NULL, '2019-09-28 11:32:07', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1047, '分类管理', 1039, 3, '/blog/category', 'menuItem', 'C', '0', 'blog:category:view', '', NULL, '2019-09-28 11:32:20', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1048, '标签管理', 1039, 4, '/blog/tags', 'menuItem', 'C', '0', 'blog:tags:view', '', NULL, '2019-09-28 11:32:37', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1049, '博客搬家', 0, 5, '', 'menuItem', 'M', '0', '', 'fa fa-edit', NULL, '2019-09-28 13:11:39', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1050, '主题新增', 1042, 1, '', 'menuItem', 'F', '0', 'blog:theme:add', '', NULL, '2019-10-21 14:36:54', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1051, '主题查询', 1042, 1, '', 'menuItem', 'F', '0', 'blog:theme:list', '', NULL, '2019-10-21 14:37:31', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1052, '主题修改', 1042, 3, '', 'menuItem', 'F', '0', 'blog:theme:edit', '', NULL, '2019-10-21 14:38:03', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1053, '主题删除', 1042, 4, '', 'menuItem', 'F', '0', 'blog:theme:remove', '', NULL, '2019-10-21 14:38:36', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1054, '主题导出', 1042, 5, '', 'menuItem', 'F', '0', 'blog:theme:export', '', NULL, '2019-10-21 14:39:05', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1055, '友链查询', 1043, 1, '', 'menuItem', 'F', '0', 'blog:link:list', '', NULL, '2019-10-21 14:40:41', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1056, '友链新增', 1043, 2, '', 'menuItem', 'F', '0', 'blog:link:add', '', NULL, '2019-10-21 14:41:10', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1057, '友链修改', 1043, 3, '', 'menuItem', 'F', '0', 'blog:link:edit', '', NULL, '2019-10-21 14:41:36', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1058, '友链删除', 1043, 4, '', 'menuItem', 'F', '0', 'blog:link:remove', '', NULL, '2019-10-21 14:43:25', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1059, '友链导出', 1043, 5, '', 'menuItem', 'F', '0', 'blog:link:export', '', NULL, '2019-10-21 14:43:50', 'xiaofeng', 'xiaofeng', NULL);
INSERT INTO `sys_menu` VALUES (1060, '评论审核', 1044, 1, '', 'menuItem', 'F', '0', 'blog:comment:audit', '', NULL, '2019-10-21 14:47:41', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1061, '评论删除', 1044, 2, '', 'menuItem', 'F', '0', 'blog:comment:remove', '', NULL, '2019-10-21 14:48:12', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1062, '评论导出', 1044, 3, '', 'menuItem', 'F', '0', 'blog:comment:export', '', NULL, '2019-10-21 14:48:42', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1063, '文章查询', 1046, 1, '', 'menuItem', 'F', '0', 'blog:articleCopy:list', '', NULL, '2019-10-21 14:50:33', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1064, '文章发布', 1046, 2, '', 'menuItem', 'F', '0', 'blog:articleCopy:add', '', NULL, '2019-10-21 14:50:49', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1065, '文章修改', 1046, 3, '', 'menuItem', 'F', '0', 'blog:articleCopy:edit', '', NULL, '2019-10-21 14:51:17', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1066, '文章删除', 1046, 4, '', 'menuItem', 'F', '0', 'blog:articleCopy:remove', '', NULL, '2019-10-21 14:51:42', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1067, '文章导出', 1046, 5, '', 'menuItem', 'F', '0', 'blog:articleCopy:export', '', NULL, '2019-10-21 14:52:14', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1068, '分类查询', 1047, 1, '', 'menuItem', 'F', '0', 'blog:category:list', '', NULL, '2019-10-21 14:53:11', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1069, '分类新增', 1047, 2, '', 'menuItem', 'F', '0', 'blog:category:add', '', NULL, '2019-10-21 14:53:31', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1070, '分类修改', 1047, 3, '', 'menuItem', 'F', '0', 'blog:category:edit', '', NULL, '2019-10-21 14:53:53', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1071, '分类删除', 1047, 4, '', 'menuItem', 'F', '0', 'blog:category:remove', '', NULL, '2019-10-21 14:54:15', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1072, '分类导出', 1047, 5, '', 'menuItem', 'F', '0', 'blog:category:export', '', NULL, '2019-10-21 14:54:38', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1073, '标签查询', 1048, 1, '', 'menuItem', 'F', '0', 'blog:tags:list', '', NULL, '2019-10-21 14:55:43', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1074, '标签新增', 1048, 2, '', 'menuItem', 'F', '0', 'blog:tags:add', '', NULL, '2019-10-21 14:56:02', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1075, '标签修改', 1048, 3, '', 'menuItem', 'F', '0', 'blog:tags:edit', '', NULL, '2019-10-21 14:56:29', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1076, '标签删除', 1048, 4, '', 'menuItem', 'F', '0', 'blog:tags:remove', '', NULL, '2019-10-21 14:57:07', 'xiaofeng', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1077, '标签导出', 1048, 5, '', 'menuItem', 'F', '0', 'blog:tags:export', '', NULL, '2019-10-21 14:57:30', 'xiaofeng', NULL, NULL);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(11) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(11) NULL DEFAULT 0 COMMENT '操作类别（0.网站用户 1.后台用户 2.其它）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(11) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 577 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (605, '文章分类', 2, 'com.suk.blog.controller.CategoryController.editSave()', 1, 'Suk', '', '/blog/category/edit', '0:0:0:0:0:0:0:1', '', '{\"id\":[\"1\"],\"pid\":[\"0\"],\"name\":[\"前端技术\"],\"description\":[\"前端技术\"],\"sort\":[\"1\"]}', 0, '', '2020-06-17 09:48:27', '2020-06-17 17:48:27');
INSERT INTO `sys_oper_log` VALUES (606, '文章分类', 2, 'com.suk.blog.controller.CategoryController.editSave()', 1, 'Suk', '', '/blog/category/edit', '0:0:0:0:0:0:0:1', '', '{\"id\":[\"2\"],\"pid\":[\"0\"],\"name\":[\"后端技术\"],\"description\":[\"后端技术\"],\"sort\":[\"2\"]}', 0, '', '2020-06-17 09:48:35', '2020-06-17 17:48:34');
INSERT INTO `sys_oper_log` VALUES (607, '用户查询', 4, 'com.suk.blog.controller.SysUserController.list()', 1, 'Suk', '', '/system/user/list', '0:0:0:0:0:0:0:1', '', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"],\"orderByColumn\":[\"createTime\"],\"isAsc\":[\"desc\"],\"parentId\":[\"\"],\"userName\":[\"\"],\"telephone\":[\"\"],\"status\":[\"\"],\"params[beginTime]\":[\"\"],\"params[endTime]\":[\"\"]}', 0, '', '2020-06-17 09:50:38', '2020-06-17 17:50:38');
INSERT INTO `sys_oper_log` VALUES (608, '文章', 2, 'com.suk.blog.controller.ArticleCopyController.editSave()', 1, 'Suk', '', '/blog/articleCopy/edit', '0:0:0:0:0:0:0:1', '', '{\"id\":[\"34\"],\"status\":[\"1\"],\"title\":[\"示例文章\"],\"content\":[\"<p>示例文章</p>\"],\"original\":[\"1\"],\"categoryId\":[\"1\"],\"tag\":[\"5\"],\"coverImage\":[\"\"],\"sliderImg\":[\"\"],\"slider\":[\"0\"],\"top\":[\"1\"],\"recommended\":[\"1\"],\"comment\":[\"1\"],\"description\":[\"示例文章\"],\"keywords\":[\"示例文章\"],\"categoryIds\":[\"\"]}', 0, '', '2020-06-17 09:51:12', '2020-06-17 17:51:11');
INSERT INTO `sys_oper_log` VALUES (609, '书签', 1, 'com.suk.blog.controller.TagsController.addSave()', 1, 'Suk', '', '/blog/tags/add', '0:0:0:0:0:0:0:1', '', '{\"name\":[\"JavaScript\"],\"description\":[\"\"]}', 0, '', '2020-06-17 09:51:25', '2020-06-17 17:51:24');
INSERT INTO `sys_oper_log` VALUES (610, '用户查询', 4, 'com.suk.blog.controller.SysUserController.list()', 1, 'Suk', '', '/system/user/list', '0:0:0:0:0:0:0:1', '', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"],\"orderByColumn\":[\"createTime\"],\"isAsc\":[\"desc\"],\"parentId\":[\"\"],\"userName\":[\"\"],\"telephone\":[\"\"],\"status\":[\"\"],\"params[beginTime]\":[\"\"],\"params[endTime]\":[\"\"]}', 0, '', '2020-06-17 09:54:39', '2020-06-17 17:54:38');
INSERT INTO `sys_oper_log` VALUES (611, '文章分类', 3, 'com.suk.blog.controller.CategoryController.remove()', 1, 'Suk', '', '/blog/category/remove', '0:0:0:0:0:0:0:1', '', '{\"ids\":[\"2\"]}', 0, '', '2020-06-17 09:55:04', '2020-06-17 17:55:03');
INSERT INTO `sys_oper_log` VALUES (612, '书签', 3, 'com.suk.blog.controller.TagsController.remove()', 1, 'Suk', '', '/blog/tags/remove', '0:0:0:0:0:0:0:1', '', '{\"ids\":[\"26\"]}', 0, '', '2020-06-17 09:55:08', '2020-06-17 17:55:08');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色key',
  `role_sort` int(11) NULL DEFAULT NULL COMMENT '角色排序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'test', 'test', 1, '1', '0', '0', '2019-07-13 17:02:54', '2019-07-13 17:02:54', 'admin', 'admin', 'test_slave');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色与菜单关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (59, 1, 1);
INSERT INTO `sys_role_menu` VALUES (60, 1, 105);
INSERT INTO `sys_role_menu` VALUES (61, 1, 1025);
INSERT INTO `sys_role_menu` VALUES (62, 1, 1029);
INSERT INTO `sys_role_menu` VALUES (63, 1, 106);
INSERT INTO `sys_role_menu` VALUES (64, 1, 1030);
INSERT INTO `sys_role_menu` VALUES (65, 1, 1034);
INSERT INTO `sys_role_menu` VALUES (66, 1, 107);
INSERT INTO `sys_role_menu` VALUES (67, 1, 1035);
INSERT INTO `sys_role_menu` VALUES (68, 1, 1036);
INSERT INTO `sys_role_menu` VALUES (69, 1, 1037);
INSERT INTO `sys_role_menu` VALUES (70, 1, 1038);
INSERT INTO `sys_role_menu` VALUES (71, 1, 108);
INSERT INTO `sys_role_menu` VALUES (72, 1, 500);
INSERT INTO `sys_role_menu` VALUES (73, 1, 1039);
INSERT INTO `sys_role_menu` VALUES (74, 1, 1040);
INSERT INTO `sys_role_menu` VALUES (75, 1, 1041);
INSERT INTO `sys_role_menu` VALUES (76, 1, 1042);
INSERT INTO `sys_role_menu` VALUES (77, 1, 501);
INSERT INTO `sys_role_menu` VALUES (78, 1, 1043);
INSERT INTO `sys_role_menu` VALUES (79, 1, 1044);
INSERT INTO `sys_role_menu` VALUES (80, 1, 1045);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密盐值',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `telephone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `weixin` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户类型（0,超级管理员，1.其它）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'Suk', 'Suk', '932992376bf137ca883c8c7412d6a0ca', 'f5598b', '0', 'suk178458994@163.com', '13026637671', NULL, NULL, 'http://images.susuk.com.cn/images/d52ed47a49ef4068b3c0709f47472c62.jpg', '0', '2019-07-13 12:02:53', '2019-07-13 12:02:55', NULL, 'admin', 'UP主', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户角色关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (5, 12, 1);

SET FOREIGN_KEY_CHECKS = 1;
