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