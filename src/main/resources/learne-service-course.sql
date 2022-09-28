CREATE
DATABASE  IF NOT EXISTS `heroku_9caa7b1e5bc1e22` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE
`heroku_9caa7b1e5bc1e22`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: us-cdbr-east-06.cleardb.net    Database: heroku_9caa7b1e5bc1e22
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
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter`
(
    `Id`         varchar(255) NOT NULL,
    `CourseId`   varchar(255) NOT NULL,
    `Name`       varchar(255) NOT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY          `FKChapter746961` (`CourseId`),
    CONSTRAINT `FKChapter746961` FOREIGN KEY (`CourseId`) REFERENCES `course` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course`
(
    `Id`             varchar(255) NOT NULL,
    `Name`           varchar(255) NOT NULL,
    `NumberOfPeople` bigint(20) DEFAULT NULL,
    `Description`    varchar(255) DEFAULT NULL,
    `CreateTime`     datetime     DEFAULT NULL,
    `UpdateTime`     datetime     DEFAULT NULL,
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extradata`
--

DROP TABLE IF EXISTS `extradata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extradata`
(
    `Id`             varchar(255) NOT NULL,
    `CourseId`       varchar(255) NOT NULL,
    `ExtraDataKey`   varchar(255) NOT NULL,
    `ExtraDataValue` varchar(255) NOT NULL,
    `CreateTime`     datetime DEFAULT NULL,
    `UpdateTime`     datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY              `FKExtraData240978` (`CourseId`),
    CONSTRAINT `FKExtraData240978` FOREIGN KEY (`CourseId`) REFERENCES `course` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson`
(
    `Id`          varchar(255) NOT NULL,
    `ChapterId`   varchar(255) NOT NULL,
    `Name`        varchar(255) NOT NULL,
    `Description` varchar(255) DEFAULT NULL,
    `Video`       varchar(255) NOT NULL,
    `CreateTime`  datetime     DEFAULT NULL,
    `UpdateTime`  datetime     DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY           `FKLesson123481` (`ChapterId`),
    CONSTRAINT `FKLesson123481` FOREIGN KEY (`ChapterId`) REFERENCES `chapter` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessonexercise`
--

DROP TABLE IF EXISTS `lessonexercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessonexercise`
(
    `Id`         varchar(255) NOT NULL,
    `LessonId`   varchar(255) NOT NULL,
    `QuestionId` varchar(255) NOT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY          `FKLessonExer910564` (`LessonId`),
    CONSTRAINT `FKLessonExer910564` FOREIGN KEY (`LessonId`) REFERENCES `lesson` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessonexercisestatus`
--

DROP TABLE IF EXISTS `lessonexercisestatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessonexercisestatus`
(
    `Id`               varchar(255) NOT NULL,
    `LessonExerciseId` varchar(255) NOT NULL,
    `UserId`           varchar(255) NOT NULL,
    `Status`           varchar(255) NOT NULL,
    `CreateTime`       datetime DEFAULT NULL,
    `UpdateTime`       datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY                `FKLessonExer257807` (`LessonExerciseId`),
    CONSTRAINT `FKLessonExer257807` FOREIGN KEY (`LessonExerciseId`) REFERENCES `lessonexercise` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessonstatus`
--

DROP TABLE IF EXISTS `lessonstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessonstatus`
(
    `Id`         varchar(255) NOT NULL,
    `LessonId`   varchar(255) NOT NULL,
    `UserId`     varchar(255) NOT NULL,
    `Status`     varchar(255) NOT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY          `FKLessonStat434395` (`LessonId`),
    CONSTRAINT `FKLessonStat434395` FOREIGN KEY (`LessonId`) REFERENCES `lesson` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-28 21:45:50