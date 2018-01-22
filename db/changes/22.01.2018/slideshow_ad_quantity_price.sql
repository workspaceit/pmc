-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 22, 2018 at 05:49 PM
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
-- Table structure for table `slideshow_ad_quantity_price`
--

CREATE TABLE IF NOT EXISTS `slideshow_ad_quantity_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `slideshow_id` int(11) NOT NULL,
  `ad_type` enum('BANNER','VIDEO') NOT NULL,
  `price` float(5,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `slideshow_ad_quantity_price`
--

INSERT INTO `slideshow_ad_quantity_price` (`id`, `slideshow_id`, `ad_type`, `price`, `quantity`, `created_at`, `created_by`) VALUES
(1, 1, 'BANNER', 23.00, 2, '0000-00-00 00:00:00', NULL),
(2, 1, 'VIDEO', 999.99, 3, '0000-00-00 00:00:00', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
