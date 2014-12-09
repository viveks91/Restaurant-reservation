# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.21)
# Database: Restaurant_Reservation
# Generation Time: 2014-12-09 18:24:56 +0000
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
  `apt_No` varchar(10) DEFAULT NULL,
  `street` varchar(225) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city` (`city`),
  KEY `state` (`state`),
  KEY `zip` (`zip`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`city`) REFERENCES `City` (`city`),
  CONSTRAINT `address_ibfk_2` FOREIGN KEY (`state`) REFERENCES `State` (`state`),
  CONSTRAINT `address_ibfk_3` FOREIGN KEY (`zip`) REFERENCES `ZIP` (`zip`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;

INSERT INTO `Address` (`id`, `apt_No`, `street`, `city`, `state`, `zip`)
VALUES
	(1,'o0001','ewrwer','seattle','WA','98119'),
	(2,'234','we','seattle','WA','90109');

/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Category`;

CREATE TABLE `Category` (
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;

INSERT INTO `Category` (`type`)
VALUES
	('Indian'),
	('Italian');

/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table City
# ------------------------------------------------------------

DROP TABLE IF EXISTS `City`;

CREATE TABLE `City` (
  `city` varchar(20) NOT NULL,
  PRIMARY KEY (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;

INSERT INTO `City` (`city`)
VALUES
	('seattle');

/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Favorites
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Favorites`;

CREATE TABLE `Favorites` (
  `userName` varchar(225) NOT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userName`,`restaurantId`),
  KEY `restaurantId` (`restaurantId`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Favorites` WRITE;
/*!40000 ALTER TABLE `Favorites` DISABLE KEYS */;

INSERT INTO `Favorites` (`userName`, `restaurantId`)
VALUES
	('p0',1),
	('p0',2);

/*!40000 ALTER TABLE `Favorites` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Following
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Following`;

CREATE TABLE `Following` (
  `userName` varchar(225) NOT NULL,
  `following` varchar(225) NOT NULL,
  PRIMARY KEY (`userName`,`following`),
  KEY `following` (`following`),
  CONSTRAINT `following_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `following_ibfk_2` FOREIGN KEY (`following`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
	('p0','pass','Preety','Mishra','User'),
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
  `people_count` int(5) DEFAULT '1',
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `restaurantId` int(11) unsigned NOT NULL,
  `userName` varchar(225) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurantId` (`restaurantId`),
  KEY `userName` (`userName`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
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
  KEY `type` (`type`),
  KEY `addressId` (`addressId`),
  CONSTRAINT `restaurant_ibfk_1` FOREIGN KEY (`type`) REFERENCES `Category` (`type`) ON UPDATE CASCADE,
  CONSTRAINT `restaurant_ibfk_2` FOREIGN KEY (`addressId`) REFERENCES `Address` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `Restaurant` WRITE;
/*!40000 ALTER TABLE `Restaurant` DISABLE KEYS */;

INSERT INTO `Restaurant` (`id`, `name`, `phoneNo`, `website`, `openingTime`, `closingTime`, `capacity`, `type`, `addressId`)
VALUES
	(1,'Roti',96868698,'www.roti.com','09:00:00','17:00:00',50,'Indian',1),
	(2,'BlueMoon',23223,'www.bluemoon.com',NULL,NULL,12,'Italian',2),
	(3,'Chutneys',234234,'www.chutneys.com','09:00:00','19:00:00',20,'Indian',1);

/*!40000 ALTER TABLE `Restaurant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reviews`;

CREATE TABLE `Reviews` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(225) DEFAULT NULL,
  `ratings` int(2) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurantId` (`restaurantId`),
  KEY `userName` (`userName`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;

INSERT INTO `Reviews` (`id`, `userName`, `ratings`, `comments`, `date`, `restaurantId`)
VALUES
	(1,'p0',2,'bad',NULL,1),
	(2,'p0',4,'good',NULL,2),
	(3,'pre284',5,'very nice',NULL,1);

/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table State
# ------------------------------------------------------------

DROP TABLE IF EXISTS `State`;

CREATE TABLE `State` (
  `state` varchar(20) NOT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `State` WRITE;
/*!40000 ALTER TABLE `State` DISABLE KEYS */;

INSERT INTO `State` (`state`)
VALUES
	('WA');

/*!40000 ALTER TABLE `State` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TimeSlot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TimeSlot`;

CREATE TABLE `TimeSlot` (
  `id` int(11) unsigned NOT NULL,
  `startTime` time DEFAULT NULL,
  `closeTime` time DEFAULT NULL,
  `capacity` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `timeslot_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `phoneNo` varchar(12) DEFAULT NULL,
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
	('9234687','pwei@kjdsa.com','p0',1),
	('2876','jdhgf','pre2718',1),
	('1234','asd','pre284',1);

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ZIP
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ZIP`;

CREATE TABLE `ZIP` (
  `zip` varchar(20) NOT NULL,
  PRIMARY KEY (`zip`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ZIP` WRITE;
/*!40000 ALTER TABLE `ZIP` DISABLE KEYS */;

INSERT INTO `ZIP` (`zip`)
VALUES
	('90109'),
	('98119');

/*!40000 ALTER TABLE `ZIP` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
