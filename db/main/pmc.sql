-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 20, 2018 at 06:44 AM
-- Server version: 5.5.59-0ubuntu0.14.04.1
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
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=140 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `email`, `user_name`, `phone_number`, `image`, `password`, `created_at`, `created_by`, `active`) VALUES
(132, 'Anik S.01564', 'aniksarker17@gmail.com', 'anik', '132165487435345', '6915014423261.png', '$2a$10$CB454JLAnGhfH9pRQaSJHebfuik3TaCkaAgopXvTAPZfzhHvtmvFi', '2017-12-19 18:08:02', NULL, 1),
(133, 'Shawon', 'shawon@gmail.com', 'shawon', '01234565', '28712497703892.png', '$2a$10$LxT2LHSvFBXOIZPGCOTTq.HzHpFeV9NotBGy4G0UU7dEzfDAPMPTC', '2018-01-09 18:47:25', NULL, 1),
(134, 'Roy Roy', 'roy@gmail.com', 'roy', '11', '', '$2a$10$v9aKn09NulrVFtdV..7caOOdlwDjL.AoVpM4SCMK6yDZHc6qdkONu', '2018-01-10 11:14:41', NULL, 1),
(135, 'lala', 'lala@a', 'lala', '1122', '', '$2a$10$wZpqzPnaz474yTSl2oDeUeWw0V/Rd0/7l2WgRdeNKLjODm6b2zq4K', '2018-01-10 11:19:22', NULL, 1),
(136, 'Rafi', 'rafi@k', 'Rafi', '354', '101989477327860.jpg', '$2a$10$DqfhIBgUvG6NH49lV3YwOOB.0M4Alo09khwApRyF3ewfzjdYNdGFm', '2018-01-10 15:08:43', NULL, 1),
(137, 'sdf', 'sdf@sdf.com', 'sdf', '34', '71813925232994.png', '$2a$10$uZrZUNnVL9nbuIRb4zObHu8EigqAz5lvlvPQtSGtIFkdU4ezLPh9G', '2018-01-17 16:17:49', NULL, 1),
(138, 'New Admin', 'dsw@w', 'New', '1', '6864466131797.png', '$2a$10$FzoXjl/ema6gi0ZEcLt2lOew6y.3UhEx.sNVwo/5..zPLTFb6mD0y', '2018-01-17 16:19:12', NULL, 1),
(139, 'Admin Admin', 'awar@s', 'Admin', '565475671', '2113899714902.png', '$2a$10$6WmxtzoMcZKdQ35tZwYX/uKnSL6du3Dewy7DQWl2atriJlPKl1j4W', '2018-01-18 11:58:32', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `admin_has_roles`
--

CREATE TABLE IF NOT EXISTS `admin_has_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `admin_role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  KEY `admin_role_id` (`admin_role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin_has_roles`
--

INSERT INTO `admin_has_roles` (`id`, `admin_id`, `admin_role_id`) VALUES
(1, 132, 1);

-- --------------------------------------------------------

--
-- Table structure for table `admin_roles`
--

CREATE TABLE IF NOT EXISTS `admin_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin_roles`
--

INSERT INTO `admin_roles` (`id`, `role`) VALUES
(1, 'superadmin');

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE IF NOT EXISTS `advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `ad_type` enum('GALLERY','SLIDESHOW','POPUP_SMS','POPUP_EMAIL') NOT NULL,
  `state` enum('DELETE','INACTIVE','ACTIVE') NOT NULL DEFAULT 'ACTIVE',
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `advertisement`
--

INSERT INTO `advertisement` (`id`, `advertiser_id`, `ad_type`, `state`, `created_at`, `created_by`) VALUES
(1, 1, 'GALLERY', 'ACTIVE', '2018-02-02 17:12:38', 132),
(2, 1, 'SLIDESHOW', 'ACTIVE', '2018-02-02 17:12:39', 132),
(3, 1, 'POPUP_SMS', 'ACTIVE', '2018-02-02 17:12:39', 132),
(4, 1, 'POPUP_EMAIL', 'ACTIVE', '2018-02-02 17:12:39', 132),
(5, 2, 'GALLERY', 'ACTIVE', '2018-02-02 17:16:57', 132),
(6, 2, 'SLIDESHOW', 'ACTIVE', '2018-02-02 17:16:57', 132),
(7, 2, 'POPUP_SMS', 'ACTIVE', '2018-02-02 17:16:57', 132),
(8, 2, 'POPUP_EMAIL', 'ACTIVE', '2018-02-02 17:16:57', 132),
(9, 3, 'GALLERY', 'ACTIVE', '2018-02-02 17:26:53', 132),
(10, 3, 'SLIDESHOW', 'ACTIVE', '2018-02-02 17:26:53', 132),
(11, 3, 'POPUP_SMS', 'ACTIVE', '2018-02-02 17:26:53', 132),
(12, 3, 'POPUP_EMAIL', 'ACTIVE', '2018-02-02 17:26:53', 132),
(13, 4, 'GALLERY', 'ACTIVE', '2018-02-14 17:17:32', 132),
(14, 4, 'SLIDESHOW', 'ACTIVE', '2018-02-14 17:17:32', 132),
(15, 4, 'POPUP_SMS', 'ACTIVE', '2018-02-14 17:17:32', 132),
(16, 4, 'POPUP_EMAIL', 'ACTIVE', '2018-02-14 17:17:32', 132);

-- --------------------------------------------------------

--
-- Table structure for table `advertisement_prices`
--

CREATE TABLE IF NOT EXISTS `advertisement_prices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('GALLERY_BACKGROUND_IMAGE','GALLERY_TOP_AD_BANNER','GALLERY_BOTTOM_AD_BANNER','SLIDESHOW_BANNER','SLIDESHOW_VIDEO','POPUP_SMS','POPUP_EMAIL') COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `advertisement_prices`
--

INSERT INTO `advertisement_prices` (`id`, `type`, `description`, `price`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'GALLERY_BACKGROUND_IMAGE', 'Gallery Background Image', 50, '2018-01-16 00:00:00', '2018-01-22 16:39:15', 132),
(2, 'GALLERY_TOP_AD_BANNER', 'Gallery Top Ad Banner', 100, '2018-01-16 00:00:00', '2018-02-07 13:20:14', 132),
(3, 'GALLERY_BOTTOM_AD_BANNER', 'Gallery Bottom Ad Banner', 110, '2018-01-16 00:00:00', '2018-01-22 16:39:36', 132),
(8, 'SLIDESHOW_BANNER', 'Slideshow ad banner', 4, NULL, '2018-01-22 17:49:11', NULL),
(9, 'SLIDESHOW_VIDEO', 'Slideshow ad video', 43, NULL, '2018-01-22 17:49:01', NULL),
(10, 'POPUP_SMS', 'Pop Up Sms', 3, NULL, '2018-01-22 18:31:18', NULL),
(11, 'POPUP_EMAIL', 'Pop Up Email', 2, NULL, '2018-01-22 18:31:18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `advertisers`
--

CREATE TABLE IF NOT EXISTS `advertisers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `city_id` int(11) NOT NULL,
  `state_id` int(11) NOT NULL,
  `zip` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `all_locations` tinyint(1) NOT NULL,
  `all_events` tinyint(1) NOT NULL,
  `other_image` text COLLATE utf8_unicode_ci,
  `runtime_starts` date NOT NULL,
  `runtime_ends` date NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `advertisers`
--

INSERT INTO `advertisers` (`id`, `name`, `address`, `city_id`, `state_id`, `zip`, `phone`, `website`, `all_locations`, `all_events`, `other_image`, `runtime_starts`, `runtime_ends`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(1, 'aaaaaaa', 'aasadsadas', 1, 1, 'saaaa', 'adsdasdsad', 'https://wsdsadd.df', 1, 1, NULL, '2018-02-02', '2018-02-02', '2018-02-02 17:12:38', '2018-02-02 17:12:38', 132, 0, 1),
(2, 'gfd', 'dssddsf', 225, 52, 'fsdf', 'fdsfdsf', 'https://rtrt.gfr', 1, 1, NULL, '2018-02-23', '2018-02-14', '2018-02-02 17:16:57', '2018-02-02 17:16:57', 132, 0, 0),
(3, 'asdsds', 'adsdsad', 117, 51, 'sdsd', 'sdsdsd', 'http://dfdsf.dd', 1, 1, NULL, '2018-02-02', '2018-02-02', '2018-02-02 17:26:52', '2018-02-14 17:59:44', 132, 1, 0),
(4, 'aa', 'aa', 225, 52, 'aa', 'aa', 'http://ss.ff', 0, 0, NULL, '2018-02-14', '2018-02-14', '2018-02-14 17:17:32', '2018-02-14 17:17:32', 132, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `advertisers_other_image`
--

CREATE TABLE IF NOT EXISTS `advertisers_other_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `advertisers_other_image`
--

INSERT INTO `advertisers_other_image` (`id`, `advertiser_id`, `image`, `created_at`, `created_by`) VALUES
(1, 1, '4282413993512.png', '2018-02-02 17:12:38', 132),
(2, 2, '4464868773931.png', '2018-02-02 17:16:57', 132);

-- --------------------------------------------------------

--
-- Table structure for table `advertiser_transaction`
--

CREATE TABLE IF NOT EXISTS `advertiser_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `subtotal` float(12,2) NOT NULL,
  `total` float(12,2) NOT NULL COMMENT 'After discount deducted amount (Subtotal- Discount)',
  `total_paid` float(12,2) NOT NULL,
  `total_due` float(12,2) NOT NULL,
  `discount` float(12,2) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `advertiser_transaction`
--

INSERT INTO `advertiser_transaction` (`id`, `advertiser_id`, `subtotal`, `total`, `total_paid`, `total_due`, `discount`, `created_by`, `created_at`) VALUES
(1, 1, 520.00, 531.00, 0.00, 520.00, 11.00, 132, '2018-02-02 17:13:13'),
(2, 3, 300.00, 314.00, 10.00, 290.00, 14.00, 132, '2018-02-02 17:39:55'),
(3, 4, 0.00, 0.00, 0.00, 0.00, 0.00, 132, '2018-02-14 17:17:56');

-- --------------------------------------------------------

--
-- Table structure for table `advertiser_transaction_details`
--

CREATE TABLE IF NOT EXISTS `advertiser_transaction_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_transaction_id` int(11) DEFAULT NULL,
  `item` varchar(200) NOT NULL,
  `price` float(12,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` float(12,2) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `advertiser_transaction_details`
--

INSERT INTO `advertiser_transaction_details` (`id`, `advertiser_transaction_id`, `item`, `price`, `quantity`, `total`, `created_by`, `created_at`) VALUES
(1, 1, 'Gallery Background Image', 50.00, 1, 50.00, NULL, '2018-02-02 17:13:13'),
(2, 1, 'Gallery Top Ad Banner', 102.00, 2, 204.00, NULL, '2018-02-02 17:13:13'),
(3, 1, 'Gallery Bottom Ad Banner', 110.00, 2, 220.00, NULL, '2018-02-02 17:13:13'),
(4, 1, 'Slide show Ad Banner', 4.00, 1, 4.00, NULL, '2018-02-02 17:13:13'),
(5, 1, 'Slide show Ad Video', 43.00, 1, 43.00, NULL, '2018-02-02 17:13:13'),
(6, 1, 'Popup Sms Ad', 3.00, 2, 6.00, NULL, '2018-02-02 17:13:13'),
(7, 1, 'Popup Email Ad', 2.00, 2, 4.00, NULL, '2018-02-02 17:13:13'),
(8, 2, 'Gallery Background Image', 50.00, 1, 50.00, NULL, '2018-02-02 17:39:55'),
(9, 2, 'Gallery Top Ad Banner', 102.00, 1, 102.00, NULL, '2018-02-02 17:39:55'),
(10, 2, 'Gallery Bottom Ad Banner', 110.00, 1, 110.00, NULL, '2018-02-02 17:39:55'),
(11, 2, 'Slide show Ad Banner', 4.00, 1, 4.00, NULL, '2018-02-02 17:39:55'),
(12, 2, 'Slide show Ad Video', 43.00, 1, 43.00, NULL, '2018-02-02 17:39:55'),
(13, 2, 'Popup Sms Ad', 3.00, 1, 3.00, NULL, '2018-02-02 17:39:55'),
(14, 2, 'Popup Email Ad', 2.00, 1, 2.00, NULL, '2018-02-02 17:39:55'),
(15, 3, 'Gallery Background Image', 50.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(16, 3, 'Gallery Top Ad Banner', 100.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(17, 3, 'Gallery Bottom Ad Banner', 110.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(18, 3, 'Slide show Ad Banner', 4.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(19, 3, 'Slide show Ad Video', 43.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(20, 3, 'Popup Sms Ad', 3.00, 0, 0.00, NULL, '2018-02-14 17:17:56'),
(21, 3, 'Popup Email Ad', 2.00, 0, 0.00, NULL, '2018-02-14 17:17:56');

-- --------------------------------------------------------

--
-- Table structure for table `base`
--

CREATE TABLE IF NOT EXISTS `base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE IF NOT EXISTS `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=292 ;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `state_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Philadelphia', 1, '2018-01-03 00:00:00', '2018-02-02 00:00:00', NULL),
(2, 'San Diego', 0, '2018-02-02 00:00:00', '2018-02-02 00:00:00', NULL),
(3, 'New York', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(4, 'Los Angeles', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(5, 'Chicago', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(6, 'Houston', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(7, 'Philadelphia', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(8, 'Phoenix', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(9, 'San Antonio', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(10, 'Diego', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(11, 'Dallas', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(12, 'San Jose', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(13, 'Austin', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(14, 'Jacksonville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(15, 'Indianapolis', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(16, 'San Francisco', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(17, 'Columbus', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(18, 'Fort Worth', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(19, 'Charlotte', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(20, 'Detroit', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(21, 'El Paso', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(22, 'Memphis', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(23, 'Boston', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(24, 'Seattle', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(25, 'Denver', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(26, 'Washington', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(27, 'Nashville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(28, 'Baltimore', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(29, 'Louisville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(30, 'Portland', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(31, 'Oklahoma City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(32, 'Milwaukee', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(33, 'Las Vegas', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(34, 'Albuquerque', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(35, 'Tucson', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(36, 'Fresno', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(37, 'Sacramento', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(38, 'Long Beach', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(39, 'Kansas City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(40, 'Mesa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(41, 'Virginia Beach', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(42, 'Atlanta', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(43, 'Colorado Springs', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(44, 'Raleigh', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(45, 'Omaha', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(46, 'Miami', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(47, 'Oakland', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(48, 'Tulsa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(49, 'Minneapolis', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(50, 'Cleveland', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(51, 'Wichita', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(52, 'Arlington', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(53, 'New Orleans', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(54, 'Bakersfield', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(55, 'Tampa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(56, 'Honolulu', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(57, 'Anaheim', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(58, 'Aurora', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(59, 'Santa Ana', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(60, 'St. Louis', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(61, 'Riverside', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(62, 'Corpus Christi', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(63, 'Pittsburgh', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(64, 'Lexington', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(65, 'Anchorage', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(66, 'Stockton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(67, 'Cincinnati', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(68, 'Saint Paul', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(69, 'Toledo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(70, 'Newark', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(71, 'Greensboro', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(72, 'Plano', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(73, 'Henderson', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(74, 'Lincoln', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(75, 'Buffalo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(76, 'Fort Wayne', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(77, 'Jersey City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(78, 'Chula Vista', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(79, 'Orlando', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(80, 'St. Petersburg', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(81, 'Norfolk', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(82, 'Chandler', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(83, 'Laredo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(84, 'Madison', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(85, 'Durham', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(86, 'Lubbock', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(87, 'Winston-Salem', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(88, 'Garland', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(89, 'Glendale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(90, 'Hialeah', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(91, 'Reno', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(92, 'Baton Rouge', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(93, 'Irvine', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(94, 'Chesapeake', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(95, 'Irving', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(96, 'Scottsdale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(97, 'North Las Vegas', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(98, 'Fremont', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(99, 'Gilbert', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(100, 'San Bernardino', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(101, 'Boise', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(102, 'Birmingham', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(103, 'Rochester', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(104, 'Richmond', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(105, 'Spokane', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(106, 'Des Moines', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(107, 'Montgomery', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(108, 'Modesto', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(109, 'Fayetteville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(110, 'Tacoma', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(111, 'Shreveport', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(112, 'Fontana', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(113, 'Oxnard', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(114, 'Aurora', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(115, 'Moreno Valley', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(116, 'Akron', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(117, 'Yonkers', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(118, 'Columbus', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(119, 'Augusta', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(120, 'Little Rock', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(121, 'Amarillo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(122, 'Mobile', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(123, 'Huntington Beach', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(124, 'Glendale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(125, 'Grand Rapids', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(126, 'Salt Lake City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(127, 'Tallahassee', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(128, 'Huntsville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(129, 'Worcester', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(130, 'Knoxville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(131, 'Grand Prairie', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(132, 'Newport News', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(133, 'Brownsville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(134, 'Santa Clarita', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(135, 'Overland Park', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(136, 'Providence', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(137, 'Jackson', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(138, 'Garden Grove', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(139, 'Oceanside', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(140, 'Chattanooga', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(141, 'Fort Lauderdale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(142, 'Rancho Cucamonga', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(143, 'Santa Rosa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(144, 'Port St. Lucie', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(145, 'Ontario', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(146, 'Tempe', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(147, 'Vancouver', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(148, 'Springfield', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(149, 'Cape Coral', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(150, 'Pembroke Pines', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(151, 'Sioux Falls', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(152, 'Peoria', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(153, 'Lancaster', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(154, 'Elk Grove', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(155, 'Corona', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(156, 'Eugene', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(157, 'Salem', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(158, 'Palmdale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(159, 'Salinas', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(160, 'Springfield', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(161, 'Pasadena', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(162, 'Rockford', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(163, 'Pomona', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(164, 'Hayward', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(165, 'Fort Collins', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(166, 'Joliet', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(167, 'Escondido', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(168, 'Kansas City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(169, 'Torrance', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(170, 'Bridgeport', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(171, 'Alexandria', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(172, 'Sunnyvale', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(173, 'Cary', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(174, 'Lakewood', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(175, 'Hollywood', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(176, 'Paterson', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(177, 'Syracuse', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(178, 'Naperville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(179, 'McKinney', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(180, 'Mesquite', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(181, 'Clarksvlle', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(182, 'Savannah', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(183, 'Dayton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(184, 'Orange', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(185, 'Fullerton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(186, 'Pasadena', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(187, 'Hampton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(188, 'McAllen', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(189, 'Killeen', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(190, 'Warren', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(191, 'West Valley City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(192, 'Columbia', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(193, 'New Haven', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(194, 'Sterling Heights', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(195, 'Olathe', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(196, 'Miramar', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(197, 'Thousand Oaks', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(198, 'Frisco', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(199, 'Cedar Rapids', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(200, 'Topeka', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(201, 'Visalia', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(202, 'Waco', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(203, 'Elizabeth', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(204, 'Bellevue', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(205, 'Gainesville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(206, 'Simi Valley', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(207, 'Charleston', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(208, 'Carrollton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(209, 'Coral Springs', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(210, 'Stamford', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(211, 'Hartford', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(212, 'Concord', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(213, 'Roseville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(214, 'Thornton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(215, 'Kent', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(216, 'Lafayette', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(217, 'Surprise', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(218, 'Denton', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(219, 'Victorville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(220, 'Evansville', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(221, 'Midland', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(222, 'Santa Clara', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(223, 'Athens', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(224, 'Allentown', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(225, 'Abilene', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(226, 'Beaumont', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(227, 'Vallejo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(228, 'Independence', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(229, 'Springfield', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(230, 'Ann Arbor', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(231, 'Provo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(232, 'Peoria', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(233, 'Norman', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(234, 'Berkeley', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(235, 'El Monte', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(236, 'Murfreesboro', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(237, 'Lansing', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(238, 'Columbia', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(239, 'Downey', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(240, 'Costa Mesa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(241, 'Inglewood', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(242, 'Miami Gardens', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(243, 'Manchester', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(244, 'Elgin', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(245, 'Wilmington', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(246, 'Waterbury', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(247, 'Fargo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(248, 'Arvada', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(249, 'Carlsbad', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(250, 'Westminster', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(251, 'Rochester', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(252, 'Gresham', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(253, 'Clearwater', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(254, 'Lowell', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(255, 'West Jordan', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(256, 'Pueblo', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(257, 'San Buenaventura (Ventura)', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(258, 'Fairfield', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(259, 'West Covina', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(260, 'Billings', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(261, 'Murrieta', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(262, 'High Point', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(263, 'Round Rock', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(264, 'Richmond', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(265, 'Cambridge', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(266, 'Norwalk', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(267, 'Odessa', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(268, 'Antioch', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(269, 'Temecula', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(270, 'Green Bay', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(271, 'Everett', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(272, 'Wichita Falls', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(273, 'Burbank', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(274, 'Palm Bay', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(275, 'Centennial', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(276, 'Daly City', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(277, 'Richardson', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(278, 'Pompano Beach', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(279, 'Broken Arrow', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(280, 'North Charleston', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(281, 'West Palm Beach', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(282, 'Boulder', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(283, 'Rialto', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(284, 'Santa Maria', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(285, 'El Cajon', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(286, 'Davenport', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(287, 'Erie', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(288, 'Las Cruces', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(289, 'South Bend', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(290, 'Flint', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL),
(291, 'Kenosha', 0, '2018-01-03 00:00:00', '2018-01-03 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `starts_at` datetime NOT NULL,
  `ends_at` datetime NOT NULL,
  `is_private` tinyint(1) NOT NULL,
  `location_id` int(11) NOT NULL,
  `venue_id` int(11) DEFAULT NULL,
  `event_photo` text COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id` (`venue_id`,`created_by`),
  KEY `venue_id` (`venue_id`),
  KEY `location_id_2` (`location_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `name`, `starts_at`, `ends_at`, `is_private`, `location_id`, `venue_id`, `event_photo`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(3, 'Event 1', '2018-01-04 00:00:00', '2018-02-22 00:00:00', 0, 1, 1, '258919124626853.jpg', '2018-02-12 00:00:00', '2018-02-16 11:58:15', NULL, 1, 0),
(4, 'Event 2', '2018-01-03 00:00:00', '2018-02-28 00:00:00', 0, 1, 1, '258919124626853.jpg', '2018-02-10 00:00:00', '2018-02-12 00:00:00', NULL, 1, 0),
(5, 'Event 3', '2018-01-03 00:00:00', '2018-01-11 00:00:00', 0, 1, 1, '258919124626853.jpg', '2018-02-10 00:00:00', '2018-02-12 00:00:00', NULL, 1, 0),
(6, 'Event 4', '2018-01-03 00:00:00', '2018-02-21 00:00:00', 0, 1, 1, '258919124626853.jpg', '2018-02-10 00:00:00', '2018-02-12 00:00:00', NULL, 1, 0),
(7, 'Event 5', '2018-01-03 00:00:00', '2018-02-21 00:00:00', 0, 1, 1, '258919124626853.jpg', '2018-02-10 00:00:00', '2018-02-12 00:00:00', NULL, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `event_advertisers`
--

CREATE TABLE IF NOT EXISTS `event_advertisers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `advertiser_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`,`created_by`),
  KEY `event_id_2` (`event_id`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `event_advertisers`
--

INSERT INTO `event_advertisers` (`id`, `event_id`, `advertiser_id`, `created_at`, `updated_at`, `created_by`) VALUES
(7, 4, 11, NULL, NULL, NULL),
(8, 4, 10, NULL, NULL, NULL),
(12, 4, 15, NULL, NULL, NULL),
(13, 4, 24, NULL, NULL, NULL),
(14, 7, 4, NULL, NULL, NULL),
(16, 3, 3, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event_images`
--

CREATE TABLE IF NOT EXISTS `event_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `watermark_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `in_slideshow` tinyint(1) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `event_id` (`event_id`),
  KEY `watermark_id` (`watermark_id`),
  KEY `id` (`id`),
  KEY `event_id_2` (`event_id`),
  KEY `event_id_3` (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `event_images`
--

INSERT INTO `event_images` (`id`, `event_id`, `image`, `watermark_id`, `is_active`, `in_slideshow`, `created_at`, `updated_at`, `created_by`) VALUES
(4, 3, '258919124626853.jpg', NULL, 1, 0, '2018-02-15 00:00:00', '2018-02-15 00:00:00', NULL),
(5, 3, '258919124626853.jpg', NULL, 1, 0, '2018-02-15 00:00:00', '2018-02-15 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event_photographers`
--

CREATE TABLE IF NOT EXISTS `event_photographers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `photographer_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`,`photographer_id`,`created_by`),
  KEY `photographer_id` (`photographer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `event_photographers`
--

INSERT INTO `event_photographers` (`id`, `event_id`, `photographer_id`, `created_at`, `created_by`) VALUES
(2, 4, 1, NULL, NULL),
(6, 3, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event_watermarks`
--

CREATE TABLE IF NOT EXISTS `event_watermarks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `watermark_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`,`watermark_id`,`created_by`),
  KEY `watermark_id` (`watermark_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `event_watermarks`
--

INSERT INTO `event_watermarks` (`id`, `event_id`, `watermark_id`, `created_at`, `created_by`) VALUES
(6, 3, 1, NULL, NULL),
(7, 3, 3, NULL, NULL),
(8, 3, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `fonts`
--

CREATE TABLE IF NOT EXISTS `fonts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `identifier` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `gallery_ads`
--

CREATE TABLE IF NOT EXISTS `gallery_ads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) DEFAULT NULL,
  `logo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `background_image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `top_banner_expiry_date` date NOT NULL,
  `bottom_banner_expiry_date` date NOT NULL,
  `top_banner_rotation_settings` enum('STATIC','ROTATE') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ROTATE',
  `bottom_banner_rotation_settings` enum('ROTATE','STATIC') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ROTATE',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `gallery_ads_bottom_banner_image`
--

CREATE TABLE IF NOT EXISTS `gallery_ads_bottom_banner_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `gallery_ad_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_ad_id` (`gallery_ad_id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `gallery_ads_top_banner_images`
--

CREATE TABLE IF NOT EXISTS `gallery_ads_top_banner_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `gallery_ad_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_ad_id` (`gallery_ad_id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `gallery_quantity_price`
--

CREATE TABLE IF NOT EXISTS `gallery_quantity_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gallery_ad_id` int(11) NOT NULL,
  `ad_type` enum('BACKGROUND_IMAGE','TOP_AD_BANNER','BOTTOM_AD_BANNER') NOT NULL,
  `price` float(12,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `state_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `zip` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `location_logo` text COLLATE utf8_unicode_ci NOT NULL,
  `has_slideshow` tinyint(1) NOT NULL,
  `duration_speed` double NOT NULL,
  `break_time` double NOT NULL,
  `fade_in_time` double NOT NULL,
  `fade_out_time` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`state_id`,`created_by`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `name`, `address`, `state_id`, `city_id`, `zip`, `phone`, `location_logo`, `has_slideshow`, `duration_speed`, `break_time`, `fade_in_time`, `fade_out_time`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(1, 'Location 1', 'Address', 1, 1, '234', '234234', '258919124626853.jpg', 1, 1, 1, 1, 1, '2018-01-09 00:00:00', '2018-01-09 00:00:00', NULL, 1, 0),
(2, 'Loc2', 'sdf', 2, 1, '234', '234', '19136980333780.png', 1, 435, 345, 1, 2, '2018-01-09 16:07:52', '2018-01-09 16:07:52', NULL, 0, 1),
(3, 'fdsg', 'fdg', 1, 1, '435', 'fdsg', '19453247125865.png', 1, 4, 4, 1, 1, '2018-01-09 16:13:29', '2018-01-09 16:13:29', NULL, 1, 1),
(4, 'Loc 2', 'asd', 1, 1, 'sad', 'asd', '20550395407420.png', 1, 12, 21, 1, 1, '2018-01-09 16:31:33', '2018-01-09 16:31:33', NULL, 1, 1),
(5, 'New Location1', '1234', 2, 1, 'aa', 'dsfdferfhbdf', '258919124626853.jpg', 0, -1, -12, 3, 4, '2018-01-15 12:28:58', '2018-01-15 12:32:21', NULL, 1, 0),
(6, 'An', 'gbdfgfdf', 1, 1, 'fgdfgdfg', 'dfgdfgdfgfgdf', '258919124626853.jpg', 1, 3, 4, 0, 0, '2018-01-18 11:51:27', '2018-01-18 11:51:27', NULL, 1, 1),
(7, 'dasdas', 'asdasd', 2, 1, 'asdasd', 'adadsdad', '19136980333780.png', 1, 2, 2, 0, 0, '2018-01-18 11:52:13', '2018-01-18 11:52:13', NULL, 1, 1),
(8, 'wewewe', 'ewqewe', 3, 1, 'ewewqe', 'qwewe', '6382421638855.jpeg', 1, -12, -11, 3, 2, '2018-01-18 12:09:10', '2018-01-18 12:10:59', NULL, 1, 0),
(9, 'Location10', 'Address', 1, 1, '234', '234234', '258919124626853.jpg', 1, 1, 1, 1, 1, '2018-01-09 00:00:00', '2018-01-09 00:00:00', NULL, 1, 0),
(10, 'asdas', 'asdasd', 1, 1, 'a2112', '43423', '10656325134110.jpg', 1, 21, 213, 0, 0, '2018-02-14 19:02:56', '2018-02-14 19:02:56', NULL, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `location_advertisers`
--

CREATE TABLE IF NOT EXISTS `location_advertisers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) NOT NULL,
  `advertiser_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`location_id`,`created_by`),
  KEY `event_id_2` (`location_id`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=191 ;

--
-- Dumping data for table `location_advertisers`
--

INSERT INTO `location_advertisers` (`id`, `location_id`, `advertiser_id`, `created_at`, `updated_at`, `created_by`) VALUES
(167, 5, 11, NULL, NULL, NULL),
(168, 5, 10, NULL, NULL, NULL),
(186, 8, 12, NULL, NULL, NULL),
(187, 7, 24, NULL, NULL, NULL),
(189, 8, 19, NULL, NULL, NULL),
(190, 9, 4, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `location_bg_images`
--

CREATE TABLE IF NOT EXISTS `location_bg_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `location_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id` (`location_id`,`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `location_bg_images`
--

INSERT INTO `location_bg_images` (`id`, `image`, `location_id`, `created_at`, `created_by`) VALUES
(1, '19133284155369.png', 2, '2018-01-09 16:07:52', NULL),
(2, '19154584076989.png', 2, '2018-01-09 16:08:06', NULL),
(3, '19447201865067.png', 3, '2018-01-09 16:13:29', NULL),
(4, '19447326312104.png', 3, '2018-01-09 16:13:29', NULL),
(5, '19448373514769.png', 3, '2018-01-09 16:13:29', NULL),
(6, '20553620236102.png', 4, '2018-01-09 16:31:33', NULL),
(7, '4133700040871.jpg', 5, '2018-01-15 12:28:58', NULL),
(8, '6304605685343.jpg', 8, '2018-01-18 12:10:59', NULL),
(9, '10659042229049.jpg', 10, '2018-02-14 19:02:56', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `photographers`
--

CREATE TABLE IF NOT EXISTS `photographers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `profile_photo` text COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `photographers`
--

INSERT INTO `photographers` (`id`, `full_name`, `phone_number`, `user_name`, `email`, `password`, `profile_photo`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(1, 'dfg01', '4534545', 'sdf45', 'aniksarker17@gmail.comsdf777', '$2a$10$lAkUrx.BBPCmTeScqPSZBedYehSVRqLsJi8T0qNVsq9IGtUSxBnuG', '33899114831166.png', '2017-12-28 17:30:38', '2017-12-29 20:47:37', 132, 1, 0),
(2, 'sdf', '24234', 'sdfsdf', 'aniksarker17@gmail.com', '$2a$10$NRqk1LRH2ysSRdllVK3Br.hBXEJhsMvhUFaQDjQvDsp6ePUPnREFW', '29289743289968.png', '2017-12-28 19:11:49', '2017-12-28 19:11:49', 132, 1, 0),
(3, 'sdf', '24234', 'sdfsdf', 'aniksarker17@gmail.com1', '$2a$10$7KnWkdQzenvXNsEHMnfYi.NdIVKUCFowWkfahcOISwvXBuN6LiIYm', '29323396614167.png', '2017-12-28 19:12:42', '2017-12-28 19:12:42', 132, 1, 0),
(4, 'sdf', '24234', 'sdfsdf1', 'aniksarker17@gmail.com2', '$2a$10$yjxzAZSfliaw61YmrJtrQ.xrdT93ZB8Y9K15fLQHuYU6/TzrxoyOq', '29764752389342.png', '2017-12-28 19:19:56', '2017-12-28 19:19:56', 132, 1, 0),
(5, 'sdf', '24234', '1sdfsdf1', 'aniksarker173@gmail.com', '$2a$10$6643qYFmKcIlpHHDefNX.eSCsgBb3FXHGnQhvNr6wtycT9f17MVm.', '29903096081751.png', '2017-12-28 19:22:34', '2017-12-28 19:22:34', 132, 0, 1),
(6, 'DFG', '234', '234', '234@SDF.COM', '$2a$10$fckjfm/BBbuBIFVH3olDueSWNgLZQeK4aJ7sTIdmKR0M/wvxxlSlK', '32286774523112.png', '2017-12-28 20:01:45', '2017-12-28 20:01:45', 132, 1, 1),
(7, 'wer', '324234', 'wer', 'wer@sdf.com', '$2a$10$kmwNjErSoVHmL7OQAD6/RO98ZF1NfLlXkNBTcLsto1uJbu1OFlUnG', '', '2017-12-29 12:21:20', '2017-12-29 12:21:20', 132, 1, 0),
(8, 'dfg', '5345', 'sdf01', 'sadf@asd.com', '$2a$10$c/2JS7U49Cy4G3lSdkl23OU6YJODjh24m5vBTnS/iHDMme2vnZjRq', '4724595750028.png', '2017-12-29 12:41:32', '2017-12-29 12:41:32', 132, 1, 0),
(9, 'dfg', '5345', '01sdf01', 'sadf@asd.com01', '$2a$10$nA.BvI8N.owvrGJxpqLPy.vhc1YrNdwmh7Qr749x5ymEKk9aMDAKO', '', '2017-12-29 12:42:08', '2017-12-29 12:42:08', 132, 1, 1),
(10, 'asdfg345345', '3423', 'sdf012', 'asd@asdf.comqwe', '$2a$10$zzwAVYjQfa9fW3OMuk7WCOgmZHjP3dCKQ62O2TQ/LEdVIeLb.zVOG', '35697004643068.png', '2017-12-29 12:44:35', '2018-01-02 17:10:46', 132, 1, 0),
(11, 'Lala', '343', 'lala', 'lala@w', '$2a$10$6bhNYKPrfmaNHjKBUMkNl.DQtOWgLftjHGgAA8RZlSLK0rBP8804C', '88123709911611.png', '2018-01-10 11:18:37', '2018-01-10 11:18:37', 132, 0, 1),
(12, 'Shuvo', '11', 'shuvo', 'shuvo11@q', '$2a$10$Wk45sjPG8cd0Agsld5oB7ewt9/eHNggFoNEo.Qz6ncSkC9dtxRp4K', '97926921713467.jpg', '2018-01-10 11:28:47', '2018-01-10 14:01:00', 132, 1, 0),
(13, 'Baba Rafi', '420', '2131312', '43423423@dfgtsdf', '$2a$10$8cmOBL0FZuZOnndLdbGRM.v5M/aYJ.Sypqq0hxRTrw5VGjjhVEyd6', '', '2018-01-10 15:10:27', '2018-01-10 16:23:34', 132, 1, 1),
(14, 'New', '4', 'New1', 'ng!kjh@jkh.vom', '$2a$10$Z8XJ17qvbYVPBuRyrU5ckuIG8SiGopHv0whes5BKXKjXdqCPVkCIS', '5378074457433.png', '2018-01-15 12:49:47', '2018-01-17 18:29:24', 132, 1, 1),
(15, 'aaaa', '2233', 'saaaa', 'p@pmc.com', '$2a$10$CB454JLAnGhfH9pRQaSJHebfuik3TaCkaAgopXvTAPZfzhHvtmvFi', '6900805543438.png', '2018-01-17 18:33:58', '2018-01-18 12:19:41', 132, 1, 0),
(16, 'lalalala', '456684', 'lalala', 'lala@lala.lala', '$2a$10$N7FKjB0.DaOtxqvJIcp28e1wV/OnvvPBF0jyu9TS8HqtBbQcA3fGm', '6878053746830.jpg', '2018-01-18 12:14:04', '2018-01-18 12:19:14', 132, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `popup_ads`
--

CREATE TABLE IF NOT EXISTS `popup_ads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `type` enum('SMS','EMAIL') COLLATE utf8_unicode_ci NOT NULL,
  `duration` int(11) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `video` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `video_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ad_rotate` enum('ROTATE','STATIC') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ROTATE',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `popup_ad_quantity_price`
--

CREATE TABLE IF NOT EXISTS `popup_ad_quantity_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `popup_ad_id` int(11) NOT NULL,
  `ad_type` enum('SMS','EMAIL') NOT NULL,
  `price` float(5,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `popup_banner_images`
--

CREATE TABLE IF NOT EXISTS `popup_banner_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `popup_ad_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `popup_ad_id` (`popup_ad_id`,`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE IF NOT EXISTS `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertisement_id` int(11) DEFAULT NULL,
  `price` float(12,2) DEFAULT NULL,
  `quantity` int(5) DEFAULT NULL,
  `duration` int(5) DEFAULT NULL,
  `rotation` enum('STATIC','ROTATE') DEFAULT NULL,
  `section_type` enum('LOGO','BACKGROUND','BANNER','TOP_BANNER','BOTTOM_BANNER','VIDEO') NOT NULL,
  `expire_date` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `advertisement_id` (`advertisement_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`id`, `advertisement_id`, `price`, `quantity`, `duration`, `rotation`, `section_type`, `expire_date`, `created_by`, `created_at`) VALUES
(1, 1, NULL, 1, NULL, 'STATIC', 'LOGO', NULL, 132, '2018-02-02 17:12:39'),
(2, 1, 50.00, 1, NULL, 'STATIC', 'BACKGROUND', NULL, 132, '2018-02-02 17:12:39'),
(3, 1, 102.00, 2, NULL, 'ROTATE', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(4, 1, 110.00, 2, NULL, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(5, 2, 43.00, 1, 1, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(6, 2, 4.00, 1, 1, 'ROTATE', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(7, 4, 2.00, 2, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(8, 3, 3.00, 2, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:12:39'),
(9, 5, NULL, 1, NULL, 'STATIC', 'LOGO', NULL, 132, '2018-02-02 17:16:58'),
(10, 5, 50.00, 1, NULL, 'STATIC', 'BACKGROUND', NULL, 132, '2018-02-02 17:16:58'),
(11, 5, 102.00, 2, NULL, 'ROTATE', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(12, 5, 110.00, 1, NULL, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(13, 6, 43.00, 1, 1, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(14, 6, 4.00, 1, 1, 'ROTATE', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(15, 8, 2.00, 1, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(16, 7, 3.00, 1, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:16:58'),
(17, 9, NULL, 1, NULL, 'STATIC', 'LOGO', NULL, 132, '2018-02-02 17:26:53'),
(18, 9, 50.00, 1, NULL, 'STATIC', 'BACKGROUND', NULL, 132, '2018-02-02 17:26:53'),
(19, 9, 102.00, 1, NULL, 'ROTATE', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(20, 9, 110.00, 1, NULL, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(21, 10, 43.00, 1, 1, 'ROTATE', 'BOTTOM_BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(22, 10, 4.00, 1, 1, 'STATIC', 'TOP_BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(23, 12, 2.00, 1, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(24, 11, 3.00, 1, 1, 'ROTATE', 'BANNER', '2018-02-02', 132, '2018-02-02 17:26:53'),
(25, 13, NULL, 0, NULL, 'STATIC', 'LOGO', NULL, 132, '2018-02-14 17:17:32'),
(26, 13, 50.00, 0, NULL, 'STATIC', 'BACKGROUND', NULL, 132, '2018-02-14 17:17:32'),
(27, 13, 100.00, 0, NULL, 'ROTATE', 'TOP_BANNER', NULL, 132, '2018-02-14 17:17:32'),
(28, 13, 110.00, 0, NULL, 'ROTATE', 'BOTTOM_BANNER', NULL, 132, '2018-02-14 17:17:32'),
(29, 14, 43.00, 0, 1, 'ROTATE', 'BOTTOM_BANNER', NULL, 132, '2018-02-14 17:17:33'),
(30, 14, 4.00, 0, 1, 'ROTATE', 'TOP_BANNER', NULL, 132, '2018-02-14 17:17:33'),
(31, 16, 2.00, 0, 1, 'ROTATE', 'BANNER', NULL, 132, '2018-02-14 17:17:33'),
(32, 15, 3.00, 0, 1, 'ROTATE', 'BANNER', NULL, 132, '2018-02-14 17:17:33');

-- --------------------------------------------------------

--
-- Table structure for table `sec_resource`
--

CREATE TABLE IF NOT EXISTS `sec_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL,
  `file_name` varchar(500) DEFAULT NULL,
  `file_type` enum('IMAGE','VIDEO') DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `section_id` (`section_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `sec_resource`
--

INSERT INTO `sec_resource` (`id`, `section_id`, `file_name`, `file_type`, `mime_type`, `created_at`) VALUES
(1, 1, '4297790964727.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(2, 2, '4305225358635.png', 'IMAGE', 'image/png', '2018-02-02 17:12:39'),
(3, 3, '4299977631983.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(4, 3, '4302033016132.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(5, 4, '4310209485304.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(6, 4, '4313957957271.png', 'IMAGE', 'image/png', '2018-02-02 17:12:39'),
(7, 5, '4336228408780.mp4', 'VIDEO', 'video/mp4', '2018-02-02 17:12:39'),
(8, 6, '4320675442509.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(9, 7, '4360965812706.png', 'IMAGE', 'image/png', '2018-02-02 17:12:39'),
(10, 7, '4364606792629.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:12:39'),
(11, 8, '4347466356758.png', 'IMAGE', 'image/png', '2018-02-02 17:12:39'),
(12, 8, '4351155702004.png', 'IMAGE', 'image/png', '2018-02-02 17:12:39'),
(13, 9, '4497126976167.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:16:58'),
(14, 10, '4511606936835.png', 'IMAGE', 'image/png', '2018-02-02 17:16:58'),
(15, 11, '4517416817097.png', 'IMAGE', 'image/png', '2018-02-02 17:16:58'),
(16, 11, '4521720035825.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:16:58'),
(17, 12, '4583184400337.mp4', 'IMAGE', 'video/mp4', '2018-02-02 17:16:58'),
(18, 13, '4609384231640.mp4', 'VIDEO', 'video/mp4', '2018-02-02 17:16:58'),
(19, 14, '4602051720365.png', 'IMAGE', 'image/png', '2018-02-02 17:16:58'),
(20, 15, '4624240352267.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:16:58'),
(21, 16, '4618979646364.mp4', 'VIDEO', 'video/mp4', '2018-02-02 17:16:58'),
(22, 17, '5154638141240.png', 'IMAGE', 'image/png', '2018-02-02 17:26:53'),
(23, 18, '5173576428837.jpg', 'IMAGE', 'image/jpeg', '2018-02-02 17:26:53'),
(24, 19, '5179245465459.png', 'IMAGE', 'image/png', '2018-02-02 17:26:53'),
(25, 20, '5187959976927.png', 'IMAGE', 'image/png', '2018-02-02 17:26:53'),
(26, 21, '5207882166455.mp4', 'VIDEO', 'video/mp4', '2018-02-02 17:26:53'),
(27, 22, '5195252418553.png', 'IMAGE', 'image/png', '2018-02-02 17:26:53'),
(28, 23, '5219861186047.mp4', 'VIDEO', 'video/mp4', '2018-02-02 17:26:53'),
(29, 24, '5212934150649.png', 'IMAGE', 'image/png', '2018-02-02 17:26:53');

-- --------------------------------------------------------

--
-- Table structure for table `sent_slideshows`
--

CREATE TABLE IF NOT EXISTS `sent_slideshows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `type` enum('sms','email') COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `sent_by` int(11) NOT NULL,
  `is_seen` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sent_by` (`sent_by`),
  KEY `sent_by_2` (`sent_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `sent_slideshow_images`
--

CREATE TABLE IF NOT EXISTS `sent_slideshow_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_image_id` int(11) NOT NULL,
  `sent_slideshow_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_image_id` (`event_image_id`),
  KEY `sent_slideshow_id` (`sent_slideshow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `slideshow_ads`
--

CREATE TABLE IF NOT EXISTS `slideshow_ads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `video` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `video_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_duration` int(11) NOT NULL,
  `banner_duration` int(11) NOT NULL,
  `video_expiry_date` datetime DEFAULT NULL,
  `banner_expiry_date` datetime DEFAULT NULL,
  `banner_rotate` enum('ROTATE','STATIC') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ROTATE',
  `video_rotate` enum('ROTATE','STATIC') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ROTATE',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `slideshow_ad_quantity_price`
--

CREATE TABLE IF NOT EXISTS `slideshow_ad_quantity_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `slideshow_ad_id` int(11) NOT NULL,
  `ad_type` enum('BANNER','VIDEO') NOT NULL,
  `price` float(5,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `slideshow_banner_images`
--

CREATE TABLE IF NOT EXISTS `slideshow_banner_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `slideshow_ad_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `slideshow_id` (`slideshow_ad_id`,`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE IF NOT EXISTS `states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=53 ;

--
-- Dumping data for table `states`
--

INSERT INTO `states` (`id`, `name`, `code`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Alaska', 'AK', NULL, NULL, NULL),
(2, 'Arkansas', 'AR', NULL, NULL, NULL),
(3, 'Arizona', 'AZ', NULL, NULL, NULL),
(5, 'California', 'CA', NULL, NULL, NULL),
(6, 'Colorado', 'CO', NULL, NULL, NULL),
(7, 'Connecticut', 'CT', NULL, NULL, NULL),
(8, 'Delaware', 'DE', NULL, NULL, NULL),
(9, 'District of Columbia', 'DC', NULL, NULL, NULL),
(10, 'Florida', 'FL', NULL, NULL, NULL),
(11, 'Georgia', 'GA', NULL, NULL, NULL),
(12, 'Hawaii', 'HI', NULL, NULL, NULL),
(13, 'Idaho', 'ID', NULL, NULL, NULL),
(14, 'Illinois', 'IL', NULL, NULL, NULL),
(15, 'Indiana', 'IN', NULL, NULL, NULL),
(16, 'Iowa', 'IA', NULL, NULL, NULL),
(17, 'Kansas', 'KS', NULL, NULL, NULL),
(18, 'Kentucky', 'KY', NULL, NULL, NULL),
(19, 'Louisiana', 'LA', NULL, NULL, NULL),
(20, 'Maine', 'ME', NULL, NULL, NULL),
(21, 'Montana', 'MT', NULL, NULL, NULL),
(22, 'Nebraska', 'NE', NULL, NULL, NULL),
(23, 'Nevada', 'NV', NULL, NULL, NULL),
(24, 'New Hampshire', 'NH', NULL, NULL, NULL),
(25, 'New Jersey', 'NJ', NULL, NULL, NULL),
(26, 'New Mexico', 'NM', NULL, NULL, NULL),
(27, 'New York', 'NY', NULL, NULL, NULL),
(28, 'North Carolina', 'NC', NULL, NULL, NULL),
(29, 'North Dakota', 'ND', NULL, NULL, NULL),
(30, 'Ohio', 'OH', NULL, NULL, NULL),
(31, 'Oklahoma', 'OK', NULL, NULL, NULL),
(32, 'Oregon', 'OR', NULL, NULL, NULL),
(33, 'Maryland', 'MD', NULL, NULL, NULL),
(34, 'Massachusetts', 'MA', NULL, NULL, NULL),
(35, 'Michigan', 'MI', NULL, NULL, NULL),
(36, 'Minnesota', 'MN', NULL, NULL, NULL),
(37, 'Mississippi', 'MS', NULL, NULL, NULL),
(38, 'Missouri', 'MO', NULL, NULL, NULL),
(39, 'Pennsylvania', 'PA', NULL, NULL, NULL),
(40, 'Rhode Island', 'RI', NULL, NULL, NULL),
(41, 'South Carolina', 'SC', NULL, NULL, NULL),
(42, 'South Dakota', 'SD', NULL, NULL, NULL),
(43, 'Tennessee', 'TN', NULL, NULL, NULL),
(44, 'Texas', 'TX', NULL, NULL, NULL),
(45, 'Utah', 'UT', NULL, NULL, NULL),
(46, 'Vermont', 'VT', NULL, NULL, NULL),
(47, 'Virginia', 'VA', NULL, NULL, NULL),
(48, 'Washington', 'WA', NULL, NULL, NULL),
(49, 'West Virginia', 'WV', NULL, NULL, NULL),
(50, 'Wisconsin', 'WI', NULL, NULL, NULL),
(51, 'Wyoming', 'WY', NULL, NULL, NULL),
(52, 'Alabama', 'AL', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `temp_file`
--

CREATE TABLE IF NOT EXISTS `temp_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` int(11) NOT NULL,
  `path` text NOT NULL,
  `file_name` varchar(500) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1542 ;

--
-- Dumping data for table `temp_file`
--

INSERT INTO `temp_file` (`id`, `token`, `path`, `file_name`, `created_date`) VALUES
(1, 1000412943, '/home/mi_rafi/project_file/pmc/11071508148606.png', '', '2017-12-28 08:08:05'),
(2, 1000399495, '/home/mi_rafi/project_file/pmc/11238577696024.png', '', '2017-12-28 08:10:52'),
(3, 1000354089, '/home/mi_rafi/project_file/pmc/11254000526726.png', '', '2017-12-28 08:11:08'),
(4, 1000891211, '/home/mi_rafi/project_file/pmc/12131260742429.png', '', '2017-12-28 08:25:45'),
(9, 1000522733, '/home/mi_rafi/project_file/pmc/21970226575765.png', '', '2017-12-28 11:09:44'),
(10, 1000772298, '/home/mi_rafi/project_file/pmc/22034118767452.png', '', '2017-12-28 11:10:48'),
(15, 1000844747, '/home/mi_rafi/project_file/pmc/tmp/32079328240090.png', '', '2017-12-28 13:58:13'),
(16, 1000642087, '/home/mi_rafi/project_file/pmc/tmp/32220480904143.png', '', '2017-12-28 14:00:34'),
(19, 1000625629, '/home/mi_rafi/project_file/pmc/tmp/21816342957768.png', '', '2017-12-29 11:26:14'),
(20, 1000133036, '/home/mi_rafi/project_file/pmc/tmp/21995222139245.png', '', '2017-12-29 11:29:13'),
(21, 1000678673, '/home/mi_rafi/project_file/pmc/tmp/28531865175492.png', '', '2017-12-29 13:18:09'),
(23, 1000790872, '/home/mi_rafi/project_file/pmc/tmp/30788788110459.png', '', '2017-12-29 13:55:46'),
(24, 1000358479, '/home/mi_rafi/project_file/pmc/tmp/31135081789775.png', '', '2017-12-29 14:01:32'),
(25, 1000045356, '/home/mi_rafi/project_file/pmc/tmp/31172647569070.png', '', '2017-12-29 14:02:10'),
(26, 1000174658, '/home/mi_rafi/project_file/pmc/tmp/31300993313666.png', '', '2017-12-29 14:04:18'),
(27, 1000311004, '/home/mi_rafi/project_file/pmc/tmp/31426908333334.png', '', '2017-12-29 14:06:24'),
(28, 1000524522, '/home/mi_rafi/project_file/pmc/tmp/31461429675915.png', '', '2017-12-29 14:06:59'),
(29, 1000569026, '/home/mi_rafi/project_file/pmc/tmp/31503944914264.png', '', '2017-12-29 14:07:41'),
(30, 1000068748, '/home/mi_rafi/project_file/pmc/tmp/31694210755020.png', '', '2017-12-29 14:10:52'),
(37, 1000848612, '/home/mi_rafi/project_file/pmc/tmp/32074662139458.png', '', '2017-12-29 14:17:12'),
(39, 1000756521, '/home/mi_rafi/project_file/pmc/tmp/32190976279773.png', '', '2017-12-29 14:19:08'),
(50, 1000144332, '/home/mi_rafi/project_file/pmc/tmp/32591991685882.png', '', '2017-12-29 14:25:49'),
(53, 1000706743, '/home/mi_rafi/project_file/pmc/tmp/32618737795658.png', '', '2017-12-29 14:26:16'),
(54, 1000263490, '/home/mi_rafi/project_file/pmc/tmp/32701368819298.png', '', '2017-12-29 14:27:39'),
(63, 1000206405, '/home/mi_rafi/project_file/pmc/tmp/33228104849209.png', '', '2017-12-29 14:36:25'),
(64, 1000098729, '/home/mi_rafi/project_file/pmc/tmp/33522958784551.png', '', '2017-12-29 14:41:20'),
(68, 1000035595, '/home/mi_rafi/project_file/pmc/tmp/33655759513396.png', '', '2017-12-29 14:43:33'),
(73, 1000839278, '/home/mi_rafi/project_file/pmc/tmp/33818717156786.png', '', '2017-12-29 14:46:16'),
(74, 1000611190, '/home/mi_rafi/project_file/pmc/tmp/33825596481177.png', '', '2017-12-29 14:46:23'),
(75, 1000232025, '/home/mi_rafi/project_file/pmc/tmp/4314777245447.png', '', '2018-01-02 07:40:39'),
(76, 1000629256, '/home/mi_rafi/project_file/pmc/tmp/4639644181695.png', '', '2018-01-02 07:46:04'),
(77, 1000794920, '/home/mi_rafi/project_file/pmc/tmp/5017382054220.png', '', '2018-01-02 07:52:22'),
(78, 1000540089, '/home/mi_rafi/project_file/pmc/tmp/5039169129321.png', '', '2018-01-02 07:52:44'),
(79, 1000227555, '/home/mi_rafi/project_file/pmc/tmp/5264380718658.png', '', '2018-01-02 07:56:29'),
(80, 1000422860, '/home/mi_rafi/project_file/pmc/tmp/5267049047836.png', '', '2018-01-02 07:56:31'),
(81, 1000811541, '/home/mi_rafi/project_file/pmc/tmp/5540630043274.png', '', '2018-01-02 08:01:05'),
(82, 1000218666, '/home/mi_rafi/project_file/pmc/tmp/5547365398952.png', '', '2018-01-02 08:01:12'),
(83, 1000515015, '/home/mi_rafi/project_file/pmc/tmp/5549847970279.png', '', '2018-01-02 08:01:14'),
(84, 1000473989, '/home/mi_rafi/project_file/pmc/tmp/5552091947838.png', '', '2018-01-02 08:01:16'),
(85, 1000319276, '/home/mi_rafi/project_file/pmc/tmp/6655029175032.png', '', '2018-01-02 08:19:39'),
(86, 1000192948, '/home/mi_rafi/project_file/pmc/tmp/6662280560530.png', '', '2018-01-02 08:19:47'),
(87, 1000078923, '/home/mi_rafi/project_file/pmc/tmp/6662337269184.png', '', '2018-01-02 08:19:47'),
(88, 1000611451, '/home/mi_rafi/project_file/pmc/tmp/6662449214580.png', '', '2018-01-02 08:19:47'),
(89, 1000410860, '/home/mi_rafi/project_file/pmc/tmp/6662506005222.png', '', '2018-01-02 08:19:47'),
(90, 1000632519, '/home/mi_rafi/project_file/pmc/tmp/6683976447373.png', '', '2018-01-02 08:20:08'),
(91, 1000869804, '/home/mi_rafi/project_file/pmc/tmp/6691181189491.png', '', '2018-01-02 08:20:16'),
(92, 1000653458, '/home/mi_rafi/project_file/pmc/tmp/6698999176480.png', '', '2018-01-02 08:20:23'),
(93, 1000536368, '/home/mi_rafi/project_file/pmc/tmp/6699002124816.png', '', '2018-01-02 08:20:23'),
(94, 1000200042, '/home/mi_rafi/project_file/pmc/tmp/6764739793879.png', '', '2018-01-02 08:21:29'),
(95, 1000453177, '/home/mi_rafi/project_file/pmc/tmp/6764748161941.png', '', '2018-01-02 08:21:29'),
(96, 1000316691, '/home/mi_rafi/project_file/pmc/tmp/6764899435488.png', '', '2018-01-02 08:21:29'),
(97, 1000446670, '/home/mi_rafi/project_file/pmc/tmp/6764974211987.png', '', '2018-01-02 08:21:29'),
(98, 1000826337, '/home/mi_rafi/project_file/pmc/tmp/12551728515279.png', '', '2018-01-02 09:57:56'),
(99, 1000678934, '/home/mi_rafi/project_file/pmc/tmp/12557370888436.png', '', '2018-01-02 09:58:02'),
(100, 1000433335, '/home/mi_rafi/project_file/pmc/tmp/12562798100403.png', '', '2018-01-02 09:58:07'),
(101, 1000712624, '/home/mi_rafi/project_file/pmc/tmp/12565950559678.png', '', '2018-01-02 09:58:10'),
(102, 1000251835, '/home/mi_rafi/project_file/pmc/tmp/12759173226394.png', '', '2018-01-02 10:01:24'),
(103, 1000838354, '/home/mi_rafi/project_file/pmc/tmp/12767807593504.png', '', '2018-01-02 10:01:32'),
(104, 1000091021, '/home/mi_rafi/project_file/pmc/tmp/12767872984141.png', '', '2018-01-02 10:01:32'),
(105, 1000285967, '/home/mi_rafi/project_file/pmc/tmp/13158860147053.png', '', '2018-01-02 10:08:03'),
(106, 1000874287, '/home/mi_rafi/project_file/pmc/tmp/13165842145899.png', '', '2018-01-02 10:08:10'),
(107, 1000597330, '/home/mi_rafi/project_file/pmc/tmp/13165869127235.png', '', '2018-01-02 10:08:10'),
(108, 1000786072, '/home/mi_rafi/project_file/pmc/tmp/13639973818059.png', '', '2018-01-02 10:16:04'),
(109, 1000035220, '/home/mi_rafi/project_file/pmc/tmp/13639970223524.png', '', '2018-01-02 10:16:04'),
(110, 1000098900, '/home/mi_rafi/project_file/pmc/tmp/13640411164481.png', '', '2018-01-02 10:16:05'),
(111, 1000707329, '/home/mi_rafi/project_file/pmc/tmp/13701288155230.png', '', '2018-01-02 10:17:06'),
(112, 1000804069, '/home/mi_rafi/project_file/pmc/tmp/13851494856125.png', '', '2018-01-02 10:19:36'),
(113, 1000366926, '/home/mi_rafi/project_file/pmc/tmp/13866221816924.png', '', '2018-01-02 10:19:51'),
(114, 1000860111, '/home/mi_rafi/project_file/pmc/tmp/13866286858337.png', '', '2018-01-02 10:19:51'),
(115, 1000502338, '/home/mi_rafi/project_file/pmc/tmp/13866373671042.png', '', '2018-01-02 10:19:51'),
(116, 1000429718, '/home/mi_rafi/project_file/pmc/tmp/13866455375707.png', '', '2018-01-02 10:19:51'),
(117, 1000352985, '/home/mi_rafi/project_file/pmc/tmp/14052851944749.png', '', '2018-01-02 10:22:57'),
(118, 1000251900, '/home/mi_rafi/project_file/pmc/tmp/14060099069871.png', '', '2018-01-02 10:23:04'),
(119, 1000181745, '/home/mi_rafi/project_file/pmc/tmp/14060115359784.png', '', '2018-01-02 10:23:05'),
(120, 1000727904, '/home/mi_rafi/project_file/pmc/tmp/14060279614898.png', '', '2018-01-02 10:23:05'),
(121, 1000776555, '/home/mi_rafi/project_file/pmc/tmp/14060301000447.png', '', '2018-01-02 10:23:05'),
(122, 1000743547, '/home/mi_rafi/project_file/pmc/tmp/14134278495081.png', '', '2018-01-02 10:24:19'),
(123, 1000692021, '/home/mi_rafi/project_file/pmc/tmp/14138545148075.png', '', '2018-01-02 10:24:23'),
(124, 1000154560, '/home/mi_rafi/project_file/pmc/tmp/14138567791282.png', '', '2018-01-02 10:24:23'),
(125, 1000344798, '/home/mi_rafi/project_file/pmc/tmp/14138680721825.png', '', '2018-01-02 10:24:23'),
(126, 1000530904, '/home/mi_rafi/project_file/pmc/tmp/15035575890036.png', '', '2018-01-02 10:39:20'),
(127, 1000257792, '/home/mi_rafi/project_file/pmc/tmp/15341989005398.png', '', '2018-01-02 10:44:26'),
(130, 1000524616, '/home/mi_rafi/project_file/pmc/tmp/20446991836987.jpg', '', '2018-01-02 12:09:31'),
(131, 1000539362, '/home/mi_rafi/project_file/pmc/tmp/20454419288728.png', '', '2018-01-02 12:09:39'),
(132, 1000060459, '/home/mi_rafi/project_file/pmc/tmp/20454426489488.png', '', '2018-01-02 12:09:39'),
(133, 1000744506, '/home/mi_rafi/project_file/pmc/tmp/20454557869030.png', '', '2018-01-02 12:09:39'),
(134, 1000828784, '/home/mi_rafi/project_file/pmc/tmp/20454599841669.png', '', '2018-01-02 12:09:39'),
(135, 1000250399, '/home/mi_rafi/project_file/pmc/tmp/20454688466422.png', '', '2018-01-02 12:09:39'),
(136, 1000279007, '/home/mi_rafi/project_file/pmc/tmp/20960013917436.jpg', '', '2018-01-02 12:18:04'),
(138, 1000287261, '/home/mi_rafi/project_file/pmc/tmp/29509776336830.jpg', '', '2018-01-02 14:40:34'),
(139, 1000310001, '/home/mi_rafi/project_file/pmc/tmp/29523316176027.png', '', '2018-01-02 14:40:48'),
(140, 1000733544, '/home/mi_rafi/project_file/pmc/tmp/29523349415554.png', '', '2018-01-02 14:40:48'),
(141, 1000368197, '/home/mi_rafi/project_file/pmc/tmp/29523467252186.png', '', '2018-01-02 14:40:48'),
(142, 1000355089, '/home/mi_rafi/project_file/pmc/tmp/34290499905865.jpg', '', '2018-01-02 16:00:15'),
(143, 1000837926, '/home/mi_rafi/project_file/pmc/tmp/34298793638497.png', '', '2018-01-02 16:00:23'),
(144, 1000700768, '/home/mi_rafi/project_file/pmc/tmp/34298807207077.png', '', '2018-01-02 16:00:23'),
(145, 1000489750, '/home/mi_rafi/project_file/pmc/tmp/34299032741232.png', '', '2018-01-02 16:00:23'),
(146, 1000738671, '/home/mi_rafi/project_file/pmc/tmp/34299218900923.png', '', '2018-01-02 16:00:24'),
(147, 1000011000, '/home/mi_rafi/project_file/pmc/tmp/98948831526939.png', '', '2018-01-03 09:57:53'),
(148, 1000790884, '/home/mi_rafi/project_file/pmc/tmp/98974713056925.png', '', '2018-01-03 09:58:19'),
(149, 1000499000, '/home/mi_rafi/project_file/pmc/tmp/99520962799802.png', '', '2018-01-03 10:07:25'),
(150, 1000738624, '/home/mi_rafi/project_file/pmc/tmp/99539212743607.png', '', '2018-01-03 10:07:44'),
(151, 1000117164, '/home/mi_rafi/project_file/pmc/tmp/99587924720066.png', '', '2018-01-03 10:08:32'),
(152, 1000337991, '/home/mi_rafi/project_file/pmc/tmp/99656764717164.png', '', '2018-01-03 10:09:41'),
(153, 1000355860, '/home/mi_rafi/project_file/pmc/tmp/99661184998867.png', '', '2018-01-03 10:09:46'),
(154, 1000820095, '/home/mi_rafi/project_file/pmc/tmp/99669557370828.png', '', '2018-01-03 10:09:54'),
(155, 1000570734, '/home/mi_rafi/project_file/pmc/tmp/99672092254462.jpg', '', '2018-01-03 10:09:56'),
(156, 1000065271, '/home/mi_rafi/project_file/pmc/tmp/105977454504979.jpg', '', '2018-01-03 11:55:02'),
(157, 1000570626, '/home/mi_rafi/project_file/pmc/tmp/105986355945201.png', '', '2018-01-03 11:55:11'),
(158, 1000044396, '/home/mi_rafi/project_file/pmc/tmp/105986374990304.png', '', '2018-01-03 11:55:11'),
(159, 1000179024, '/home/mi_rafi/project_file/pmc/tmp/107127822805245.png', '', '2018-01-03 12:14:12'),
(160, 1000265876, '/home/mi_rafi/project_file/pmc/tmp/109576454635983.png', '', '2018-01-03 12:55:01'),
(161, 1000717604, '/home/mi_rafi/project_file/pmc/tmp/109596060107824.jpg', '', '2018-01-03 12:55:21'),
(162, 1000880521, '/home/mi_rafi/project_file/pmc/tmp/3587792995240.mp4', '', '2018-01-04 07:16:23'),
(163, 1000625375, '/home/mi_rafi/project_file/pmc/tmp/6162465794711.mp4', '', '2018-01-04 07:59:18'),
(164, 1000639792, '/home/mi_rafi/project_file/pmc/tmp/11269368608786.png', '', '2018-01-04 09:24:25'),
(166, 1000292685, '/home/mi_rafi/project_file/pmc/tmp/11773149023653.png', '', '2018-01-04 09:32:49'),
(167, 1000329175, '/home/mi_rafi/project_file/pmc/tmp/15345148055381.png', '', '2018-01-04 10:32:21'),
(168, 1000853418, '/home/mi_rafi/project_file/pmc/tmp/15350232626173.png', '', '2018-01-04 10:32:26'),
(169, 1000178091, '/home/mi_rafi/project_file/pmc/tmp/15405359315665.png', '', '2018-01-04 10:33:21'),
(170, 1000813454, '/home/mi_rafi/project_file/pmc/tmp/6448766242856.png', '', '2018-01-05 08:28:25'),
(171, 1000658069, '/home/mi_rafi/project_file/pmc/tmp/6553788881329.png', '', '2018-01-05 08:30:09'),
(172, 1000595495, '/home/mi_rafi/project_file/pmc/tmp/6627512737460.png', '', '2018-01-05 08:31:23'),
(173, 1000602858, '/home/mi_rafi/project_file/pmc/tmp/6656344620377.png', '', '2018-01-05 08:31:52'),
(174, 1000277147, '/home/mi_rafi/project_file/pmc/tmp/6663836474223.png', '', '2018-01-05 08:31:59'),
(175, 1000506450, '/home/mi_rafi/project_file/pmc/tmp/6669699632550.png', '', '2018-01-05 08:32:05'),
(176, 1000708778, '/home/mi_rafi/project_file/pmc/tmp/12245630667660.png', '', '2018-01-05 10:05:02'),
(177, 1000376488, '/home/mi_rafi/project_file/pmc/tmp/12252403966763.png', '', '2018-01-05 10:05:08'),
(178, 1000479675, '/home/mi_rafi/project_file/pmc/tmp/12259205170105.png', '', '2018-01-05 10:05:15'),
(179, 1000074044, '/home/mi_rafi/project_file/pmc/tmp/12263755751266.png', '', '2018-01-05 10:05:19'),
(180, 1000224214, '/home/mi_rafi/project_file/pmc/tmp/12986135410773.png', '', '2018-01-05 10:17:22'),
(181, 1000252097, '/home/mi_rafi/project_file/pmc/tmp/12989858994827.png', '', '2018-01-05 10:17:26'),
(182, 1000175918, '/home/mi_rafi/project_file/pmc/tmp/12996407670050.png', '', '2018-01-05 10:17:32'),
(183, 1000658927, '/home/mi_rafi/project_file/pmc/tmp/12999043310274.png', '', '2018-01-05 10:17:35'),
(184, 1000834786, '/home/mi_rafi/project_file/pmc/tmp/13100266451365.png', '', '2018-01-05 10:19:16'),
(185, 1000856514, '/home/mi_rafi/project_file/pmc/tmp/13102263130093.png', '', '2018-01-05 10:19:18'),
(186, 1000847618, '/home/mi_rafi/project_file/pmc/tmp/13104869897012.png', '', '2018-01-05 10:19:21'),
(187, 1000775225, '/home/mi_rafi/project_file/pmc/tmp/13107856553126.png', '', '2018-01-05 10:19:24'),
(188, 1000149828, '/home/mi_rafi/project_file/pmc/tmp/14926788524408.png', '', '2018-01-05 10:49:42'),
(189, 1000134175, '/home/mi_rafi/project_file/pmc/tmp/14983766925546.png', '', '2018-01-05 10:50:39'),
(190, 1000087950, '/home/mi_rafi/project_file/pmc/tmp/15069279707111.png', '', '2018-01-05 10:52:05'),
(191, 1000085597, '/home/mi_rafi/project_file/pmc/tmp/15242393491503.mp4', '', '2018-01-05 10:54:58'),
(192, 1000473177, '/home/mi_rafi/project_file/pmc/tmp/15423597015769.mp4', '', '2018-01-05 10:57:59'),
(196, 1000729139, '/home/mi_rafi/project_file/pmc/tmp/16403299882643.mp4', '', '2018-01-05 11:14:19'),
(199, 1000196997, '/home/mi_rafi/project_file/pmc/tmp/16553087430744.png', '', '2018-01-05 11:16:49'),
(200, 1000283119, '/home/mi_rafi/project_file/pmc/tmp/19472762631349.png', '', '2018-01-05 12:05:28'),
(201, 1000837458, '/home/mi_rafi/project_file/pmc/tmp/19550720122840.png', '', '2018-01-05 12:06:46'),
(202, 1000748772, '/home/mi_rafi/project_file/pmc/tmp/19556111363649.png', '', '2018-01-05 12:06:52'),
(203, 1000505859, '/home/mi_rafi/project_file/pmc/tmp/19565342100027.mp4', '', '2018-01-05 12:07:01'),
(204, 1000357310, '/home/mi_rafi/project_file/pmc/tmp/19570451251698.mp4', '', '2018-01-05 12:07:06'),
(205, 1000598210, '/home/mi_rafi/project_file/pmc/tmp/24370593441840.png', '', '2018-01-05 13:27:06'),
(206, 1000576733, '/home/mi_rafi/project_file/pmc/tmp/24370606401030.png', '', '2018-01-05 13:27:06'),
(207, 1000752351, '/home/mi_rafi/project_file/pmc/tmp/24370997295491.png', '', '2018-01-05 13:27:07'),
(208, 1000622910, '/home/mi_rafi/project_file/pmc/tmp/25797591509870.png', '', '2018-01-05 13:50:53'),
(210, 1000781804, '/home/mi_rafi/project_file/pmc/tmp/25804678808643.png', '', '2018-01-05 13:51:00'),
(211, 1000763450, '/home/mi_rafi/project_file/pmc/tmp/25807616472171.png', '', '2018-01-05 13:51:03'),
(212, 1000892991, '/home/mi_rafi/project_file/pmc/tmp/25810968364153.png', '', '2018-01-05 13:51:07'),
(213, 1000410404, '/home/mi_rafi/project_file/pmc/tmp/25813732537533.png', '', '2018-01-05 13:51:09'),
(214, 1000250955, '/home/mi_rafi/project_file/pmc/tmp/25823987910644.png', '', '2018-01-05 13:51:20'),
(215, 1000144277, '/home/mi_rafi/project_file/pmc/tmp/10190916879080.jpg', '', '2018-01-08 08:12:09'),
(216, 1000790102, '/home/mi_rafi/project_file/pmc/tmp/10193196584102.jpg', '', '2018-01-08 08:12:11'),
(217, 1000285671, '/home/mi_rafi/project_file/pmc/tmp/10196323578475.jpg', '', '2018-01-08 08:12:14'),
(218, 1000680633, '/home/mi_rafi/project_file/pmc/tmp/10198211822230.jpg', '', '2018-01-08 08:12:16'),
(219, 1000747525, '/home/mi_rafi/project_file/pmc/tmp/10201134224631.png', '', '2018-01-08 08:12:19'),
(220, 1000231554, '/home/mi_rafi/project_file/pmc/tmp/10203666465287.png', '', '2018-01-08 08:12:22'),
(221, 1000060610, '/home/mi_rafi/project_file/pmc/tmp/10417770387235.png', '', '2018-01-08 08:15:56'),
(222, 1000632258, '/home/mi_rafi/project_file/pmc/tmp/10420807996340.png', '', '2018-01-08 08:15:59'),
(223, 1000464423, '/home/mi_rafi/project_file/pmc/tmp/10423478949287.png', '', '2018-01-08 08:16:01'),
(224, 1000321257, '/home/mi_rafi/project_file/pmc/tmp/10426011997729.png', '', '2018-01-08 08:16:04'),
(225, 1000870736, '/home/mi_rafi/project_file/pmc/tmp/13707045723548.png', '', '2018-01-08 09:10:45'),
(226, 1000727451, '/home/mi_rafi/project_file/pmc/tmp/13775316766407.png', '', '2018-01-08 09:11:53'),
(227, 1000616186, '/home/mi_rafi/project_file/pmc/tmp/13779501609367.png', '', '2018-01-08 09:11:57'),
(228, 1000672003, '/home/mi_rafi/project_file/pmc/tmp/13782007508888.png', '', '2018-01-08 09:12:00'),
(229, 1000037192, '/home/mi_rafi/project_file/pmc/tmp/13888304119715.png', '', '2018-01-08 09:13:46'),
(230, 1000628738, '/home/mi_rafi/project_file/pmc/tmp/13890010160132.png', '', '2018-01-08 09:13:48'),
(231, 1000077334, '/home/mi_rafi/project_file/pmc/tmp/13894072931488.png', '', '2018-01-08 09:13:52'),
(232, 1000848700, '/home/mi_rafi/project_file/pmc/tmp/13907979872262.png', '', '2018-01-08 09:14:06'),
(233, 1000339490, '/home/mi_rafi/project_file/pmc/tmp/13913486766701.mkv', '', '2018-01-08 09:14:11'),
(234, 1000178538, '/home/mi_rafi/project_file/pmc/tmp/13917232866008.mkv', '', '2018-01-08 09:14:15'),
(235, 1000495808, '/home/mi_rafi/project_file/pmc/tmp/13944131777024.mp4', '', '2018-01-08 09:14:42'),
(236, 1000545545, '/home/mi_rafi/project_file/pmc/tmp/22574829319134.mp4', '', '2018-01-08 11:38:33'),
(237, 1000298668, '/home/mi_rafi/project_file/pmc/tmp/24846374409061.png', '', '2018-01-08 12:16:24'),
(238, 1000493937, '/home/mi_rafi/project_file/pmc/tmp/24849968175052.png', '', '2018-01-08 12:16:28'),
(239, 1000673339, '/home/mi_rafi/project_file/pmc/tmp/24854198231631.png', '', '2018-01-08 12:16:32'),
(240, 1000894127, '/home/mi_rafi/project_file/pmc/tmp/24857132218848.png', '', '2018-01-08 12:16:35'),
(241, 1000147486, '/home/mi_rafi/project_file/pmc/tmp/24868789143295.png', '', '2018-01-08 12:16:47'),
(242, 1000898898, '/home/mi_rafi/project_file/pmc/tmp/24874021177089.mp4', '', '2018-01-08 12:16:52'),
(243, 1000195835, '/home/mi_rafi/project_file/pmc/tmp/24948411631905.png', '', '2018-01-08 12:18:06'),
(244, 1000525657, '/home/mi_rafi/project_file/pmc/tmp/24953503876126.png', '', '2018-01-08 12:18:11'),
(245, 1000659383, '/home/mi_rafi/project_file/pmc/tmp/24957964314059.mp4', '', '2018-01-08 12:18:16'),
(246, 1000425150, '/home/mi_rafi/project_file/pmc/tmp/24962135661722.mp4', '', '2018-01-08 12:18:20'),
(247, 1000108449, '/home/mi_rafi/project_file/pmc/tmp/26525443149358.png', '', '2018-01-08 12:44:23'),
(248, 1000749818, '/home/mi_rafi/project_file/pmc/tmp/26528766097408.png', '', '2018-01-08 12:44:27'),
(249, 1000625225, '/home/mi_rafi/project_file/pmc/tmp/26531638843083.png', '', '2018-01-08 12:44:30'),
(250, 1000482380, '/home/mi_rafi/project_file/pmc/tmp/26534308562016.png', '', '2018-01-08 12:44:32'),
(251, 1000795327, '/home/mi_rafi/project_file/pmc/tmp/26543229807264.png', '', '2018-01-08 12:44:41'),
(252, 1000773039, '/home/mi_rafi/project_file/pmc/tmp/26558251436919.mp4', '', '2018-01-08 12:44:56'),
(253, 1000038588, '/home/mi_rafi/project_file/pmc/tmp/26589551168009.png', '', '2018-01-08 12:45:27'),
(254, 1000668695, '/home/mi_rafi/project_file/pmc/tmp/26595075969772.mp4', '', '2018-01-08 12:45:33'),
(255, 1000817973, '/home/mi_rafi/project_file/pmc/tmp/26602654710148.mp4', '', '2018-01-08 12:45:41'),
(256, 1000781161, '/home/mi_rafi/project_file/pmc/tmp/26606339329028.png', '', '2018-01-08 12:45:44'),
(257, 1000390030, '/home/mi_rafi/project_file/pmc/tmp/27803292441717.png', '', '2018-01-08 13:05:41'),
(258, 1000755354, '/home/mi_rafi/project_file/pmc/tmp/27808054042947.png', '', '2018-01-08 13:05:46'),
(259, 1000630714, '/home/mi_rafi/project_file/pmc/tmp/27810386697231.png', '', '2018-01-08 13:05:48'),
(260, 1000587294, '/home/mi_rafi/project_file/pmc/tmp/27812498590245.png', '', '2018-01-08 13:05:50'),
(261, 1000836047, '/home/mi_rafi/project_file/pmc/tmp/27814624522172.png', '', '2018-01-08 13:05:52'),
(262, 1000027666, '/home/mi_rafi/project_file/pmc/tmp/27819105104613.png', '', '2018-01-08 13:05:57'),
(263, 1000158686, '/home/mi_rafi/project_file/pmc/tmp/27823753943761.mp4', '', '2018-01-08 13:06:02'),
(264, 1000077446, '/home/mi_rafi/project_file/pmc/tmp/27865967251252.png', '', '2018-01-08 13:06:44'),
(265, 1000317719, '/home/mi_rafi/project_file/pmc/tmp/27869901701571.mp4', '', '2018-01-08 13:06:48'),
(266, 1000688718, '/home/mi_rafi/project_file/pmc/tmp/27874842620663.png', '', '2018-01-08 13:06:53'),
(267, 1000554149, '/home/mi_rafi/project_file/pmc/tmp/27878811043989.mp4', '', '2018-01-08 13:06:57'),
(268, 1000610026, '/home/mi_rafi/project_file/pmc/tmp/31888887894484.png', '', '2018-01-08 14:13:47'),
(269, 1000537188, '/home/mi_rafi/project_file/pmc/tmp/31945664533708.png', '', '2018-01-08 14:14:44'),
(270, 1000110841, '/home/mi_rafi/project_file/pmc/tmp/31947792118250.png', '', '2018-01-08 14:14:46'),
(271, 1000152510, '/home/mi_rafi/project_file/pmc/tmp/31952918570517.png', '', '2018-01-08 14:14:51'),
(272, 1000847855, '/home/mi_rafi/project_file/pmc/tmp/31955642014974.png', '', '2018-01-08 14:14:54'),
(273, 1000030620, '/home/mi_rafi/project_file/pmc/tmp/31962363666569.png', '', '2018-01-08 14:15:00'),
(274, 1000373700, '/home/mi_rafi/project_file/pmc/tmp/32249331796602.mp4', '', '2018-01-08 14:19:47'),
(275, 1000605025, '/home/mi_rafi/project_file/pmc/tmp/32258838233852.mp4', '', '2018-01-08 14:19:57'),
(276, 1000151535, '/home/mi_rafi/project_file/pmc/tmp/32261644943674.mp4', '', '2018-01-08 14:20:00'),
(277, 1000121393, '/home/mi_rafi/project_file/pmc/tmp/32265686033134.png', '', '2018-01-08 14:20:04'),
(278, 1000496319, '/home/mi_rafi/project_file/pmc/tmp/32268984994016.png', '', '2018-01-08 14:20:07'),
(279, 1000492620, '/home/mi_rafi/project_file/pmc/tmp/32274181863188.png', '', '2018-01-08 14:20:12'),
(280, 1000157624, '/home/mi_rafi/project_file/pmc/tmp/32277074205763.png', '', '2018-01-08 14:20:15'),
(281, 1000185796, '/home/mi_rafi/project_file/pmc/tmp/35439252704579.png', '', '2018-01-08 15:12:57'),
(282, 1000602165, '/home/mi_rafi/project_file/pmc/tmp/35443208920968.png', '', '2018-01-08 15:13:01'),
(283, 1000654724, '/home/mi_rafi/project_file/pmc/tmp/35541627328165.png', '', '2018-01-08 15:14:39'),
(285, 1000258675, '/home/mi_rafi/project_file/pmc/tmp/35808343709763.png', '', '2018-01-08 15:19:06'),
(286, 1000394575, '/home/mi_rafi/project_file/pmc/tmp/35808778810154.png', '', '2018-01-08 15:19:07'),
(287, 1000510765, '/home/mi_rafi/project_file/pmc/tmp/35815888086623.png', '', '2018-01-08 15:19:14'),
(288, 1000865439, '/home/mi_rafi/project_file/pmc/tmp/35817871622259.png', '', '2018-01-08 15:19:16'),
(289, 1000237508, '/home/mi_rafi/project_file/pmc/tmp/35819719440397.png', '', '2018-01-08 15:19:18'),
(290, 1000032220, '/home/mi_rafi/project_file/pmc/tmp/35822037814632.png', '', '2018-01-08 15:19:20'),
(291, 1000507489, '/home/mi_rafi/project_file/pmc/tmp/35825303114202.png', '', '2018-01-08 15:19:23'),
(292, 1000690369, '/home/mi_rafi/project_file/pmc/tmp/35829960980138.mp4', '', '2018-01-08 15:19:28'),
(293, 1000230146, '/home/mi_rafi/project_file/pmc/tmp/35842025046851.png', '', '2018-01-08 15:19:40'),
(294, 1000325349, '/home/mi_rafi/project_file/pmc/tmp/35845720861797.mp4', '', '2018-01-08 15:19:44'),
(295, 1000842254, '/home/mi_rafi/project_file/pmc/tmp/35850065758221.png', '', '2018-01-08 15:19:48'),
(296, 1000254333, '/home/mi_rafi/project_file/pmc/tmp/35853925099086.mp4', '', '2018-01-08 15:19:52'),
(297, 1000378005, '/home/mi_rafi/project_file/pmc/tmp/35979558556284.png', '', '2018-01-08 15:21:57'),
(298, 1000100458, '/home/mi_rafi/project_file/pmc/tmp/35981879516585.png', '', '2018-01-08 15:22:00'),
(299, 1000405156, '/home/mi_rafi/project_file/pmc/tmp/35983759189257.png', '', '2018-01-08 15:22:02'),
(300, 1000357576, '/home/mi_rafi/project_file/pmc/tmp/35985761378521.png', '', '2018-01-08 15:22:04'),
(301, 1000799903, '/home/mi_rafi/project_file/pmc/tmp/35992651211953.png', '', '2018-01-08 15:22:11'),
(302, 1000465530, '/home/mi_rafi/project_file/pmc/tmp/35998321457008.png', '', '2018-01-08 15:22:16'),
(303, 1000218069, '/home/mi_rafi/project_file/pmc/tmp/36000395766538.png', '', '2018-01-08 15:22:18'),
(304, 1000588031, '/home/mi_rafi/project_file/pmc/tmp/36004750189491.mp4', '', '2018-01-08 15:22:23'),
(305, 1000066137, '/home/mi_rafi/project_file/pmc/tmp/36007534047772.mp4', '', '2018-01-08 15:22:25'),
(306, 1000580983, '/home/mi_rafi/project_file/pmc/tmp/36017815635093.mp4', '', '2018-01-08 15:22:36'),
(307, 1000820060, '/home/mi_rafi/project_file/pmc/tmp/36197416469055.png', '', '2018-01-08 15:25:35'),
(308, 1000140846, '/home/mi_rafi/project_file/pmc/tmp/36197446255750.png', '', '2018-01-08 15:25:35'),
(309, 1000062999, '/home/mi_rafi/project_file/pmc/tmp/36197616871457.png', '', '2018-01-08 15:25:35'),
(310, 1000186327, '/home/mi_rafi/project_file/pmc/tmp/36202983060293.png', '', '2018-01-08 15:25:41'),
(311, 1000791590, '/home/mi_rafi/project_file/pmc/tmp/36204902401325.png', '', '2018-01-08 15:25:43'),
(312, 1000285200, '/home/mi_rafi/project_file/pmc/tmp/36206839926483.png', '', '2018-01-08 15:25:45'),
(313, 1000629220, '/home/mi_rafi/project_file/pmc/tmp/36208898576921.png', '', '2018-01-08 15:25:47'),
(314, 1000334772, '/home/mi_rafi/project_file/pmc/tmp/36211932313110.png', '', '2018-01-08 15:25:50'),
(315, 1000810241, '/home/mi_rafi/project_file/pmc/tmp/36226298566706.mp4', '', '2018-01-08 15:26:04'),
(316, 1000827590, '/home/mi_rafi/project_file/pmc/tmp/36230479149097.mp4', '', '2018-01-08 15:26:08'),
(317, 1000053833, '/home/mi_rafi/project_file/pmc/tmp/36238782398297.png', '', '2018-01-08 15:26:17'),
(318, 1000749582, '/home/mi_rafi/project_file/pmc/tmp/36244185786174.png', '', '2018-01-08 15:26:22'),
(319, 1000812833, '/home/mi_rafi/project_file/pmc/tmp/36252706929031.mp4', '', '2018-01-08 15:26:31'),
(320, 1000671855, '/home/mi_rafi/project_file/pmc/tmp/36256253222891.mp4', '', '2018-01-08 15:26:34'),
(321, 1000452187, '/home/mi_rafi/project_file/pmc/tmp/8797928353037.png', '', '2018-01-09 07:15:28'),
(322, 1000574261, '/home/mi_rafi/project_file/pmc/tmp/8797928353013.png', '', '2018-01-09 07:15:28'),
(323, 1000341149, '/home/mi_rafi/project_file/pmc/tmp/8799636271727.png', '', '2018-01-09 07:15:29'),
(324, 1000567748, '/home/mi_rafi/project_file/pmc/tmp/8799634938151.png', '', '2018-01-09 07:15:29'),
(325, 1000756817, '/home/mi_rafi/project_file/pmc/tmp/8817233622367.png', '', '2018-01-09 07:15:47'),
(326, 1000448282, '/home/mi_rafi/project_file/pmc/tmp/8820756460916.png', '', '2018-01-09 07:15:51'),
(327, 1000699004, '/home/mi_rafi/project_file/pmc/tmp/8823032574950.png', '', '2018-01-09 07:15:53'),
(328, 1000315526, '/home/mi_rafi/project_file/pmc/tmp/8827722209980.png', '', '2018-01-09 07:15:58'),
(329, 1000319722, '/home/mi_rafi/project_file/pmc/tmp/8830231504155.png', '', '2018-01-09 07:16:00'),
(330, 1000721309, '/home/mi_rafi/project_file/pmc/tmp/8832162610522.png', '', '2018-01-09 07:16:02'),
(331, 1000820954, '/home/mi_rafi/project_file/pmc/tmp/8835016884598.png', '', '2018-01-09 07:16:05'),
(332, 1000173747, '/home/mi_rafi/project_file/pmc/tmp/8954536126979.png', '', '2018-01-09 07:18:04'),
(333, 1000686575, '/home/mi_rafi/project_file/pmc/tmp/8954546680264.png', '', '2018-01-09 07:18:04'),
(334, 1000756052, '/home/mi_rafi/project_file/pmc/tmp/8954732431229.png', '', '2018-01-09 07:18:05'),
(335, 1000133025, '/home/mi_rafi/project_file/pmc/tmp/8954780232724.png', '', '2018-01-09 07:18:05'),
(336, 1000321776, '/home/mi_rafi/project_file/pmc/tmp/8959753886149.png', '', '2018-01-09 07:18:10'),
(337, 1000649282, '/home/mi_rafi/project_file/pmc/tmp/8964132556076.png', '', '2018-01-09 07:18:14'),
(338, 1000056927, '/home/mi_rafi/project_file/pmc/tmp/8964145797071.png', '', '2018-01-09 07:18:14'),
(339, 1000129085, '/home/mi_rafi/project_file/pmc/tmp/8964756598515.png', '', '2018-01-09 07:18:15'),
(340, 1000831909, '/home/mi_rafi/project_file/pmc/tmp/8970591280156.png', '', '2018-01-09 07:18:20'),
(341, 1000577455, '/home/mi_rafi/project_file/pmc/tmp/8970601052984.png', '', '2018-01-09 07:18:20'),
(342, 1000529300, '/home/mi_rafi/project_file/pmc/tmp/8975483632963.png', '', '2018-01-09 07:18:25'),
(343, 1000391158, '/home/mi_rafi/project_file/pmc/tmp/8975480969184.png', '', '2018-01-09 07:18:25'),
(344, 1000523094, '/home/mi_rafi/project_file/pmc/tmp/8981607548062.png', '', '2018-01-09 07:18:31'),
(345, 1000014908, '/home/mi_rafi/project_file/pmc/tmp/8993174135124.mp4', '', '2018-01-09 07:18:43'),
(346, 1000810429, '/home/mi_rafi/project_file/pmc/tmp/9000495315320.mp4', '', '2018-01-09 07:18:50'),
(347, 1000591279, '/home/mi_rafi/project_file/pmc/tmp/9003725081632.png', '', '2018-01-09 07:18:54'),
(348, 1000822428, '/home/mi_rafi/project_file/pmc/tmp/9007997865982.mp4', '', '2018-01-09 07:18:58'),
(349, 1000311730, '/home/mi_rafi/project_file/pmc/tmp/9011260753476.png', '', '2018-01-09 07:19:01'),
(350, 1000590482, '/home/mi_rafi/project_file/pmc/tmp/17936574891756.png', '', '2018-01-09 09:47:47'),
(351, 1000434999, '/home/mi_rafi/project_file/pmc/tmp/17989284465861.png', '', '2018-01-09 09:48:39'),
(352, 1000077112, '/home/mi_rafi/project_file/pmc/tmp/18288318199160.png', '', '2018-01-09 09:53:38'),
(354, 1000122512, '/home/mi_rafi/project_file/pmc/tmp/19133284155369.png', '', '2018-01-09 10:07:43'),
(355, 1000482659, '/home/mi_rafi/project_file/pmc/tmp/19136980333780.png', '', '2018-01-09 10:07:47'),
(356, 1000788206, '/home/mi_rafi/project_file/pmc/tmp/19154584076989.png', '', '2018-01-09 10:08:04'),
(357, 1000535164, '/home/mi_rafi/project_file/pmc/tmp/19282552118517.png', '', '2018-01-09 10:10:12'),
(358, 1000588551, '/home/mi_rafi/project_file/pmc/tmp/19290704187425.png', '', '2018-01-09 10:10:21'),
(359, 1000198842, '/home/mi_rafi/project_file/pmc/tmp/19290754487932.png', '', '2018-01-09 10:10:21'),
(360, 1000488478, '/home/mi_rafi/project_file/pmc/tmp/19291044709978.png', '', '2018-01-09 10:10:21'),
(362, 1000490674, '/home/mi_rafi/project_file/pmc/tmp/19447201865067.png', '', '2018-01-09 10:12:57'),
(363, 1000395114, '/home/mi_rafi/project_file/pmc/tmp/19447326312104.png', '', '2018-01-09 10:12:57'),
(364, 1000409446, '/home/mi_rafi/project_file/pmc/tmp/19448373514769.png', '', '2018-01-09 10:12:58'),
(365, 1000279909, '/home/mi_rafi/project_file/pmc/tmp/19453247125865.png', '', '2018-01-09 10:13:03'),
(366, 1000634589, '/home/mi_rafi/project_file/pmc/tmp/20550395407420.png', '', '2018-01-09 10:31:20'),
(367, 1000778419, '/home/mi_rafi/project_file/pmc/tmp/20553620236102.png', '', '2018-01-09 10:31:23'),
(368, 1000488583, '/home/mi_rafi/project_file/pmc/tmp/26126512604901.png', '', '2018-01-09 12:04:16'),
(369, 1000400848, '/home/mi_rafi/project_file/pmc/tmp/26568356524048.png', '', '2018-01-09 12:11:38'),
(370, 1000222620, '/home/mi_rafi/project_file/pmc/tmp/26568361567170.png', '', '2018-01-09 12:11:38'),
(371, 1000071081, '/home/mi_rafi/project_file/pmc/tmp/26568511558180.png', '', '2018-01-09 12:11:38'),
(372, 1000171962, '/home/mi_rafi/project_file/pmc/tmp/26568568661475.png', '', '2018-01-09 12:11:38'),
(373, 1000894353, '/home/mi_rafi/project_file/pmc/tmp/26581284410619.jpg', '', '2018-01-09 12:11:51'),
(374, 1000243074, '/home/mi_rafi/project_file/pmc/tmp/26583333565609.jpg', '', '2018-01-09 12:11:53'),
(375, 1000761313, '/home/mi_rafi/project_file/pmc/tmp/26585223722368.jpg', '', '2018-01-09 12:11:55'),
(376, 1000529529, '/home/mi_rafi/project_file/pmc/tmp/26587237787381.jpg', '', '2018-01-09 12:11:57'),
(377, 1000487450, '/home/mi_rafi/project_file/pmc/tmp/26589924229932.jpg', '', '2018-01-09 12:12:00'),
(378, 1000291521, '/home/mi_rafi/project_file/pmc/tmp/26595443207367.mp4', '', '2018-01-09 12:12:05'),
(379, 1000280605, '/home/mi_rafi/project_file/pmc/tmp/26605889825553.png', '', '2018-01-09 12:12:16'),
(380, 1000881788, '/home/mi_rafi/project_file/pmc/tmp/26615279579233.mp4', '', '2018-01-09 12:12:25'),
(381, 1000115962, '/home/mi_rafi/project_file/pmc/tmp/26622883633972.mp4', '', '2018-01-09 12:12:33'),
(382, 1000193965, '/home/mi_rafi/project_file/pmc/tmp/26741011190126.png', '', '2018-01-09 12:14:31'),
(383, 1000483506, '/home/mi_rafi/project_file/pmc/tmp/26757493714575.png', '', '2018-01-09 12:14:47'),
(384, 1000764352, '/home/mi_rafi/project_file/pmc/tmp/26759970293104.png', '', '2018-01-09 12:14:50'),
(385, 1000080213, '/home/mi_rafi/project_file/pmc/tmp/26763145270745.png', '', '2018-01-09 12:14:53'),
(386, 1000020835, '/home/mi_rafi/project_file/pmc/tmp/26765546346237.png', '', '2018-01-09 12:14:55'),
(387, 1000851172, '/home/mi_rafi/project_file/pmc/tmp/26770320152896.png', '', '2018-01-09 12:15:00'),
(388, 1000185587, '/home/mi_rafi/project_file/pmc/tmp/26814500491721.mp4', '', '2018-01-09 12:15:44'),
(389, 1000031539, '/home/mi_rafi/project_file/pmc/tmp/26821419872097.png', '', '2018-01-09 12:15:51'),
(390, 1000784096, '/home/mi_rafi/project_file/pmc/tmp/26825437715509.mp4', '', '2018-01-09 12:15:55'),
(391, 1000495518, '/home/mi_rafi/project_file/pmc/tmp/26829859864063.png', '', '2018-01-09 12:16:00'),
(392, 1000833331, '/home/mi_rafi/project_file/pmc/tmp/26833155138828.mp4', '', '2018-01-09 12:16:03'),
(393, 1000469505, '/home/mi_rafi/project_file/pmc/tmp/28712497703892.png', '', '2018-01-09 12:47:22'),
(394, 1000525289, '/home/mi_rafi/project_file/pmc/tmp/31725242911301.png', '', '2018-01-09 13:37:35'),
(395, 1000378026, '/home/mi_rafi/project_file/pmc/tmp/32022075837509.png', '', '2018-01-09 13:42:32'),
(397, 1000608985, '/home/mi_rafi/project_file/pmc/tmp/92296838259199.jpg', '', '2018-01-10 06:27:07'),
(399, 1000292091, '/home/mi_rafi/project_file/pmc/tmp/101989477327860.jpg', '', '2018-01-10 09:08:39'),
(400, 1000812160, '/home/mi_rafi/project_file/pmc/tmp/117625261251617.png', '', '2018-01-10 13:29:15'),
(401, 1000063296, '/home/mi_rafi/project_file/pmc/tmp/117628340806023.png', '', '2018-01-10 13:29:18'),
(402, 1000234972, '/home/mi_rafi/project_file/pmc/tmp/117630938051947.png', '', '2018-01-10 13:29:21'),
(403, 1000824876, '/home/mi_rafi/project_file/pmc/tmp/117633970327401.png', '', '2018-01-10 13:29:24'),
(404, 1000823936, '/home/mi_rafi/project_file/pmc/tmp/117639906014763.png', '', '2018-01-10 13:29:30'),
(405, 1000248496, '/home/mi_rafi/project_file/pmc/tmp/117644471360895.mp4', '', '2018-01-10 13:29:34'),
(406, 1000633263, '/home/mi_rafi/project_file/pmc/tmp/117651607245185.png', '', '2018-01-10 13:29:41'),
(407, 1000261532, '/home/mi_rafi/project_file/pmc/tmp/117656397146499.mp4', '', '2018-01-10 13:29:46'),
(408, 1000651012, '/home/mi_rafi/project_file/pmc/tmp/117660615043297.png', '', '2018-01-10 13:29:50'),
(409, 1000580033, '/home/mi_rafi/project_file/pmc/tmp/117664856385003.mp4', '', '2018-01-10 13:29:55'),
(410, 1000015085, '/home/mi_rafi/project_file/pmc/tmp/122599493938656.png', '', '2018-01-10 14:52:09'),
(412, 1000642890, '/home/mi_rafi/project_file/pmc/tmp/175223001944998.png', '', '2018-01-11 05:29:13'),
(413, 1000187253, '/home/mi_rafi/project_file/pmc/tmp/175223005950810.png', '', '2018-01-11 05:29:13'),
(414, 1000342335, '/home/mi_rafi/project_file/pmc/tmp/175343500033865.jpg', '', '2018-01-11 05:31:13'),
(415, 1000144823, '/home/mi_rafi/project_file/pmc/tmp/175346556578373.jpg', '', '2018-01-11 05:31:16'),
(416, 1000530388, '/home/mi_rafi/project_file/pmc/tmp/175349026666998.jpg', '', '2018-01-11 05:31:19'),
(417, 1000545369, '/home/mi_rafi/project_file/pmc/tmp/175351177528395.jpg', '', '2018-01-11 05:31:21'),
(418, 1000157290, '/home/mi_rafi/project_file/pmc/tmp/175353395202332.jpg', '', '2018-01-11 05:31:23'),
(419, 1000237690, '/home/mi_rafi/project_file/pmc/tmp/175355269970785.jpg', '', '2018-01-11 05:31:25'),
(420, 1000330833, '/home/mi_rafi/project_file/pmc/tmp/175359978513628.jpg', '', '2018-01-11 05:31:30'),
(421, 1000328898, '/home/mi_rafi/project_file/pmc/tmp/175562723557531.png', '', '2018-01-11 05:34:53'),
(422, 1000811846, '/home/mi_rafi/project_file/pmc/tmp/175562720814603.png', '', '2018-01-11 05:34:53'),
(423, 1000308001, '/home/mi_rafi/project_file/pmc/tmp/175562912216788.png', '', '2018-01-11 05:34:53'),
(424, 1000094870, '/home/mi_rafi/project_file/pmc/tmp/175579688060009.jpg', '', '2018-01-11 05:35:09'),
(425, 1000679542, '/home/mi_rafi/project_file/pmc/tmp/175581761412603.jpg', '', '2018-01-11 05:35:12'),
(426, 1000781608, '/home/mi_rafi/project_file/pmc/tmp/175583580053305.jpg', '', '2018-01-11 05:35:13'),
(427, 1000821524, '/home/mi_rafi/project_file/pmc/tmp/175585265897305.jpg', '', '2018-01-11 05:35:15'),
(428, 1000674638, '/home/mi_rafi/project_file/pmc/tmp/175588643806401.jpg', '', '2018-01-11 05:35:18'),
(429, 1000729381, '/home/mi_rafi/project_file/pmc/tmp/175593261198575.jpg', '', '2018-01-11 05:35:23'),
(431, 1000341316, '/home/mi_rafi/project_file/pmc/tmp/175609097685438.png', '', '2018-01-11 05:35:39'),
(432, 1000005665, '/home/mi_rafi/project_file/pmc/tmp/175612719275089.mp4', '', '2018-01-11 05:35:43'),
(433, 1000868833, '/home/mi_rafi/project_file/pmc/tmp/175622299817304.png', '', '2018-01-11 05:35:52'),
(434, 1000023561, '/home/mi_rafi/project_file/pmc/tmp/175625627828074.mp4', '', '2018-01-11 05:35:55'),
(436, 1000089215, '/home/mi_rafi/project_file/pmc/tmp/175656252479022.flv', '', '2018-01-11 05:36:26'),
(437, 1000608900, '/home/mi_rafi/project_file/pmc/tmp/175666133795002.3gp', '', '2018-01-11 05:36:36'),
(438, 1000759989, '/home/mi_rafi/project_file/pmc/tmp/180237192640792.png', '', '2018-01-11 06:52:47'),
(439, 1000072341, '/home/mi_rafi/project_file/pmc/tmp/180237192640787.png', '', '2018-01-11 06:52:47'),
(440, 1000071395, '/home/mi_rafi/project_file/pmc/tmp/180237810619809.png', '', '2018-01-11 06:52:48'),
(441, 1000794553, '/home/mi_rafi/project_file/pmc/tmp/180252078469730.png', '', '2018-01-11 06:53:02'),
(442, 1000304476, '/home/mi_rafi/project_file/pmc/tmp/180254125943095.png', '', '2018-01-11 06:53:04'),
(443, 1000543284, '/home/mi_rafi/project_file/pmc/tmp/180257972963690.png', '', '2018-01-11 06:53:08'),
(444, 1000283910, '/home/mi_rafi/project_file/pmc/tmp/180257984927102.png', '', '2018-01-11 06:53:08'),
(445, 1000358132, '/home/mi_rafi/project_file/pmc/tmp/180264550556111.png', '', '2018-01-11 06:53:14'),
(446, 1000301222, '/home/mi_rafi/project_file/pmc/tmp/180264557003798.png', '', '2018-01-11 06:53:14'),
(447, 1000382243, '/home/mi_rafi/project_file/pmc/tmp/180264782871109.png', '', '2018-01-11 06:53:15'),
(448, 1000565341, '/home/mi_rafi/project_file/pmc/tmp/180271700570832.png', '', '2018-01-11 06:53:22'),
(449, 1000255082, '/home/mi_rafi/project_file/pmc/tmp/180276497385959.webm', '', '2018-01-11 06:53:26'),
(451, 1000679113, '/home/mi_rafi/project_file/pmc/tmp/180308539567883.png', '', '2018-01-11 06:53:58'),
(452, 1000470258, '/home/mi_rafi/project_file/pmc/tmp/180314112592599.ogv', '', '2018-01-11 06:54:04'),
(453, 1000227883, '/home/mi_rafi/project_file/pmc/tmp/180317937261213.mp4', '', '2018-01-11 06:54:08'),
(454, 1000606499, '/home/mi_rafi/project_file/pmc/tmp/180322070135158.png', '', '2018-01-11 06:54:12'),
(455, 1000464119, '/home/mi_rafi/project_file/pmc/tmp/186976490126627.webm', '', '2018-01-11 08:45:06'),
(456, 1000828406, '/home/mi_rafi/project_file/pmc/tmp/187142651214657.mp4', '', '2018-01-11 08:47:53'),
(457, 1000723192, '/home/mi_rafi/project_file/pmc/tmp/187217612575935.webm', '', '2018-01-11 08:49:07'),
(458, 1000556528, '/home/mi_rafi/project_file/pmc/tmp/188083496756476.png', '', '2018-01-11 09:03:33'),
(459, 1000068985, '/home/mi_rafi/project_file/pmc/tmp/188083496765312.png', '', '2018-01-11 09:03:33'),
(460, 1000087785, '/home/mi_rafi/project_file/pmc/tmp/188083968901120.png', '', '2018-01-11 09:03:34'),
(461, 1000655547, '/home/mi_rafi/project_file/pmc/tmp/188104344233116.png', '', '2018-01-11 09:03:54'),
(462, 1000045115, '/home/mi_rafi/project_file/pmc/tmp/188107659060021.png', '', '2018-01-11 09:03:57'),
(463, 1000783790, '/home/mi_rafi/project_file/pmc/tmp/188112221236657.png', '', '2018-01-11 09:04:02'),
(464, 1000499306, '/home/mi_rafi/project_file/pmc/tmp/188112212987733.png', '', '2018-01-11 09:04:02'),
(465, 1000335672, '/home/mi_rafi/project_file/pmc/tmp/188112391267153.png', '', '2018-01-11 09:04:02'),
(466, 1000760621, '/home/mi_rafi/project_file/pmc/tmp/188117362953558.png', '', '2018-01-11 09:04:07'),
(467, 1000794281, '/home/mi_rafi/project_file/pmc/tmp/188117370512175.png', '', '2018-01-11 09:04:07'),
(468, 1000183782, '/home/mi_rafi/project_file/pmc/tmp/188117541441223.png', '', '2018-01-11 09:04:07'),
(469, 1000385046, '/home/mi_rafi/project_file/pmc/tmp/188121676190927.png', '', '2018-01-11 09:04:11'),
(470, 1000491425, '/home/mi_rafi/project_file/pmc/tmp/188127023311529.webm', '', '2018-01-11 09:04:17'),
(471, 1000222270, '/home/mi_rafi/project_file/pmc/tmp/188137296302440.ogv', '', '2018-01-11 09:04:27'),
(472, 1000883568, '/home/mi_rafi/project_file/pmc/tmp/188142022991700.png', '', '2018-01-11 09:04:32'),
(473, 1000787170, '/home/mi_rafi/project_file/pmc/tmp/188151176825991.mp4', '', '2018-01-11 09:04:41'),
(474, 1000305856, '/home/mi_rafi/project_file/pmc/tmp/188156200146741.png', '', '2018-01-11 09:04:46'),
(475, 1000134222, '/home/mi_rafi/project_file/pmc/tmp/188888053545192.png', '', '2018-01-11 09:16:58'),
(476, 1000632612, '/home/mi_rafi/project_file/pmc/tmp/188891945358247.png', '', '2018-01-11 09:17:02'),
(477, 1000410482, '/home/mi_rafi/project_file/pmc/tmp/188891949616600.png', '', '2018-01-11 09:17:02'),
(478, 1000154951, '/home/mi_rafi/project_file/pmc/tmp/188892108373112.png', '', '2018-01-11 09:17:02'),
(479, 1000072049, '/home/mi_rafi/project_file/pmc/tmp/188894286014157.png', '', '2018-01-11 09:17:04'),
(480, 1000795175, '/home/mi_rafi/project_file/pmc/tmp/188896285477950.png', '', '2018-01-11 09:17:06'),
(481, 1000557543, '/home/mi_rafi/project_file/pmc/tmp/188899071388818.png', '', '2018-01-11 09:17:09'),
(482, 1000700190, '/home/mi_rafi/project_file/pmc/tmp/188904371037767.webm', '', '2018-01-11 09:17:14'),
(483, 1000759730, '/home/mi_rafi/project_file/pmc/tmp/188909940584144.ogv', '', '2018-01-11 09:17:20'),
(484, 1000640589, '/home/mi_rafi/project_file/pmc/tmp/188912897739819.mp4', '', '2018-01-11 09:17:23'),
(485, 1000780461, '/home/mi_rafi/project_file/pmc/tmp/188918509774437.png', '', '2018-01-11 09:17:28'),
(486, 1000376681, '/home/mi_rafi/project_file/pmc/tmp/188920986725591.png', '', '2018-01-11 09:17:31'),
(487, 1000631409, '/home/mi_rafi/project_file/pmc/tmp/189059236158922.png', '', '2018-01-11 09:19:49'),
(488, 1000578125, '/home/mi_rafi/project_file/pmc/tmp/6116282660464.png', '', '2018-01-11 12:17:24'),
(489, 1000197190, '/home/mi_rafi/project_file/pmc/tmp/6118133901170.png', '', '2018-01-11 12:17:25'),
(490, 1000871646, '/home/mi_rafi/project_file/pmc/tmp/89067432448380.png', '', '2018-01-12 11:19:55'),
(491, 1000587593, '/home/mi_rafi/project_file/pmc/tmp/89071749785760.png', '', '2018-01-12 11:19:59'),
(492, 1000681971, '/home/mi_rafi/project_file/pmc/tmp/89075475817275.png', '', '2018-01-12 11:20:03'),
(493, 1000216298, '/home/mi_rafi/project_file/pmc/tmp/99180266512725.png', '', '2018-01-12 14:08:28'),
(494, 1000497450, '/home/mi_rafi/project_file/pmc/tmp/99399805075468.jpg', '', '2018-01-12 14:12:08'),
(495, 1000530511, '/home/mi_rafi/project_file/pmc/tmp/4101017069972.jpg', '', '2018-01-15 06:27:46'),
(496, 1000457442, '/home/mi_rafi/project_file/pmc/tmp/4133700040871.jpg', '', '2018-01-15 06:28:19'),
(497, 1000666479, '/home/mi_rafi/project_file/pmc/tmp/4613666577974.jpeg', '', '2018-01-15 06:36:19'),
(498, 1000721913, '/home/mi_rafi/project_file/pmc/tmp/4762591896555.jpeg', '', '2018-01-15 06:38:48'),
(499, 1000895027, '/home/mi_rafi/project_file/pmc/tmp/5044984895095.jpeg', '', '2018-01-15 06:43:30'),
(500, 1000162004, '/home/mi_rafi/project_file/pmc/tmp/5185232393155.png', '', '2018-01-15 06:45:51'),
(502, 1000389357, '/home/mi_rafi/project_file/pmc/tmp/6406954794992.jpeg', '', '2018-01-15 07:06:12'),
(503, 1000448302, '/home/mi_rafi/project_file/pmc/tmp/6409830754522.jpg', '', '2018-01-15 07:06:15'),
(504, 1000277346, '/home/mi_rafi/project_file/pmc/tmp/6414818483204.jpg', '', '2018-01-15 07:06:20'),
(505, 1000705318, '/home/mi_rafi/project_file/pmc/tmp/6481755777189.jpeg', '', '2018-01-15 07:07:27'),
(506, 1000567693, '/home/mi_rafi/project_file/pmc/tmp/6486244303931.jpg', '', '2018-01-15 07:07:32'),
(508, 1000583401, '/home/mi_rafi/project_file/pmc/tmp/6539006446523.png', '', '2018-01-15 07:08:24'),
(509, 1000460411, '/home/mi_rafi/project_file/pmc/tmp/6545946453065.jpg', '', '2018-01-15 07:08:31'),
(510, 1000361816, '/home/mi_rafi/project_file/pmc/tmp/6549268310774.jpg', '', '2018-01-15 07:08:35'),
(511, 1000546996, '/home/mi_rafi/project_file/pmc/tmp/6557744392709.jpg', '', '2018-01-15 07:08:43'),
(512, 1000122341, '/home/mi_rafi/project_file/pmc/tmp/6560604138188.jpg', '', '2018-01-15 07:08:46'),
(513, 1000497658, '/home/mi_rafi/project_file/pmc/tmp/6565462410093.png', '', '2018-01-15 07:08:51'),
(514, 1000804636, '/home/mi_rafi/project_file/pmc/tmp/6568639968780.jpg', '', '2018-01-15 07:08:54'),
(515, 1000231846, '/home/mi_rafi/project_file/pmc/tmp/6627406338298.png', '', '2018-01-15 07:09:53'),
(516, 1000012515, '/home/mi_rafi/project_file/pmc/tmp/6632211693877.jpg', '', '2018-01-15 07:09:58'),
(517, 1000709697, '/home/mi_rafi/project_file/pmc/tmp/6774618593983.png', '', '2018-01-15 07:12:20'),
(518, 1000642398, '/home/mi_rafi/project_file/pmc/tmp/6778185146236.png', '', '2018-01-15 07:12:24'),
(519, 1000054008, '/home/mi_rafi/project_file/pmc/tmp/6785018740306.png', '', '2018-01-15 07:12:30'),
(520, 1000238885, '/home/mi_rafi/project_file/pmc/tmp/7510792762375.jpg', '', '2018-01-15 07:24:36'),
(521, 1000327949, '/home/mi_rafi/project_file/pmc/tmp/7513241748580.jpg', '', '2018-01-15 07:24:39'),
(522, 1000281083, '/home/mi_rafi/project_file/pmc/tmp/7517247198750.jpeg', '', '2018-01-15 07:24:43'),
(523, 1000496852, '/home/mi_rafi/project_file/pmc/tmp/7519660960494.png', '', '2018-01-15 07:24:45'),
(524, 1000671426, '/home/mi_rafi/project_file/pmc/tmp/7761019467078.jpg', '', '2018-01-15 07:28:46'),
(525, 1000655572, '/home/mi_rafi/project_file/pmc/tmp/7943988272497.mp4', '', '2018-01-15 07:31:49'),
(526, 1000638296, '/home/mi_rafi/project_file/pmc/tmp/7966203502035.mp4', '', '2018-01-15 07:32:12'),
(527, 1000613356, '/home/mi_rafi/project_file/pmc/tmp/7978797269163.png', '', '2018-01-15 07:32:24'),
(528, 1000505773, '/home/mi_rafi/project_file/pmc/tmp/7978918759982.jpg', '', '2018-01-15 07:32:24'),
(529, 1000747062, '/home/mi_rafi/project_file/pmc/tmp/7988205210105.png', '', '2018-01-15 07:32:34'),
(530, 1000064098, '/home/mi_rafi/project_file/pmc/tmp/7990624133944.png', '', '2018-01-15 07:32:36'),
(531, 1000261383, '/home/mi_rafi/project_file/pmc/tmp/7999784081649.png', '', '2018-01-15 07:32:45'),
(532, 1000889714, '/home/mi_rafi/project_file/pmc/tmp/8005583815727.png', '', '2018-01-15 07:32:51'),
(533, 1000201334, '/home/mi_rafi/project_file/pmc/tmp/8032916798792.jpeg', '', '2018-01-15 07:33:18'),
(534, 1000272973, '/home/mi_rafi/project_file/pmc/tmp/8035610380762.jpg', '', '2018-01-15 07:33:21'),
(535, 1000789662, '/home/mi_rafi/project_file/pmc/tmp/8039835199913.png', '', '2018-01-15 07:33:25'),
(536, 1000100074, '/home/mi_rafi/project_file/pmc/tmp/8043323510338.jpg', '', '2018-01-15 07:33:29'),
(537, 1000355098, '/home/mi_rafi/project_file/pmc/tmp/8046447983246.png', '', '2018-01-15 07:33:32'),
(538, 1000102623, '/home/mi_rafi/project_file/pmc/tmp/8050464747774.png', '', '2018-01-15 07:33:36'),
(539, 1000074084, '/home/mi_rafi/project_file/pmc/tmp/8054115552054.jpg', '', '2018-01-15 07:33:39'),
(540, 1000318864, '/home/mi_rafi/project_file/pmc/tmp/8082638247902.png', '', '2018-01-15 07:34:08'),
(541, 1000208007, '/home/mi_rafi/project_file/pmc/tmp/11123719658885.jpg', '', '2018-01-15 08:24:49'),
(542, 1000547035, '/home/mi_rafi/project_file/pmc/tmp/11586600947071.jpg', '', '2018-01-15 08:32:32'),
(543, 1000481786, '/home/mi_rafi/project_file/pmc/tmp/11589449279152.jpg', '', '2018-01-15 08:32:35'),
(544, 1000217877, '/home/mi_rafi/project_file/pmc/tmp/11592802304896.jpg', '', '2018-01-15 08:32:38'),
(545, 1000350365, '/home/mi_rafi/project_file/pmc/tmp/11882980666602.jpg', '', '2018-01-15 08:37:28'),
(546, 1000420188, '/home/mi_rafi/project_file/pmc/tmp/11902215220127.jpg', '', '2018-01-15 08:37:48'),
(547, 1000897451, '/home/mi_rafi/project_file/pmc/tmp/11904229861648.jpg', '', '2018-01-15 08:37:50'),
(548, 1000494566, '/home/mi_rafi/project_file/pmc/tmp/12146138008302.jpg', '', '2018-01-15 08:41:52'),
(549, 1000760453, '/home/mi_rafi/project_file/pmc/tmp/12147848592779.jpg', '', '2018-01-15 08:41:53'),
(550, 1000117892, '/home/mi_rafi/project_file/pmc/tmp/12256353649087.jpg', '', '2018-01-15 08:43:42'),
(551, 1000572925, '/home/mi_rafi/project_file/pmc/tmp/12270097168072.mp4', '', '2018-01-15 08:43:55'),
(552, 1000016067, '/home/mi_rafi/project_file/pmc/tmp/12290608112207.jpg', '', '2018-01-15 08:44:16'),
(553, 1000386802, '/home/mi_rafi/project_file/pmc/tmp/12493192482093.jpg', '', '2018-01-15 08:47:39'),
(554, 1000409078, '/home/mi_rafi/project_file/pmc/tmp/12935259995491.jpg', '', '2018-01-15 08:55:01'),
(555, 1000329227, '/home/mi_rafi/project_file/pmc/tmp/12989008916779.jpg', '', '2018-01-15 08:55:54'),
(556, 1000575395, '/home/mi_rafi/project_file/pmc/tmp/13040503855418.jpg', '', '2018-01-15 08:56:46'),
(557, 1000745793, '/home/mi_rafi/project_file/pmc/tmp/13086231538204.jpg', '', '2018-01-15 08:57:32'),
(558, 1000170447, '/home/mi_rafi/project_file/pmc/tmp/13133613647953.jpg', '', '2018-01-15 08:58:19'),
(559, 1000757181, '/home/mi_rafi/project_file/pmc/tmp/13341083906113.jpg', '', '2018-01-15 09:01:46'),
(561, 1000553467, '/home/mi_rafi/project_file/pmc/tmp/13530421711359.jpg', '', '2018-01-15 09:04:56'),
(562, 1000245398, '/home/mi_rafi/project_file/pmc/tmp/13535566911563.mp4', '', '2018-01-15 09:05:01');
INSERT INTO `temp_file` (`id`, `token`, `path`, `file_name`, `created_date`) VALUES
(563, 1000072606, '/home/mi_rafi/project_file/pmc/tmp/14068937142491.png', '', '2018-01-15 09:13:54'),
(564, 1000120660, '/home/mi_rafi/project_file/pmc/tmp/14087818879552.mp4', '', '2018-01-15 09:14:13'),
(565, 1000364623, '/home/mi_rafi/project_file/pmc/tmp/14120112083923.ogv', '', '2018-01-15 09:14:45'),
(566, 1000698875, '/home/mi_rafi/project_file/pmc/tmp/14249907052450.png', '', '2018-01-15 09:16:55'),
(567, 1000473683, '/home/mi_rafi/project_file/pmc/tmp/14254543269731.png', '', '2018-01-15 09:17:00'),
(568, 1000698589, '/home/mi_rafi/project_file/pmc/tmp/14261105271426.ogv', '', '2018-01-15 09:17:06'),
(569, 1000597195, '/home/mi_rafi/project_file/pmc/tmp/14264103978791.ogv', '', '2018-01-15 09:17:09'),
(570, 1000636901, '/home/mi/project_file/pmc/tmp/7129714776303.png', '', '2018-01-16 12:46:26'),
(571, 1000664718, '/home/mi/project_file/pmc/tmp/8790947382147.png', '', '2018-01-16 13:14:07'),
(572, 1000573469, '/home/mi/project_file/pmc/tmp/58661022275535.png', '', '2018-01-17 06:38:33'),
(573, 1000358161, '/home/mi/project_file/pmc/tmp/58717616990469.png', '', '2018-01-17 06:39:30'),
(574, 1000242112, '/home/mi/project_file/pmc/tmp/58775247456959.png', '', '2018-01-17 06:40:27'),
(575, 1000779006, '/home/mi/project_file/pmc/tmp/58967455762409.png', '', '2018-01-17 06:43:40'),
(576, 1000297765, '/home/mi/project_file/pmc/tmp/58969978210824.png', '', '2018-01-17 06:43:42'),
(577, 1000157819, '/home/mi/project_file/pmc/tmp/58976455911012.png', '', '2018-01-17 06:43:49'),
(579, 1000570224, '/home/mi/project_file/pmc/tmp/59009818820291.png', '', '2018-01-17 06:44:22'),
(580, 1000585869, '/home/mi/project_file/pmc/tmp/59012463801069.png', '', '2018-01-17 06:44:25'),
(581, 1000029159, '/home/mi/project_file/pmc/tmp/59037520667263.png', '', '2018-01-17 06:44:50'),
(582, 1000309408, '/home/mi/project_file/pmc/tmp/59048135493906.png', '', '2018-01-17 06:45:00'),
(584, 1000184721, '/home/mi/project_file/pmc/tmp/59106637588707.png', '', '2018-01-17 06:45:59'),
(585, 1000687300, '/home/mi/project_file/pmc/tmp/59141759019474.png', '', '2018-01-17 06:46:34'),
(586, 1000201785, '/home/mi/project_file/pmc/tmp/59145645910426.png', '', '2018-01-17 06:46:38'),
(587, 1000331350, '/home/mi/project_file/pmc/tmp/59228282549373.png', '', '2018-01-17 06:48:00'),
(589, 1000611191, '/home/mi/project_file/pmc/tmp/59255379222891.png', '', '2018-01-17 06:48:28'),
(590, 1000774737, '/home/mi/project_file/pmc/tmp/59259703082101.png', '', '2018-01-17 06:48:32'),
(591, 1000531457, '/home/mi/project_file/pmc/tmp/59271012550895.png', '', '2018-01-17 06:48:43'),
(593, 1000262592, '/home/mi/project_file/pmc/tmp/59340427708509.png', '', '2018-01-17 06:49:53'),
(594, 1000736657, '/home/mi/project_file/pmc/tmp/59364965322127.png', '', '2018-01-17 06:50:17'),
(595, 1000491872, '/home/mi/project_file/pmc/tmp/59539383785044.png', '', '2018-01-17 06:53:12'),
(596, 1000661515, '/home/mi/project_file/pmc/tmp/59543265018462.png', '', '2018-01-17 06:53:15'),
(599, 1000448535, '/home/mi/project_file/pmc/tmp/59710030919654.png', '', '2018-01-17 06:56:02'),
(602, 1000241633, '/home/mi/project_file/pmc/tmp/59811419965895.png', '', '2018-01-17 06:57:44'),
(604, 1000499176, '/home/mi/project_file/pmc/tmp/59829650276923.png', '', '2018-01-17 06:58:02'),
(606, 1000402966, '/home/mi/project_file/pmc/tmp/67960501034603.jpeg', '', '2018-01-17 09:13:33'),
(607, 1000367779, '/home/mi/project_file/pmc/tmp/67966036906033.jpg', '', '2018-01-17 09:13:38'),
(608, 1000626414, '/home/mi/project_file/pmc/tmp/67968983372652.jpeg', '', '2018-01-17 09:13:41'),
(609, 1000788873, '/home/mi/project_file/pmc/tmp/67971797376833.png', '', '2018-01-17 09:13:44'),
(610, 1000095169, '/home/mi/project_file/pmc/tmp/67976629088205.png', '', '2018-01-17 09:13:49'),
(611, 1000372433, '/home/mi/project_file/pmc/tmp/67980236460887.png', '', '2018-01-17 09:13:52'),
(612, 1000813479, '/home/mi/project_file/pmc/tmp/67982921927301.png', '', '2018-01-17 09:13:55'),
(613, 1000360807, '/home/mi/project_file/pmc/tmp/67985946115133.jpg', '', '2018-01-17 09:13:58'),
(615, 1000472309, '/home/mi/project_file/pmc/tmp/68014785697231.png', '', '2018-01-17 09:14:27'),
(616, 1000709943, '/home/mi/project_file/pmc/tmp/68028796060017.png', '', '2018-01-17 09:14:41'),
(617, 1000668488, '/home/mi/project_file/pmc/tmp/68049264089600.png', '', '2018-01-17 09:15:01'),
(618, 1000072077, '/home/mi/project_file/pmc/tmp/68071187937356.mp4', '', '2018-01-17 09:15:23'),
(619, 1000741250, '/home/mi/project_file/pmc/tmp/68078728449396.mp4', '', '2018-01-17 09:15:31'),
(620, 1000694704, '/home/mi/project_file/pmc/tmp/68085518332350.mp4', '', '2018-01-17 09:15:38'),
(621, 1000472548, '/home/mi/project_file/pmc/tmp/71274899351526.png', '', '2018-01-17 10:08:47'),
(622, 1000371488, '/home/mi/project_file/pmc/tmp/71339366610399.png', '', '2018-01-17 10:09:52'),
(623, 1000605843, '/home/mi/project_file/pmc/tmp/71595162975927.png', '', '2018-01-17 10:14:07'),
(624, 1000469333, '/home/mi/project_file/pmc/tmp/71813925232994.png', '', '2018-01-17 10:17:46'),
(625, 1000320205, '/home/mi/project_file/pmc/tmp/71892893030084.png', '', '2018-01-17 10:19:05'),
(626, 1000353386, '/home/mi/project_file/pmc/tmp/3084285912058.png', '', '2018-01-18 05:15:59'),
(627, 1000052877, '/home/mi/project_file/pmc/tmp/5057363761758.jpeg', '', '2018-01-18 05:48:52'),
(628, 1000745352, '/home/mi/project_file/pmc/tmp/5062186928326.jpg', '', '2018-01-18 05:48:56'),
(629, 1000510037, '/home/mi/project_file/pmc/tmp/5285435796594.png', '', '2018-01-18 05:52:40'),
(630, 1000820642, '/home/mi/project_file/pmc/tmp/5288401335815.jpg', '', '2018-01-18 05:52:43'),
(631, 1000689638, '/home/mi/project_file/pmc/tmp/5295252521698.jpg', '', '2018-01-18 05:52:49'),
(632, 1000242087, '/home/mi/project_file/pmc/tmp/5297643418925.png', '', '2018-01-18 05:52:52'),
(633, 1000733819, '/home/mi/project_file/pmc/tmp/5305513372987.png', '', '2018-01-18 05:53:00'),
(634, 1000726183, '/home/mi/project_file/pmc/tmp/5310302022466.mp4', '', '2018-01-18 05:53:05'),
(635, 1000715560, '/home/mi/project_file/pmc/tmp/5319013451960.png', '', '2018-01-18 05:53:13'),
(636, 1000881126, '/home/mi/project_file/pmc/tmp/5325150121192.jpg', '', '2018-01-18 05:53:19'),
(637, 1000106473, '/home/mi/project_file/pmc/tmp/5332646008410.mp4', '', '2018-01-18 05:53:27'),
(638, 1000301686, '/home/mi/project_file/pmc/tmp/5341526079607.mp4', '', '2018-01-18 05:53:36'),
(639, 1000602190, '/home/mi/project_file/pmc/tmp/5621444081456.png', '', '2018-01-18 05:58:16'),
(640, 1000046994, '/home/mi/project_file/pmc/tmp/6304605685343.jpg', '', '2018-01-18 06:09:39'),
(641, 1000339232, '/home/mi/project_file/pmc/tmp/6382421638855.jpeg', '', '2018-01-18 06:10:57'),
(643, 1000194639, '/home/mi/project_file/pmc/tmp/6769518748437.jpg', '', '2018-01-18 06:17:24'),
(648, 1000262623, '/home/mi/project_file/pmc/tmp/7312995097593.png', '', '2018-01-18 06:26:27'),
(649, 1000514032, '/home/mi/project_file/pmc/tmp/7359444203373.png', '', '2018-01-18 06:27:14'),
(650, 1000692157, '/home/mi/project_file/pmc/tmp/7596599306988.png', '', '2018-01-18 06:31:11'),
(651, 1000855222, '/home/mi/project_file/pmc/tmp/8374872473109.png', '', '2018-01-18 06:44:09'),
(652, 1000128995, '/home/mi/project_file/pmc/tmp/8900846407709.png', '', '2018-01-18 06:52:55'),
(653, 1000221839, '/home/mi/project_file/pmc/tmp/26208423750269.png', '', '2018-01-18 11:41:23'),
(654, 1000459187, '/home/mi/project_file/pmc/tmp/26211446543795.png', '', '2018-01-18 11:41:26'),
(655, 1000379226, '/home/mi/project_file/pmc/tmp/26214881399404.png', '', '2018-01-18 11:41:29'),
(656, 1000664921, '/home/mi/project_file/pmc/tmp/26217562439441.png', '', '2018-01-18 11:41:32'),
(657, 1000329478, '/home/mi/project_file/pmc/tmp/26221681803263.png', '', '2018-01-18 11:41:36'),
(658, 1000638235, '/home/mi/project_file/pmc/tmp/26228027907776.png', '', '2018-01-18 11:41:42'),
(659, 1000100784, '/home/mi/project_file/pmc/tmp/26230965955201.png', '', '2018-01-18 11:41:45'),
(660, 1000572229, '/home/mi/project_file/pmc/tmp/26305972930036.webm', '', '2018-01-18 11:43:00'),
(661, 1000101033, '/home/mi/project_file/pmc/tmp/26309301221684.webm', '', '2018-01-18 11:43:04'),
(662, 1000273914, '/home/mi/project_file/pmc/tmp/26321525779040.ogv', '', '2018-01-18 11:43:16'),
(663, 1000076232, '/home/mi/project-file/pmc/tmp/1848817695648.png', '', '2018-01-22 05:34:50'),
(664, 1000027361, '/home/mi/project-file/pmc/tmp/2113899714902.png', '', '2018-01-22 05:39:15'),
(665, 1000295376, '/home/mi/project-file/pmc/tmp/6864466131797.png', '', '2018-01-22 06:58:26'),
(666, 1000536075, '/home/mi/project-file/pmc/tmp/6915014423261.png', '', '2018-01-22 06:59:16'),
(667, 1000201127, '/home/mi/project-file/pmc/tmp/8505625194657.png', '', '2018-01-22 07:25:47'),
(668, 1000842562, '/home/mi/project-file/pmc/tmp/8508857405527.png', '', '2018-01-22 07:25:50'),
(669, 1000543082, '/home/mi/project-file/pmc/tmp/8510882034589.png', '', '2018-01-22 07:25:52'),
(670, 1000839585, '/home/mi/project-file/pmc/tmp/8513516143187.png', '', '2018-01-22 07:25:55'),
(671, 1000183756, '/home/mi/project-file/pmc/tmp/15535678474892.png', '', '2018-01-23 10:39:59'),
(672, 1000515581, '/home/mi/project-file/pmc/tmp/15537810038985.png', '', '2018-01-23 10:40:01'),
(673, 1000553018, '/home/mi/project-file/pmc/tmp/16169326756078.png', '', '2018-01-23 10:50:32'),
(674, 1000531127, '/home/mi/project-file/pmc/tmp/16171165595516.png', '', '2018-01-23 10:50:34'),
(675, 1000700612, '/home/mi/project-file/pmc/tmp/18027587204002.png', '', '2018-01-23 11:21:30'),
(676, 1000456002, '/home/mi/project-file/pmc/tmp/18030115869967.png', '', '2018-01-23 11:21:33'),
(677, 1000032015, '/home/mi/project-file/pmc/tmp/18031961964935.png', '', '2018-01-23 11:21:35'),
(678, 1000253122, '/home/mi/project-file/pmc/tmp/18033943991816.png', '', '2018-01-23 11:21:37'),
(679, 1000092431, '/home/mi/project-file/pmc/tmp/18037545380625.png', '', '2018-01-23 11:21:40'),
(681, 1000123318, '/home/mi/project-file/pmc/tmp/18040888422962.png', '', '2018-01-23 11:21:44'),
(682, 1000814582, '/home/mi/project-file/pmc/tmp/18109477578001.png', '', '2018-01-23 11:22:52'),
(683, 1000328352, '/home/mi/project-file/pmc/tmp/18111051206713.png', '', '2018-01-23 11:22:54'),
(684, 1000112918, '/home/mi/project-file/pmc/tmp/18112951947337.png', '', '2018-01-23 11:22:56'),
(685, 1000070258, '/home/mi/project-file/pmc/tmp/18116312409502.png', '', '2018-01-23 11:22:59'),
(686, 1000548139, '/home/mi/project-file/pmc/tmp/18118072336225.png', '', '2018-01-23 11:23:01'),
(687, 1000519989, '/home/mi/project-file/pmc/tmp/18121285467487.png', '', '2018-01-23 11:23:04'),
(688, 1000656297, '/home/mi/project-file/pmc/tmp/21283924133467.png', '', '2018-01-23 12:15:47'),
(689, 1000616219, '/home/mi/project-file/pmc/tmp/21289994477970.png', '', '2018-01-23 12:15:53'),
(690, 1000579450, '/home/mi/project-file/pmc/tmp/21293014701054.png', '', '2018-01-23 12:15:56'),
(691, 1000818022, '/home/mi/project-file/pmc/tmp/21295132778548.png', '', '2018-01-23 12:15:58'),
(692, 1000014510, '/home/mi/project-file/pmc/tmp/21365938950072.png', '', '2018-01-23 12:17:09'),
(693, 1000284281, '/home/mi/project-file/pmc/tmp/21449962816055.webm', '', '2018-01-23 12:18:33'),
(695, 1000867876, '/home/mi/project-file/pmc/tmp/21469133274055.png', '', '2018-01-23 12:18:52'),
(696, 1000284375, '/home/mi/project-file/pmc/tmp/21471441988924.png', '', '2018-01-23 12:18:54'),
(697, 1000786890, '/home/mi/project-file/pmc/tmp/21473492780631.png', '', '2018-01-23 12:18:56'),
(698, 1000370893, '/home/mi/project-file/pmc/tmp/21475634606467.png', '', '2018-01-23 12:18:59'),
(699, 1000863061, '/home/mi/project-file/pmc/tmp/21479797459445.webm', '', '2018-01-23 12:19:03'),
(700, 1000038064, '/home/mi/project-file/pmc/tmp/21482651797138.ogv', '', '2018-01-23 12:19:06'),
(701, 1000534062, '/home/mi/project-file/pmc/tmp/21496720979782.png', '', '2018-01-23 12:19:20'),
(702, 1000417362, '/home/mi/project-file/pmc/tmp/21499306674316.png', '', '2018-01-23 12:19:22'),
(703, 1000330823, '/home/mi/project-file/pmc/tmp/22867088934663.png', '', '2018-01-23 12:42:10'),
(704, 1000014618, '/home/mi/project-file/pmc/tmp/22869033950953.png', '', '2018-01-23 12:42:12'),
(705, 1000211625, '/home/mi/project-file/pmc/tmp/22876732308438.png', '', '2018-01-23 12:42:20'),
(706, 1000744857, '/home/mi/project-file/pmc/tmp/22879041212807.png', '', '2018-01-23 12:42:22'),
(707, 1000456536, '/home/mi/project-file/pmc/tmp/22881206928690.png', '', '2018-01-23 12:42:24'),
(708, 1000682063, '/home/mi/project-file/pmc/tmp/22884521722714.png', '', '2018-01-23 12:42:27'),
(709, 1000582460, '/home/mi/project-file/pmc/tmp/22886446297999.png', '', '2018-01-23 12:42:29'),
(710, 1000750202, '/home/mi/project-file/pmc/tmp/22889217367992.png', '', '2018-01-23 12:42:32'),
(711, 1000893324, '/home/mi/project-file/pmc/tmp/22891879740647.png', '', '2018-01-23 12:42:35'),
(712, 1000431570, '/home/mi/project-file/pmc/tmp/22896536045830.png', '', '2018-01-23 12:42:39'),
(713, 1000424913, '/home/mi/project-file/pmc/tmp/22901669952087.ogv', '', '2018-01-23 12:42:45'),
(714, 1000578906, '/home/mi/project-file/pmc/tmp/22907336784609.png', '', '2018-01-23 12:42:50'),
(715, 1000171396, '/home/mi/project-file/pmc/tmp/22913623041639.webm', '', '2018-01-23 12:42:57'),
(716, 1000042357, '/home/mi/project-file/pmc/tmp/22917834558788.png', '', '2018-01-23 12:43:01'),
(717, 1000494803, '/home/mi/project-file/pmc/tmp/22922503608299.webm', '', '2018-01-23 12:43:05'),
(718, 1000507864, '/home/mi/project-file/pmc/tmp/23014327243379.png', '', '2018-01-23 12:44:37'),
(719, 1000640448, '/home/mi/project-file/pmc/tmp/23018267884342.png', '', '2018-01-23 12:44:41'),
(720, 1000543443, '/home/mi/project-file/pmc/tmp/23020559379140.png', '', '2018-01-23 12:44:43'),
(721, 1000489585, '/home/mi/project-file/pmc/tmp/23022442444987.png', '', '2018-01-23 12:44:45'),
(722, 1000757137, '/home/mi/project-file/pmc/tmp/23024458684509.png', '', '2018-01-23 12:44:47'),
(723, 1000192414, '/home/mi/project-file/pmc/tmp/23027104867148.png', '', '2018-01-23 12:44:50'),
(724, 1000103398, '/home/mi/project-file/pmc/tmp/23029131008171.png', '', '2018-01-23 12:44:52'),
(725, 1000416561, '/home/mi/project-file/pmc/tmp/23031419002332.png', '', '2018-01-23 12:44:54'),
(726, 1000419973, '/home/mi/project-file/pmc/tmp/23035149683345.png', '', '2018-01-23 12:44:58'),
(727, 1000360114, '/home/mi/project-file/pmc/tmp/23044184521082.png', '', '2018-01-23 12:45:07'),
(728, 1000851134, '/home/mi/project-file/pmc/tmp/23047063019132.mp4', '', '2018-01-23 12:45:10'),
(729, 1000403008, '/home/mi/project-file/pmc/tmp/23052503674029.png', '', '2018-01-23 12:45:15'),
(730, 1000781882, '/home/mi/project-file/pmc/tmp/23094349200259.png', '', '2018-01-23 12:45:57'),
(731, 1000195664, '/home/mi/project-file/pmc/tmp/23100649015666.png', '', '2018-01-23 12:46:04'),
(732, 1000401234, '/home/mi/project-file/pmc/tmp/23102898841558.png', '', '2018-01-23 12:46:06'),
(733, 1000806070, '/home/mi/project-file/pmc/tmp/23110306186626.png', '', '2018-01-23 12:46:13'),
(734, 1000189405, '/home/mi/project-file/pmc/tmp/23117407184753.png', '', '2018-01-23 12:46:20'),
(735, 1000789896, '/home/mi/project-file/pmc/tmp/23121318365354.png', '', '2018-01-23 12:46:24'),
(736, 1000600371, '/home/mi/project-file/pmc/tmp/23124118163126.png', '', '2018-01-23 12:46:27'),
(737, 1000479214, '/home/mi/project-file/pmc/tmp/23126962296197.png', '', '2018-01-23 12:46:30'),
(738, 1000740083, '/home/mi/project-file/pmc/tmp/23130858958648.png', '', '2018-01-23 12:46:34'),
(739, 1000834611, '/home/mi/project-file/pmc/tmp/23134620827543.mp4', '', '2018-01-23 12:46:38'),
(740, 1000685629, '/home/mi/project-file/pmc/tmp/23138326728422.png', '', '2018-01-23 12:46:41'),
(741, 1000673320, '/home/mi/project-file/pmc/tmp/23140267392708.png', '', '2018-01-23 12:46:43'),
(742, 1000579984, '/home/mi/project-file/pmc/tmp/23144038048416.mp4', '', '2018-01-23 12:46:47'),
(743, 1000132681, '/home/mi/project-file/pmc/tmp/23146925898435.ogv', '', '2018-01-23 12:46:50'),
(744, 1000146173, '/home/mi/project-file/pmc/tmp/23233089079943.png', '', '2018-01-23 12:48:16'),
(745, 1000208715, '/home/mi/project-file/pmc/tmp/23241997089266.png', '', '2018-01-23 12:48:25'),
(746, 1000723918, '/home/mi/project-file/pmc/tmp/23245026763726.png', '', '2018-01-23 12:48:28'),
(747, 1000617023, '/home/mi/project-file/pmc/tmp/23248061943396.png', '', '2018-01-23 12:48:31'),
(748, 1000081732, '/home/mi/project-file/pmc/tmp/23251162745785.png', '', '2018-01-23 12:48:34'),
(749, 1000618360, '/home/mi/project-file/pmc/tmp/23253831597156.png', '', '2018-01-23 12:48:37'),
(750, 1000025809, '/home/mi/project-file/pmc/tmp/23258878430766.png', '', '2018-01-23 12:48:42'),
(751, 1000510283, '/home/mi/project-file/pmc/tmp/23262979660370.mp4', '', '2018-01-23 12:48:46'),
(752, 1000153163, '/home/mi/project-file/pmc/tmp/23267165856847.png', '', '2018-01-23 12:48:50'),
(753, 1000030956, '/home/mi/project-file/pmc/tmp/23269794637512.png', '', '2018-01-23 12:48:53'),
(754, 1000563959, '/home/mi/project-file/pmc/tmp/23273971536724.ogv', '', '2018-01-23 12:48:57'),
(755, 1000525626, '/home/mi/project-file/pmc/tmp/23276610137459.ogv', '', '2018-01-23 12:49:00'),
(756, 1000521802, '/home/mi/project-file/pmc/tmp/23504519866951.png', '', '2018-01-23 12:52:47'),
(757, 1000667197, '/home/mi/project-file/pmc/tmp/23507952236478.png', '', '2018-01-23 12:52:51'),
(758, 1000850344, '/home/mi/project-file/pmc/tmp/23509882534000.png', '', '2018-01-23 12:52:53'),
(759, 1000333616, '/home/mi/project-file/pmc/tmp/23513974515602.png', '', '2018-01-23 12:52:57'),
(760, 1000386204, '/home/mi/project-file/pmc/tmp/23571283928946.png', '', '2018-01-23 12:53:54'),
(761, 1000297206, '/home/mi/project-file/pmc/tmp/23574145281638.png', '', '2018-01-23 12:53:57'),
(762, 1000139687, '/home/mi/project-file/pmc/tmp/23576047440453.png', '', '2018-01-23 12:53:59'),
(763, 1000612027, '/home/mi/project-file/pmc/tmp/23578256074985.png', '', '2018-01-23 12:54:01'),
(764, 1000125585, '/home/mi/project-file/pmc/tmp/23580175396336.png', '', '2018-01-23 12:54:03'),
(765, 1000502590, '/home/mi/project-file/pmc/tmp/23632548090371.png', '', '2018-01-23 12:54:55'),
(766, 1000025312, '/home/mi/project-file/pmc/tmp/23635137271156.png', '', '2018-01-23 12:54:58'),
(767, 1000600021, '/home/mi/project-file/pmc/tmp/23638158603807.png', '', '2018-01-23 12:55:01'),
(768, 1000294389, '/home/mi/project-file/pmc/tmp/23640483659033.png', '', '2018-01-23 12:55:03'),
(769, 1000865392, '/home/mi/project-file/pmc/tmp/23643354204501.png', '', '2018-01-23 12:55:06'),
(770, 1000392472, '/home/mi/project-file/pmc/tmp/23647374781584.ogv', '', '2018-01-23 12:55:10'),
(771, 1000310285, '/home/mi/project-file/pmc/tmp/23652415009547.png', '', '2018-01-23 12:55:15'),
(772, 1000104305, '/home/mi/project-file/pmc/tmp/23655790239553.webm', '', '2018-01-23 12:55:19'),
(773, 1000375871, '/home/mi/project-file/pmc/tmp/23659773719409.png', '', '2018-01-23 12:55:23'),
(774, 1000803582, '/home/mi/project-file/pmc/tmp/23663035163076.mp4', '', '2018-01-23 12:55:26'),
(775, 1000778260, '/home/mi/project-file/pmc/tmp/30229836871551.png', '', '2018-01-23 14:44:53'),
(776, 1000079766, '/home/mi/project-file/pmc/tmp/30235018074762.png', '', '2018-01-23 14:44:58'),
(777, 1000484011, '/home/mi/project-file/pmc/tmp/30237141540851.png', '', '2018-01-23 14:45:00'),
(778, 1000322199, '/home/mi/project-file/pmc/tmp/30239926211112.png', '', '2018-01-23 14:45:03'),
(779, 1000135361, '/home/mi/project-file/pmc/tmp/30242755734094.png', '', '2018-01-23 14:45:06'),
(780, 1000274313, '/home/mi/project-file/pmc/tmp/30246055829331.png', '', '2018-01-23 14:45:09'),
(781, 1000401958, '/home/mi/project-file/pmc/tmp/30248861639317.png', '', '2018-01-23 14:45:12'),
(782, 1000103596, '/home/mi/project-file/pmc/tmp/30254336057651.png', '', '2018-01-23 14:45:17'),
(783, 1000138292, '/home/mi/project-file/pmc/tmp/30257671686022.mp4', '', '2018-01-23 14:45:21'),
(784, 1000569291, '/home/mi/project-file/pmc/tmp/30262291919697.png', '', '2018-01-23 14:45:25'),
(785, 1000875683, '/home/mi/project-file/pmc/tmp/30268579223164.png', '', '2018-01-23 14:45:31'),
(786, 1000254414, '/home/mi/project-file/pmc/tmp/30272851086903.webm', '', '2018-01-23 14:45:36'),
(787, 1000260187, '/home/mi/project-file/pmc/tmp/30275071139478.ogv', '', '2018-01-23 14:45:38'),
(788, 1000743201, '/home/mi/project-file/pmc/tmp/30328806885105.png', '', '2018-01-23 14:46:32'),
(789, 1000173445, '/home/mi/project-file/pmc/tmp/30332025535411.png', '', '2018-01-23 14:46:35'),
(790, 1000098727, '/home/mi/project-file/pmc/tmp/30334020310499.png', '', '2018-01-23 14:46:37'),
(791, 1000402976, '/home/mi/project-file/pmc/tmp/30336035922761.png', '', '2018-01-23 14:46:39'),
(792, 1000748795, '/home/mi/project-file/pmc/tmp/30337751952170.png', '', '2018-01-23 14:46:41'),
(793, 1000883642, '/home/mi/project-file/pmc/tmp/30340147685159.png', '', '2018-01-23 14:46:43'),
(794, 1000838942, '/home/mi/project-file/pmc/tmp/30343923395433.png', '', '2018-01-23 14:46:47'),
(795, 1000836685, '/home/mi/project-file/pmc/tmp/30345848086425.png', '', '2018-01-23 14:46:49'),
(796, 1000538147, '/home/mi/project-file/pmc/tmp/30352613482853.webm', '', '2018-01-23 14:46:56'),
(797, 1000076420, '/home/mi/project-file/pmc/tmp/30355819248276.webm', '', '2018-01-23 14:46:59'),
(798, 1000128202, '/home/mi/project-file/pmc/tmp/30360045963671.png', '', '2018-01-23 14:47:03'),
(799, 1000196282, '/home/mi/project-file/pmc/tmp/30403754199833.png', '', '2018-01-23 14:47:47'),
(800, 1000359105, '/home/mi/project-file/pmc/tmp/30405502106555.png', '', '2018-01-23 14:47:48'),
(801, 1000815133, '/home/mi/project-file/pmc/tmp/30407224547071.png', '', '2018-01-23 14:47:50'),
(802, 1000463441, '/home/mi/project-file/pmc/tmp/30409423532884.png', '', '2018-01-23 14:47:52'),
(803, 1000241413, '/home/mi/project-file/pmc/tmp/30412041316702.png', '', '2018-01-23 14:47:55'),
(804, 1000413859, '/home/mi/project-file/pmc/tmp/30415063207610.mp4', '', '2018-01-23 14:47:58'),
(805, 1000734814, '/home/mi/project-file/pmc/tmp/30418940972320.png', '', '2018-01-23 14:48:02'),
(806, 1000362970, '/home/mi/project-file/pmc/tmp/30427797505208.png', '', '2018-01-23 14:48:11'),
(807, 1000480311, '/home/mi/project-file/pmc/tmp/30462763274102.mp4', '', '2018-01-23 14:48:46'),
(808, 1000483156, '/home/mi/project-file/pmc/tmp/30468007325883.mp4', '', '2018-01-23 14:48:51'),
(809, 1000428475, '/home/mi/project-file/pmc/tmp/6479450106574.png', '', '2018-01-24 07:06:20'),
(810, 1000341440, '/home/mi/project-file/pmc/tmp/6482876823251.png', '', '2018-01-24 07:06:23'),
(811, 1000671812, '/home/mi/project-file/pmc/tmp/6491029231857.png', '', '2018-01-24 07:06:31'),
(812, 1000307187, '/home/mi/project-file/pmc/tmp/6493205314067.png', '', '2018-01-24 07:06:33'),
(813, 1000178338, '/home/mi/project-file/pmc/tmp/6495848060404.png', '', '2018-01-24 07:06:36'),
(814, 1000402178, '/home/mi/project-file/pmc/tmp/6498917028926.png', '', '2018-01-24 07:06:39'),
(815, 1000626648, '/home/mi/project-file/pmc/tmp/6501544989632.png', '', '2018-01-24 07:06:42'),
(816, 1000425372, '/home/mi/project-file/pmc/tmp/6506310150413.png', '', '2018-01-24 07:06:47'),
(817, 1000598810, '/home/mi/project-file/pmc/tmp/6511997796214.mp4', '', '2018-01-24 07:06:52'),
(818, 1000858140, '/home/mi/project-file/pmc/tmp/6516859430655.png', '', '2018-01-24 07:06:57'),
(819, 1000877571, '/home/mi/project-file/pmc/tmp/6518755440731.png', '', '2018-01-24 07:06:59'),
(820, 1000180681, '/home/mi/project-file/pmc/tmp/6522170594702.mp4', '', '2018-01-24 07:07:02'),
(821, 1000156171, '/home/mi/project-file/pmc/tmp/6526118376571.png', '', '2018-01-24 07:07:06'),
(822, 1000316260, '/home/mi/project-file/pmc/tmp/6529374258819.png', '', '2018-01-24 07:07:10'),
(823, 1000183234, '/home/mi/project-file/pmc/tmp/6534266231060.ogv', '', '2018-01-24 07:07:15'),
(824, 1000411058, '/home/mi/project-file/pmc/tmp/24323552999547.png', '', '2018-01-24 12:03:44'),
(825, 1000365526, '/home/mi/project-file/pmc/tmp/24328910014935.png', '', '2018-01-24 12:03:49'),
(826, 1000484327, '/home/mi/project-file/pmc/tmp/24330856388990.png', '', '2018-01-24 12:03:51'),
(827, 1000500540, '/home/mi/project-file/pmc/tmp/24332910566449.png', '', '2018-01-24 12:03:53'),
(828, 1000429280, '/home/mi/project-file/pmc/tmp/24335492264398.png', '', '2018-01-24 12:03:56'),
(829, 1000523822, '/home/mi/project-file/pmc/tmp/24338681033116.png', '', '2018-01-24 12:03:59'),
(830, 1000127654, '/home/mi/project-file/pmc/tmp/24342647711364.ogv', '', '2018-01-24 12:04:03'),
(831, 1000811153, '/home/mi/project-file/pmc/tmp/24346640660818.png', '', '2018-01-24 12:04:07'),
(832, 1000394671, '/home/mi/project-file/pmc/tmp/24349713607705.ogv', '', '2018-01-24 12:04:10'),
(833, 1000493244, '/home/mi/project-file/pmc/tmp/24353227776752.webm', '', '2018-01-24 12:04:14'),
(834, 1000073731, '/home/mi/project-file/pmc/tmp/24356240608653.png', '', '2018-01-24 12:04:17'),
(835, 1000584782, '/home/mi/project-file/pmc/tmp/1262901082353.png', '', '2018-01-25 05:35:11'),
(836, 1000003227, '/home/mi/project-file/pmc/tmp/13470570552373.png', '', '2018-01-25 08:58:39'),
(837, 1000331225, '/home/mi/project-file/pmc/tmp/13748921587501.jpeg', '', '2018-01-25 09:03:17'),
(838, 1000658840, '/home/mi/project-file/pmc/tmp/13748921740599.jpeg', '', '2018-01-25 09:03:17'),
(839, 1000620769, '/home/mi/project-file/pmc/tmp/13749043395230.jpeg', '', '2018-01-25 09:03:17'),
(840, 1000360323, '/home/mi/project-file/pmc/tmp/13749047735058.jpeg', '', '2018-01-25 09:03:17'),
(841, 1000649916, '/home/mi/project-file/pmc/tmp/13749084781597.jpeg', '', '2018-01-25 09:03:17'),
(842, 1000391444, '/home/mi/project-file/pmc/tmp/13857211583679.jpeg', '', '2018-01-25 09:05:05'),
(843, 1000368935, '/home/mi/project-file/pmc/tmp/13859404202988.jpeg', '', '2018-01-25 09:05:08'),
(844, 1000047784, '/home/mi/project-file/pmc/tmp/13909876333259.jpeg', '', '2018-01-25 09:05:58'),
(845, 1000119409, '/home/mi/project-file/pmc/tmp/13909878303693.jpeg', '', '2018-01-25 09:05:58'),
(846, 1000188441, '/home/mi/project-file/pmc/tmp/13909927294054.jpeg', '', '2018-01-25 09:05:58'),
(847, 1000018693, '/home/mi/project-file/pmc/tmp/13909960311728.jpeg', '', '2018-01-25 09:05:58'),
(848, 1000669511, '/home/mi/project-file/pmc/tmp/13916960115846.jpeg', '', '2018-01-25 09:06:05'),
(849, 1000195357, '/home/mi/project-file/pmc/tmp/13916964138908.jpeg', '', '2018-01-25 09:06:05'),
(850, 1000528327, '/home/mi/project-file/pmc/tmp/13917017278479.jpeg', '', '2018-01-25 09:06:05'),
(851, 1000466264, '/home/mi/project-file/pmc/tmp/13935043271412.jpeg', '', '2018-01-25 09:06:23'),
(852, 1000131510, '/home/mi/project-file/pmc/tmp/13935043307453.jpeg', '', '2018-01-25 09:06:23'),
(853, 1000218330, '/home/mi/project-file/pmc/tmp/13935092042690.jpeg', '', '2018-01-25 09:06:23'),
(854, 1000129907, '/home/mi/project-file/pmc/tmp/13941314443608.ogv', '', '2018-01-25 09:06:30'),
(855, 1000491465, '/home/mi/project-file/pmc/tmp/13959956909258.mp4', '', '2018-01-25 09:06:48'),
(856, 1000559284, '/home/mi/project-file/pmc/tmp/13962762775109.mp4', '', '2018-01-25 09:06:51'),
(857, 1000620617, '/home/mi/project-file/pmc/tmp/13971782211401.jpeg', '', '2018-01-25 09:07:00'),
(858, 1000408467, '/home/mi/project-file/pmc/tmp/13974557998564.jpeg', '', '2018-01-25 09:07:03'),
(859, 1000788219, '/home/mi/project-file/pmc/tmp/19843015845797.jpeg', '', '2018-01-26 14:44:06'),
(860, 1000681928, '/home/mi/project-file/pmc/tmp/19843015845226.jpeg', '', '2018-01-26 14:44:06'),
(861, 1000395411, '/home/mi/project-file/pmc/tmp/19843203411319.jpeg', '', '2018-01-26 14:44:06'),
(862, 1000654878, '/home/mi/project-file/pmc/tmp/19843222788788.jpeg', '', '2018-01-26 14:44:06'),
(863, 1000465864, '/home/mi/project-file/pmc/tmp/19843245297109.jpeg', '', '2018-01-26 14:44:07'),
(864, 1000805079, '/home/mi/project-file/pmc/tmp/19843277861443.jpeg', '', '2018-01-26 14:44:07'),
(865, 1000085246, '/home/mi/project-file/pmc/tmp/19851658397795.jpeg', '', '2018-01-26 14:44:15'),
(866, 1000046275, '/home/mi/project-file/pmc/tmp/19894261507857.jpeg', '', '2018-01-26 14:44:58'),
(867, 1000660678, '/home/mi/project-file/pmc/tmp/19894268620644.jpeg', '', '2018-01-26 14:44:58'),
(868, 1000325813, '/home/mi/project-file/pmc/tmp/19894317313389.jpeg', '', '2018-01-26 14:44:58'),
(869, 1000108741, '/home/mi/project-file/pmc/tmp/19901745331906.jpeg', '', '2018-01-26 14:45:05'),
(870, 1000893413, '/home/mi/project-file/pmc/tmp/19903388950062.jpeg', '', '2018-01-26 14:45:07'),
(871, 1000254341, '/home/mi/project-file/pmc/tmp/19906946295790.jpeg', '', '2018-01-26 14:45:10'),
(872, 1000160287, '/home/mi/project-file/pmc/tmp/19906958524140.jpeg', '', '2018-01-26 14:45:10'),
(873, 1000251839, '/home/mi/project-file/pmc/tmp/19907007066644.jpeg', '', '2018-01-26 14:45:10'),
(874, 1000055685, '/home/mi/project-file/pmc/tmp/19907039487753.jpeg', '', '2018-01-26 14:45:10'),
(875, 1000721775, '/home/mi/project-file/pmc/tmp/19907072083304.jpeg', '', '2018-01-26 14:45:10'),
(876, 1000080617, '/home/mi/project-file/pmc/tmp/19907106743531.jpeg', '', '2018-01-26 14:45:10'),
(877, 1000320420, '/home/mi/project-file/pmc/tmp/19911563746472.jpeg', '', '2018-01-26 14:45:15'),
(878, 1000712658, '/home/mi/project-file/pmc/tmp/19911564075876.jpeg', '', '2018-01-26 14:45:15'),
(879, 1000423606, '/home/mi/project-file/pmc/tmp/19911624626968.jpeg', '', '2018-01-26 14:45:15'),
(880, 1000721566, '/home/mi/project-file/pmc/tmp/19918499635968.jpeg', '', '2018-01-26 14:45:22'),
(881, 1000025456, '/home/mi/project-file/pmc/tmp/19918504844168.jpeg', '', '2018-01-26 14:45:22'),
(882, 1000761766, '/home/mi/project-file/pmc/tmp/19918555889575.jpeg', '', '2018-01-26 14:45:22'),
(883, 1000276839, '/home/mi/project-file/pmc/tmp/19918602841290.jpeg', '', '2018-01-26 14:45:22'),
(884, 1000866463, '/home/mi/project-file/pmc/tmp/19918631122054.jpeg', '', '2018-01-26 14:45:22'),
(885, 1000897682, '/home/mi/project-file/pmc/tmp/19918664849274.jpeg', '', '2018-01-26 14:45:22'),
(886, 1000772707, '/home/mi/project-file/pmc/tmp/19926854499488.ogv', '', '2018-01-26 14:45:30'),
(887, 1000497116, '/home/mi/project-file/pmc/tmp/19935474963550.jpeg', '', '2018-01-26 14:45:39'),
(888, 1000452953, '/home/mi/project-file/pmc/tmp/19935477778224.jpeg', '', '2018-01-26 14:45:39'),
(889, 1000067098, '/home/mi/project-file/pmc/tmp/19935598915743.jpeg', '', '2018-01-26 14:45:39'),
(890, 1000340092, '/home/mi/project-file/pmc/tmp/19939662064682.ogv', '', '2018-01-26 14:45:43'),
(891, 1000665575, '/home/mi/project-file/pmc/tmp/19942292099998.ogv', '', '2018-01-26 14:45:46'),
(892, 1000196270, '/home/mi/project-file/pmc/tmp/19948973781580.jpeg', '', '2018-01-26 14:45:52'),
(893, 1000063246, '/home/mi/project-file/pmc/tmp/19948975061314.jpeg', '', '2018-01-26 14:45:52'),
(894, 1000735546, '/home/mi/project-file/pmc/tmp/19949033095005.jpeg', '', '2018-01-26 14:45:52'),
(895, 1000057998, '/home/mi/project-file/pmc/tmp/663301384890.jpeg', '', '2018-01-29 05:58:57'),
(896, 1000404978, '/home/mi/project-file/pmc/tmp/666210200053.jpeg', '', '2018-01-29 05:59:00'),
(897, 1000605377, '/home/mi/project-file/pmc/tmp/667821678784.jpeg', '', '2018-01-29 05:59:01'),
(898, 1000452351, '/home/mi/project-file/pmc/tmp/671602138741.jpeg', '', '2018-01-29 05:59:05'),
(899, 1000187351, '/home/mi/project-file/pmc/tmp/671611460985.jpeg', '', '2018-01-29 05:59:05'),
(900, 1000807439, '/home/mi/project-file/pmc/tmp/671664904114.jpeg', '', '2018-01-29 05:59:05'),
(901, 1000781671, '/home/mi/project-file/pmc/tmp/671696864760.jpeg', '', '2018-01-29 05:59:05'),
(902, 1000098391, '/home/mi/project-file/pmc/tmp/671729308211.jpeg', '', '2018-01-29 05:59:05'),
(903, 1000160936, '/home/mi/project-file/pmc/tmp/676477526911.jpeg', '', '2018-01-29 05:59:10'),
(904, 1000871494, '/home/mi/project-file/pmc/tmp/676488690557.jpeg', '', '2018-01-29 05:59:10'),
(905, 1000739723, '/home/mi/project-file/pmc/tmp/676538801494.jpeg', '', '2018-01-29 05:59:10'),
(906, 1000057342, '/home/mi/project-file/pmc/tmp/676573729729.jpeg', '', '2018-01-29 05:59:10'),
(907, 1000813121, '/home/mi/project-file/pmc/tmp/676604782196.jpeg', '', '2018-01-29 05:59:10'),
(908, 1000625169, '/home/mi/project-file/pmc/tmp/685171213112.jpeg', '', '2018-01-29 05:59:19'),
(909, 1000226623, '/home/mi/project-file/pmc/tmp/685172961966.jpeg', '', '2018-01-29 05:59:19'),
(910, 1000428169, '/home/mi/project-file/pmc/tmp/685234728404.jpeg', '', '2018-01-29 05:59:19'),
(911, 1000813558, '/home/mi/project-file/pmc/tmp/685266434444.jpeg', '', '2018-01-29 05:59:19'),
(912, 1000487301, '/home/mi/project-file/pmc/tmp/685299097406.jpeg', '', '2018-01-29 05:59:19'),
(913, 1000152146, '/home/mi/project-file/pmc/tmp/694918112280.mp4', '', '2018-01-29 05:59:28'),
(914, 1000869981, '/home/mi/project-file/pmc/tmp/703077701756.mp4', '', '2018-01-29 05:59:36'),
(915, 1000600490, '/home/mi/project-file/pmc/tmp/705351421913.ogv', '', '2018-01-29 05:59:39'),
(916, 1000348591, '/home/mi/project-file/pmc/tmp/712437087327.jpeg', '', '2018-01-29 05:59:46'),
(917, 1000014738, '/home/mi/project-file/pmc/tmp/712437112135.jpeg', '', '2018-01-29 05:59:46'),
(918, 1000009826, '/home/mi/project-file/pmc/tmp/712540568264.jpeg', '', '2018-01-29 05:59:46'),
(919, 1000782534, '/home/mi/project-file/pmc/tmp/717704097736.jpeg', '', '2018-01-29 05:59:51'),
(920, 1000094022, '/home/mi/project-file/pmc/tmp/717704247556.jpeg', '', '2018-01-29 05:59:51'),
(921, 1000451073, '/home/mi/project-file/pmc/tmp/717773802568.jpeg', '', '2018-01-29 05:59:51'),
(922, 1000106690, '/home/mi/project-file/pmc/tmp/15342308636326.jpeg', '', '2018-01-30 10:04:03'),
(923, 1000007626, '/home/mi/project-file/pmc/tmp/15344243387066.jpeg', '', '2018-01-30 10:04:05'),
(924, 1000295337, '/home/mi/project-file/pmc/tmp/15349964198921.jpeg', '', '2018-01-30 10:04:10'),
(925, 1000093299, '/home/mi/project-file/pmc/tmp/15349981783688.jpeg', '', '2018-01-30 10:04:10'),
(926, 1000604344, '/home/mi/project-file/pmc/tmp/15350024555073.jpeg', '', '2018-01-30 10:04:10'),
(927, 1000168063, '/home/mi/project-file/pmc/tmp/15354535536439.jpeg', '', '2018-01-30 10:04:15'),
(928, 1000650981, '/home/mi/project-file/pmc/tmp/15354535491238.jpeg', '', '2018-01-30 10:04:15'),
(929, 1000128730, '/home/mi/project-file/pmc/tmp/15354586863157.jpeg', '', '2018-01-30 10:04:15'),
(930, 1000446621, '/home/mi/project-file/pmc/tmp/15388824164421.ogv', '', '2018-01-30 10:04:49'),
(931, 1000011143, '/home/mi/project-file/pmc/tmp/15393177595239.jpeg', '', '2018-01-30 10:04:53'),
(932, 1000612474, '/home/mi/project-file/pmc/tmp/15400986380058.jpeg', '', '2018-01-30 10:05:01'),
(933, 1000258595, '/home/mi/project-file/pmc/tmp/15400986976355.jpeg', '', '2018-01-30 10:05:01'),
(934, 1000296991, '/home/mi/project-file/pmc/tmp/15401049028333.jpeg', '', '2018-01-30 10:05:01'),
(935, 1000713886, '/home/mi/project-file/pmc/tmp/15408401946483.mp4', '', '2018-01-30 10:05:09'),
(936, 1000619486, '/home/mi/project-file/pmc/tmp/15416971965751.jpeg', '', '2018-01-30 10:05:17'),
(937, 1000313214, '/home/mi/project-file/pmc/tmp/15416974182255.jpeg', '', '2018-01-30 10:05:17'),
(938, 1000266056, '/home/mi/project-file/pmc/tmp/15421253813035.ogv', '', '2018-01-30 10:05:22'),
(939, 1000528773, '/home/mi/project-file/pmc/tmp/15864369512236.jpeg', '', '2018-01-30 10:12:45'),
(940, 1000748506, '/home/mi/project-file/pmc/tmp/15864370433515.jpeg', '', '2018-01-30 10:12:45'),
(941, 1000369755, '/home/mi/project-file/pmc/tmp/15864498941657.jpeg', '', '2018-01-30 10:12:45'),
(942, 1000257342, '/home/mi/project-file/pmc/tmp/15875925775534.jpeg', '', '2018-01-30 10:12:56'),
(943, 1000301582, '/home/mi/project-file/pmc/tmp/15879049234139.jpeg', '', '2018-01-30 10:12:59'),
(944, 1000091276, '/home/mi/project-file/pmc/tmp/15879052575435.jpeg', '', '2018-01-30 10:12:59'),
(945, 1000014373, '/home/mi/project-file/pmc/tmp/15879142918699.jpeg', '', '2018-01-30 10:12:59'),
(946, 1000569434, '/home/mi/project-file/pmc/tmp/15879233705629.jpeg', '', '2018-01-30 10:13:00'),
(947, 1000440022, '/home/mi/project-file/pmc/tmp/15882179377722.jpeg', '', '2018-01-30 10:13:02'),
(948, 1000879343, '/home/mi/project-file/pmc/tmp/15882179349889.jpeg', '', '2018-01-30 10:13:02'),
(949, 1000148494, '/home/mi/project-file/pmc/tmp/15882244449872.jpeg', '', '2018-01-30 10:13:03'),
(950, 1000678781, '/home/mi/project-file/pmc/tmp/15882275972900.jpeg', '', '2018-01-30 10:13:03'),
(951, 1000527872, '/home/mi/project-file/pmc/tmp/15886595661493.jpeg', '', '2018-01-30 10:13:07'),
(952, 1000835975, '/home/mi/project-file/pmc/tmp/15886595751223.jpeg', '', '2018-01-30 10:13:07'),
(953, 1000805437, '/home/mi/project-file/pmc/tmp/15886685031236.jpeg', '', '2018-01-30 10:13:07'),
(954, 1000530421, '/home/mi/project-file/pmc/tmp/15886717652778.jpeg', '', '2018-01-30 10:13:07'),
(955, 1000004682, '/home/mi/project-file/pmc/tmp/15891923293425.jpeg', '', '2018-01-30 10:13:12'),
(956, 1000179377, '/home/mi/project-file/pmc/tmp/15891923212103.jpeg', '', '2018-01-30 10:13:12'),
(957, 1000557348, '/home/mi/project-file/pmc/tmp/15892021336659.jpeg', '', '2018-01-30 10:13:12'),
(958, 1000393223, '/home/mi/project-file/pmc/tmp/15892052937321.jpeg', '', '2018-01-30 10:13:12'),
(959, 1000426619, '/home/mi/project-file/pmc/tmp/15905349574780.jpeg', '', '2018-01-30 10:13:26'),
(960, 1000183310, '/home/mi/project-file/pmc/tmp/15905349542771.jpeg', '', '2018-01-30 10:13:26'),
(961, 1000218417, '/home/mi/project-file/pmc/tmp/15905426868101.jpeg', '', '2018-01-30 10:13:26'),
(962, 1000092208, '/home/mi/project-file/pmc/tmp/15905460552694.jpeg', '', '2018-01-30 10:13:26'),
(963, 1000879818, '/home/mi/project-file/pmc/tmp/15910466413627.ogv', '', '2018-01-30 10:13:31'),
(964, 1000505984, '/home/mi/project-file/pmc/tmp/15920659874220.jpeg', '', '2018-01-30 10:13:41'),
(965, 1000605247, '/home/mi/project-file/pmc/tmp/15920659859315.jpeg', '', '2018-01-30 10:13:41'),
(966, 1000346817, '/home/mi/project-file/pmc/tmp/15920719786815.jpeg', '', '2018-01-30 10:13:41'),
(967, 1000794994, '/home/mi/project-file/pmc/tmp/15934053502281.ogv', '', '2018-01-30 10:13:54'),
(968, 1000574807, '/home/mi/project-file/pmc/tmp/15960335438868.mp4', '', '2018-01-30 10:14:21'),
(969, 1000470814, '/home/mi/project-file/pmc/tmp/1394738297647.ogv', '', '2018-01-31 06:18:53'),
(970, 1000743413, '/home/mi/project-file/pmc/tmp/1783700275463.png', '', '2018-01-31 06:25:22'),
(971, 1000731034, '/home/mi/project-file/pmc/tmp/1787893273794.png', '', '2018-01-31 06:25:26'),
(972, 1000575040, '/home/mi/project-file/pmc/tmp/1796059100187.png', '', '2018-01-31 06:25:34'),
(973, 1000068896, '/home/mi/project-file/pmc/tmp/1819177870018.png', '', '2018-01-31 06:25:57'),
(974, 1000824387, '/home/mi/project-file/pmc/tmp/1922002548977.ogv', '', '2018-01-31 06:27:40'),
(975, 1000545785, '/home/mi/project-file/pmc/tmp/1926543501786.png', '', '2018-01-31 06:27:45'),
(976, 1000492637, '/home/mi/project-file/pmc/tmp/2569460369132.png', '', '2018-01-31 06:38:28'),
(977, 1000048507, '/home/mi/project-file/pmc/tmp/2575796845288.png', '', '2018-01-31 06:38:34'),
(978, 1000094566, '/home/mi/project-file/pmc/tmp/2614797547373.png', '', '2018-01-31 06:39:13'),
(979, 1000099888, '/home/mi/project-file/pmc/tmp/2628480315083.jpg', '', '2018-01-31 06:39:27'),
(980, 1000726048, '/home/mi/project-file/pmc/tmp/2628483697902.jpg', '', '2018-01-31 06:39:27'),
(981, 1000750935, '/home/mi/project-file/pmc/tmp/2628541448704.jpg', '', '2018-01-31 06:39:27'),
(982, 1000405752, '/home/mi/project-file/pmc/tmp/2628587148311.jpg', '', '2018-01-31 06:39:27'),
(983, 1000623956, '/home/mi/project-file/pmc/tmp/2628632052708.jpg', '', '2018-01-31 06:39:27'),
(984, 1000353673, '/home/mi/project-file/pmc/tmp/2661729629362.jpg', '', '2018-01-31 06:40:00'),
(985, 1000380035, '/home/mi/project-file/pmc/tmp/2663250255056.jpg', '', '2018-01-31 06:40:02'),
(986, 1000152143, '/home/mi/project-file/pmc/tmp/2665284636825.jpg', '', '2018-01-31 06:40:04'),
(987, 1000497573, '/home/mi/project-file/pmc/tmp/2669131165178.jpg', '', '2018-01-31 06:40:07'),
(988, 1000284660, '/home/mi/project-file/pmc/tmp/2670858645121.jpg', '', '2018-01-31 06:40:09'),
(989, 1000427267, '/home/mi/project-file/pmc/tmp/2675491942604.jpg', '', '2018-01-31 06:40:14'),
(990, 1000238567, '/home/mi/project-file/pmc/tmp/2939452389308.jpg', '', '2018-01-31 06:44:38'),
(992, 1000751795, '/home/mi/project-file/pmc/tmp/2939515067984.jpg', '', '2018-01-31 06:44:38'),
(1005, 1000447899, '/home/mi/project-file/pmc/tmp/3190519690512.mp4', '', '2018-01-31 06:48:49'),
(1007, 1000455207, '/home/mi/project-file/pmc/tmp/3267040337031.ogv', '', '2018-01-31 06:50:05'),
(1014, 1000456673, '/home/mi/project-file/pmc/tmp/3335386077397.ogv', '', '2018-01-31 06:51:14'),
(1015, 1000287690, '/home/mi/project-file/pmc/tmp/3353263057112.mp4', '', '2018-01-31 06:51:32'),
(1016, 1000287639, '/home/mi/project-file/pmc/tmp/3363590804178.mp4', '', '2018-01-31 06:51:42'),
(1017, 1000774410, '/home/mi/project-file/pmc/tmp/3365301434334.mp4', '', '2018-01-31 06:51:44'),
(1018, 1000231005, '/home/mi/project-file/pmc/tmp/3367282201056.mp4', '', '2018-01-31 06:51:46'),
(1019, 1000135280, '/home/mi/project-file/pmc/tmp/3786622665936.mp4', '', '2018-01-31 06:58:45'),
(1020, 1000726858, '/home/mi/project-file/pmc/tmp/3788329933218.mp4', '', '2018-01-31 06:58:47'),
(1021, 1000321509, '/home/mi/project-file/pmc/tmp/3790125020181.mp4', '', '2018-01-31 06:58:48'),
(1022, 1000419651, '/home/mi/project-file/pmc/tmp/3803134511842.mp4', '', '2018-01-31 06:59:01'),
(1023, 1000856100, '/home/mi/project-file/pmc/tmp/3807418606053.mp4', '', '2018-01-31 06:59:06'),
(1024, 1000208694, '/home/mi/project-file/pmc/tmp/3844648477538.mp4', '', '2018-01-31 06:59:43'),
(1027, 1000829314, '/home/mi/project-file/pmc/tmp/3874590447417.mp4', '', '2018-01-31 07:00:13'),
(1028, 1000071465, '/home/mi/project-file/pmc/tmp/3902178856137.mp4', '', '2018-01-31 07:00:40'),
(1030, 1000051288, '/home/mi/project-file/pmc/tmp/3927830182504.mp4', '', '2018-01-31 07:01:06'),
(1032, 1000016195, '/home/mi/project-file/pmc/tmp/3932529046265.mp4', '', '2018-01-31 07:01:11'),
(1034, 1000892355, '/home/mi/project-file/pmc/tmp/3939382910458.mp4', '', '2018-01-31 07:01:18'),
(1037, 1000699000, '/home/mi/project-file/pmc/tmp/3960195679388.ogv', '', '2018-01-31 07:01:38'),
(1038, 1000464247, '/home/mi/project-file/pmc/tmp/3960201465218.ogv', '', '2018-01-31 07:01:38'),
(1042, 1000549951, '/home/mi/project-file/pmc/tmp/4024191322916.mp4', '', '2018-01-31 07:02:42'),
(1043, 1000050586, '/home/mi/project-file/pmc/tmp/4040457949016.jpeg', '', '2018-01-31 07:02:59'),
(1044, 1000240958, '/home/mi/project-file/pmc/tmp/4040475980898.jpeg', '', '2018-01-31 07:02:59'),
(1045, 1000523553, '/home/mi/project-file/pmc/tmp/4040569483683.jpeg', '', '2018-01-31 07:02:59'),
(1046, 1000230011, '/home/mi/project-file/pmc/tmp/4040667072553.jpeg', '', '2018-01-31 07:02:59'),
(1047, 1000366429, '/home/mi/project-file/pmc/tmp/4044778321873.jpeg', '', '2018-01-31 07:03:03'),
(1048, 1000364073, '/home/mi/project-file/pmc/tmp/4053094763368.jpeg', '', '2018-01-31 07:03:11'),
(1049, 1000341435, '/home/mi/project-file/pmc/tmp/4057231139671.jpeg', '', '2018-01-31 07:03:16'),
(1056, 1000442425, '/home/mi/project-file/pmc/tmp/4107282443719.jpeg', '', '2018-01-31 07:04:06'),
(1057, 1000094462, '/home/mi/project-file/pmc/tmp/4118795070371.jpeg', '', '2018-01-31 07:04:17'),
(1058, 1000424238, '/home/mi/project-file/pmc/tmp/4118795052672.jpeg', '', '2018-01-31 07:04:17'),
(1059, 1000480896, '/home/mi/project-file/pmc/tmp/4118848201649.jpeg', '', '2018-01-31 07:04:17'),
(1060, 1000374802, '/home/mi/project-file/pmc/tmp/4118882494586.jpeg', '', '2018-01-31 07:04:17'),
(1061, 1000476121, '/home/mi/project-file/pmc/tmp/4118912518550.jpeg', '', '2018-01-31 07:04:17'),
(1068, 1000627410, '/home/mi/project-file/pmc/tmp/4156600364588.png', '', '2018-01-31 07:04:55'),
(1069, 1000327274, '/home/mi/project-file/pmc/tmp/4158405054574.png', '', '2018-01-31 07:04:57'),
(1074, 1000512377, '/home/mi/project-file/pmc/tmp/4299286116020.ogv', '', '2018-01-31 07:07:18'),
(1076, 1000436721, '/home/mi/project-file/pmc/tmp/8937601490654.png', '', '2018-01-31 08:24:36'),
(1086, 1000789581, '/home/mi/project-file/pmc/tmp/10126414352850.mp4', '', '2018-01-31 08:44:25'),
(1087, 1000030641, '/home/mi/project-file/pmc/tmp/10357921379305.png', '', '2018-01-31 08:48:16'),
(1088, 1000304292, '/home/mi/project-file/pmc/tmp/10404322484052.png', '', '2018-01-31 08:49:03'),
(1089, 1000075803, '/home/mi/project-file/pmc/tmp/10430639688236.png', '', '2018-01-31 08:49:29'),
(1090, 1000521658, '/home/mi/project-file/pmc/tmp/10432117653239.png', '', '2018-01-31 08:49:30'),
(1095, 1000731509, '/home/mi/project-file/pmc/tmp/10544359945505.mp4', '', '2018-01-31 08:51:23'),
(1096, 1000416708, '/home/mi/project-file/pmc/tmp/10804989187130.mp4', '', '2018-01-31 08:55:43'),
(1097, 1000157894, '/home/mi/project-file/pmc/tmp/10807004400061.mp4', '', '2018-01-31 08:55:45'),
(1098, 1000471213, '/home/mi/project-file/pmc/tmp/10812608492655.png', '', '2018-01-31 08:55:51'),
(1100, 1000784255, '/home/mi/project-file/pmc/tmp/11290381827306.mp4', '', '2018-01-31 09:03:49'),
(1110, 1000670867, '/home/mi/project-file/pmc/tmp/11481592972892.mp4', '', '2018-01-31 09:07:00'),
(1111, 1000654709, '/home/mi/project-file/pmc/tmp/11484390581066.mp4', '', '2018-01-31 09:07:03'),
(1112, 1000322296, '/home/mi/project-file/pmc/tmp/11487068689412.mp4', '', '2018-01-31 09:07:05'),
(1113, 1000439837, '/home/mi/project-file/pmc/tmp/11548262648292.png', '', '2018-01-31 09:08:07'),
(1114, 1000222092, '/home/mi/project-file/pmc/tmp/11552530224811.png', '', '2018-01-31 09:08:11'),
(1118, 1000630777, '/home/mi/project-file/pmc/tmp/11639257602862.mp4', '', '2018-01-31 09:09:38'),
(1119, 1000109470, '/home/mi/project-file/pmc/tmp/11649260914011.jpg', '', '2018-01-31 09:09:48'),
(1120, 1000429851, '/home/mi/project-file/pmc/tmp/11649266737243.jpg', '', '2018-01-31 09:09:48'),
(1121, 1000617411, '/home/mi/project-file/pmc/tmp/11649326961042.jpg', '', '2018-01-31 09:09:48'),
(1122, 1000515063, '/home/mi/project-file/pmc/tmp/11649365855961.jpg', '', '2018-01-31 09:09:48'),
(1123, 1000124003, '/home/mi/project-file/pmc/tmp/11649392923871.jpg', '', '2018-01-31 09:09:48'),
(1124, 1000144793, '/home/mi/project-file/pmc/tmp/11649418908185.jpg', '', '2018-01-31 09:09:48'),
(1125, 1000240972, '/home/mi/project-file/pmc/tmp/14334701251300.jpg', '', '2018-01-31 09:54:33'),
(1126, 1000678362, '/home/mi/project-file/pmc/tmp/14334703803104.jpg', '', '2018-01-31 09:54:33'),
(1127, 1000253728, '/home/mi/project-file/pmc/tmp/14334757446261.jpg', '', '2018-01-31 09:54:33'),
(1128, 1000047667, '/home/mi/project-file/pmc/tmp/14334787931583.jpg', '', '2018-01-31 09:54:33'),
(1129, 1000487662, '/home/mi/project-file/pmc/tmp/14334827339910.jpg', '', '2018-01-31 09:54:33'),
(1130, 1000681244, '/home/mi/project-file/pmc/tmp/14334868076767.jpg', '', '2018-01-31 09:54:33'),
(1131, 1000283620, '/home/mi/project-file/pmc/tmp/14403751064038.jpg', '', '2018-01-31 09:55:42'),
(1132, 1000888919, '/home/mi/project-file/pmc/tmp/14403750986795.jpg', '', '2018-01-31 09:55:42'),
(1133, 1000694189, '/home/mi/project-file/pmc/tmp/14403796747085.jpg', '', '2018-01-31 09:55:42'),
(1134, 1000408146, '/home/mi/project-file/pmc/tmp/14403830371891.jpg', '', '2018-01-31 09:55:42'),
(1135, 1000141005, '/home/mi/project-file/pmc/tmp/14403860347315.jpg', '', '2018-01-31 09:55:42'),
(1136, 1000623676, '/home/mi/project-file/pmc/tmp/14403895637237.jpg', '', '2018-01-31 09:55:42'),
(1137, 1000481519, '/home/mi/project-file/pmc/tmp/14601653008977.jpg', '', '2018-01-31 09:59:00'),
(1138, 1000152783, '/home/mi/project-file/pmc/tmp/14601653245992.jpg', '', '2018-01-31 09:59:00'),
(1139, 1000005237, '/home/mi/project-file/pmc/tmp/14601770881920.jpg', '', '2018-01-31 09:59:00'),
(1140, 1000745255, '/home/mi/project-file/pmc/tmp/14601844487938.jpg', '', '2018-01-31 09:59:00'),
(1141, 1000211472, '/home/mi/project-file/pmc/tmp/14601902347553.jpg', '', '2018-01-31 09:59:00'),
(1142, 1000439926, '/home/mi/project-file/pmc/tmp/14601994923592.jpg', '', '2018-01-31 09:59:00'),
(1143, 1000680810, '/home/mi/project-file/pmc/tmp/14632565885950.jpg', '', '2018-01-31 09:59:31'),
(1144, 1000060906, '/home/mi/project-file/pmc/tmp/14632567685356.jpg', '', '2018-01-31 09:59:31'),
(1145, 1000414453, '/home/mi/project-file/pmc/tmp/14632610626191.jpg', '', '2018-01-31 09:59:31'),
(1146, 1000705227, '/home/mi/project-file/pmc/tmp/14632645503572.jpg', '', '2018-01-31 09:59:31'),
(1147, 1000276955, '/home/mi/project-file/pmc/tmp/14632675651340.jpg', '', '2018-01-31 09:59:31'),
(1148, 1000488816, '/home/mi/project-file/pmc/tmp/14632708317778.jpg', '', '2018-01-31 09:59:31'),
(1149, 1000638083, '/home/mi/project-file/pmc/tmp/14668104010611.jpg', '', '2018-01-31 10:00:06'),
(1150, 1000793743, '/home/mi/project-file/pmc/tmp/14668104041732.jpg', '', '2018-01-31 10:00:06'),
(1151, 1000528909, '/home/mi/project-file/pmc/tmp/14668156271671.jpg', '', '2018-01-31 10:00:06'),
(1152, 1000776649, '/home/mi/project-file/pmc/tmp/14668187950985.jpg', '', '2018-01-31 10:00:06'),
(1153, 1000772683, '/home/mi/project-file/pmc/tmp/14668218395031.jpg', '', '2018-01-31 10:00:06'),
(1154, 1000728552, '/home/mi/project-file/pmc/tmp/14668254244915.jpg', '', '2018-01-31 10:00:07'),
(1155, 1000887459, '/home/mi/project-file/pmc/tmp/14680191580257.jpg', '', '2018-01-31 10:00:18'),
(1156, 1000429405, '/home/mi/project-file/pmc/tmp/14680191322727.jpg', '', '2018-01-31 10:00:18'),
(1157, 1000556799, '/home/mi/project-file/pmc/tmp/14680238987680.jpg', '', '2018-01-31 10:00:19'),
(1158, 1000826957, '/home/mi/project-file/pmc/tmp/14680271181458.jpg', '', '2018-01-31 10:00:19'),
(1159, 1000771537, '/home/mi/project-file/pmc/tmp/14680301767504.jpg', '', '2018-01-31 10:00:19'),
(1160, 1000125293, '/home/mi/project-file/pmc/tmp/14680335777270.jpg', '', '2018-01-31 10:00:19'),
(1162, 1000854401, '/home/mi/project-file/pmc/tmp/14710132680872.jpg', '', '2018-01-31 10:00:48'),
(1163, 1000236143, '/home/mi/project-file/pmc/tmp/14710198545649.jpg', '', '2018-01-31 10:00:48'),
(1164, 1000281191, '/home/mi/project-file/pmc/tmp/14710244459449.jpg', '', '2018-01-31 10:00:49'),
(1165, 1000600836, '/home/mi/project-file/pmc/tmp/14710278688643.jpg', '', '2018-01-31 10:00:49'),
(1166, 1000429381, '/home/mi/project-file/pmc/tmp/14710323161839.jpg', '', '2018-01-31 10:00:49'),
(1167, 1000236974, '/home/mi/project-file/pmc/tmp/15368916679794.jpg', '', '2018-01-31 10:11:47'),
(1168, 1000697487, '/home/mi/project-file/pmc/tmp/15368916962936.jpg', '', '2018-01-31 10:11:47'),
(1169, 1000296383, '/home/mi/project-file/pmc/tmp/15368984597990.jpg', '', '2018-01-31 10:11:47'),
(1170, 1000355916, '/home/mi/project-file/pmc/tmp/15368990231937.jpg', '', '2018-01-31 10:11:47'),
(1171, 1000057245, '/home/mi/project-file/pmc/tmp/15369032481740.jpg', '', '2018-01-31 10:11:47'),
(1172, 1000658873, '/home/mi/project-file/pmc/tmp/15369069197295.jpg', '', '2018-01-31 10:11:47');
INSERT INTO `temp_file` (`id`, `token`, `path`, `file_name`, `created_date`) VALUES
(1173, 1000540454, '/home/mi/project-file/pmc/tmp/15429658547849.jpg', '', '2018-01-31 10:12:48'),
(1174, 1000826562, '/home/mi/project-file/pmc/tmp/15429662668818.jpg', '', '2018-01-31 10:12:48'),
(1175, 1000052899, '/home/mi/project-file/pmc/tmp/15429717327084.jpg', '', '2018-01-31 10:12:48'),
(1176, 1000423612, '/home/mi/project-file/pmc/tmp/15429745902776.jpg', '', '2018-01-31 10:12:48'),
(1177, 1000851825, '/home/mi/project-file/pmc/tmp/15429781544914.jpg', '', '2018-01-31 10:12:48'),
(1178, 1000218597, '/home/mi/project-file/pmc/tmp/15429818534913.jpg', '', '2018-01-31 10:12:48'),
(1181, 1000280480, '/home/mi/project-file/pmc/tmp/15878520342287.jpg', '', '2018-01-31 10:20:17'),
(1188, 1000328543, '/home/mi/project-file/pmc/tmp/16240686625758.jpg', '', '2018-01-31 10:26:19'),
(1189, 1000857463, '/home/mi/project-file/pmc/tmp/16240718688913.jpg', '', '2018-01-31 10:26:19'),
(1194, 1000563695, '/home/mi/project-file/pmc/tmp/16645967026779.jpg', '', '2018-01-31 10:33:04'),
(1196, 1000769054, '/home/mi/project-file/pmc/tmp/16646066279485.jpg', '', '2018-01-31 10:33:04'),
(1197, 1000872206, '/home/mi/project-file/pmc/tmp/16646123918155.jpg', '', '2018-01-31 10:33:04'),
(1198, 1000714158, '/home/mi/project-file/pmc/tmp/16646129838462.jpg', '', '2018-01-31 10:33:04'),
(1205, 1000540141, '/home/mi/project-file/pmc/tmp/16994276285854.jpg', '', '2018-01-31 10:38:53'),
(1206, 1000266027, '/home/mi/project-file/pmc/tmp/16995197109821.jpg', '', '2018-01-31 10:38:53'),
(1207, 1000677172, '/home/mi/project-file/pmc/tmp/16997983903089.jpg', '', '2018-01-31 10:38:56'),
(1208, 1000095951, '/home/mi/project-file/pmc/tmp/17010248832099.jpg', '', '2018-01-31 10:39:09'),
(1212, 1000623367, '/home/mi/project-file/pmc/tmp/17023588679711.jpg', '', '2018-01-31 10:39:22'),
(1213, 1000645921, '/home/mi/project-file/pmc/tmp/17023634051018.jpg', '', '2018-01-31 10:39:22'),
(1214, 1000394511, '/home/mi/project-file/pmc/tmp/17749581128240.jpg', '', '2018-01-31 10:51:28'),
(1216, 1000357909, '/home/mi/project-file/pmc/tmp/17805057645550.mp4', '', '2018-01-31 10:52:23'),
(1217, 1000424499, '/home/mi/project-file/pmc/tmp/17807488391124.ogv', '', '2018-01-31 10:52:26'),
(1218, 1000654768, '/home/mi/project-file/pmc/tmp/19073157237625.jpeg', '', '2018-01-31 11:13:31'),
(1219, 1000706057, '/home/mi/project-file/pmc/tmp/19073163288712.jpeg', '', '2018-01-31 11:13:31'),
(1220, 1000292926, '/home/mi/project-file/pmc/tmp/19073550289566.jpeg', '', '2018-01-31 11:13:32'),
(1221, 1000130483, '/home/mi/project-file/pmc/tmp/19073556606222.jpeg', '', '2018-01-31 11:13:32'),
(1222, 1000788416, '/home/mi/project-file/pmc/tmp/19078098130498.jpeg', '', '2018-01-31 11:13:36'),
(1223, 1000897888, '/home/mi/project-file/pmc/tmp/19079621444137.jpeg', '', '2018-01-31 11:13:38'),
(1224, 1000717706, '/home/mi/project-file/pmc/tmp/19086299841366.jpeg', '', '2018-01-31 11:13:45'),
(1225, 1000324873, '/home/mi/project-file/pmc/tmp/19086301966451.jpeg', '', '2018-01-31 11:13:45'),
(1226, 1000023895, '/home/mi/project-file/pmc/tmp/19086345304061.jpeg', '', '2018-01-31 11:13:45'),
(1227, 1000149100, '/home/mi/project-file/pmc/tmp/19086378284242.jpeg', '', '2018-01-31 11:13:45'),
(1228, 1000773324, '/home/mi/project-file/pmc/tmp/19086410398242.jpeg', '', '2018-01-31 11:13:45'),
(1229, 1000664207, '/home/mi/project-file/pmc/tmp/19086445150932.jpeg', '', '2018-01-31 11:13:45'),
(1230, 1000250741, '/home/mi/project-file/pmc/tmp/19091521934577.jpeg', '', '2018-01-31 11:13:50'),
(1231, 1000060377, '/home/mi/project-file/pmc/tmp/19091523225641.jpeg', '', '2018-01-31 11:13:50'),
(1232, 1000067990, '/home/mi/project-file/pmc/tmp/19091570841068.jpeg', '', '2018-01-31 11:13:50'),
(1233, 1000476103, '/home/mi/project-file/pmc/tmp/19091610872125.jpeg', '', '2018-01-31 11:13:50'),
(1234, 1000035331, '/home/mi/project-file/pmc/tmp/19091644297507.jpeg', '', '2018-01-31 11:13:50'),
(1235, 1000264810, '/home/mi/project-file/pmc/tmp/19091677797190.jpeg', '', '2018-01-31 11:13:50'),
(1236, 1000578141, '/home/mi/project-file/pmc/tmp/19098063270098.jpeg', '', '2018-01-31 11:13:56'),
(1237, 1000440081, '/home/mi/project-file/pmc/tmp/19098064446796.jpeg', '', '2018-01-31 11:13:56'),
(1238, 1000250037, '/home/mi/project-file/pmc/tmp/19098103536790.jpeg', '', '2018-01-31 11:13:56'),
(1239, 1000014818, '/home/mi/project-file/pmc/tmp/19098136667059.jpeg', '', '2018-01-31 11:13:56'),
(1240, 1000432707, '/home/mi/project-file/pmc/tmp/19098169319601.jpeg', '', '2018-01-31 11:13:56'),
(1241, 1000757245, '/home/mi/project-file/pmc/tmp/19098202903779.jpeg', '', '2018-01-31 11:13:56'),
(1242, 1000566220, '/home/mi/project-file/pmc/tmp/19102207766928.ogv', '', '2018-01-31 11:14:00'),
(1243, 1000233722, '/home/mi/project-file/pmc/tmp/19107870065561.mp4', '', '2018-01-31 11:14:06'),
(1251, 1000170928, '/home/mi/project-file/pmc/tmp/19133784566543.jpg', '', '2018-01-31 11:14:32'),
(1252, 1000086192, '/home/mi/project-file/pmc/tmp/19133784560237.jpg', '', '2018-01-31 11:14:32'),
(1253, 1000000882, '/home/mi/project-file/pmc/tmp/19133848058605.jpg', '', '2018-01-31 11:14:32'),
(1254, 1000749049, '/home/mi/project-file/pmc/tmp/19647085670963.jpg', '', '2018-01-31 11:23:05'),
(1255, 1000076866, '/home/mi/project-file/pmc/tmp/19647109787167.jpg', '', '2018-01-31 11:23:05'),
(1256, 1000846962, '/home/mi/project-file/pmc/tmp/19647152320561.jpg', '', '2018-01-31 11:23:05'),
(1257, 1000564440, '/home/mi/project-file/pmc/tmp/19647171049249.jpg', '', '2018-01-31 11:23:05'),
(1258, 1000329094, '/home/mi/project-file/pmc/tmp/19647211024976.jpg', '', '2018-01-31 11:23:05'),
(1259, 1000625146, '/home/mi/project-file/pmc/tmp/19647252772825.jpg', '', '2018-01-31 11:23:06'),
(1260, 1000076281, '/home/mi/project-file/pmc/tmp/19662017004450.jpg', '', '2018-01-31 11:23:20'),
(1261, 1000009904, '/home/mi/project-file/pmc/tmp/19664147661917.jpg', '', '2018-01-31 11:23:22'),
(1262, 1000146097, '/home/mi/project-file/pmc/tmp/19666882672590.jpg', '', '2018-01-31 11:23:25'),
(1263, 1000481474, '/home/mi/project-file/pmc/tmp/19671931538977.jpg', '', '2018-01-31 11:23:30'),
(1264, 1000692102, '/home/mi/project-file/pmc/tmp/19671934580357.jpg', '', '2018-01-31 11:23:30'),
(1265, 1000091268, '/home/mi/project-file/pmc/tmp/19671978055050.jpg', '', '2018-01-31 11:23:30'),
(1266, 1000499101, '/home/mi/project-file/pmc/tmp/19672021149842.jpg', '', '2018-01-31 11:23:30'),
(1267, 1000695351, '/home/mi/project-file/pmc/tmp/19672060408509.jpg', '', '2018-01-31 11:23:30'),
(1268, 1000737098, '/home/mi/project-file/pmc/tmp/19672103255004.jpg', '', '2018-01-31 11:23:30'),
(1269, 1000386579, '/home/mi/project-file/pmc/tmp/19677835717362.jpg', '', '2018-01-31 11:23:36'),
(1270, 1000538165, '/home/mi/project-file/pmc/tmp/19677838675981.jpg', '', '2018-01-31 11:23:36'),
(1271, 1000163495, '/home/mi/project-file/pmc/tmp/19677878736527.jpg', '', '2018-01-31 11:23:36'),
(1272, 1000151438, '/home/mi/project-file/pmc/tmp/19677911639587.jpg', '', '2018-01-31 11:23:36'),
(1273, 1000360921, '/home/mi/project-file/pmc/tmp/19677945803303.jpg', '', '2018-01-31 11:23:36'),
(1274, 1000608435, '/home/mi/project-file/pmc/tmp/19677978381227.jpg', '', '2018-01-31 11:23:36'),
(1275, 1000443986, '/home/mi/project-file/pmc/tmp/19695996915736.mp4', '', '2018-01-31 11:23:54'),
(1276, 1000705591, '/home/mi/project-file/pmc/tmp/19701614665094.jpeg', '', '2018-01-31 11:24:00'),
(1277, 1000036485, '/home/mi/project-file/pmc/tmp/19701621151174.jpeg', '', '2018-01-31 11:24:00'),
(1278, 1000607843, '/home/mi/project-file/pmc/tmp/19701711350399.jpeg', '', '2018-01-31 11:24:00'),
(1279, 1000567878, '/home/mi/project-file/pmc/tmp/19719164133898.mp4', '', '2018-01-31 11:24:17'),
(1280, 1000805066, '/home/mi/project-file/pmc/tmp/19862797311640.png', '', '2018-01-31 11:26:41'),
(1281, 1000660347, '/home/mi/project-file/pmc/tmp/19870327234335.png', '', '2018-01-31 11:26:49'),
(1282, 1000484623, '/home/mi/project-file/pmc/tmp/19872415298055.png', '', '2018-01-31 11:26:51'),
(1283, 1000800141, '/home/mi/project-file/pmc/tmp/19874572290859.png', '', '2018-01-31 11:26:53'),
(1284, 1000315458, '/home/mi/project-file/pmc/tmp/19876215824181.png', '', '2018-01-31 11:26:54'),
(1285, 1000319456, '/home/mi/project-file/pmc/tmp/19884748103557.png', '', '2018-01-31 11:27:03'),
(1286, 1000582337, '/home/mi/project-file/pmc/tmp/19889743660319.mp4', '', '2018-01-31 11:27:08'),
(1287, 1000392053, '/home/mi/project-file/pmc/tmp/19896701217186.ogv', '', '2018-01-31 11:27:15'),
(1288, 1000893370, '/home/mi/project-file/pmc/tmp/19899895851958.png', '', '2018-01-31 11:27:18'),
(1292, 1000209695, '/home/mi/project-file/pmc/tmp/20241530555948.mp4', '', '2018-01-31 11:33:00'),
(1293, 1000435822, '/home/mi/project-file/pmc/tmp/20245708083670.png', '', '2018-01-31 11:33:04'),
(1294, 1000832151, '/home/mi/project-file/pmc/tmp/20247752809278.png', '', '2018-01-31 11:33:06'),
(1295, 1000476798, '/home/mi/project-file/pmc/tmp/20311358570694.mp4', '', '2018-01-31 11:34:10'),
(1297, 1000119667, '/home/mi/project-file/pmc/tmp/20321156678447.png', '', '2018-01-31 11:34:19'),
(1298, 1000808655, '/home/mi/project-file/pmc/tmp/20322522409298.png', '', '2018-01-31 11:34:21'),
(1299, 1000342689, '/home/mi/project-file/pmc/tmp/20362351821217.png', '', '2018-01-31 11:35:01'),
(1300, 1000525332, '/home/mi/project-file/pmc/tmp/20364715870765.png', '', '2018-01-31 11:35:03'),
(1301, 1000629151, '/home/mi/project-file/pmc/tmp/20367907276291.png', '', '2018-01-31 11:35:06'),
(1302, 1000309882, '/home/mi/project-file/pmc/tmp/20370449959858.png', '', '2018-01-31 11:35:09'),
(1303, 1000048921, '/home/mi/project-file/pmc/tmp/20374938711741.png', '', '2018-01-31 11:35:13'),
(1304, 1000451525, '/home/mi/project-file/pmc/tmp/20409377880407.png', '', '2018-01-31 11:35:48'),
(1305, 1000549066, '/home/mi/project-file/pmc/tmp/20411228630713.png', '', '2018-01-31 11:35:50'),
(1306, 1000224832, '/home/mi/project-file/pmc/tmp/20413168341254.png', '', '2018-01-31 11:35:51'),
(1307, 1000662604, '/home/mi/project-file/pmc/tmp/20415030437013.png', '', '2018-01-31 11:35:53'),
(1308, 1000551699, '/home/mi/project-file/pmc/tmp/20419389330436.png', '', '2018-01-31 11:35:58'),
(1310, 1000848243, '/home/mi/project-file/pmc/tmp/20438588391791.mp4', '', '2018-01-31 11:36:17'),
(1311, 1000250247, '/home/mi/project-file/pmc/tmp/20442834702216.mp4', '', '2018-01-31 11:36:21'),
(1312, 1000342759, '/home/mi/project-file/pmc/tmp/20446687560959.png', '', '2018-01-31 11:36:25'),
(1313, 1000057776, '/home/mi/project-file/pmc/tmp/20449078116411.png', '', '2018-01-31 11:36:27'),
(1319, 1000844777, '/home/mi/project-file/pmc/tmp/20774339875424.png', '', '2018-01-31 11:41:53'),
(1320, 1000523977, '/home/mi/project-file/pmc/tmp/20776203174728.png', '', '2018-01-31 11:41:54'),
(1321, 1000227858, '/home/mi/project-file/pmc/tmp/20779617363573.png', '', '2018-01-31 11:41:58'),
(1322, 1000736806, '/home/mi/project-file/pmc/tmp/20781110631806.png', '', '2018-01-31 11:41:59'),
(1323, 1000437487, '/home/mi/project-file/pmc/tmp/20786528821657.png', '', '2018-01-31 11:42:05'),
(1324, 1000206460, '/home/mi/project-file/pmc/tmp/20815003684923.png', '', '2018-01-31 11:42:33'),
(1325, 1000872181, '/home/mi/project-file/pmc/tmp/21019914705001.png', '', '2018-01-31 11:45:58'),
(1326, 1000712005, '/home/mi/project-file/pmc/tmp/21042829868431.png', '', '2018-01-31 11:46:21'),
(1327, 1000624594, '/home/mi/project-file/pmc/tmp/21044475774650.png', '', '2018-01-31 11:46:23'),
(1329, 1000698485, '/home/mi/project-file/pmc/tmp/21058313904822.png', '', '2018-01-31 11:46:37'),
(1330, 1000687500, '/home/mi/project-file/pmc/tmp/23632960243838.png', '', '2018-01-31 12:29:31'),
(1331, 1000036706, '/home/mi/project-file/pmc/tmp/23641834756880.png', '', '2018-01-31 12:29:40'),
(1332, 1000001041, '/home/mi/project-file/pmc/tmp/23643238158194.png', '', '2018-01-31 12:29:42'),
(1333, 1000629686, '/home/mi/project-file/pmc/tmp/23646686340374.png', '', '2018-01-31 12:29:45'),
(1334, 1000144230, '/home/mi/project-file/pmc/tmp/23648396278215.png', '', '2018-01-31 12:29:47'),
(1335, 1000565476, '/home/mi/project-file/pmc/tmp/23652833488493.png', '', '2018-01-31 12:29:51'),
(1336, 1000156172, '/home/mi/project-file/pmc/tmp/23663142131453.png', '', '2018-01-31 12:30:01'),
(1337, 1000310149, '/home/mi/project-file/pmc/tmp/23671195016643.mp4', '', '2018-01-31 12:30:09'),
(1338, 1000511118, '/home/mi/project-file/pmc/tmp/23676067003141.png', '', '2018-01-31 12:30:14'),
(1340, 1000856961, '/home/mi/project-file/pmc/tmp/23696141549728.mp4', '', '2018-01-31 12:30:34'),
(1341, 1000395592, '/home/mi/project-file/pmc/tmp/11440975363780.png', '', '2018-02-01 08:40:57'),
(1342, 1000223263, '/home/mi/project-file/pmc/tmp/11459929578841.jpg', '', '2018-02-01 08:41:16'),
(1343, 1000547043, '/home/mi/project-file/pmc/tmp/11672485392540.jpg', '', '2018-02-01 08:44:49'),
(1344, 1000117172, '/home/mi/project-file/pmc/tmp/12106692726390.jpg', '', '2018-02-01 08:52:03'),
(1345, 1000586397, '/home/mi/project-file/pmc/tmp/12906513030464.jpg', '', '2018-02-01 09:05:23'),
(1346, 1000404953, '/home/mi/project-file/pmc/tmp/12987904773865.jpg', '', '2018-02-01 09:06:44'),
(1347, 1000369706, '/home/mi/project-file/pmc/tmp/13275377292645.jpg', '', '2018-02-01 09:11:32'),
(1348, 1000050943, '/home/mi/project-file/pmc/tmp/14195372215490.jpg', '', '2018-02-01 09:26:52'),
(1349, 1000790824, '/home/mi/project-file/pmc/tmp/14501422296984.jpg', '', '2018-02-01 09:31:58'),
(1350, 1000024898, '/home/mi/project-file/pmc/tmp/14563879645165.jpg', '', '2018-02-01 09:33:00'),
(1351, 1000105268, '/home/mi/project-file/pmc/tmp/14563881417053.jpg', '', '2018-02-01 09:33:00'),
(1352, 1000657839, '/home/mi/project-file/pmc/tmp/14563957369969.jpg', '', '2018-02-01 09:33:00'),
(1353, 1000639893, '/home/mi/project-file/pmc/tmp/14563988924283.jpg', '', '2018-02-01 09:33:00'),
(1354, 1000632504, '/home/mi/project-file/pmc/tmp/15385162346955.jpg', '', '2018-02-01 09:46:41'),
(1355, 1000075333, '/home/mi/project-file/pmc/tmp/15385162346844.jpg', '', '2018-02-01 09:46:41'),
(1356, 1000804052, '/home/mi/project-file/pmc/tmp/15385283708937.jpg', '', '2018-02-01 09:46:42'),
(1357, 1000436251, '/home/mi/project-file/pmc/tmp/15436871583478.jpg', '', '2018-02-01 09:47:33'),
(1358, 1000337362, '/home/mi/project-file/pmc/tmp/15436876327747.jpg', '', '2018-02-01 09:47:33'),
(1359, 1000496811, '/home/mi/project-file/pmc/tmp/15436936074505.jpg', '', '2018-02-01 09:47:33'),
(1360, 1000747552, '/home/mi/project-file/pmc/tmp/15436968140170.jpg', '', '2018-02-01 09:47:33'),
(1361, 1000184971, '/home/mi/project-file/pmc/tmp/16920727636690.jpg', '', '2018-02-01 10:12:17'),
(1363, 1000896508, '/home/mi/project-file/pmc/tmp/16932880832148.ogv', '', '2018-02-01 10:12:29'),
(1364, 1000477066, '/home/mi/project-file/pmc/tmp/17165364933908.jpeg', '', '2018-02-01 10:16:22'),
(1365, 1000350669, '/home/mi/project-file/pmc/tmp/17170243008038.ogv', '', '2018-02-01 10:16:27'),
(1366, 1000684309, '/home/mi/project-file/pmc/tmp/17261111471600.jpg', '', '2018-02-01 10:17:57'),
(1367, 1000541534, '/home/mi/project-file/pmc/tmp/17265343852661.ogv', '', '2018-02-01 10:18:02'),
(1369, 1000506302, '/home/mi/project-file/pmc/tmp/21475509192463.png', '', '2018-02-01 11:28:12'),
(1370, 1000427246, '/home/mi/project-file/pmc/tmp/21478784857359.png', '', '2018-02-01 11:28:15'),
(1371, 1000866802, '/home/mi/project-file/pmc/tmp/21492629516312.mp4', '', '2018-02-01 11:28:29'),
(1372, 1000655627, '/home/mi/project-file/pmc/tmp/21570690493150.jpg', '', '2018-02-01 11:29:47'),
(1373, 1000630671, '/home/mi/project-file/pmc/tmp/21570695723401.jpg', '', '2018-02-01 11:29:47'),
(1374, 1000770158, '/home/mi/project-file/pmc/tmp/21570775549685.jpg', '', '2018-02-01 11:29:47'),
(1375, 1000891932, '/home/mi/project-file/pmc/tmp/21570801658341.jpg', '', '2018-02-01 11:29:47'),
(1376, 1000222032, '/home/mi/project-file/pmc/tmp/21570850874347.jpg', '', '2018-02-01 11:29:47'),
(1377, 1000010175, '/home/mi/project-file/pmc/tmp/21583390531445.mp4', '', '2018-02-01 11:30:00'),
(1378, 1000345424, '/home/mi/project-file/pmc/tmp/21633274501070.jpg', '', '2018-02-01 11:30:50'),
(1379, 1000096110, '/home/mi/project-file/pmc/tmp/21633274511018.jpg', '', '2018-02-01 11:30:50'),
(1380, 1000814755, '/home/mi/project-file/pmc/tmp/21633373774528.jpg', '', '2018-02-01 11:30:50'),
(1381, 1000843381, '/home/mi/project-file/pmc/tmp/21633411363294.jpg', '', '2018-02-01 11:30:50'),
(1382, 1000166465, '/home/mi/project-file/pmc/tmp/21633483397278.jpg', '', '2018-02-01 11:30:50'),
(1383, 1000221618, '/home/mi/project-file/pmc/tmp/21633528302325.jpg', '', '2018-02-01 11:30:50'),
(1384, 1000189853, '/home/mi/project-file/pmc/tmp/21637646200313.jpg', '', '2018-02-01 11:30:54'),
(1385, 1000760636, '/home/mi/project-file/pmc/tmp/21778296345741.jpg', '', '2018-02-01 11:33:15'),
(1386, 1000572507, '/home/mi/project-file/pmc/tmp/21779690151590.jpg', '', '2018-02-01 11:33:16'),
(1387, 1000614785, '/home/mi/project-file/pmc/tmp/21787489663665.mp4', '', '2018-02-01 11:33:24'),
(1388, 1000607364, '/home/mi/project-file/pmc/tmp/21802531171647.jpg', '', '2018-02-01 11:33:39'),
(1389, 1000212663, '/home/mi/project-file/pmc/tmp/21802526405160.jpg', '', '2018-02-01 11:33:39'),
(1390, 1000085008, '/home/mi/project-file/pmc/tmp/21802595576918.jpg', '', '2018-02-01 11:33:39'),
(1391, 1000678985, '/home/mi/project-file/pmc/tmp/21802621318755.jpg', '', '2018-02-01 11:33:39'),
(1392, 1000158568, '/home/mi/project-file/pmc/tmp/21802656522031.jpg', '', '2018-02-01 11:33:39'),
(1393, 1000311382, '/home/mi/project-file/pmc/tmp/21809544789820.jpg', '', '2018-02-01 11:33:46'),
(1394, 1000415316, '/home/mi/project-file/pmc/tmp/21809544960006.jpg', '', '2018-02-01 11:33:46'),
(1395, 1000403171, '/home/mi/project-file/pmc/tmp/21809607172745.jpg', '', '2018-02-01 11:33:46'),
(1396, 1000154904, '/home/mi/project-file/pmc/tmp/21809642199886.jpg', '', '2018-02-01 11:33:46'),
(1397, 1000167223, '/home/mi/project-file/pmc/tmp/21829735638073.jpg', '', '2018-02-01 11:34:06'),
(1398, 1000128760, '/home/mi/project-file/pmc/tmp/21829735647371.jpg', '', '2018-02-01 11:34:06'),
(1399, 1000563598, '/home/mi/project-file/pmc/tmp/21829801635952.jpg', '', '2018-02-01 11:34:06'),
(1400, 1000537300, '/home/mi/project-file/pmc/tmp/21829831974022.jpg', '', '2018-02-01 11:34:06'),
(1401, 1000563085, '/home/mi/project-file/pmc/tmp/21986906876890.jpg', '', '2018-02-01 11:36:43'),
(1402, 1000661856, '/home/mi/project-file/pmc/tmp/21986895397462.jpg', '', '2018-02-01 11:36:43'),
(1403, 1000114733, '/home/mi/project-file/pmc/tmp/21987006474677.jpg', '', '2018-02-01 11:36:43'),
(1404, 1000414123, '/home/mi/project-file/pmc/tmp/21991323510692.jpg', '', '2018-02-01 11:36:48'),
(1405, 1000683447, '/home/mi/project-file/pmc/tmp/21991328616588.jpg', '', '2018-02-01 11:36:48'),
(1406, 1000366376, '/home/mi/project-file/pmc/tmp/21991384502763.jpg', '', '2018-02-01 11:36:48'),
(1407, 1000035132, '/home/mi/project-file/pmc/tmp/22328840755542.jpg', '', '2018-02-01 11:42:25'),
(1408, 1000474328, '/home/mi/project-file/pmc/tmp/22328833380829.jpg', '', '2018-02-01 11:42:25'),
(1409, 1000409318, '/home/mi/project-file/pmc/tmp/22328976236127.jpg', '', '2018-02-01 11:42:25'),
(1410, 1000818247, '/home/mi/project-file/pmc/tmp/22328976248523.jpg', '', '2018-02-01 11:42:25'),
(1411, 1000784155, '/home/mi/project-file/pmc/tmp/22333440750950.jpg', '', '2018-02-01 11:42:30'),
(1412, 1000042272, '/home/mi/project-file/pmc/tmp/22333440759372.jpg', '', '2018-02-01 11:42:30'),
(1413, 1000679769, '/home/mi/project-file/pmc/tmp/22333510184764.jpg', '', '2018-02-01 11:42:30'),
(1414, 1000727458, '/home/mi/project-file/pmc/tmp/22333536747909.jpg', '', '2018-02-01 11:42:30'),
(1415, 1000388953, '/home/mi/project-file/pmc/tmp/22333569663965.jpg', '', '2018-02-01 11:42:30'),
(1416, 1000364511, '/home/mi/project-file/pmc/tmp/22593999412324.jpg', '', '2018-02-01 11:46:50'),
(1417, 1000683093, '/home/mi/project-file/pmc/tmp/22594004322285.jpg', '', '2018-02-01 11:46:50'),
(1418, 1000682563, '/home/mi/project-file/pmc/tmp/22594145837563.jpg', '', '2018-02-01 11:46:50'),
(1419, 1000095372, '/home/mi/project-file/pmc/tmp/22594145902307.jpg', '', '2018-02-01 11:46:50'),
(1420, 1000372326, '/home/mi/project-file/pmc/tmp/22594187365840.jpg', '', '2018-02-01 11:46:50'),
(1421, 1000563850, '/home/mi/project-file/pmc/tmp/22599414680121.jpg', '', '2018-02-01 11:46:56'),
(1422, 1000091546, '/home/mi/project-file/pmc/tmp/22599414961537.jpg', '', '2018-02-01 11:46:56'),
(1423, 1000038932, '/home/mi/project-file/pmc/tmp/22599481849798.jpg', '', '2018-02-01 11:46:56'),
(1424, 1000847234, '/home/mi/project-file/pmc/tmp/22599513515686.jpg', '', '2018-02-01 11:46:56'),
(1425, 1000618619, '/home/mi/project-file/pmc/tmp/22599545095512.jpg', '', '2018-02-01 11:46:56'),
(1426, 1000505566, '/home/mi/project-file/pmc/tmp/22783214733965.jpg', '', '2018-02-01 11:50:00'),
(1427, 1000259814, '/home/mi/project-file/pmc/tmp/22783215387978.jpg', '', '2018-02-01 11:50:00'),
(1428, 1000178683, '/home/mi/project-file/pmc/tmp/22783339437497.jpg', '', '2018-02-01 11:50:00'),
(1429, 1000359319, '/home/mi/project-file/pmc/tmp/22783340698543.jpg', '', '2018-02-01 11:50:00'),
(1430, 1000430211, '/home/mi/project-file/pmc/tmp/22783377112076.jpg', '', '2018-02-01 11:50:00'),
(1431, 1000769270, '/home/mi/project-file/pmc/tmp/22878383468678.jpg', '', '2018-02-01 11:51:35'),
(1432, 1000772716, '/home/mi/project-file/pmc/tmp/22878380384687.jpg', '', '2018-02-01 11:51:35'),
(1433, 1000733844, '/home/mi/project-file/pmc/tmp/22878504982309.jpg', '', '2018-02-01 11:51:35'),
(1434, 1000819539, '/home/mi/project-file/pmc/tmp/22878512685113.jpg', '', '2018-02-01 11:51:35'),
(1435, 1000713556, '/home/mi/project-file/pmc/tmp/22967501949518.jpg', '', '2018-02-01 11:53:04'),
(1436, 1000098185, '/home/mi/project-file/pmc/tmp/22967501949551.jpg', '', '2018-02-01 11:53:04'),
(1437, 1000666548, '/home/mi/project-file/pmc/tmp/22967622873330.jpg', '', '2018-02-01 11:53:04'),
(1438, 1000493591, '/home/mi/project-file/pmc/tmp/22967622864381.jpg', '', '2018-02-01 11:53:04'),
(1439, 1000408420, '/home/mi/project-file/pmc/tmp/23205268411017.jpg', '', '2018-02-01 11:57:02'),
(1440, 1000145705, '/home/mi/project-file/pmc/tmp/23205272837540.jpg', '', '2018-02-01 11:57:02'),
(1441, 1000360718, '/home/mi/project-file/pmc/tmp/23205391703208.jpg', '', '2018-02-01 11:57:02'),
(1442, 1000601402, '/home/mi/project-file/pmc/tmp/23205411765092.jpg', '', '2018-02-01 11:57:02'),
(1443, 1000246632, '/home/mi/project-file/pmc/tmp/3626434690527.jpg', '', '2018-02-02 05:44:42'),
(1444, 1000253520, '/home/mi/project-file/pmc/tmp/3631695197713.jpg', '', '2018-02-02 05:44:47'),
(1445, 1000262536, '/home/mi/project-file/pmc/tmp/3633246408740.jpg', '', '2018-02-02 05:44:49'),
(1446, 1000309127, '/home/mi/project-file/pmc/tmp/3650283270723.jpg', '', '2018-02-02 05:45:06'),
(1447, 1000117123, '/home/mi/project-file/pmc/tmp/3650283690161.jpg', '', '2018-02-02 05:45:06'),
(1448, 1000447044, '/home/mi/project-file/pmc/tmp/3650350837531.jpg', '', '2018-02-02 05:45:06'),
(1449, 1000491588, '/home/mi/project-file/pmc/tmp/3650371661089.jpg', '', '2018-02-02 05:45:06'),
(1450, 1000546995, '/home/mi/project-file/pmc/tmp/3655818765242.jpg', '', '2018-02-02 05:45:11'),
(1451, 1000338988, '/home/mi/project-file/pmc/tmp/3655820093051.jpg', '', '2018-02-02 05:45:11'),
(1452, 1000197845, '/home/mi/project-file/pmc/tmp/3655877740546.jpg', '', '2018-02-02 05:45:11'),
(1453, 1000780362, '/home/mi/project-file/pmc/tmp/3655904543280.jpg', '', '2018-02-02 05:45:11'),
(1454, 1000487925, '/home/mi/project-file/pmc/tmp/3655943878163.jpg', '', '2018-02-02 05:45:11'),
(1455, 1000732107, '/home/mi/project-file/pmc/tmp/3662962086314.jpg', '', '2018-02-02 05:45:18'),
(1456, 1000422808, '/home/mi/project-file/pmc/tmp/3662968126295.jpg', '', '2018-02-02 05:45:18'),
(1457, 1000061610, '/home/mi/project-file/pmc/tmp/3663028653523.jpg', '', '2018-02-02 05:45:18'),
(1458, 1000198922, '/home/mi/project-file/pmc/tmp/3672698924795.ogv', '', '2018-02-02 05:45:28'),
(1459, 1000899277, '/home/mi/project-file/pmc/tmp/3687347507753.ogv', '', '2018-02-02 05:45:43'),
(1460, 1000725318, '/home/mi/project-file/pmc/tmp/3774540838252.png', '', '2018-02-02 05:47:10'),
(1461, 1000100326, '/home/mi/project-file/pmc/tmp/3777764744838.png', '', '2018-02-02 05:47:13'),
(1462, 1000447500, '/home/mi/project-file/pmc/tmp/3784743050533.ogv', '', '2018-02-02 05:47:20'),
(1463, 1000876836, '/home/mi/project-file/pmc/tmp/3798490007558.mp4', '', '2018-02-02 05:47:34'),
(1464, 1000759053, '/home/mi/project-file/pmc/tmp/3803110827295.png', '', '2018-02-02 05:47:38'),
(1465, 1000545555, '/home/mi/project-file/pmc/tmp/3804902093550.png', '', '2018-02-02 05:47:40'),
(1466, 1000204736, '/home/mi/project-file/pmc/tmp/3820946163534.jpg', '', '2018-02-02 05:47:56'),
(1467, 1000562940, '/home/mi/project-file/pmc/tmp/3820951415650.jpg', '', '2018-02-02 05:47:56'),
(1468, 1000879426, '/home/mi/project-file/pmc/tmp/3821019799343.jpg', '', '2018-02-02 05:47:56'),
(1469, 1000213368, '/home/mi/project-file/pmc/tmp/3821050958315.jpg', '', '2018-02-02 05:47:56'),
(1470, 1000378479, '/home/mi/project-file/pmc/tmp/3825082162289.jpg', '', '2018-02-02 05:48:00'),
(1471, 1000067906, '/home/mi/project-file/pmc/tmp/3825082588032.jpg', '', '2018-02-02 05:48:00'),
(1472, 1000478271, '/home/mi/project-file/pmc/tmp/3825147853463.jpg', '', '2018-02-02 05:48:00'),
(1473, 1000578728, '/home/mi/project-file/pmc/tmp/3825187999378.jpg', '', '2018-02-02 05:48:00'),
(1474, 1000094420, '/home/mi/project-file/pmc/tmp/3834084788331.jpg', '', '2018-02-02 05:48:09'),
(1475, 1000695005, '/home/mi/project-file/pmc/tmp/3835540900781.jpg', '', '2018-02-02 05:48:11'),
(1476, 1000051758, '/home/mi/project-file/pmc/tmp/9168244645793.jpg', '', '2018-02-02 07:17:04'),
(1477, 1000049726, '/home/mi/project-file/pmc/tmp/9168235463996.jpg', '', '2018-02-02 07:17:04'),
(1478, 1000567513, '/home/mi/project-file/pmc/tmp/9168371666603.jpg', '', '2018-02-02 07:17:04'),
(1479, 1000057007, '/home/mi/project-file/pmc/tmp/9168378903103.jpg', '', '2018-02-02 07:17:04'),
(1480, 1000615758, '/home/mi/project-file/pmc/tmp/9168443937523.jpg', '', '2018-02-02 07:17:04'),
(1481, 1000303724, '/home/mi/project-file/pmc/tmp/9381245972161.jpg', '', '2018-02-02 07:20:37'),
(1482, 1000809244, '/home/mi/project-file/pmc/tmp/9381249067108.jpg', '', '2018-02-02 07:20:37'),
(1483, 1000319952, '/home/mi/project-file/pmc/tmp/9381429314322.jpg', '', '2018-02-02 07:20:37'),
(1484, 1000857889, '/home/mi/project-file/pmc/tmp/9628613036862.jpg', '', '2018-02-02 07:24:44'),
(1485, 1000218383, '/home/mi/project-file/pmc/tmp/9628614553454.jpg', '', '2018-02-02 07:24:44'),
(1486, 1000860157, '/home/mi/project-file/pmc/tmp/9628750580652.jpg', '', '2018-02-02 07:24:44'),
(1487, 1000276990, '/home/mi/project-file/pmc/tmp/9631825206035.jpg', '', '2018-02-02 07:24:47'),
(1488, 1000328773, '/home/mi/project-file/pmc/tmp/9637029391878.jpg', '', '2018-02-02 07:24:52'),
(1489, 1000765805, '/home/mi/project-file/pmc/tmp/9637031477969.jpg', '', '2018-02-02 07:24:52'),
(1490, 1000008230, '/home/mi/project-file/pmc/tmp/9637093845522.jpg', '', '2018-02-02 07:24:52'),
(1491, 1000160102, '/home/mi/project-file/pmc/tmp/9637113289801.jpg', '', '2018-02-02 07:24:52'),
(1492, 1000884778, '/home/mi/project-file/pmc/tmp/9642504396076.jpg', '', '2018-02-02 07:24:58'),
(1493, 1000298842, '/home/mi/project-file/pmc/tmp/9642504384766.jpg', '', '2018-02-02 07:24:58'),
(1494, 1000457676, '/home/mi/project-file/pmc/tmp/9642571854673.jpg', '', '2018-02-02 07:24:58'),
(1495, 1000049447, '/home/mi/project-file/pmc/tmp/9642603569224.jpg', '', '2018-02-02 07:24:58'),
(1496, 1000558033, '/home/mi/project-file/pmc/tmp/9648838797345.jpg', '', '2018-02-02 07:25:04'),
(1497, 1000258613, '/home/mi/project-file/pmc/tmp/9653938676442.jpg', '', '2018-02-02 07:25:09'),
(1498, 1000542642, '/home/mi/project-file/pmc/tmp/9653939486992.jpg', '', '2018-02-02 07:25:09'),
(1499, 1000456721, '/home/mi/project-file/pmc/tmp/9653996413470.jpg', '', '2018-02-02 07:25:09'),
(1500, 1000678402, '/home/mi/project-file/pmc/tmp/9654029271201.jpg', '', '2018-02-02 07:25:09'),
(1501, 1000434477, '/home/mi/project-file/pmc/tmp/9654063225333.jpg', '', '2018-02-02 07:25:09'),
(1502, 1000336139, '/home/mi/project-file/pmc/tmp/9658123323405.ogv', '', '2018-02-02 07:25:13'),
(1503, 1000105582, '/home/mi/project-file/pmc/tmp/9663874032104.png', '', '2018-02-02 07:25:19'),
(1504, 1000728039, '/home/mi/project-file/pmc/tmp/9665407668449.png', '', '2018-02-02 07:25:21'),
(1505, 1000646507, '/home/mi/project-file/pmc/tmp/9669513304382.ogv', '', '2018-02-02 07:25:25'),
(1506, 1000681635, '/home/anik/project_file/pmc/tmp/4282413993512.png', '', '2018-02-02 11:11:14'),
(1507, 1000825771, '/home/anik/project_file/pmc/tmp/4297790964727.jpg', '', '2018-02-02 11:11:29'),
(1508, 1000818021, '/home/anik/project_file/pmc/tmp/4299977631983.jpg', '', '2018-02-02 11:11:31'),
(1509, 1000039603, '/home/anik/project_file/pmc/tmp/4302033016132.jpg', '', '2018-02-02 11:11:33'),
(1510, 1000009520, '/home/anik/project_file/pmc/tmp/4305225358635.png', '', '2018-02-02 11:11:36'),
(1511, 1000583159, '/home/anik/project_file/pmc/tmp/4310209485304.jpg', '', '2018-02-02 11:11:41'),
(1512, 1000891013, '/home/anik/project_file/pmc/tmp/4313957957271.png', '', '2018-02-02 11:11:45'),
(1513, 1000803681, '/home/anik/project_file/pmc/tmp/4320675442509.jpg', '', '2018-02-02 11:11:52'),
(1514, 1000412238, '/home/anik/project_file/pmc/tmp/4336228408780.mp4', '', '2018-02-02 11:12:07'),
(1515, 1000600771, '/home/anik/project_file/pmc/tmp/4347466356758.png', '', '2018-02-02 11:12:19'),
(1516, 1000179252, '/home/anik/project_file/pmc/tmp/4351155702004.png', '', '2018-02-02 11:12:22'),
(1517, 1000644111, '/home/anik/project_file/pmc/tmp/4360965812706.png', '', '2018-02-02 11:12:32'),
(1518, 1000560159, '/home/anik/project_file/pmc/tmp/4364606792629.jpg', '', '2018-02-02 11:12:36'),
(1519, 1000757620, '/home/anik/project_file/pmc/tmp/4464868773931.png', '', '2018-02-02 11:14:16'),
(1520, 1000190010, '/home/anik/project_file/pmc/tmp/4497126976167.jpg', '', '2018-02-02 11:14:48'),
(1522, 1000880322, '/home/anik/project_file/pmc/tmp/4511606936835.png', '', '2018-02-02 11:15:03'),
(1523, 1000173266, '/home/anik/project_file/pmc/tmp/4517416817097.png', '', '2018-02-02 11:15:09'),
(1524, 1000343769, '/home/anik/project_file/pmc/tmp/4521720035825.jpg', '', '2018-02-02 11:15:13'),
(1527, 1000644765, '/home/anik/project_file/pmc/tmp/4583184400337.mp4', '', '2018-02-02 11:16:14'),
(1528, 1000897515, '/home/anik/project_file/pmc/tmp/4602051720365.png', '', '2018-02-02 11:16:33'),
(1529, 1000362575, '/home/anik/project_file/pmc/tmp/4609384231640.mp4', '', '2018-02-02 11:16:41'),
(1530, 1000704150, '/home/anik/project_file/pmc/tmp/4618979646364.mp4', '', '2018-02-02 11:16:50'),
(1531, 1000007673, '/home/anik/project_file/pmc/tmp/4624240352267.jpg', '', '2018-02-02 11:16:55'),
(1532, 1000683387, '/home/anik/project_file/pmc/tmp/5154638141240.png', '', '2018-02-02 11:25:46'),
(1533, 1000627649, '/home/anik/project_file/pmc/tmp/5173576428837.jpg', '', '2018-02-02 11:26:05'),
(1534, 1000840294, '/home/anik/project_file/pmc/tmp/5179245465459.png', '', '2018-02-02 11:26:10'),
(1535, 1000170412, '/home/anik/project_file/pmc/tmp/5187959976927.png', '', '2018-02-02 11:26:19'),
(1536, 1000215293, '/home/anik/project_file/pmc/tmp/5195252418553.png', '', '2018-02-02 11:26:26'),
(1537, 1000595816, '/home/anik/project_file/pmc/tmp/5207882166455.mp4', '', '2018-02-02 11:26:39'),
(1538, 1000660546, '/home/anik/project_file/pmc/tmp/5212934150649.png', '', '2018-02-02 11:26:44'),
(1539, 1000544408, '/home/anik/project_file/pmc/tmp/5219861186047.mp4', '', '2018-02-02 11:26:51'),
(1540, 1000159301, '/home/anik/project_file/pmc/tmp/10656325134110.jpg', '', '2018-02-14 13:02:24'),
(1541, 1000416119, '/home/anik/project_file/pmc/tmp/10659042229049.jpg', '', '2018-02-14 13:02:27');

-- --------------------------------------------------------

--
-- Table structure for table `top_banner_images`
--

CREATE TABLE IF NOT EXISTS `top_banner_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gallery_ad_id` int(11) NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_ad_id` (`gallery_ad_id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `venues`
--

CREATE TABLE IF NOT EXISTS `venues` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `location_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `location_id` (`location_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`id`, `name`, `location_id`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(1, 'Venue 1', 1, '2018-02-07 00:00:00', '2018-02-07 00:00:00', NULL, 1, 0),
(2, 'Venue 2', 1, NULL, NULL, NULL, 1, 0),
(3, 'ddf1', 2, '2018-01-10 13:51:52', '2018-01-10 13:52:27', 132, 0, 1),
(4, 'kkk', 4, '2018-01-10 13:52:39', '2018-01-10 13:52:39', 132, 0, 1),
(5, 'test', 3, '2018-01-10 14:01:42', '2018-01-10 14:01:42', 132, 0, 1),
(6, 'New Venu1', 5, '2018-01-15 12:33:20', '2018-01-15 12:33:35', 132, 1, 0),
(7, '123', 2, '2018-01-15 12:34:18', '2018-01-15 12:34:18', 132, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `watermarks`
--

CREATE TABLE IF NOT EXISTS `watermarks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `type` enum('image','text') COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sample_image_name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `logo_image` text COLLATE utf8_unicode_ci,
  `size` enum('thumb','small','medium','large','x_large') COLLATE utf8_unicode_ci DEFAULT NULL,
  `fade` double DEFAULT NULL,
  `watermark_text` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `font_id` int(11) DEFAULT NULL,
  `placement` enum('tl','tc','tr','cl','cc','cr','bl','bc','br') COLLATE utf8_unicode_ci DEFAULT NULL,
  `color` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `watermarks`
--

INSERT INTO `watermarks` (`id`, `name`, `type`, `logo_name`, `sample_image_name`, `logo_image`, `size`, `fade`, `watermark_text`, `font_id`, `placement`, `color`, `created_at`, `updated_at`, `created_by`, `active`, `deleted`) VALUES
(1, 'ewfwe', 'image', 'dfg', '', '26126512604901.png', 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 18:05:08', '2018-01-09 18:05:08', NULL, 1, 0),
(2, 'wat2', 'image', 'Roy', '', '92296838259199.jpg', 'small', 6, '', NULL, 'br', '', '2018-01-10 12:27:10', '2018-01-10 12:27:10', NULL, 0, 1),
(3, 'www', 'text', 'Roy', '', '', NULL, NULL, 'Roy', NULL, NULL, 'B3FF57', '2018-01-10 12:28:11', '2018-01-10 12:28:11', NULL, 1, 1),
(4, 'ewat', 'image', 'Rainbow', '', '5044984895095.jpeg', 'medium', 29, '', NULL, 'bl', '', '2018-01-15 12:36:39', '2018-01-15 12:43:34', NULL, 1, 0),
(5, 'wat', 'text', 'New 1', '', '', NULL, NULL, 'New 1', NULL, NULL, 'FFFFFF', '2018-01-15 12:45:14', '2018-01-15 12:46:05', NULL, 0, 1),
(6, 'ad', 'text', 'dasd', '', '', NULL, NULL, 'sdd', NULL, NULL, 'B3FF57', '2018-02-16 12:45:48', '2018-02-16 12:45:48', NULL, 0, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_has_roles`
--
ALTER TABLE `admin_has_roles`
  ADD CONSTRAINT `admin_has_roles_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`),
  ADD CONSTRAINT `admin_has_roles_ibfk_2` FOREIGN KEY (`admin_role_id`) REFERENCES `admin_roles` (`id`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`);

--
-- Constraints for table `event_images`
--
ALTER TABLE `event_images`
  ADD CONSTRAINT `event_images_ibfk_2` FOREIGN KEY (`watermark_id`) REFERENCES `watermarks` (`id`),
  ADD CONSTRAINT `event_images_ibfk_3` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`);

--
-- Constraints for table `event_photographers`
--
ALTER TABLE `event_photographers`
  ADD CONSTRAINT `event_photographers_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `event_photographers_ibfk_2` FOREIGN KEY (`photographer_id`) REFERENCES `photographers` (`id`);

--
-- Constraints for table `event_watermarks`
--
ALTER TABLE `event_watermarks`
  ADD CONSTRAINT `event_watermarks_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `event_watermarks_ibfk_2` FOREIGN KEY (`watermark_id`) REFERENCES `watermarks` (`id`);

--
-- Constraints for table `sent_slideshows`
--
ALTER TABLE `sent_slideshows`
  ADD CONSTRAINT `sent_slideshows_ibfk_1` FOREIGN KEY (`sent_by`) REFERENCES `photographers` (`id`);

--
-- Constraints for table `sent_slideshow_images`
--
ALTER TABLE `sent_slideshow_images`
  ADD CONSTRAINT `sent_slideshow_images_ibfk_1` FOREIGN KEY (`event_image_id`) REFERENCES `event_images` (`id`),
  ADD CONSTRAINT `sent_slideshow_images_ibfk_2` FOREIGN KEY (`sent_slideshow_id`) REFERENCES `sent_slideshows` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
