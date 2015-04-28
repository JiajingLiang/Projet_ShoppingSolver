-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: shoppingsolverdb
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `ID` bigint(20) NOT NULL,
  `BALANCE` double DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `POSTCODE` varchar(255) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (51,0,'Montreal','Canada','jiajing.liang@polymtl.ca','jjliang','jjliang','H3S 1X4','Place Decelles','5140000000'),(101,0,'Montreal','Canada','chunxia.zhang@polymtl.ca','cxzhang','cxzhang','H3T 1G1','Rue Willodale','5140000000');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_favorite_products`
--

DROP TABLE IF EXISTS `client_favorite_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_favorite_products` (
  `clientId` bigint(20) NOT NULL,
  `productId` varchar(20) NOT NULL,
  PRIMARY KEY (`clientId`,`productId`),
  KEY `FK_client_favorite_products_productId` (`productId`),
  CONSTRAINT `FK_client_favorite_products_clientId` FOREIGN KEY (`clientId`) REFERENCES `client` (`ID`),
  CONSTRAINT `FK_client_favorite_products_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`BARCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_favorite_products`
--

LOCK TABLES `client_favorite_products` WRITE;
/*!40000 ALTER TABLE `client_favorite_products` DISABLE KEYS */;
INSERT INTO `client_favorite_products` VALUES (101,'068200010311');
/*!40000 ALTER TABLE `client_favorite_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `BARCODE` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`BARCODE`),
  KEY `FK_product_CATEGORY_ID` (`CATEGORY_ID`),
  CONSTRAINT `FK_product_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `productcategory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('068100047219','Kraft CalorieWise Balsamic Vinaigrette 475ml',13),('068200010311','Lactantia skim milk 1L',12),('667888133697','Buongusto Spaghettini 900g',8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_price_in_shop`
--

DROP TABLE IF EXISTS `product_price_in_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_price_in_shop` (
  `PRICE` double NOT NULL,
  `RATIOTAXFEDERAL` float NOT NULL,
  `RATIOTAXPROVINCIAL` float NOT NULL,
  `productId` varchar(20) NOT NULL,
  `shopBranchId` bigint(20) NOT NULL,
  PRIMARY KEY (`productId`,`shopBranchId`),
  KEY `FK_product_price_in_shop_shopBranchId` (`shopBranchId`),
  CONSTRAINT `FK_product_price_in_shop_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`BARCODE`),
  CONSTRAINT `FK_product_price_in_shop_shopBranchId` FOREIGN KEY (`shopBranchId`) REFERENCES `shopbranch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_price_in_shop`
--

LOCK TABLES `product_price_in_shop` WRITE;
/*!40000 ALTER TABLE `product_price_in_shop` DISABLE KEYS */;
INSERT INTO `product_price_in_shop` VALUES (3.29,0.05,0.09975,'068100047219',3),(3.29,0.05,0.09975,'068100047219',4),(2.89,0.05,0.09975,'068100047219',5),(2.19,0,0,'068200010311',3),(2.19,0,0,'068200010311',4),(1.78,0,0,'667888133697',651);
/*!40000 ALTER TABLE `product_price_in_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_transact_record`
--

DROP TABLE IF EXISTS `product_transact_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_transact_record` (
  `PRICE` double NOT NULL,
  `QUANTITY` float NOT NULL,
  `RATIOTAXFEDERAL` float NOT NULL,
  `RATIOTAXPROVINCIAL` float NOT NULL,
  `productId` varchar(20) NOT NULL,
  `transactionId` bigint(20) NOT NULL,
  PRIMARY KEY (`productId`,`transactionId`),
  KEY `FK_product_transact_record_transactionId` (`transactionId`),
  CONSTRAINT `FK_product_transact_record_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`BARCODE`),
  CONSTRAINT `FK_product_transact_record_transactionId` FOREIGN KEY (`transactionId`) REFERENCES `transact` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_transact_record`
--

LOCK TABLES `product_transact_record` WRITE;
/*!40000 ALTER TABLE `product_transact_record` DISABLE KEYS */;
INSERT INTO `product_transact_record` VALUES (3.29,1,0.05,0.09975,'068100047219',301),(3.29,2,0.05,0.09975,'068100047219',351),(3.29,2,0.05,0.09975,'068100047219',451),(3.29,1,0.05,0.09975,'068100047219',501),(3.29,1,0.05,0.09975,'068100047219',551),(2.29,1,0,0,'068200010311',301),(2.29,1,0,0,'068200010311',351),(2.29,1,0,0,'068200010311',451),(2.29,1,0,0,'068200010311',501),(2.29,12,0,0,'068200010311',551);
/*!40000 ALTER TABLE `product_transact_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcategory`
--

DROP TABLE IF EXISTS `productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productcategory` (
  `ID` bigint(20) NOT NULL,
  `CATEGORYNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (7,'COFFEE'),(8,'PASTA'),(9,'CEREAL'),(10,'HONEY'),(11,'JUICE'),(12,'MILK'),(13,'VINEGARS');
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registeddevice`
--

DROP TABLE IF EXISTS `registeddevice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registeddevice` (
  `ID` bigint(20) NOT NULL,
  `DEVICEID` varchar(200) NOT NULL,
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_registeddevice_CLIENT_ID` (`CLIENT_ID`),
  CONSTRAINT `FK_registeddevice_CLIENT_ID` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registeddevice`
--

LOCK TABLES `registeddevice` WRITE;
/*!40000 ALTER TABLE `registeddevice` DISABLE KEYS */;
INSERT INTO `registeddevice` VALUES (401,'APA91bFDZmR7LCYAgtwPhdfAJyE6rvV3fGQ8AnJkQEQo4RwIZl7yuF8epfsKBXqvx_gLrc91irMf8Xv17XsOgHXiVWKLppulX2VmXQrD28WvQ6m3SBC3qto_EYkSBGjI-y9hEsf5PO9KOI4LgWUBtdGjLzFTb6qEvw',101);
/*!40000 ALTER TABLE `registeddevice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',700);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopbranch`
--

DROP TABLE IF EXISTS `shopbranch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopbranch` (
  `ID` bigint(20) NOT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `POSTCODE` varchar(255) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  `BRAND_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_shopbranch_BRAND_ID` (`BRAND_ID`),
  CONSTRAINT `FK_shopbranch_BRAND_ID` FOREIGN KEY (`BRAND_ID`) REFERENCES `shopbrand` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopbranch`
--

LOCK TABLES `shopbranch` WRITE;
/*!40000 ALTER TABLE `shopbranch` DISABLE KEYS */;
INSERT INTO `shopbranch` VALUES (3,'Montreal','Canada','H3S 2B2','6700 chemin de la cote des neiges',1),(4,'Saint-Laurent','Canada','H4R 1P8','3820 Boul de la Cote Vertu',1),(5,'Montreal','Canada','H3T 1X8 ','5150 Chemin De La Cote-Des-Neiges',2),(6,'Montreal','Canada','H3S 1P6','2875 Avenue Van-Horne',2),(601,'Montreal','Canada','H3S 2B6','6825 Cote-Des-Neiges',251),(651,'Lasalle','Canada','H8N 1X1','7081 Newman',251);
/*!40000 ALTER TABLE `shopbranch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopbrand`
--

DROP TABLE IF EXISTS `shopbrand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopbrand` (
  `ID` bigint(20) NOT NULL,
  `BRANDNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopbrand`
--

LOCK TABLES `shopbrand` WRITE;
/*!40000 ALTER TABLE `shopbrand` DISABLE KEYS */;
INSERT INTO `shopbrand` VALUES (1,'Walmart'),(2,'Metro'),(251,'Maxi&cie');
/*!40000 ALTER TABLE `shopbrand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transact`
--

DROP TABLE IF EXISTS `transact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transact` (
  `ID` bigint(20) NOT NULL,
  `TOTAL` double NOT NULL,
  `TRANSACTIONTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  `SHOP_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_transact_SHOP_ID` (`SHOP_ID`),
  KEY `FK_transact_CLIENT_ID` (`CLIENT_ID`),
  CONSTRAINT `FK_transact_CLIENT_ID` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`ID`),
  CONSTRAINT `FK_transact_SHOP_ID` FOREIGN KEY (`SHOP_ID`) REFERENCES `shopbranch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transact`
--

LOCK TABLES `transact` WRITE;
/*!40000 ALTER TABLE `transact` DISABLE KEYS */;
INSERT INTO `transact` VALUES (301,6.07,'2015-04-11 03:12:34',51,3),(351,9.86,'2015-04-11 03:17:04',51,4),(451,9.86,'2015-04-18 15:35:48',51,3),(501,6.07,'2015-04-18 16:41:33',101,4),(551,31.26,'2015-04-18 21:26:26',51,4);
/*!40000 ALTER TABLE `transact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 20:54:34
