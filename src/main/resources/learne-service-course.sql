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
-- Table structure for table `answerchoice`
--

DROP TABLE IF EXISTS `answerchoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answerchoice`
(
    `Id`                      varchar(255) NOT NULL,
    `LessonQuestionHistoryId` varchar(255) NOT NULL,
    `AnswerId`                varchar(255) NOT NULL,
    `CreateTime`              date DEFAULT NULL,
    `UpdateTime`              date DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY                       `FKAnswerChoi188174` (`LessonQuestionHistoryId`),
    CONSTRAINT `FKAnswerChoi188174` FOREIGN KEY (`LessonQuestionHistoryId`) REFERENCES `lessonquestionhistory` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
    `Id`          varchar(255) NOT NULL,
    `Name`        varchar(255) NOT NULL,
    `Description` longtext,
    `Author`      varchar(255) DEFAULT NULL,
    `Image`       varchar(255) DEFAULT NULL,
    `Level`       varchar(255) DEFAULT NULL,
    `Price`       varchar(255) DEFAULT NULL,
    `CreateTime`  datetime     DEFAULT NULL,
    `UpdateTime`  datetime     DEFAULT NULL,
    PRIMARY KEY (`Id`)
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
    `Duration`    varchar(255) NOT NULL,
    `Description` longtext,
    `Video`       varchar(255) NOT NULL,
    `CreateTime`  datetime DEFAULT NULL,
    `UpdateTime`  datetime DEFAULT NULL,
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
    `Id`          varchar(255) NOT NULL,
    `LessonId`    varchar(255) NOT NULL,
    `Name`        varchar(255) NOT NULL,
    `Description` longtext,
    `CreateTime`  date DEFAULT NULL,
    `UpdateTime`  date DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY           `FKLessonExer910564` (`LessonId`),
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
    `LessonId`         varchar(255) NOT NULL,
    `UserId`           varchar(255) NOT NULL,
    `Status`           varchar(255) NOT NULL,
    `CreateTime`       datetime DEFAULT NULL,
    `UpdateTime`       datetime DEFAULT NULL,
    `Score`            datetime     NOT NULL,
    PRIMARY KEY (`Id`),
    KEY                `FKLessonExer936762` (`LessonId`),
    KEY                `FKLessonExer257807` (`LessonExerciseId`),
    CONSTRAINT `FKLessonExer257807` FOREIGN KEY (`LessonExerciseId`) REFERENCES `lessonexercise` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FKLessonExer936762` FOREIGN KEY (`LessonId`) REFERENCES `lesson` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessonquestion`
--

DROP TABLE IF EXISTS `lessonquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessonquestion`
(
    `Id`               varchar(255) NOT NULL,
    `LessonExerciseId` varchar(255) NOT NULL,
    `CreateTime`       date DEFAULT NULL,
    `UpdateTime`       date DEFAULT NULL,
    `QuestionId`       varchar(255) NOT NULL,
    PRIMARY KEY (`Id`),
    KEY                `FKLessonQues641174` (`LessonExerciseId`),
    CONSTRAINT `FKLessonQues641174` FOREIGN KEY (`LessonExerciseId`) REFERENCES `lessonexercise` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lessonquestionhistory`
--

DROP TABLE IF EXISTS `lessonquestionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessonquestionhistory`
(
    `Id`               varchar(255) NOT NULL,
    `LessonQuestionId` varchar(255) NOT NULL,
    `UserId`           varchar(255) NOT NULL,
    `CreateTime`       date DEFAULT NULL,
    `UpdateTime`       date DEFAULT NULL,
    `Score`            float        NOT NULL,
    PRIMARY KEY (`Id`),
    KEY                `FKLessonQues214875` (`LessonQuestionId`),
    CONSTRAINT `FKLessonQues214875` FOREIGN KEY (`LessonQuestionId`) REFERENCES `lessonquestion` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
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

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request`
(
    `Id`         varchar(255) NOT NULL,
    `CourseId`   varchar(255) NOT NULL,
    `Text`       longtext     NOT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY          `FKRequest930580` (`CourseId`),
    CONSTRAINT `FKRequest930580` FOREIGN KEY (`CourseId`) REFERENCES `course` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `target`
--

DROP TABLE IF EXISTS `target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `target`
(
    `Id`         varchar(255) NOT NULL,
    `CourseId`   varchar(255) NOT NULL,
    `Text`       longtext     NOT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`Id`),
    KEY          `FKTarget487279` (`CourseId`),
    CONSTRAINT `FKTarget487279` FOREIGN KEY (`CourseId`) REFERENCES `course` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
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

-- Dump completed on 2022-10-03 23:39:27