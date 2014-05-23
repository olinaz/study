CREATE USER 'user_cs548'@'localhost' IDENTIFIED BY 'spring548';

GRANT ALL PRIVILEGES ON cs548_project.* TO 'user_cs548'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'user_cs548'@'localhost';

delimiter ;
DROP SCHEMA IF EXISTS `cs548_project`; 
CREATE SCHEMA `cs548_project` ;
use `cs548_project`;