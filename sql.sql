CREATE TABLE `news` (
`url` varchar(200) DEFAULT NULL ,
  `title` varchar(200) DEFAULT NULL,
  `news_type` varchar(20) DEFAULT NULL,
  `cmt_count` int(11) DEFAULT NULL,
  create_time varchar(50) DEFAULT null,
	modify_time varchar(50) DEFAULT null,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table sys_param(
	id int not null,
	param_name varchar(100),
	param_value varchar(200),
	PRIMARY key (id)
)engine=innodb default charset=utf8;

INSERT INTO sys_param VALUES(1,'rate','0.3');

