-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: ecomm_backend
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `date_created` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `units_in_stock` int NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cypb0k23bovo3rn1a5jqs6j4` (`category_id`),
  CONSTRAINT `FK5cypb0k23bovo3rn1a5jqs6j4` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,_binary '','2024-10-18','Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)','assets/images/products/laptops/dell-laptop-1000.png',NULL,'DELL - Laptop','DELL-LAPTOP-1000',19.99,100,1),(2,_binary '','2024-10-18','Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)','assets/images/products/laptops/hp-laptop-1001.png',NULL,'HP - Laptop','HP-LAPTOP-1001',59.99,100,1),(3,_binary '','2024-10-18','Acer Aspire Lite 12th Gen Intel Core i5-1235U Thin and Light Laptop ','assets/images/products/laptops/acer-laptop-1002.png',NULL,'ACER - Laptop','ACER-LAPTOP-1002',49.99,100,1),(4,_binary '','2024-10-18','Lenovo ThinkPad E14 AMD Ryzen 5','assets/images/products/laptops/lenovo-laptop-1003.png',NULL,'Lenovo ThinkPad E14','LENOVO-LAPTOP-1003',45.00,100,1),(5,_binary '','2024-10-18','Apple iPhone 13 (128GB) - Green','assets/images/products/mobiles/apple-mobile-1000.png',NULL,'Apple-Iphone','Apple-IPhone-1000',59.00,100,2),(6,_binary '','2024-10-18','Redmi 13C (Stardust Black, 6GB RAM, 128GB Storage)','assets/images/products/mobiles/redmi-mobile-1001.png',NULL,'Redmi 13C','RED-MI-1001',29.00,100,2),(7,_binary '','2024-10-18','SAMSUNG Galaxy F14 5G 6GB RAM 128GB STORAGE','assets/images/products/mobiles/samsung-mobile-1002.png',NULL,'Samsung Mobile','SAMSUNG-Galaxy-1002',39.00,100,2),(8,_binary '','2024-10-18','POCO C65 (Pastel Green 4GB RAM 128GB Storage)','assets/images/products/mobiles/poco-mobile-1003.png',NULL,'POCO C65','POCO C65',49.00,100,2),(9,_binary '','2024-10-18','Amazon Brand - Symbol Mens Cotton Shirt','assets/images/products/clothes/shirt-1000.png',NULL,'Mens Cotton Shirt','Mens Cotton Shirt',5.00,100,3),(10,_binary '','2024-11-04','Maybelline New York Fit Me Matte+Poreless Liquid Foundation, 115 Ivory, 30ml','assets/images/products/beauty/Maybelline_Fit_Me.jpg',NULL,'Maybelline Fit Me','MAYBELLINE',145.00,100,5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-06 22:42:46
