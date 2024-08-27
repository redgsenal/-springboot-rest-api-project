USE `restaurant_db`;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`username`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles`
VALUES
('admin','ROLE_ADMIN'),
('manager','ROLE_MANAGER'),
('webapi','ROLE_WEBAPI')