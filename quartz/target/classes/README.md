#内容均为本人盗版吸取各个大佬文章进行优化整合,如有侵权请告知 谢谢 QQ:993303626
#讨论技术欢迎加 QQ:993303626

#使用必读
本工程基于springboot 2.3.0 + quartz + mybatis + 一张简单界面 + 多数据源  集成的快速开发的无任何封装定时任务框架可自由修改调试

#只需要初始化一张表
CREATE TABLE `job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分组',
  `job_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '对应 任务名称',
  `job_cron_exprssion` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'cron表达式',
  `job_status` int(1) DEFAULT NULL COMMENT '1启用 2 禁用',
  `job_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1027 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时任务表'