CREATE DATABASE `housesmanage` /*!40100 DEFAULT CHARACTER SET utf8 */;
use `housesmanage`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(32) NOT NULL COMMENT '盐',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `workplace` varchar(128) DEFAULT NULL COMMENT '工作地址',
  `type` varchar(1) NOT NULL COMMENT '账号类型(0为管理员用户，1为业主用户)',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号信息表';