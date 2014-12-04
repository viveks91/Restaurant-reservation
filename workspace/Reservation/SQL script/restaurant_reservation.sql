# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.21)
# Database: Restaurant_Reservation
# Generation Time: 2014-11-25 22:19:18 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Address`;

CREATE TABLE `Address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(225) NOT NULL,
  `apt_No` varchar(10) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Category`;

CREATE TABLE `Category` (
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Favorites
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Favorites`;

CREATE TABLE `Favorites` (
  `userName` varchar(225) NOT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userName`, `restaurantId`),
  FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Follower
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Following`;

CREATE TABLE `Following` (
  `userName` varchar(225) NOT NULL,
  `following` varchar(225) NOT NULL,
  PRIMARY KEY (`userName`, `following`),
  FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`following`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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




# Dump of table Reservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reservation`;

CREATE TABLE `Reservation` (
  `id` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `people_count` int(5) DEFAULT 1,
  `time` datetime DEFAULT current_timestamp,
  `restaurantId` int(11) unsigned NOT NULL,
  `userName` varchar(225) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Restaurant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Restaurant`;

CREATE TABLE `Restaurant` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `phoneNo` int(50) DEFAULT NULL,
  `website` varchar(500) DEFAULT NULL,
  `openingTime` time DEFAULT NULL,
  `closingTime` time DEFAULT NULL,
  `capacity` int(10) NOT NULL,
  `type` varchar(100) NOT NULL,
  `addressId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`type`) REFERENCES `Category` (`type`)  ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`addressId`) REFERENCES `Address` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reviews`;

CREATE TABLE `Reviews` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(225),
  `ratings` int(2) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Dump of table TimeSlot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TimeSlot`;

CREATE TABLE `TimeSlot` (
  `id` int(11) unsigned NOT NULL,
  `startTime` time DEFAULT NULL,
  `closeTime` time DEFAULT NULL,
  `capacity` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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