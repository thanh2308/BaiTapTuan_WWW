-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               MariaDB / MySQL
-- Database:                     shopdb
-- --------------------------------------------------------

CREATE DATABASE IF NOT EXISTS `shopdb` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_uca1400_vietnamese_ai_ci */;
USE `shopdb`;

-- Dumping structure for table shopdb.products
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `IMGURL` longtext DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf16 COLLATE=utf16_uca1400_vietnamese_ai_ci;

-- Dumping data for table shopdb.products: ~4 rows (approximately)
DELETE FROM `products`;
INSERT INTO `products` (`ID`, `MODEL`, `DESCRIPTION`, `QUANTITY`, `PRICE`, `IMGURL`) VALUES
	(1, 'Samsung', 'SamSung TV LCD', 4, 20000, 'samsung1.jpg'),
	(2, 'Sony', 'Sony Brivia', 1, 23000, 'sony.jpg'),
	(3, 'Iphone 15', 'Iphone 15 Detail', 10, 17000, 'ip15.jpg'),
	(4, 'Iphone 16', 'Iphone 16 Detail', 3, 18000, 'ip16.jpg');
