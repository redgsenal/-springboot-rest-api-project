USE `restaurant_db`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
    `password` char(68) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users`
VALUES
('admin','{bcrypt}$2a$10$WsLqROQ.VImyAq1dD4SAv.dVjt88in2Cn2oTNsw4Lt1bAV9VGHtL2',1),
('manager','{bcrypt}$2a$10$WVMQnN1S72bI1Tfl6u8RIOAzRnUZS8.yQORMaWi6szfkaC3DGizwO',1),
('webapi','{bcrypt}$2a$10$oH6obIPeMDIEDSfbeSmBneMdElS0tggA2DpjVurCN4upDOclcVZJe',1);

-- https://www.bcryptcalculator.com/encode
-- admin = super123
-- manager = hello123
-- webapi = test123
