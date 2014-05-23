delimiter $$

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `brand` varchar(40) DEFAULT NULL,
  `invtquantity` int(10) unsigned zerofill DEFAULT NULL,
  `price` float unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

INSERT INTO `product` (`name`, `brand`, `price`,`invtquantity`) VALUES ('Purple Wallet', 'Prada', '320','3');
INSERT INTO `product` (`name`, `brand`, `price`,`invtquantity`) VALUES ('Blue Handbag', 'Coach', '134.99','15');
INSERT INTO `product` (`name`, `brand`, `price`,`invtquantity`) VALUES ('Pink Cross-body', 'Kate Spade', '99','210');