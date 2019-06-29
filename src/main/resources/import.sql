DROP TABLE IF EXISTS `client`;

CREATE SEQUENCE IF NOT EXISTS client_seq START WITH 20;

create table `client`
(
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
