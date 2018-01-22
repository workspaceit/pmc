-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 22, 2018 at 05:22 PM
-- Server version: 5.6.39
-- PHP Version: 5.5.9-1ubuntu4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pmc`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertisement_prices`
--

CREATE TABLE IF NOT EXISTS `advertisement_prices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('GALLERY_BACKGROUND_IMAGE','GALLERY_TOP_AD_BANNER','GALLERY_BOTTOM_AD_BANNER') COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `advertisement_prices`
--

INSERT INTO `advertisement_prices` (`id`, `type`, `description`, `price`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'GALLERY_BACKGROUND_IMAGE', 'Gallery Background Image', 50, '2018-01-16 00:00:00', '2018-01-22 16:39:15', 132),
(2, 'GALLERY_TOP_AD_BANNER', 'Top Ad Banner', 102, '2018-01-16 00:00:00', '2018-01-22 16:39:25', 132),
(3, 'GALLERY_BOTTOM_AD_BANNER', 'Bottom Ad Banner', 110, '2018-01-16 00:00:00', '2018-01-22 16:39:36', 132);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
