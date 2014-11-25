# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.21)
# Database: Restaurant_Reservation
# Generation Time: 2014-11-25 01:31:46 +0000
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
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(225) DEFAULT NULL,
  `apt_No` int(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `A_id_user_fk` FOREIGN KEY (`id`) REFERENCES `User` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Category`;

CREATE TABLE `Category` (
  `id` int(11) unsigned NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pk_res_fk` FOREIGN KEY (`id`) REFERENCES `Restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Favorites
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Favorites`;

CREATE TABLE `Favorites` (
  `userId` int(11) unsigned NOT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `restaurantId_fk` (`restaurantId`),
  CONSTRAINT `restaurantId_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`) ON DELETE CASCADE,
  CONSTRAINT `userID_fk` FOREIGN KEY (`userId`) REFERENCES `User` (`personId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Follower
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Follower`;

CREATE TABLE `Follower` (
  `Following` int(11) unsigned DEFAULT NULL,
  `Follower` int(11) unsigned DEFAULT NULL,
  KEY `user_fk1` (`Following`),
  KEY `user_fk2` (`Follower`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`Following`) REFERENCES `User` (`personId`) ON DELETE CASCADE,
  CONSTRAINT `user_fk2` FOREIGN KEY (`Follower`) REFERENCES `User` (`personId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Person`;

CREATE TABLE `Person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `firstName` varchar(250) DEFAULT NULL,
  `lastName` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;



# Dump of table Reservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reservation`;

CREATE TABLE `Reservation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `people_count` int(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `time_slot` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_userId_fk` (`userId`),
  KEY `pk_reservation_fk` (`restaurantId`),
  CONSTRAINT `pk_reservation_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`),
  CONSTRAINT `pk_userId_fk` FOREIGN KEY (`userId`) REFERENCES `User` (`personId`)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Reviews
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reviews`;

CREATE TABLE `Reviews` (
  `id` int(11) unsigned NOT NULL,
  `ratings` int(2) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `restaurantId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_rest_fk` (`restaurantId`),
  CONSTRAINT `pk_rest_fk` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`id`),
  CONSTRAINT `pk_user_fk` FOREIGN KEY (`id`) REFERENCES `User` (`personId`)
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
  `email_Id` varchar(225) DEFAULT NULL,
  `personId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`personId`),
  CONSTRAINT `p_id_fk` FOREIGN KEY (`personId`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
