CREATE TABLE `kan_content` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '内容ID',
                               `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级内容ID',
                               `parent_type` TINYINT NOT NULL DEFAULT '0' COMMENT '父级内容类型：0主题',
                               `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
                               `content` varchar(4000) NOT NULL DEFAULT '' COMMENT '内容',
                               `type` TINYINT(4) NOT NULL DEFAULT '2' COMMENT '类型：0标题，1图片，2文本',
                               `sort` int(11) NOT NULL DEFAULT '100' COMMENT '排序，越小越靠前',
                               `created_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
                               `modified_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
                               `deleted_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '删除时间',
                               `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除、1已删除',
                               PRIMARY KEY (`id`) USING BTREE,
                               FULLTEXT INDEX `content_index`(`content`) WITH PARSER ngram
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `kan_post` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主题ID',
                            `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
                            `visit_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
                            `comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
                            `upvote_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
                            `collection_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '收藏数',
                            `is_top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否置顶',
                            `is_essence` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否精华',
                            `latest_replied_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '最新回复时间',
                            `ip` varchar(64) NOT NULL DEFAULT '' COMMENT 'IP地址',
                            `ip_loc` varchar(64) NOT NULL DEFAULT '' COMMENT 'IP城市文字地址',
                            `created_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
                            `modified_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
                            `deleted_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '删除时间',
                            `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `kan_user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                           `nickname` varchar(32) NOT NULL DEFAULT '' COMMENT '昵称',
                           `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
                           `phone` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号',
                           `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
                           `exp` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户经验',
                           `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态：0正常，1封禁',
                           `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像链接',
                           `gender` TINYINT NOT NULL DEFAULT '0' COMMENT '用户性别：0不显示，1男，2女，3...',
                           `introduction` varchar(1000) NOT NULL DEFAULT '' COMMENT '用户简介内容',
                           `permission_group` BIGINT NOT NULL DEFAULT '0' COMMENT '用户所属权限组，0：普通用户权限',
                           `ip` varchar(32) NOT NULL COMMENT '用户ip',
                           `ip_loc` varchar(32) NOT NULL COMMENT 'ip地点',
                           `user_auth` TINYINT NOT NULL DEFAULT '0' COMMENT '用户认证类型：0未认证，1程序官方认证，2其他官方认证',
                           `user_title` varchar(32) NOT NULL DEFAULT '' COMMENT '用户认证信息',
                           `created_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
                           `modified_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
                           `deleted_on` bigint(20) NOT NULL DEFAULT '0' COMMENT '删除时间',
                           `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0否，1是',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;