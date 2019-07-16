DROP TABLE IF EXISTS `app_user`;

CREATE SEQUENCE IF NOT EXISTS app_user_seq START WITH 20;

create table `app_user`
(
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `mobile_no` varchar(255),
  `enabled` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `client`;

CREATE SEQUENCE IF NOT EXISTS client_seq START WITH 20;

create table `client`
(
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO app_user (id, name, password, role, enabled) VALUES (1, 'pyra', '$2a$10$dn6lNXblW6sTchxRZMxzaOviTb9EikgBWk7vD4Sa5ByzMGeM.U9uq', 'ROLE_ADMIN', true);
INSERT INTO app_user (id, name, password, role, enabled) VALUES (3, 'oper', '$2a$10$dn6lNXblW6sTchxRZMxzaOviTb9EikgBWk7vD4Sa5ByzMGeM.U9uq', 'ROLE_OPERATOR', true);

INSERT INTO client (id, name, mobile_no, product) VALUES (1, 'Luis Mckenna', '134', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (2, 'Jae Whittington', '555', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (3, 'Wendy Cohen', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (4, 'Caroline Melton', '123', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (5, 'Jay Hope', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (6, 'Harper Seymour', '666', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (7, 'Yaqub Pemberton', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (8, 'Rayyan Parrish', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (9, 'Matas Mason', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (10, 'Evie-Mai Craft', '785', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (11, 'Caitlin Warner', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (12, 'Renesmee Santos', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (13, 'Summer-Louise Mcdonald', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (14, 'Regina Vinson', '11', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (15, 'Rose Knight', '', 'Product 1');
INSERT INTO client (id, name, mobile_no, product) VALUES (16, 'Jada Clegg', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (17, 'Elana Coombes', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (18, 'Tyreece Abbott', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (19, 'Muhamed Samuels', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (20, 'Jade Walls', '888888', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (21, 'Blanche Edge', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (22, 'Lex Solomon', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (23, 'Jaskaran Wang', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (24, 'Prince Tait', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (25, 'Soren Harris', '', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (26, 'Ishmael Webster', '450', 'Product 2');
INSERT INTO client (id, name, mobile_no, product) VALUES (27, 'Kashif Healy', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (28, 'Daniella Timms', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (29, 'Kayan Sampson', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (30, 'Fenella Crowther', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (31, 'Cain Cassidy', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (32, 'Cavan Millar', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (33, 'Tara Holden', '123', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (34, 'Ammarah Grimes', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (35, 'Nate Aldred', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (36, 'Bill Dunlop', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (37, 'Jadene Everett', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (38, 'Liana Calvert', '123', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (39, 'Ruby-Mae English', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (40, 'Sophie-Louise Oakley', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (41, 'Adela Waters', '', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (42, 'Tazmin Wallis', '122', 'Product 3');
INSERT INTO client (id, name, mobile_no, product) VALUES (43, 'Boyd Baxter', '', 'Product 3');