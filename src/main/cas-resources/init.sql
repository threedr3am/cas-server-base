DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(256) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(256) DEFAULT NULL COMMENT '用户真实姓名',
  `id_card_num` varchar(256) DEFAULT NULL COMMENT '用户身份证号',
  `state` char(1) DEFAULT '0' COMMENT '用户状态：0:正常状态,1：用户被锁定',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `id_card_num` (`id_card_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `user_info` (`uid`,`username`,`password`,`name`,`id_card_num`,`state`) VALUES (4,'admin','e10adc3949ba59abbe56e057f20f883e','一想到你','441283xxxxxxxxxxxx','0');
INSERT INTO `user_info` (`uid`,`username`,`password`,`name`,`id_card_num`,`state`) VALUES (5,'test','ed0290f05224a188160858124a5f5077','我就哦哦哦哦哦','166666666666666666','0');
