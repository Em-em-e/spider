CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `news_type` varchar(20) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `cmt_count` int(11) DEFAULT NULL,
  `first_time` datetime(6) DEFAULT NULL,
  `last_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;