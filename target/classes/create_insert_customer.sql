delimiter $$

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `addr` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter ;
INSERT INTO `customer` (`name`,`addr`,`email`,`phone`,`state`) VALUES ('Tracy','1234 Mission Blvd','tracy@gmail.com','510-516-1234','CA');
INSERT INTO `customer` (`name`,`addr`,`email`,`phone`,`state`) VALUES ('Rosemary','43177 Warren','rosemary@yahoo.com','510-396-7758','CA');