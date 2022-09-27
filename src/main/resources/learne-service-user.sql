CREATE
DATABASE  IF NOT EXISTS `heroku_b483559a4cc08c2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE
`heroku_b483559a4cc08c2`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: us-cdbr-east-06.cleardb.net    Database: heroku_b483559a4cc08c2
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account`
(
    `Id`         varchar(255) NOT NULL,
    `Email`      varchar(255) NOT NULL,
    `Password`   varchar(255) NOT NULL,
    `CreateTime` date DEFAULT NULL,
    `UpdateTime` date DEFAULT NULL,
    PRIMARY KEY (`Id`),
    UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK
TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address`
(
    `Id`          varchar(255) NOT NULL,
    `Nation`      varchar(255) DEFAULT NULL,
    `City`        varchar(255) DEFAULT NULL,
    `Province`    varchar(255) DEFAULT NULL,
    `District`    varchar(255) DEFAULT NULL,
    `Street`      varchar(255) DEFAULT NULL,
    `NumberHouse` varchar(255) DEFAULT NULL,
    `CreateTime`  date         DEFAULT NULL,
    `UpdateTime`  date         DEFAULT NULL,
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK
TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `fullname`
--

DROP TABLE IF EXISTS `fullname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fullname`
(
    `Id`         varchar(255) NOT NULL,
    `FirstName`  varchar(255) DEFAULT NULL,
    `MidName`    varchar(255) DEFAULT NULL,
    `LastName`   varchar(255) DEFAULT NULL,
    `CreateTime` date         DEFAULT NULL,
    `UpdateTime` date         DEFAULT NULL,
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fullname`
--

LOCK
TABLES `fullname` WRITE;
/*!40000 ALTER TABLE `fullname` DISABLE KEYS */;
/*!40000 ALTER TABLE `fullname` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `Id`          varchar(255) NOT NULL,
    `Role`        varchar(255) NOT NULL,
    `Gender`      varchar(255) DEFAULT NULL,
    `UserName`    varchar(255) NOT NULL,
    `DateOfBirth` date         DEFAULT NULL,
    `PhoneNumber` varchar(255) DEFAULT NULL,
    `Avatar`      varchar(255) DEFAULT NULL,
    `CreateTime`  date         DEFAULT NULL,
    `UpdateTime`  date         DEFAULT NULL,
    `FullNameId`  varchar(255) DEFAULT NULL,
    `AddressId`   varchar(255) DEFAULT NULL,
    `AccountId`   varchar(255) NOT NULL,
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UserName` (`UserName`),
    KEY           `FKUser568361` (`FullNameId`),
    KEY           `FKUser847479` (`AddressId`),
    KEY           `FKUser921429` (`AccountId`),
    CONSTRAINT `FKUser568361` FOREIGN KEY (`FullNameId`) REFERENCES `fullname` (`Id`),
    CONSTRAINT `FKUser847479` FOREIGN KEY (`AddressId`) REFERENCES `address` (`Id`),
    CONSTRAINT `FKUser921429` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-27 23:20:51