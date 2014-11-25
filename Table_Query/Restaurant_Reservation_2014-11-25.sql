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
  `street` varchar(225) NOT NULL DEFAULT '',
  `apt_No` varchar(10) DEFAULT NULL,
  `city` varchar(20) NOT NULL DEFAULT '',
  `state` varchar(20) NOT NULL DEFAULT '',
  `zip` varchar(20) NOT NULL DEFAULT '',
  `userName` varchar(225) NOT NULL DEFAULT '',
  PRIMARY KEY (`userName`),
  CONSTRAINT `pk_user_fk` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Category`;

CREATE TABLE `Category` (
  `type` varchar(100) NOT NULL DEFAULT '',
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Favorites
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Favorites`;

CREATE TABLE `Favorites` (
  `userName` varchar(225) NOT NULL DEFAULT '',
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userName`),
  KEY `restaurantId_fk` (`restaurantId`),
  CONSTRAINT `pk_user7_fk` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`) ON DELETE CASCADE,
  CONSTRAINT `restaurantId_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Follower
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Follower`;

CREATE TABLE `Follower` (
  `Following` varchar(225) DEFAULT '',
  `Follower` varchar(225) NOT NULL DEFAULT '',
  KEY `pk_user4_fk` (`Following`),
  KEY `pk_user5_fk` (`Follower`),
  CONSTRAINT `pk_user4_fk` FOREIGN KEY (`Following`) REFERENCES `User` (`userName`),
  CONSTRAINT `pk_user5_fk` FOREIGN KEY (`Follower`) REFERENCES `User` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Person`;

CREATE TABLE `Person` (
  `userName` varchar(225) NOT NULL DEFAULT '',
  `password` varchar(225) NOT NULL DEFAULT '',
  `firstName` varchar(250) NOT NULL DEFAULT '',
  `lastName` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Reservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reservation`;

CREATE TABLE `Reservation` (
  `id` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `people_count` int(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `time_slot` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  `userName` varchar(225) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `pk_reservation_fk` (`restaurantId`),
  KEY `pk_user2_fk` (`userName`),
  CONSTRAINT `pk_reservation_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`),
  CONSTRAINT `pk_user2_fk` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Restaurant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Restaurant`;

CREATE TABLE `Restaurant` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(225) DEFAULT NULL,
  `phoneNo.` int(50) DEFAULT NULL,
  `website` varchar(500) DEFAULT NULL,
  `openingTime` time DEFAULT NULL,
  `closingTime` time DEFAULT NULL,
  `capacity` int(10) NOT NULL,
  `type` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `type_fk` (`type`),
  CONSTRAINT `type_fk` FOREIGN KEY (`type`) REFERENCES `Category` (`type`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reviews`;

CREATE TABLE `Reviews` (
  `userName` varchar(225) NOT NULL DEFAULT '',
  `ratings` int(2) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userName`),
  KEY `pk_rest_fk` (`restaurantId`),
  CONSTRAINT `pk_rest_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`),
  CONSTRAINT `pk_user1_fk` FOREIGN KEY (`userName`) REFERENCES `User` (`userName`)
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
  CONSTRAINT `pk_rest_time_fk` FOREIGN KEY (`id`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `phoneNo.` int(12) DEFAULT NULL,
  `email_Id` varchar(225) NOT NULL DEFAULT '',
  `userName` varchar(225) NOT NULL DEFAULT '',
  PRIMARY KEY (`userName`),
  CONSTRAINT `pk_person_fk` FOREIGN KEY (`userName`) REFERENCES `Person` (`userName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
