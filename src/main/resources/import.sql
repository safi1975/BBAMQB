DROP TABLE IF EXISTS `app_user`;

CREATE SEQUENCE IF NOT EXISTS app_user_seq START WITH 20;

create table `app_user`
(
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `client`;

CREATE SEQUENCE IF NOT EXISTS client_seq START WITH 20;

create table `client`
(
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
