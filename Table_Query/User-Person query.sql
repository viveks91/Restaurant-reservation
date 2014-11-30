# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.21)
# Database: Restaurant_Reservation
# Generation Time: 2014-11-30 23:06:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Person`;

CREATE TABLE `Person` (
  `userName` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `lastName` varchar(250) NOT NULL,
  `dtype` varchar(225) NOT NULL DEFAULT '',
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;

INSERT INTO `Person` (`userName`, `password`, `firstName`, `lastName`, `dtype`)
VALUES
	('pre2712','jack12','jack','pager','Person'),
	('pre2718','1233','ksjdfh','miss','User'),
	('pre284','1234','pret','mish','User');

/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `phoneNo` int(12) DEFAULT NULL,
  `emailId` varchar(225) NOT NULL DEFAULT '',
  `userName` varchar(225) NOT NULL,
  `addressId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userName`),
  KEY `addressId` (`addressId`),
  KEY `user_username_fk` (`phoneNo`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`addressId`) REFERENCES `Address` (`id`) ON DELETE SET NULL,
  CONSTRAINT `username_fk` FOREIGN KEY (`userName`) REFERENCES `Person` (`userName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`phoneNo`, `emailId`, `userName`, `addressId`)
VALUES
	(2876,'jdhgf','pre2718',1),
	(1234,'asd','pre284',1);

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
