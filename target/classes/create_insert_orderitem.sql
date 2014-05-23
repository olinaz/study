
delimiter $$

CREATE TABLE `orderitem` (
  `orderItemId` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `prodId` int(11) NOT NULL,
  `numOfProdOrdered`  int(4) NOT NULL,
  
  PRIMARY KEY (`orderItemId`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter ;
INSERT INTO `orderitem` VALUES (1,1,1,1);
INSERT INTO `orderitem` VALUES (2,1,2,2);
INSERT INTO `orderitem` VALUES (3,1,3,3);
INSERT INTO `orderitem` VALUES (4,2,6,2);