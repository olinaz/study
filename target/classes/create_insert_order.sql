
delimiter $$

CREATE TABLE `cusorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusname` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `tax` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter ;
INSERT INTO `cusorder` (`cusname`,`date`,`subtotal`,`tax`,`total`) VALUES ('Tracy','2013-03-21',100,9,109);
INSERT INTO `cusorder` (`cusname`,`date`,`subtotal`,`tax`,`total`) VALUES ('Rosemary','2013-05-19',340,30,370);

