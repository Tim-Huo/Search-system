CREATE TABLE `dianpingdb`.`user`  (
  `id` int(0) NOT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `telphone` varchar(40) NOT NULL COMMENT '电话',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `nick_name` varchar(40) NOT NULL COMMENT '昵称',
  `gender` int(0) NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `telphone_upique_index`(`telphone`) USING BTREE
);

CREATE TABLE `dianpingdb`.`seller`  (
  `id` int(0) NOT NULL,
  `name` varchar(80) NOT NULL COMMENT '商户名称',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '修改时间',
  `remark_score` decimal(2, 1) NOT NULL COMMENT '商户评分',
  `disabled_flag` int(0) NOT NULL COMMENT '启用',
  PRIMARY KEY (`id`)
);

CREATE TABLE `dianpingdb`.`category`  (
  `id` int(0) NOT NULL,
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '修改时间',
  `name` varchar(20) NOT NULL COMMENT '品类名称',
  `icon_url` varchar(200) NOT NULL COMMENT '品类展示',
  `sort` int(255) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_upique_index`(`name`) USING BTREE
);

CREATE TABLE `dianpingdb`.`shop`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(80) NOT NULL DEFAULT '',
  `remark_score` decimal(2, 1) NOT NULL DEFAULT 0,
  `price_per_man` int(0) NOT NULL DEFAULT 0,
  `latitude` decimal(10, 6) NOT NULL DEFAULT 0,
  `longitude` decimal(10, 6) NOT NULL DEFAULT 0,
  `category_id` int(0) NOT NULL DEFAULT 0,
  `tags` varchar(1000) NOT NULL DEFAULT '',
  `start_time` varchar(200) NOT NULL DEFAULT '',
  `end_time` varchar(200) NOT NULL DEFAULT '',
  `address` varchar(200) NOT NULL DEFAULT '',
  `seller_id` int(0) NOT NULL DEFAULT 0,
  `icon_url` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);