-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.8


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema toggle_service
--

CREATE DATABASE IF NOT EXISTS toggle_service;
USE toggle_service;

--
-- Definition of table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `version` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`name`,`version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services`
--

/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` (`id`,`name`,`version`) VALUES 
 (1,'ABC','v1.0'),
 (22,'IDM','v2.0');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;


--
-- Definition of table `toggle`
--

DROP TABLE IF EXISTS `toggle`;
CREATE TABLE `toggle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `isActivate` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `toggle`
--

/*!40000 ALTER TABLE `toggle` DISABLE KEYS */;
INSERT INTO `toggle` (`id`,`name`,`isActivate`) VALUES 
 (1,'isButtonBlue',1),
 (2,'isButtonGreen',1),
 (3,'isButtonRed',1),
 (19,'coupon',1);
/*!40000 ALTER TABLE `toggle` ENABLE KEYS */;


--
-- Definition of table `tooggleservices`
--

DROP TABLE IF EXISTS `tooggleservices`;
CREATE TABLE `tooggleservices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `serviceId` int(10) unsigned NOT NULL,
  `toggleId` int(10) unsigned NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`serviceId`,`toggleId`),
  KEY `FK_New Table_2` (`toggleId`) USING BTREE,
  CONSTRAINT `FK_New Table_1` FOREIGN KEY (`serviceId`) REFERENCES `services` (`id`),
  CONSTRAINT `FK_New Table_2` FOREIGN KEY (`toggleId`) REFERENCES `toggle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tooggleservices`
--

/*!40000 ALTER TABLE `tooggleservices` DISABLE KEYS */;
INSERT INTO `tooggleservices` (`id`,`serviceId`,`toggleId`,`status`) VALUES 
 (1,1,1,1),
 (17,22,19,0);
/*!40000 ALTER TABLE `tooggleservices` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
