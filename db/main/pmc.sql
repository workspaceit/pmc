-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 15, 2018 at 08:14 PM
-- Server version: 5.5.57-0ubuntu0.14.04.1-log
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=137 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `email`, `user_name`, `phone_number`, `image`, `password`, `created_at`, `created_by`) VALUES
(132, 'Anik S.', 'aniksarker17@gmail.com', '', '', '', '$2a$10$6C3HCNuSOm52lli4CK43wuGMoi4MvPXzxPpsSEbe/P5DHA4YlhSgC', '2017-12-19 18:08:02', NULL),
(133, 'Shawon', 'shawon@gmail.com', 'shawon', '01234565', '28712497703892.png', '$2a$10$LxT2LHSvFBXOIZPGCOTTq.HzHpFeV9NotBGy4G0UU7dEzfDAPMPTC', '2018-01-09 18:47:25', NULL),
(134, 'Roy Roy', 'roy@gmail.com', 'roy', '11', '', '$2a$10$v9aKn09NulrVFtdV..7caOOdlwDjL.AoVpM4SCMK6yDZHc6qdkONu', '2018-01-10 11:14:41', NULL),
(135, 'lala', 'lala@a', 'lala', '1122', '', '$2a$10$wZpqzPnaz474yTSl2oDeUeWw0V/Rd0/7l2WgRdeNKLjODm6b2zq4K', '2018-01-10 11:19:22', NULL),
(136, 'Rafi', 'rafi@k', 'Rafi', '354', '101989477327860.jpg', '$2a$10$DqfhIBgUvG6NH49lV3YwOOB.0M4Alo09khwApRyF3ewfzjdYNdGFm', '2018-01-10 15:08:43', NULL);

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
-- Table structure for table `advertisement_prices`
--

CREATE TABLE IF NOT EXISTS `advertisement_prices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `advertisers`
--

INSERT INTO `advertisers` (`id`, `name`, `address`, `city_id`, `state_id`, `zip`, `phone`, `website`, `all_locations`, `all_events`, `other_image`, `runtime_starts`, `runtime_ends`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'fdsa', 'sdf', 1, 1, 'sdf', 'sdf', 'sdf', 1, 0, NULL, '2018-01-01', '2018-08-02', '2018-01-11 11:37:33', '2018-01-11 11:37:33', 132),
(2, 'fdsa', 'sdf', 1, 1, 'sdf', 'sdf', 'sdf', 1, 0, NULL, '2018-01-01', '2018-08-02', '2018-01-11 11:38:23', '2018-01-11 11:38:23', 132),
(3, 'fdsa', 'sdf', 1, 1, 'sdf', 'sdf', 'sdf', 1, 0, NULL, '2018-01-01', '2018-08-02', '2018-01-11 11:40:10', '2018-01-11 11:40:10', 132),
(4, 'fdsa', 'sdf', 1, 1, 'sdf', 'sdf', 'sdf', 1, 0, NULL, '2018-01-01', '2018-08-02', '2018-01-11 11:40:39', '2018-01-11 11:40:39', 132),
(5, 'dfg', 'dfg', 1, 1, 'dfg', 'dfg', 'dfg', 0, 0, NULL, '2018-01-11', '2018-01-11', '2018-01-11 12:54:14', '2018-01-11 12:54:14', 132),
(6, 'dfg', 'dfg', 1, 1, 'dfg', 'dfg', 'dfg', 0, 0, NULL, '2018-01-11', '2018-01-11', '2018-01-11 12:54:21', '2018-01-11 12:54:21', 132),
(7, 'dfg', 'dfg', 1, 1, 'dfg', 'dfg', 'dfg', 0, 0, NULL, '2018-01-11', '2018-01-11', '2018-01-11 12:55:43', '2018-01-11 12:55:43', 132),
(8, 'dfg', 'dfg', 2, 3, 'dfg', 'dfg', 'dfg', 0, 0, NULL, '2018-01-11', '2018-01-11', '2018-01-11 12:56:34', '2018-01-15 13:28:47', 132),
(9, 'sdf3423', '4dsf234', 2, 3, 'sdf2343', 'sd34', 'sdf34', 0, 1, NULL, '2018-01-02', '2018-01-10', '2018-01-11 15:05:33', '2018-01-12 20:12:18', 132),
(10, 'ttyus', 'tyutyu', 2, 3, 'tyu', 'tyutyu', 'tyutyu', 1, 0, NULL, '2018-01-11', '2018-04-12', '2018-01-11 15:17:34', '2018-01-15 13:27:18', 132);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=30 ;

--
-- Dumping data for table `advertisers_other_image`
--

INSERT INTO `advertisers_other_image` (`id`, `advertiser_id`, `image`, `created_at`, `created_by`) VALUES
(1, 1, '175562720814603.png', '2018-01-11 11:37:33', 132),
(2, 1, '175562723557531.png', '2018-01-11 11:37:33', 132),
(3, 1, '175562912216788.png', '2018-01-11 11:37:33', 132),
(4, 2, '175562720814603.png', '2018-01-11 11:38:23', 132),
(5, 2, '175562723557531.png', '2018-01-11 11:38:23', 132),
(6, 2, '175562912216788.png', '2018-01-11 11:38:23', 132),
(7, 3, '175562720814603.png', '2018-01-11 11:40:10', 132),
(8, 3, '175562723557531.png', '2018-01-11 11:40:10', 132),
(9, 3, '175562912216788.png', '2018-01-11 11:40:10', 132),
(10, 4, '175562720814603.png', '2018-01-11 11:40:39', 132),
(11, 4, '175562723557531.png', '2018-01-11 11:40:39', 132),
(12, 4, '175562912216788.png', '2018-01-11 11:40:39', 132),
(13, 5, '180237192640792.png', '2018-01-11 12:54:15', 132),
(14, 5, '180237192640787.png', '2018-01-11 12:54:15', 132),
(15, 5, '180237810619809.png', '2018-01-11 12:54:15', 132),
(16, 6, '180237192640792.png', '2018-01-11 12:54:21', 132),
(17, 6, '180237192640787.png', '2018-01-11 12:54:21', 132),
(18, 6, '180237810619809.png', '2018-01-11 12:54:21', 132),
(19, 7, '180237192640792.png', '2018-01-11 12:55:43', 132),
(20, 7, '180237192640787.png', '2018-01-11 12:55:43', 132),
(21, 7, '180237810619809.png', '2018-01-11 12:55:43', 132),
(22, 8, '180237192640792.png', '2018-01-11 12:56:34', 132),
(23, 8, '180237192640787.png', '2018-01-11 12:56:34', 132),
(24, 8, '180237810619809.png', '2018-01-11 12:56:34', 132),
(26, 9, '188083496765312.png', '2018-01-11 15:05:33', 132),
(29, 9, '99399805075468.jpg', '2018-01-12 20:12:09', 132);

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
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Philadelphia', '2018-01-03 00:00:00', '0000-00-00 00:00:00', NULL),
(2, 'San Diego', '0000-00-00 00:00:00', '0000-00-00 00:00:00', NULL);

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
  `venue_id` int(11) NOT NULL,
  `event_photo` text COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id` (`venue_id`,`created_by`),
  KEY `venue_id` (`venue_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `name`, `starts_at`, `ends_at`, `is_private`, `venue_id`, `event_photo`, `created_at`, `updated_at`, `created_by`) VALUES
(3, 'Event 1', '2018-01-03 00:00:00', '2018-01-24 00:00:00', 0, 1, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', NULL),
(4, 'Event 2', '2018-01-03 00:00:00', '2018-01-11 00:00:00', 0, 1, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `event_advertisers`
--

INSERT INTO `event_advertisers` (`id`, `event_id`, `advertiser_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 3, 3, NULL, NULL, NULL),
(2, 4, 3, NULL, NULL, NULL),
(3, 4, 1, NULL, NULL, NULL),
(4, 3, 1, NULL, NULL, NULL),
(5, 3, 2, NULL, NULL, NULL),
(6, 4, 2, NULL, NULL, NULL),
(7, 4, 3, NULL, NULL, NULL),
(8, 3, 3, NULL, NULL, NULL),
(9, 3, 4, NULL, NULL, NULL),
(10, 4, 4, NULL, NULL, NULL),
(11, 3, 5, NULL, NULL, NULL),
(12, 4, 5, NULL, NULL, NULL),
(13, 4, 6, NULL, NULL, NULL),
(14, 3, 6, NULL, NULL, NULL),
(15, 4, 7, NULL, NULL, NULL),
(16, 3, 7, NULL, NULL, NULL),
(23, 3, 8, NULL, NULL, NULL),
(24, 4, 8, NULL, NULL, NULL),
(26, 3, 10, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event_images`
--

CREATE TABLE IF NOT EXISTS `event_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `image` int(11) NOT NULL,
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
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `event_watermarks`
--

CREATE TABLE IF NOT EXISTS `event_watermarks` (
  `id` int(11) NOT NULL DEFAULT '0',
  `event_id` int(11) NOT NULL,
  `watermark_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`,`watermark_id`,`created_by`),
  KEY `watermark_id` (`watermark_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `gallery_ads`
--

INSERT INTO `gallery_ads` (`id`, `advertiser_id`, `logo`, `background_image`, `top_banner_expiry_date`, `bottom_banner_expiry_date`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 1, '175579688060009.jpg', '175581761412603.jpg', '2018-01-11', '2018-01-11', '2018-01-11 11:37:33', '2018-01-11 11:37:33', 132),
(2, 2, '175579688060009.jpg', '175581761412603.jpg', '2018-01-11', '2018-01-25', '2018-01-11 11:38:23', '2018-01-11 11:38:23', 132),
(3, 3, '175579688060009.jpg', '175581761412603.jpg', '2018-01-11', '2018-01-25', '2018-01-11 11:40:10', '2018-01-11 11:40:10', 132),
(4, 4, '175579688060009.jpg', '175581761412603.jpg', '2018-01-11', '2018-01-25', '2018-01-11 11:40:39', '2018-01-11 11:40:39', 132),
(5, 5, '180252078469730.png', '180254125943095.png', '2018-01-11', '2018-01-11', '2018-01-11 12:54:15', '2018-01-11 12:54:15', 132),
(6, 6, '180252078469730.png', '180254125943095.png', '2018-01-11', '2018-01-11', '2018-01-11 12:54:21', '2018-01-11 12:54:21', 132),
(7, 7, '180252078469730.png', '180254125943095.png', '2018-01-11', '2018-01-11', '2018-01-11 12:55:43', '2018-01-11 12:55:43', 132),
(8, 8, '180252078469730.png', '180254125943095.png', '2018-01-11', '2018-01-11', '2018-01-11 12:56:34', '2018-01-11 12:56:34', 132),
(9, 9, '12147848592779.jpg', '12146138008302.jpg', '2018-01-09', '2018-01-16', '2018-01-11 15:05:33', '2018-01-15 16:08:41', 132),
(10, 10, '188888053545192.png', '188891945358247.png', '2018-01-11', '2018-01-11', '2018-01-11 15:17:34', '2018-01-11 15:17:34', 132);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=21 ;

--
-- Dumping data for table `gallery_ads_bottom_banner_image`
--

INSERT INTO `gallery_ads_bottom_banner_image` (`id`, `image`, `gallery_ad_id`, `created_at`, `created_by`) VALUES
(1, '175588643806401.jpg', 1, '2018-01-11 11:37:33', NULL),
(2, '175588643806401.jpg', 2, '2018-01-11 11:38:23', NULL),
(3, '175588643806401.jpg', 3, '2018-01-11 11:40:10', NULL),
(4, '175588643806401.jpg', 4, '2018-01-11 11:40:39', NULL),
(5, '180264550556111.png', 5, '2018-01-11 12:54:15', NULL),
(6, '180264557003798.png', 5, '2018-01-11 12:54:15', NULL),
(7, '180264782871109.png', 5, '2018-01-11 12:54:15', NULL),
(8, '180264550556111.png', 6, '2018-01-11 12:54:21', NULL),
(9, '180264557003798.png', 6, '2018-01-11 12:54:21', NULL),
(10, '180264782871109.png', 6, '2018-01-11 12:54:21', NULL),
(11, '180264550556111.png', 7, '2018-01-11 12:55:43', NULL),
(12, '180264557003798.png', 7, '2018-01-11 12:55:43', NULL),
(13, '180264782871109.png', 7, '2018-01-11 12:55:43', NULL),
(14, '180264550556111.png', 8, '2018-01-11 12:56:34', NULL),
(15, '180264557003798.png', 8, '2018-01-11 12:56:34', NULL),
(16, '180264782871109.png', 8, '2018-01-11 12:56:34', NULL),
(18, '188117370512175.png', 9, '2018-01-11 15:05:33', NULL),
(20, '188896285477950.png', 10, '2018-01-11 15:17:34', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=22 ;

--
-- Dumping data for table `gallery_ads_top_banner_images`
--

INSERT INTO `gallery_ads_top_banner_images` (`id`, `image`, `gallery_ad_id`, `created_at`, `created_by`) VALUES
(1, '175583580053305.jpg', 1, '2018-01-11 11:37:33', NULL),
(2, '175585265897305.jpg', 1, '2018-01-11 11:37:33', NULL),
(3, '175583580053305.jpg', 2, '2018-01-11 11:38:23', NULL),
(4, '175585265897305.jpg', 2, '2018-01-11 11:38:23', NULL),
(5, '175583580053305.jpg', 3, '2018-01-11 11:40:10', NULL),
(6, '175585265897305.jpg', 3, '2018-01-11 11:40:10', NULL),
(7, '175583580053305.jpg', 4, '2018-01-11 11:40:39', NULL),
(8, '175585265897305.jpg', 4, '2018-01-11 11:40:39', NULL),
(9, '180257972963690.png', 5, '2018-01-11 12:54:15', NULL),
(10, '180257984927102.png', 5, '2018-01-11 12:54:15', NULL),
(11, '180257972963690.png', 6, '2018-01-11 12:54:21', NULL),
(12, '180257984927102.png', 6, '2018-01-11 12:54:21', NULL),
(13, '180257972963690.png', 7, '2018-01-11 12:55:43', NULL),
(14, '180257984927102.png', 7, '2018-01-11 12:55:43', NULL),
(15, '180257972963690.png', 8, '2018-01-11 12:56:34', NULL),
(16, '180257984927102.png', 8, '2018-01-11 12:56:34', NULL),
(18, '188112212987733.png', 9, '2018-01-11 15:05:33', NULL),
(20, '188894286014157.png', 10, '2018-01-11 15:17:34', NULL),
(21, '11882980666602.jpg', 9, '2018-01-15 14:37:36', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `state_id` int(11) NOT NULL,
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
  PRIMARY KEY (`id`),
  KEY `city_id` (`state_id`,`created_by`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `name`, `address`, `state_id`, `zip`, `phone`, `location_logo`, `has_slideshow`, `duration_speed`, `break_time`, `fade_in_time`, `fade_out_time`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Location1', 'Address', 1, '234', '234234', '23423', 1, 1, 1, 1, 1, '2018-01-09 00:00:00', '2018-01-09 00:00:00', NULL),
(2, 'Loc2', 'sdf', 2, '234', '234', '19136980333780.png', 1, 435, 345, 1, 2, '2018-01-09 16:07:52', '2018-01-09 16:07:52', NULL),
(3, 'fdsg', 'fdg', 1, '435', 'fdsg', '19453247125865.png', 1, 4, 4, 1, 1, '2018-01-09 16:13:29', '2018-01-09 16:13:29', NULL),
(4, 'Loc 2', 'asd', 1, 'sad', 'asd', '20550395407420.png', 1, 12, 21, 1, 1, '2018-01-09 16:31:33', '2018-01-09 16:31:33', NULL),
(5, 'New Location1', '1234', 2, 'aa', 'dsfdferfhbdf', '4101017069972.jpg', 0, -1, -12, 3, 4, '2018-01-15 12:28:58', '2018-01-15 12:32:21', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=156 ;

--
-- Dumping data for table `location_advertisers`
--

INSERT INTO `location_advertisers` (`id`, `location_id`, `advertiser_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 1, 3, NULL, NULL, NULL),
(2, 2, 3, NULL, NULL, NULL),
(3, 3, 3, NULL, NULL, NULL),
(4, 4, 3, NULL, NULL, NULL),
(5, 1, 5, NULL, NULL, NULL),
(6, 3, 5, NULL, NULL, NULL),
(7, 1, 6, NULL, NULL, NULL),
(8, 3, 6, NULL, NULL, NULL),
(9, 1, 7, NULL, NULL, NULL),
(10, 3, 7, NULL, NULL, NULL),
(34, 1, 8, NULL, NULL, NULL),
(35, 3, 8, NULL, NULL, NULL),
(153, 1, 9, NULL, NULL, NULL),
(154, 3, 9, NULL, NULL, NULL),
(155, 4, 9, NULL, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

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
(7, '4133700040871.jpg', 5, '2018-01-15 12:28:58', NULL);

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
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=15 ;

--
-- Dumping data for table `photographers`
--

INSERT INTO `photographers` (`id`, `full_name`, `phone_number`, `user_name`, `email`, `password`, `profile_photo`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'dfg01', '4534545', 'sdf45', 'aniksarker17@gmail.comsdf777', '$2a$10$lAkUrx.BBPCmTeScqPSZBedYehSVRqLsJi8T0qNVsq9IGtUSxBnuG', '33899114831166.png', '2017-12-28 17:30:38', '2017-12-29 20:47:37', 132),
(2, 'sdf', '24234', 'sdfsdf', 'aniksarker17@gmail.com', '$2a$10$NRqk1LRH2ysSRdllVK3Br.hBXEJhsMvhUFaQDjQvDsp6ePUPnREFW', '29289743289968.png', '2017-12-28 19:11:49', '2017-12-28 19:11:49', 132),
(3, 'sdf', '24234', 'sdfsdf', 'aniksarker17@gmail.com1', '$2a$10$7KnWkdQzenvXNsEHMnfYi.NdIVKUCFowWkfahcOISwvXBuN6LiIYm', '29323396614167.png', '2017-12-28 19:12:42', '2017-12-28 19:12:42', 132),
(4, 'sdf', '24234', 'sdfsdf1', 'aniksarker17@gmail.com2', '$2a$10$yjxzAZSfliaw61YmrJtrQ.xrdT93ZB8Y9K15fLQHuYU6/TzrxoyOq', '29764752389342.png', '2017-12-28 19:19:56', '2017-12-28 19:19:56', 132),
(5, 'sdf', '24234', '1sdfsdf1', 'aniksarker17@gmail.com3', '$2a$10$6643qYFmKcIlpHHDefNX.eSCsgBb3FXHGnQhvNr6wtycT9f17MVm.', '29903096081751.png', '2017-12-28 19:22:34', '2017-12-28 19:22:34', 132),
(6, 'DFG', '234', '234', '234@SDF.COM', '$2a$10$fckjfm/BBbuBIFVH3olDueSWNgLZQeK4aJ7sTIdmKR0M/wvxxlSlK', '32286774523112.png', '2017-12-28 20:01:45', '2017-12-28 20:01:45', 132),
(7, 'wer', '324234', 'wer', 'wer@sdf.com', '$2a$10$kmwNjErSoVHmL7OQAD6/RO98ZF1NfLlXkNBTcLsto1uJbu1OFlUnG', '', '2017-12-29 12:21:20', '2017-12-29 12:21:20', 132),
(8, 'dfg', '5345', 'sdf01', 'sadf@asd.com', '$2a$10$c/2JS7U49Cy4G3lSdkl23OU6YJODjh24m5vBTnS/iHDMme2vnZjRq', '4724595750028.png', '2017-12-29 12:41:32', '2017-12-29 12:41:32', 132),
(9, 'dfg', '5345', '01sdf01', 'sadf@asd.com01', '$2a$10$nA.BvI8N.owvrGJxpqLPy.vhc1YrNdwmh7Qr749x5ymEKk9aMDAKO', '', '2017-12-29 12:42:08', '2017-12-29 12:42:08', 132),
(10, 'asdfg345345', '3423', 'sdf012', 'asd@asdf.comqwe', '$2a$10$zzwAVYjQfa9fW3OMuk7WCOgmZHjP3dCKQ62O2TQ/LEdVIeLb.zVOG', '35697004643068.png', '2017-12-29 12:44:35', '2018-01-02 17:10:46', 132),
(11, 'Lala', '343', 'lala', 'lala@w', '$2a$10$6bhNYKPrfmaNHjKBUMkNl.DQtOWgLftjHGgAA8RZlSLK0rBP8804C', '88123709911611.png', '2018-01-10 11:18:37', '2018-01-10 11:18:37', 132),
(12, 'Shuvo', '11', 'shuvo', 'shuvo11@q', '$2a$10$Wk45sjPG8cd0Agsld5oB7ewt9/eHNggFoNEo.Qz6ncSkC9dtxRp4K', '97926921713467.jpg', '2018-01-10 11:28:47', '2018-01-10 14:01:00', 132),
(13, 'Baba Rafi', '420', '2131312', '43423423@dfgtsdf', '$2a$10$8cmOBL0FZuZOnndLdbGRM.v5M/aYJ.Sypqq0hxRTrw5VGjjhVEyd6', '', '2018-01-10 15:10:27', '2018-01-10 16:23:34', 132),
(14, 'New', '4', 'New1', 'ng!@dd', '$2a$10$Z8XJ17qvbYVPBuRyrU5ckuIG8SiGopHv0whes5BKXKjXdqCPVkCIS', '5378074457433.png', '2018-01-15 12:49:47', '2018-01-15 12:53:46', 132);

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
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `popup_ads`
--

INSERT INTO `popup_ads` (`id`, `advertiser_id`, `type`, `duration`, `expiry_date`, `video`, `video_type`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 4, 'SMS', 1, '2018-01-11 00:00:00', '175666133795002.3gp', '', '2018-01-11 11:40:39', '2018-01-11 11:40:39', NULL),
(2, 4, 'EMAIL', 1, '2018-01-11 00:00:00', '175625627828074.mp4', '', '2018-01-11 11:40:39', '2018-01-11 11:40:39', NULL),
(3, 8, 'SMS', 1, '2018-01-11 00:00:00', '180314112592599.ogv', NULL, '2018-01-11 12:56:35', '2018-01-11 12:56:35', NULL),
(4, 8, 'EMAIL', 1, '2018-01-11 00:00:00', '180317937261213.mp4', NULL, '2018-01-11 12:56:35', '2018-01-11 12:56:35', NULL),
(5, 9, 'SMS', 5, '2018-01-15 00:00:00', '14261105271426.ogv', NULL, '2018-01-11 15:05:34', '2018-01-15 15:22:02', NULL),
(6, 9, 'EMAIL', 4, '2018-01-24 00:00:00', '14264103978791.ogv', NULL, '2018-01-11 15:05:34', '2018-01-15 15:22:02', NULL),
(7, 10, 'SMS', 1, '2018-01-11 00:00:00', '188909940584144.ogv', NULL, '2018-01-11 15:17:35', '2018-01-11 15:17:35', NULL),
(8, 10, 'EMAIL', 1, '2018-01-11 00:00:00', '188912897739819.mp4', NULL, '2018-01-11 15:17:35', '2018-01-11 15:17:35', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `popup_banner_images`
--

INSERT INTO `popup_banner_images` (`id`, `image`, `popup_ad_id`, `created_at`, `created_by`) VALUES
(1, '175609097685438.png', 1, '2018-01-11 11:40:39', 132),
(2, '175622299817304.png', 2, '2018-01-11 11:40:39', 132),
(3, '180308539567883.png', 3, '2018-01-11 12:56:35', 132),
(4, '180322070135158.png', 4, '2018-01-11 12:56:35', 132),
(7, '188918509774437.png', 7, '2018-01-11 15:17:35', 132),
(8, '188920986725591.png', 8, '2018-01-11 15:17:35', 132),
(10, '14249907052450.png', 5, '2018-01-15 15:17:16', 132),
(11, '14254543269731.png', 6, '2018-01-15 15:17:16', 132);

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
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `slideshow_ads`
--

INSERT INTO `slideshow_ads` (`id`, `advertiser_id`, `video`, `video_type`, `video_duration`, `banner_duration`, `video_expiry_date`, `banner_expiry_date`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 4, '8993174135124.mp4', 'video/mp4', 1, 1, NULL, NULL, '2018-01-11 11:40:39', '2018-01-11 11:40:39', 132),
(2, 8, '180276497385959.webm', 'video/webm', 1, 1, '2018-01-11 00:00:00', '2018-01-11 00:00:00', '2018-01-11 12:56:35', '2018-01-11 12:56:35', 132),
(3, 9, '13535566911563.mp4', 'video/mp4', 2, 3, '2018-01-17 00:00:00', '2018-01-25 00:00:00', '2018-01-11 15:05:33', '2018-01-15 15:13:39', 132),
(4, 10, '188904371037767.webm', 'video/webm', 1, 1, '2018-01-11 00:00:00', '2018-01-11 00:00:00', '2018-01-11 15:17:34', '2018-01-11 15:17:34', 132);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `slideshow_banner_images`
--

INSERT INTO `slideshow_banner_images` (`id`, `image`, `slideshow_ad_id`, `created_at`, `created_by`) VALUES
(1, '175593261198575.jpg', 1, '2018-01-11 11:40:39', 132),
(2, '180271700570832.png', 2, '2018-01-11 12:56:35', 132),
(4, '188899071388818.png', 4, '2018-01-11 15:17:34', 132),
(6, '13530421711359.jpg', 3, '2018-01-15 15:05:03', 132);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `states`
--

INSERT INTO `states` (`id`, `name`, `code`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Alaska', 'AK', NULL, NULL, NULL),
(2, 'Arkansas', 'AR', NULL, NULL, NULL),
(3, 'Arizona', 'AZ', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `temp_file`
--

CREATE TABLE IF NOT EXISTS `temp_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` int(11) NOT NULL,
  `path` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=570 ;

--
-- Dumping data for table `temp_file`
--

INSERT INTO `temp_file` (`id`, `token`, `path`, `created_date`) VALUES
(1, 1000412943, '/home/mi_rafi/project_file/pmc/11071508148606.png', '2017-12-28 08:08:05'),
(2, 1000399495, '/home/mi_rafi/project_file/pmc/11238577696024.png', '2017-12-28 08:10:52'),
(3, 1000354089, '/home/mi_rafi/project_file/pmc/11254000526726.png', '2017-12-28 08:11:08'),
(4, 1000891211, '/home/mi_rafi/project_file/pmc/12131260742429.png', '2017-12-28 08:25:45'),
(9, 1000522733, '/home/mi_rafi/project_file/pmc/21970226575765.png', '2017-12-28 11:09:44'),
(10, 1000772298, '/home/mi_rafi/project_file/pmc/22034118767452.png', '2017-12-28 11:10:48'),
(15, 1000844747, '/home/mi_rafi/project_file/pmc/tmp/32079328240090.png', '2017-12-28 13:58:13'),
(16, 1000642087, '/home/mi_rafi/project_file/pmc/tmp/32220480904143.png', '2017-12-28 14:00:34'),
(19, 1000625629, '/home/mi_rafi/project_file/pmc/tmp/21816342957768.png', '2017-12-29 11:26:14'),
(20, 1000133036, '/home/mi_rafi/project_file/pmc/tmp/21995222139245.png', '2017-12-29 11:29:13'),
(21, 1000678673, '/home/mi_rafi/project_file/pmc/tmp/28531865175492.png', '2017-12-29 13:18:09'),
(23, 1000790872, '/home/mi_rafi/project_file/pmc/tmp/30788788110459.png', '2017-12-29 13:55:46'),
(24, 1000358479, '/home/mi_rafi/project_file/pmc/tmp/31135081789775.png', '2017-12-29 14:01:32'),
(25, 1000045356, '/home/mi_rafi/project_file/pmc/tmp/31172647569070.png', '2017-12-29 14:02:10'),
(26, 1000174658, '/home/mi_rafi/project_file/pmc/tmp/31300993313666.png', '2017-12-29 14:04:18'),
(27, 1000311004, '/home/mi_rafi/project_file/pmc/tmp/31426908333334.png', '2017-12-29 14:06:24'),
(28, 1000524522, '/home/mi_rafi/project_file/pmc/tmp/31461429675915.png', '2017-12-29 14:06:59'),
(29, 1000569026, '/home/mi_rafi/project_file/pmc/tmp/31503944914264.png', '2017-12-29 14:07:41'),
(30, 1000068748, '/home/mi_rafi/project_file/pmc/tmp/31694210755020.png', '2017-12-29 14:10:52'),
(37, 1000848612, '/home/mi_rafi/project_file/pmc/tmp/32074662139458.png', '2017-12-29 14:17:12'),
(39, 1000756521, '/home/mi_rafi/project_file/pmc/tmp/32190976279773.png', '2017-12-29 14:19:08'),
(50, 1000144332, '/home/mi_rafi/project_file/pmc/tmp/32591991685882.png', '2017-12-29 14:25:49'),
(53, 1000706743, '/home/mi_rafi/project_file/pmc/tmp/32618737795658.png', '2017-12-29 14:26:16'),
(54, 1000263490, '/home/mi_rafi/project_file/pmc/tmp/32701368819298.png', '2017-12-29 14:27:39'),
(63, 1000206405, '/home/mi_rafi/project_file/pmc/tmp/33228104849209.png', '2017-12-29 14:36:25'),
(64, 1000098729, '/home/mi_rafi/project_file/pmc/tmp/33522958784551.png', '2017-12-29 14:41:20'),
(68, 1000035595, '/home/mi_rafi/project_file/pmc/tmp/33655759513396.png', '2017-12-29 14:43:33'),
(73, 1000839278, '/home/mi_rafi/project_file/pmc/tmp/33818717156786.png', '2017-12-29 14:46:16'),
(74, 1000611190, '/home/mi_rafi/project_file/pmc/tmp/33825596481177.png', '2017-12-29 14:46:23'),
(75, 1000232025, '/home/mi_rafi/project_file/pmc/tmp/4314777245447.png', '2018-01-02 07:40:39'),
(76, 1000629256, '/home/mi_rafi/project_file/pmc/tmp/4639644181695.png', '2018-01-02 07:46:04'),
(77, 1000794920, '/home/mi_rafi/project_file/pmc/tmp/5017382054220.png', '2018-01-02 07:52:22'),
(78, 1000540089, '/home/mi_rafi/project_file/pmc/tmp/5039169129321.png', '2018-01-02 07:52:44'),
(79, 1000227555, '/home/mi_rafi/project_file/pmc/tmp/5264380718658.png', '2018-01-02 07:56:29'),
(80, 1000422860, '/home/mi_rafi/project_file/pmc/tmp/5267049047836.png', '2018-01-02 07:56:31'),
(81, 1000811541, '/home/mi_rafi/project_file/pmc/tmp/5540630043274.png', '2018-01-02 08:01:05'),
(82, 1000218666, '/home/mi_rafi/project_file/pmc/tmp/5547365398952.png', '2018-01-02 08:01:12'),
(83, 1000515015, '/home/mi_rafi/project_file/pmc/tmp/5549847970279.png', '2018-01-02 08:01:14'),
(84, 1000473989, '/home/mi_rafi/project_file/pmc/tmp/5552091947838.png', '2018-01-02 08:01:16'),
(85, 1000319276, '/home/mi_rafi/project_file/pmc/tmp/6655029175032.png', '2018-01-02 08:19:39'),
(86, 1000192948, '/home/mi_rafi/project_file/pmc/tmp/6662280560530.png', '2018-01-02 08:19:47'),
(87, 1000078923, '/home/mi_rafi/project_file/pmc/tmp/6662337269184.png', '2018-01-02 08:19:47'),
(88, 1000611451, '/home/mi_rafi/project_file/pmc/tmp/6662449214580.png', '2018-01-02 08:19:47'),
(89, 1000410860, '/home/mi_rafi/project_file/pmc/tmp/6662506005222.png', '2018-01-02 08:19:47'),
(90, 1000632519, '/home/mi_rafi/project_file/pmc/tmp/6683976447373.png', '2018-01-02 08:20:08'),
(91, 1000869804, '/home/mi_rafi/project_file/pmc/tmp/6691181189491.png', '2018-01-02 08:20:16'),
(92, 1000653458, '/home/mi_rafi/project_file/pmc/tmp/6698999176480.png', '2018-01-02 08:20:23'),
(93, 1000536368, '/home/mi_rafi/project_file/pmc/tmp/6699002124816.png', '2018-01-02 08:20:23'),
(94, 1000200042, '/home/mi_rafi/project_file/pmc/tmp/6764739793879.png', '2018-01-02 08:21:29'),
(95, 1000453177, '/home/mi_rafi/project_file/pmc/tmp/6764748161941.png', '2018-01-02 08:21:29'),
(96, 1000316691, '/home/mi_rafi/project_file/pmc/tmp/6764899435488.png', '2018-01-02 08:21:29'),
(97, 1000446670, '/home/mi_rafi/project_file/pmc/tmp/6764974211987.png', '2018-01-02 08:21:29'),
(98, 1000826337, '/home/mi_rafi/project_file/pmc/tmp/12551728515279.png', '2018-01-02 09:57:56'),
(99, 1000678934, '/home/mi_rafi/project_file/pmc/tmp/12557370888436.png', '2018-01-02 09:58:02'),
(100, 1000433335, '/home/mi_rafi/project_file/pmc/tmp/12562798100403.png', '2018-01-02 09:58:07'),
(101, 1000712624, '/home/mi_rafi/project_file/pmc/tmp/12565950559678.png', '2018-01-02 09:58:10'),
(102, 1000251835, '/home/mi_rafi/project_file/pmc/tmp/12759173226394.png', '2018-01-02 10:01:24'),
(103, 1000838354, '/home/mi_rafi/project_file/pmc/tmp/12767807593504.png', '2018-01-02 10:01:32'),
(104, 1000091021, '/home/mi_rafi/project_file/pmc/tmp/12767872984141.png', '2018-01-02 10:01:32'),
(105, 1000285967, '/home/mi_rafi/project_file/pmc/tmp/13158860147053.png', '2018-01-02 10:08:03'),
(106, 1000874287, '/home/mi_rafi/project_file/pmc/tmp/13165842145899.png', '2018-01-02 10:08:10'),
(107, 1000597330, '/home/mi_rafi/project_file/pmc/tmp/13165869127235.png', '2018-01-02 10:08:10'),
(108, 1000786072, '/home/mi_rafi/project_file/pmc/tmp/13639973818059.png', '2018-01-02 10:16:04'),
(109, 1000035220, '/home/mi_rafi/project_file/pmc/tmp/13639970223524.png', '2018-01-02 10:16:04'),
(110, 1000098900, '/home/mi_rafi/project_file/pmc/tmp/13640411164481.png', '2018-01-02 10:16:05'),
(111, 1000707329, '/home/mi_rafi/project_file/pmc/tmp/13701288155230.png', '2018-01-02 10:17:06'),
(112, 1000804069, '/home/mi_rafi/project_file/pmc/tmp/13851494856125.png', '2018-01-02 10:19:36'),
(113, 1000366926, '/home/mi_rafi/project_file/pmc/tmp/13866221816924.png', '2018-01-02 10:19:51'),
(114, 1000860111, '/home/mi_rafi/project_file/pmc/tmp/13866286858337.png', '2018-01-02 10:19:51'),
(115, 1000502338, '/home/mi_rafi/project_file/pmc/tmp/13866373671042.png', '2018-01-02 10:19:51'),
(116, 1000429718, '/home/mi_rafi/project_file/pmc/tmp/13866455375707.png', '2018-01-02 10:19:51'),
(117, 1000352985, '/home/mi_rafi/project_file/pmc/tmp/14052851944749.png', '2018-01-02 10:22:57'),
(118, 1000251900, '/home/mi_rafi/project_file/pmc/tmp/14060099069871.png', '2018-01-02 10:23:04'),
(119, 1000181745, '/home/mi_rafi/project_file/pmc/tmp/14060115359784.png', '2018-01-02 10:23:05'),
(120, 1000727904, '/home/mi_rafi/project_file/pmc/tmp/14060279614898.png', '2018-01-02 10:23:05'),
(121, 1000776555, '/home/mi_rafi/project_file/pmc/tmp/14060301000447.png', '2018-01-02 10:23:05'),
(122, 1000743547, '/home/mi_rafi/project_file/pmc/tmp/14134278495081.png', '2018-01-02 10:24:19'),
(123, 1000692021, '/home/mi_rafi/project_file/pmc/tmp/14138545148075.png', '2018-01-02 10:24:23'),
(124, 1000154560, '/home/mi_rafi/project_file/pmc/tmp/14138567791282.png', '2018-01-02 10:24:23'),
(125, 1000344798, '/home/mi_rafi/project_file/pmc/tmp/14138680721825.png', '2018-01-02 10:24:23'),
(126, 1000530904, '/home/mi_rafi/project_file/pmc/tmp/15035575890036.png', '2018-01-02 10:39:20'),
(127, 1000257792, '/home/mi_rafi/project_file/pmc/tmp/15341989005398.png', '2018-01-02 10:44:26'),
(130, 1000524616, '/home/mi_rafi/project_file/pmc/tmp/20446991836987.jpg', '2018-01-02 12:09:31'),
(131, 1000539362, '/home/mi_rafi/project_file/pmc/tmp/20454419288728.png', '2018-01-02 12:09:39'),
(132, 1000060459, '/home/mi_rafi/project_file/pmc/tmp/20454426489488.png', '2018-01-02 12:09:39'),
(133, 1000744506, '/home/mi_rafi/project_file/pmc/tmp/20454557869030.png', '2018-01-02 12:09:39'),
(134, 1000828784, '/home/mi_rafi/project_file/pmc/tmp/20454599841669.png', '2018-01-02 12:09:39'),
(135, 1000250399, '/home/mi_rafi/project_file/pmc/tmp/20454688466422.png', '2018-01-02 12:09:39'),
(136, 1000279007, '/home/mi_rafi/project_file/pmc/tmp/20960013917436.jpg', '2018-01-02 12:18:04'),
(138, 1000287261, '/home/mi_rafi/project_file/pmc/tmp/29509776336830.jpg', '2018-01-02 14:40:34'),
(139, 1000310001, '/home/mi_rafi/project_file/pmc/tmp/29523316176027.png', '2018-01-02 14:40:48'),
(140, 1000733544, '/home/mi_rafi/project_file/pmc/tmp/29523349415554.png', '2018-01-02 14:40:48'),
(141, 1000368197, '/home/mi_rafi/project_file/pmc/tmp/29523467252186.png', '2018-01-02 14:40:48'),
(142, 1000355089, '/home/mi_rafi/project_file/pmc/tmp/34290499905865.jpg', '2018-01-02 16:00:15'),
(143, 1000837926, '/home/mi_rafi/project_file/pmc/tmp/34298793638497.png', '2018-01-02 16:00:23'),
(144, 1000700768, '/home/mi_rafi/project_file/pmc/tmp/34298807207077.png', '2018-01-02 16:00:23'),
(145, 1000489750, '/home/mi_rafi/project_file/pmc/tmp/34299032741232.png', '2018-01-02 16:00:23'),
(146, 1000738671, '/home/mi_rafi/project_file/pmc/tmp/34299218900923.png', '2018-01-02 16:00:24'),
(147, 1000011000, '/home/mi_rafi/project_file/pmc/tmp/98948831526939.png', '2018-01-03 09:57:53'),
(148, 1000790884, '/home/mi_rafi/project_file/pmc/tmp/98974713056925.png', '2018-01-03 09:58:19'),
(149, 1000499000, '/home/mi_rafi/project_file/pmc/tmp/99520962799802.png', '2018-01-03 10:07:25'),
(150, 1000738624, '/home/mi_rafi/project_file/pmc/tmp/99539212743607.png', '2018-01-03 10:07:44'),
(151, 1000117164, '/home/mi_rafi/project_file/pmc/tmp/99587924720066.png', '2018-01-03 10:08:32'),
(152, 1000337991, '/home/mi_rafi/project_file/pmc/tmp/99656764717164.png', '2018-01-03 10:09:41'),
(153, 1000355860, '/home/mi_rafi/project_file/pmc/tmp/99661184998867.png', '2018-01-03 10:09:46'),
(154, 1000820095, '/home/mi_rafi/project_file/pmc/tmp/99669557370828.png', '2018-01-03 10:09:54'),
(155, 1000570734, '/home/mi_rafi/project_file/pmc/tmp/99672092254462.jpg', '2018-01-03 10:09:56'),
(156, 1000065271, '/home/mi_rafi/project_file/pmc/tmp/105977454504979.jpg', '2018-01-03 11:55:02'),
(157, 1000570626, '/home/mi_rafi/project_file/pmc/tmp/105986355945201.png', '2018-01-03 11:55:11'),
(158, 1000044396, '/home/mi_rafi/project_file/pmc/tmp/105986374990304.png', '2018-01-03 11:55:11'),
(159, 1000179024, '/home/mi_rafi/project_file/pmc/tmp/107127822805245.png', '2018-01-03 12:14:12'),
(160, 1000265876, '/home/mi_rafi/project_file/pmc/tmp/109576454635983.png', '2018-01-03 12:55:01'),
(161, 1000717604, '/home/mi_rafi/project_file/pmc/tmp/109596060107824.jpg', '2018-01-03 12:55:21'),
(162, 1000880521, '/home/mi_rafi/project_file/pmc/tmp/3587792995240.mp4', '2018-01-04 07:16:23'),
(163, 1000625375, '/home/mi_rafi/project_file/pmc/tmp/6162465794711.mp4', '2018-01-04 07:59:18'),
(164, 1000639792, '/home/mi_rafi/project_file/pmc/tmp/11269368608786.png', '2018-01-04 09:24:25'),
(166, 1000292685, '/home/mi_rafi/project_file/pmc/tmp/11773149023653.png', '2018-01-04 09:32:49'),
(167, 1000329175, '/home/mi_rafi/project_file/pmc/tmp/15345148055381.png', '2018-01-04 10:32:21'),
(168, 1000853418, '/home/mi_rafi/project_file/pmc/tmp/15350232626173.png', '2018-01-04 10:32:26'),
(169, 1000178091, '/home/mi_rafi/project_file/pmc/tmp/15405359315665.png', '2018-01-04 10:33:21'),
(170, 1000813454, '/home/mi_rafi/project_file/pmc/tmp/6448766242856.png', '2018-01-05 08:28:25'),
(171, 1000658069, '/home/mi_rafi/project_file/pmc/tmp/6553788881329.png', '2018-01-05 08:30:09'),
(172, 1000595495, '/home/mi_rafi/project_file/pmc/tmp/6627512737460.png', '2018-01-05 08:31:23'),
(173, 1000602858, '/home/mi_rafi/project_file/pmc/tmp/6656344620377.png', '2018-01-05 08:31:52'),
(174, 1000277147, '/home/mi_rafi/project_file/pmc/tmp/6663836474223.png', '2018-01-05 08:31:59'),
(175, 1000506450, '/home/mi_rafi/project_file/pmc/tmp/6669699632550.png', '2018-01-05 08:32:05'),
(176, 1000708778, '/home/mi_rafi/project_file/pmc/tmp/12245630667660.png', '2018-01-05 10:05:02'),
(177, 1000376488, '/home/mi_rafi/project_file/pmc/tmp/12252403966763.png', '2018-01-05 10:05:08'),
(178, 1000479675, '/home/mi_rafi/project_file/pmc/tmp/12259205170105.png', '2018-01-05 10:05:15'),
(179, 1000074044, '/home/mi_rafi/project_file/pmc/tmp/12263755751266.png', '2018-01-05 10:05:19'),
(180, 1000224214, '/home/mi_rafi/project_file/pmc/tmp/12986135410773.png', '2018-01-05 10:17:22'),
(181, 1000252097, '/home/mi_rafi/project_file/pmc/tmp/12989858994827.png', '2018-01-05 10:17:26'),
(182, 1000175918, '/home/mi_rafi/project_file/pmc/tmp/12996407670050.png', '2018-01-05 10:17:32'),
(183, 1000658927, '/home/mi_rafi/project_file/pmc/tmp/12999043310274.png', '2018-01-05 10:17:35'),
(184, 1000834786, '/home/mi_rafi/project_file/pmc/tmp/13100266451365.png', '2018-01-05 10:19:16'),
(185, 1000856514, '/home/mi_rafi/project_file/pmc/tmp/13102263130093.png', '2018-01-05 10:19:18'),
(186, 1000847618, '/home/mi_rafi/project_file/pmc/tmp/13104869897012.png', '2018-01-05 10:19:21'),
(187, 1000775225, '/home/mi_rafi/project_file/pmc/tmp/13107856553126.png', '2018-01-05 10:19:24'),
(188, 1000149828, '/home/mi_rafi/project_file/pmc/tmp/14926788524408.png', '2018-01-05 10:49:42'),
(189, 1000134175, '/home/mi_rafi/project_file/pmc/tmp/14983766925546.png', '2018-01-05 10:50:39'),
(190, 1000087950, '/home/mi_rafi/project_file/pmc/tmp/15069279707111.png', '2018-01-05 10:52:05'),
(191, 1000085597, '/home/mi_rafi/project_file/pmc/tmp/15242393491503.mp4', '2018-01-05 10:54:58'),
(192, 1000473177, '/home/mi_rafi/project_file/pmc/tmp/15423597015769.mp4', '2018-01-05 10:57:59'),
(196, 1000729139, '/home/mi_rafi/project_file/pmc/tmp/16403299882643.mp4', '2018-01-05 11:14:19'),
(199, 1000196997, '/home/mi_rafi/project_file/pmc/tmp/16553087430744.png', '2018-01-05 11:16:49'),
(200, 1000283119, '/home/mi_rafi/project_file/pmc/tmp/19472762631349.png', '2018-01-05 12:05:28'),
(201, 1000837458, '/home/mi_rafi/project_file/pmc/tmp/19550720122840.png', '2018-01-05 12:06:46'),
(202, 1000748772, '/home/mi_rafi/project_file/pmc/tmp/19556111363649.png', '2018-01-05 12:06:52'),
(203, 1000505859, '/home/mi_rafi/project_file/pmc/tmp/19565342100027.mp4', '2018-01-05 12:07:01'),
(204, 1000357310, '/home/mi_rafi/project_file/pmc/tmp/19570451251698.mp4', '2018-01-05 12:07:06'),
(205, 1000598210, '/home/mi_rafi/project_file/pmc/tmp/24370593441840.png', '2018-01-05 13:27:06'),
(206, 1000576733, '/home/mi_rafi/project_file/pmc/tmp/24370606401030.png', '2018-01-05 13:27:06'),
(207, 1000752351, '/home/mi_rafi/project_file/pmc/tmp/24370997295491.png', '2018-01-05 13:27:07'),
(208, 1000622910, '/home/mi_rafi/project_file/pmc/tmp/25797591509870.png', '2018-01-05 13:50:53'),
(210, 1000781804, '/home/mi_rafi/project_file/pmc/tmp/25804678808643.png', '2018-01-05 13:51:00'),
(211, 1000763450, '/home/mi_rafi/project_file/pmc/tmp/25807616472171.png', '2018-01-05 13:51:03'),
(212, 1000892991, '/home/mi_rafi/project_file/pmc/tmp/25810968364153.png', '2018-01-05 13:51:07'),
(213, 1000410404, '/home/mi_rafi/project_file/pmc/tmp/25813732537533.png', '2018-01-05 13:51:09'),
(214, 1000250955, '/home/mi_rafi/project_file/pmc/tmp/25823987910644.png', '2018-01-05 13:51:20'),
(215, 1000144277, '/home/mi_rafi/project_file/pmc/tmp/10190916879080.jpg', '2018-01-08 08:12:09'),
(216, 1000790102, '/home/mi_rafi/project_file/pmc/tmp/10193196584102.jpg', '2018-01-08 08:12:11'),
(217, 1000285671, '/home/mi_rafi/project_file/pmc/tmp/10196323578475.jpg', '2018-01-08 08:12:14'),
(218, 1000680633, '/home/mi_rafi/project_file/pmc/tmp/10198211822230.jpg', '2018-01-08 08:12:16'),
(219, 1000747525, '/home/mi_rafi/project_file/pmc/tmp/10201134224631.png', '2018-01-08 08:12:19'),
(220, 1000231554, '/home/mi_rafi/project_file/pmc/tmp/10203666465287.png', '2018-01-08 08:12:22'),
(221, 1000060610, '/home/mi_rafi/project_file/pmc/tmp/10417770387235.png', '2018-01-08 08:15:56'),
(222, 1000632258, '/home/mi_rafi/project_file/pmc/tmp/10420807996340.png', '2018-01-08 08:15:59'),
(223, 1000464423, '/home/mi_rafi/project_file/pmc/tmp/10423478949287.png', '2018-01-08 08:16:01'),
(224, 1000321257, '/home/mi_rafi/project_file/pmc/tmp/10426011997729.png', '2018-01-08 08:16:04'),
(225, 1000870736, '/home/mi_rafi/project_file/pmc/tmp/13707045723548.png', '2018-01-08 09:10:45'),
(226, 1000727451, '/home/mi_rafi/project_file/pmc/tmp/13775316766407.png', '2018-01-08 09:11:53'),
(227, 1000616186, '/home/mi_rafi/project_file/pmc/tmp/13779501609367.png', '2018-01-08 09:11:57'),
(228, 1000672003, '/home/mi_rafi/project_file/pmc/tmp/13782007508888.png', '2018-01-08 09:12:00'),
(229, 1000037192, '/home/mi_rafi/project_file/pmc/tmp/13888304119715.png', '2018-01-08 09:13:46'),
(230, 1000628738, '/home/mi_rafi/project_file/pmc/tmp/13890010160132.png', '2018-01-08 09:13:48'),
(231, 1000077334, '/home/mi_rafi/project_file/pmc/tmp/13894072931488.png', '2018-01-08 09:13:52'),
(232, 1000848700, '/home/mi_rafi/project_file/pmc/tmp/13907979872262.png', '2018-01-08 09:14:06'),
(233, 1000339490, '/home/mi_rafi/project_file/pmc/tmp/13913486766701.mkv', '2018-01-08 09:14:11'),
(234, 1000178538, '/home/mi_rafi/project_file/pmc/tmp/13917232866008.mkv', '2018-01-08 09:14:15'),
(235, 1000495808, '/home/mi_rafi/project_file/pmc/tmp/13944131777024.mp4', '2018-01-08 09:14:42'),
(236, 1000545545, '/home/mi_rafi/project_file/pmc/tmp/22574829319134.mp4', '2018-01-08 11:38:33'),
(237, 1000298668, '/home/mi_rafi/project_file/pmc/tmp/24846374409061.png', '2018-01-08 12:16:24'),
(238, 1000493937, '/home/mi_rafi/project_file/pmc/tmp/24849968175052.png', '2018-01-08 12:16:28'),
(239, 1000673339, '/home/mi_rafi/project_file/pmc/tmp/24854198231631.png', '2018-01-08 12:16:32'),
(240, 1000894127, '/home/mi_rafi/project_file/pmc/tmp/24857132218848.png', '2018-01-08 12:16:35'),
(241, 1000147486, '/home/mi_rafi/project_file/pmc/tmp/24868789143295.png', '2018-01-08 12:16:47'),
(242, 1000898898, '/home/mi_rafi/project_file/pmc/tmp/24874021177089.mp4', '2018-01-08 12:16:52'),
(243, 1000195835, '/home/mi_rafi/project_file/pmc/tmp/24948411631905.png', '2018-01-08 12:18:06'),
(244, 1000525657, '/home/mi_rafi/project_file/pmc/tmp/24953503876126.png', '2018-01-08 12:18:11'),
(245, 1000659383, '/home/mi_rafi/project_file/pmc/tmp/24957964314059.mp4', '2018-01-08 12:18:16'),
(246, 1000425150, '/home/mi_rafi/project_file/pmc/tmp/24962135661722.mp4', '2018-01-08 12:18:20'),
(247, 1000108449, '/home/mi_rafi/project_file/pmc/tmp/26525443149358.png', '2018-01-08 12:44:23'),
(248, 1000749818, '/home/mi_rafi/project_file/pmc/tmp/26528766097408.png', '2018-01-08 12:44:27'),
(249, 1000625225, '/home/mi_rafi/project_file/pmc/tmp/26531638843083.png', '2018-01-08 12:44:30'),
(250, 1000482380, '/home/mi_rafi/project_file/pmc/tmp/26534308562016.png', '2018-01-08 12:44:32'),
(251, 1000795327, '/home/mi_rafi/project_file/pmc/tmp/26543229807264.png', '2018-01-08 12:44:41'),
(252, 1000773039, '/home/mi_rafi/project_file/pmc/tmp/26558251436919.mp4', '2018-01-08 12:44:56'),
(253, 1000038588, '/home/mi_rafi/project_file/pmc/tmp/26589551168009.png', '2018-01-08 12:45:27'),
(254, 1000668695, '/home/mi_rafi/project_file/pmc/tmp/26595075969772.mp4', '2018-01-08 12:45:33'),
(255, 1000817973, '/home/mi_rafi/project_file/pmc/tmp/26602654710148.mp4', '2018-01-08 12:45:41'),
(256, 1000781161, '/home/mi_rafi/project_file/pmc/tmp/26606339329028.png', '2018-01-08 12:45:44'),
(257, 1000390030, '/home/mi_rafi/project_file/pmc/tmp/27803292441717.png', '2018-01-08 13:05:41'),
(258, 1000755354, '/home/mi_rafi/project_file/pmc/tmp/27808054042947.png', '2018-01-08 13:05:46'),
(259, 1000630714, '/home/mi_rafi/project_file/pmc/tmp/27810386697231.png', '2018-01-08 13:05:48'),
(260, 1000587294, '/home/mi_rafi/project_file/pmc/tmp/27812498590245.png', '2018-01-08 13:05:50'),
(261, 1000836047, '/home/mi_rafi/project_file/pmc/tmp/27814624522172.png', '2018-01-08 13:05:52'),
(262, 1000027666, '/home/mi_rafi/project_file/pmc/tmp/27819105104613.png', '2018-01-08 13:05:57'),
(263, 1000158686, '/home/mi_rafi/project_file/pmc/tmp/27823753943761.mp4', '2018-01-08 13:06:02'),
(264, 1000077446, '/home/mi_rafi/project_file/pmc/tmp/27865967251252.png', '2018-01-08 13:06:44'),
(265, 1000317719, '/home/mi_rafi/project_file/pmc/tmp/27869901701571.mp4', '2018-01-08 13:06:48'),
(266, 1000688718, '/home/mi_rafi/project_file/pmc/tmp/27874842620663.png', '2018-01-08 13:06:53'),
(267, 1000554149, '/home/mi_rafi/project_file/pmc/tmp/27878811043989.mp4', '2018-01-08 13:06:57'),
(268, 1000610026, '/home/mi_rafi/project_file/pmc/tmp/31888887894484.png', '2018-01-08 14:13:47'),
(269, 1000537188, '/home/mi_rafi/project_file/pmc/tmp/31945664533708.png', '2018-01-08 14:14:44'),
(270, 1000110841, '/home/mi_rafi/project_file/pmc/tmp/31947792118250.png', '2018-01-08 14:14:46'),
(271, 1000152510, '/home/mi_rafi/project_file/pmc/tmp/31952918570517.png', '2018-01-08 14:14:51'),
(272, 1000847855, '/home/mi_rafi/project_file/pmc/tmp/31955642014974.png', '2018-01-08 14:14:54'),
(273, 1000030620, '/home/mi_rafi/project_file/pmc/tmp/31962363666569.png', '2018-01-08 14:15:00'),
(274, 1000373700, '/home/mi_rafi/project_file/pmc/tmp/32249331796602.mp4', '2018-01-08 14:19:47'),
(275, 1000605025, '/home/mi_rafi/project_file/pmc/tmp/32258838233852.mp4', '2018-01-08 14:19:57'),
(276, 1000151535, '/home/mi_rafi/project_file/pmc/tmp/32261644943674.mp4', '2018-01-08 14:20:00'),
(277, 1000121393, '/home/mi_rafi/project_file/pmc/tmp/32265686033134.png', '2018-01-08 14:20:04'),
(278, 1000496319, '/home/mi_rafi/project_file/pmc/tmp/32268984994016.png', '2018-01-08 14:20:07'),
(279, 1000492620, '/home/mi_rafi/project_file/pmc/tmp/32274181863188.png', '2018-01-08 14:20:12'),
(280, 1000157624, '/home/mi_rafi/project_file/pmc/tmp/32277074205763.png', '2018-01-08 14:20:15'),
(281, 1000185796, '/home/mi_rafi/project_file/pmc/tmp/35439252704579.png', '2018-01-08 15:12:57'),
(282, 1000602165, '/home/mi_rafi/project_file/pmc/tmp/35443208920968.png', '2018-01-08 15:13:01'),
(283, 1000654724, '/home/mi_rafi/project_file/pmc/tmp/35541627328165.png', '2018-01-08 15:14:39'),
(284, 1000563695, '/home/mi_rafi/project_file/pmc/tmp/35808335484686.png', '2018-01-08 15:19:06'),
(285, 1000258675, '/home/mi_rafi/project_file/pmc/tmp/35808343709763.png', '2018-01-08 15:19:06'),
(286, 1000394575, '/home/mi_rafi/project_file/pmc/tmp/35808778810154.png', '2018-01-08 15:19:07'),
(287, 1000510765, '/home/mi_rafi/project_file/pmc/tmp/35815888086623.png', '2018-01-08 15:19:14'),
(288, 1000865439, '/home/mi_rafi/project_file/pmc/tmp/35817871622259.png', '2018-01-08 15:19:16'),
(289, 1000237508, '/home/mi_rafi/project_file/pmc/tmp/35819719440397.png', '2018-01-08 15:19:18'),
(290, 1000032220, '/home/mi_rafi/project_file/pmc/tmp/35822037814632.png', '2018-01-08 15:19:20'),
(291, 1000507489, '/home/mi_rafi/project_file/pmc/tmp/35825303114202.png', '2018-01-08 15:19:23'),
(292, 1000690369, '/home/mi_rafi/project_file/pmc/tmp/35829960980138.mp4', '2018-01-08 15:19:28'),
(293, 1000230146, '/home/mi_rafi/project_file/pmc/tmp/35842025046851.png', '2018-01-08 15:19:40'),
(294, 1000325349, '/home/mi_rafi/project_file/pmc/tmp/35845720861797.mp4', '2018-01-08 15:19:44'),
(295, 1000842254, '/home/mi_rafi/project_file/pmc/tmp/35850065758221.png', '2018-01-08 15:19:48'),
(296, 1000254333, '/home/mi_rafi/project_file/pmc/tmp/35853925099086.mp4', '2018-01-08 15:19:52'),
(297, 1000378005, '/home/mi_rafi/project_file/pmc/tmp/35979558556284.png', '2018-01-08 15:21:57'),
(298, 1000100458, '/home/mi_rafi/project_file/pmc/tmp/35981879516585.png', '2018-01-08 15:22:00'),
(299, 1000405156, '/home/mi_rafi/project_file/pmc/tmp/35983759189257.png', '2018-01-08 15:22:02'),
(300, 1000357576, '/home/mi_rafi/project_file/pmc/tmp/35985761378521.png', '2018-01-08 15:22:04'),
(301, 1000799903, '/home/mi_rafi/project_file/pmc/tmp/35992651211953.png', '2018-01-08 15:22:11'),
(302, 1000465530, '/home/mi_rafi/project_file/pmc/tmp/35998321457008.png', '2018-01-08 15:22:16'),
(303, 1000218069, '/home/mi_rafi/project_file/pmc/tmp/36000395766538.png', '2018-01-08 15:22:18'),
(304, 1000588031, '/home/mi_rafi/project_file/pmc/tmp/36004750189491.mp4', '2018-01-08 15:22:23'),
(305, 1000066137, '/home/mi_rafi/project_file/pmc/tmp/36007534047772.mp4', '2018-01-08 15:22:25'),
(306, 1000580983, '/home/mi_rafi/project_file/pmc/tmp/36017815635093.mp4', '2018-01-08 15:22:36'),
(307, 1000820060, '/home/mi_rafi/project_file/pmc/tmp/36197416469055.png', '2018-01-08 15:25:35'),
(308, 1000140846, '/home/mi_rafi/project_file/pmc/tmp/36197446255750.png', '2018-01-08 15:25:35'),
(309, 1000062999, '/home/mi_rafi/project_file/pmc/tmp/36197616871457.png', '2018-01-08 15:25:35'),
(310, 1000186327, '/home/mi_rafi/project_file/pmc/tmp/36202983060293.png', '2018-01-08 15:25:41'),
(311, 1000791590, '/home/mi_rafi/project_file/pmc/tmp/36204902401325.png', '2018-01-08 15:25:43'),
(312, 1000285200, '/home/mi_rafi/project_file/pmc/tmp/36206839926483.png', '2018-01-08 15:25:45'),
(313, 1000629220, '/home/mi_rafi/project_file/pmc/tmp/36208898576921.png', '2018-01-08 15:25:47'),
(314, 1000334772, '/home/mi_rafi/project_file/pmc/tmp/36211932313110.png', '2018-01-08 15:25:50'),
(315, 1000810241, '/home/mi_rafi/project_file/pmc/tmp/36226298566706.mp4', '2018-01-08 15:26:04'),
(316, 1000827590, '/home/mi_rafi/project_file/pmc/tmp/36230479149097.mp4', '2018-01-08 15:26:08'),
(317, 1000053833, '/home/mi_rafi/project_file/pmc/tmp/36238782398297.png', '2018-01-08 15:26:17'),
(318, 1000749582, '/home/mi_rafi/project_file/pmc/tmp/36244185786174.png', '2018-01-08 15:26:22'),
(319, 1000812833, '/home/mi_rafi/project_file/pmc/tmp/36252706929031.mp4', '2018-01-08 15:26:31'),
(320, 1000671855, '/home/mi_rafi/project_file/pmc/tmp/36256253222891.mp4', '2018-01-08 15:26:34'),
(321, 1000452187, '/home/mi_rafi/project_file/pmc/tmp/8797928353037.png', '2018-01-09 07:15:28'),
(322, 1000574261, '/home/mi_rafi/project_file/pmc/tmp/8797928353013.png', '2018-01-09 07:15:28'),
(323, 1000341149, '/home/mi_rafi/project_file/pmc/tmp/8799636271727.png', '2018-01-09 07:15:29'),
(324, 1000567748, '/home/mi_rafi/project_file/pmc/tmp/8799634938151.png', '2018-01-09 07:15:29'),
(325, 1000756817, '/home/mi_rafi/project_file/pmc/tmp/8817233622367.png', '2018-01-09 07:15:47'),
(326, 1000448282, '/home/mi_rafi/project_file/pmc/tmp/8820756460916.png', '2018-01-09 07:15:51'),
(327, 1000699004, '/home/mi_rafi/project_file/pmc/tmp/8823032574950.png', '2018-01-09 07:15:53'),
(328, 1000315526, '/home/mi_rafi/project_file/pmc/tmp/8827722209980.png', '2018-01-09 07:15:58'),
(329, 1000319722, '/home/mi_rafi/project_file/pmc/tmp/8830231504155.png', '2018-01-09 07:16:00'),
(330, 1000721309, '/home/mi_rafi/project_file/pmc/tmp/8832162610522.png', '2018-01-09 07:16:02'),
(331, 1000820954, '/home/mi_rafi/project_file/pmc/tmp/8835016884598.png', '2018-01-09 07:16:05'),
(332, 1000173747, '/home/mi_rafi/project_file/pmc/tmp/8954536126979.png', '2018-01-09 07:18:04'),
(333, 1000686575, '/home/mi_rafi/project_file/pmc/tmp/8954546680264.png', '2018-01-09 07:18:04'),
(334, 1000756052, '/home/mi_rafi/project_file/pmc/tmp/8954732431229.png', '2018-01-09 07:18:05'),
(335, 1000133025, '/home/mi_rafi/project_file/pmc/tmp/8954780232724.png', '2018-01-09 07:18:05'),
(336, 1000321776, '/home/mi_rafi/project_file/pmc/tmp/8959753886149.png', '2018-01-09 07:18:10'),
(337, 1000649282, '/home/mi_rafi/project_file/pmc/tmp/8964132556076.png', '2018-01-09 07:18:14'),
(338, 1000056927, '/home/mi_rafi/project_file/pmc/tmp/8964145797071.png', '2018-01-09 07:18:14'),
(339, 1000129085, '/home/mi_rafi/project_file/pmc/tmp/8964756598515.png', '2018-01-09 07:18:15'),
(340, 1000831909, '/home/mi_rafi/project_file/pmc/tmp/8970591280156.png', '2018-01-09 07:18:20'),
(341, 1000577455, '/home/mi_rafi/project_file/pmc/tmp/8970601052984.png', '2018-01-09 07:18:20'),
(342, 1000529300, '/home/mi_rafi/project_file/pmc/tmp/8975483632963.png', '2018-01-09 07:18:25'),
(343, 1000391158, '/home/mi_rafi/project_file/pmc/tmp/8975480969184.png', '2018-01-09 07:18:25'),
(344, 1000523094, '/home/mi_rafi/project_file/pmc/tmp/8981607548062.png', '2018-01-09 07:18:31'),
(345, 1000014908, '/home/mi_rafi/project_file/pmc/tmp/8993174135124.mp4', '2018-01-09 07:18:43'),
(346, 1000810429, '/home/mi_rafi/project_file/pmc/tmp/9000495315320.mp4', '2018-01-09 07:18:50'),
(347, 1000591279, '/home/mi_rafi/project_file/pmc/tmp/9003725081632.png', '2018-01-09 07:18:54'),
(348, 1000822428, '/home/mi_rafi/project_file/pmc/tmp/9007997865982.mp4', '2018-01-09 07:18:58'),
(349, 1000311730, '/home/mi_rafi/project_file/pmc/tmp/9011260753476.png', '2018-01-09 07:19:01'),
(350, 1000590482, '/home/mi_rafi/project_file/pmc/tmp/17936574891756.png', '2018-01-09 09:47:47'),
(351, 1000434999, '/home/mi_rafi/project_file/pmc/tmp/17989284465861.png', '2018-01-09 09:48:39'),
(352, 1000077112, '/home/mi_rafi/project_file/pmc/tmp/18288318199160.png', '2018-01-09 09:53:38'),
(354, 1000122512, '/home/mi_rafi/project_file/pmc/tmp/19133284155369.png', '2018-01-09 10:07:43'),
(355, 1000482659, '/home/mi_rafi/project_file/pmc/tmp/19136980333780.png', '2018-01-09 10:07:47'),
(356, 1000788206, '/home/mi_rafi/project_file/pmc/tmp/19154584076989.png', '2018-01-09 10:08:04'),
(357, 1000535164, '/home/mi_rafi/project_file/pmc/tmp/19282552118517.png', '2018-01-09 10:10:12'),
(358, 1000588551, '/home/mi_rafi/project_file/pmc/tmp/19290704187425.png', '2018-01-09 10:10:21'),
(359, 1000198842, '/home/mi_rafi/project_file/pmc/tmp/19290754487932.png', '2018-01-09 10:10:21'),
(360, 1000488478, '/home/mi_rafi/project_file/pmc/tmp/19291044709978.png', '2018-01-09 10:10:21'),
(362, 1000490674, '/home/mi_rafi/project_file/pmc/tmp/19447201865067.png', '2018-01-09 10:12:57'),
(363, 1000395114, '/home/mi_rafi/project_file/pmc/tmp/19447326312104.png', '2018-01-09 10:12:57'),
(364, 1000409446, '/home/mi_rafi/project_file/pmc/tmp/19448373514769.png', '2018-01-09 10:12:58'),
(365, 1000279909, '/home/mi_rafi/project_file/pmc/tmp/19453247125865.png', '2018-01-09 10:13:03'),
(366, 1000634589, '/home/mi_rafi/project_file/pmc/tmp/20550395407420.png', '2018-01-09 10:31:20'),
(367, 1000778419, '/home/mi_rafi/project_file/pmc/tmp/20553620236102.png', '2018-01-09 10:31:23'),
(368, 1000488583, '/home/mi_rafi/project_file/pmc/tmp/26126512604901.png', '2018-01-09 12:04:16'),
(369, 1000400848, '/home/mi_rafi/project_file/pmc/tmp/26568356524048.png', '2018-01-09 12:11:38'),
(370, 1000222620, '/home/mi_rafi/project_file/pmc/tmp/26568361567170.png', '2018-01-09 12:11:38'),
(371, 1000071081, '/home/mi_rafi/project_file/pmc/tmp/26568511558180.png', '2018-01-09 12:11:38'),
(372, 1000171962, '/home/mi_rafi/project_file/pmc/tmp/26568568661475.png', '2018-01-09 12:11:38'),
(373, 1000894353, '/home/mi_rafi/project_file/pmc/tmp/26581284410619.jpg', '2018-01-09 12:11:51'),
(374, 1000243074, '/home/mi_rafi/project_file/pmc/tmp/26583333565609.jpg', '2018-01-09 12:11:53'),
(375, 1000761313, '/home/mi_rafi/project_file/pmc/tmp/26585223722368.jpg', '2018-01-09 12:11:55'),
(376, 1000529529, '/home/mi_rafi/project_file/pmc/tmp/26587237787381.jpg', '2018-01-09 12:11:57'),
(377, 1000487450, '/home/mi_rafi/project_file/pmc/tmp/26589924229932.jpg', '2018-01-09 12:12:00'),
(378, 1000291521, '/home/mi_rafi/project_file/pmc/tmp/26595443207367.mp4', '2018-01-09 12:12:05'),
(379, 1000280605, '/home/mi_rafi/project_file/pmc/tmp/26605889825553.png', '2018-01-09 12:12:16'),
(380, 1000881788, '/home/mi_rafi/project_file/pmc/tmp/26615279579233.mp4', '2018-01-09 12:12:25'),
(381, 1000115962, '/home/mi_rafi/project_file/pmc/tmp/26622883633972.mp4', '2018-01-09 12:12:33'),
(382, 1000193965, '/home/mi_rafi/project_file/pmc/tmp/26741011190126.png', '2018-01-09 12:14:31'),
(383, 1000483506, '/home/mi_rafi/project_file/pmc/tmp/26757493714575.png', '2018-01-09 12:14:47'),
(384, 1000764352, '/home/mi_rafi/project_file/pmc/tmp/26759970293104.png', '2018-01-09 12:14:50'),
(385, 1000080213, '/home/mi_rafi/project_file/pmc/tmp/26763145270745.png', '2018-01-09 12:14:53'),
(386, 1000020835, '/home/mi_rafi/project_file/pmc/tmp/26765546346237.png', '2018-01-09 12:14:55'),
(387, 1000851172, '/home/mi_rafi/project_file/pmc/tmp/26770320152896.png', '2018-01-09 12:15:00'),
(388, 1000185587, '/home/mi_rafi/project_file/pmc/tmp/26814500491721.mp4', '2018-01-09 12:15:44'),
(389, 1000031539, '/home/mi_rafi/project_file/pmc/tmp/26821419872097.png', '2018-01-09 12:15:51'),
(390, 1000784096, '/home/mi_rafi/project_file/pmc/tmp/26825437715509.mp4', '2018-01-09 12:15:55'),
(391, 1000495518, '/home/mi_rafi/project_file/pmc/tmp/26829859864063.png', '2018-01-09 12:16:00'),
(392, 1000833331, '/home/mi_rafi/project_file/pmc/tmp/26833155138828.mp4', '2018-01-09 12:16:03'),
(393, 1000469505, '/home/mi_rafi/project_file/pmc/tmp/28712497703892.png', '2018-01-09 12:47:22'),
(394, 1000525289, '/home/mi_rafi/project_file/pmc/tmp/31725242911301.png', '2018-01-09 13:37:35'),
(395, 1000378026, '/home/mi_rafi/project_file/pmc/tmp/32022075837509.png', '2018-01-09 13:42:32'),
(397, 1000608985, '/home/mi_rafi/project_file/pmc/tmp/92296838259199.jpg', '2018-01-10 06:27:07'),
(399, 1000292091, '/home/mi_rafi/project_file/pmc/tmp/101989477327860.jpg', '2018-01-10 09:08:39'),
(400, 1000812160, '/home/mi_rafi/project_file/pmc/tmp/117625261251617.png', '2018-01-10 13:29:15'),
(401, 1000063296, '/home/mi_rafi/project_file/pmc/tmp/117628340806023.png', '2018-01-10 13:29:18'),
(402, 1000234972, '/home/mi_rafi/project_file/pmc/tmp/117630938051947.png', '2018-01-10 13:29:21'),
(403, 1000824876, '/home/mi_rafi/project_file/pmc/tmp/117633970327401.png', '2018-01-10 13:29:24'),
(404, 1000823936, '/home/mi_rafi/project_file/pmc/tmp/117639906014763.png', '2018-01-10 13:29:30'),
(405, 1000248496, '/home/mi_rafi/project_file/pmc/tmp/117644471360895.mp4', '2018-01-10 13:29:34'),
(406, 1000633263, '/home/mi_rafi/project_file/pmc/tmp/117651607245185.png', '2018-01-10 13:29:41'),
(407, 1000261532, '/home/mi_rafi/project_file/pmc/tmp/117656397146499.mp4', '2018-01-10 13:29:46'),
(408, 1000651012, '/home/mi_rafi/project_file/pmc/tmp/117660615043297.png', '2018-01-10 13:29:50'),
(409, 1000580033, '/home/mi_rafi/project_file/pmc/tmp/117664856385003.mp4', '2018-01-10 13:29:55'),
(410, 1000015085, '/home/mi_rafi/project_file/pmc/tmp/122599493938656.png', '2018-01-10 14:52:09'),
(412, 1000642890, '/home/mi_rafi/project_file/pmc/tmp/175223001944998.png', '2018-01-11 05:29:13'),
(413, 1000187253, '/home/mi_rafi/project_file/pmc/tmp/175223005950810.png', '2018-01-11 05:29:13'),
(414, 1000342335, '/home/mi_rafi/project_file/pmc/tmp/175343500033865.jpg', '2018-01-11 05:31:13'),
(415, 1000144823, '/home/mi_rafi/project_file/pmc/tmp/175346556578373.jpg', '2018-01-11 05:31:16'),
(416, 1000530388, '/home/mi_rafi/project_file/pmc/tmp/175349026666998.jpg', '2018-01-11 05:31:19'),
(417, 1000545369, '/home/mi_rafi/project_file/pmc/tmp/175351177528395.jpg', '2018-01-11 05:31:21'),
(418, 1000157290, '/home/mi_rafi/project_file/pmc/tmp/175353395202332.jpg', '2018-01-11 05:31:23'),
(419, 1000237690, '/home/mi_rafi/project_file/pmc/tmp/175355269970785.jpg', '2018-01-11 05:31:25'),
(420, 1000330833, '/home/mi_rafi/project_file/pmc/tmp/175359978513628.jpg', '2018-01-11 05:31:30'),
(421, 1000328898, '/home/mi_rafi/project_file/pmc/tmp/175562723557531.png', '2018-01-11 05:34:53'),
(422, 1000811846, '/home/mi_rafi/project_file/pmc/tmp/175562720814603.png', '2018-01-11 05:34:53'),
(423, 1000308001, '/home/mi_rafi/project_file/pmc/tmp/175562912216788.png', '2018-01-11 05:34:53'),
(424, 1000094870, '/home/mi_rafi/project_file/pmc/tmp/175579688060009.jpg', '2018-01-11 05:35:09'),
(425, 1000679542, '/home/mi_rafi/project_file/pmc/tmp/175581761412603.jpg', '2018-01-11 05:35:12'),
(426, 1000781608, '/home/mi_rafi/project_file/pmc/tmp/175583580053305.jpg', '2018-01-11 05:35:13'),
(427, 1000821524, '/home/mi_rafi/project_file/pmc/tmp/175585265897305.jpg', '2018-01-11 05:35:15'),
(428, 1000674638, '/home/mi_rafi/project_file/pmc/tmp/175588643806401.jpg', '2018-01-11 05:35:18'),
(429, 1000729381, '/home/mi_rafi/project_file/pmc/tmp/175593261198575.jpg', '2018-01-11 05:35:23'),
(431, 1000341316, '/home/mi_rafi/project_file/pmc/tmp/175609097685438.png', '2018-01-11 05:35:39'),
(432, 1000005665, '/home/mi_rafi/project_file/pmc/tmp/175612719275089.mp4', '2018-01-11 05:35:43'),
(433, 1000868833, '/home/mi_rafi/project_file/pmc/tmp/175622299817304.png', '2018-01-11 05:35:52'),
(434, 1000023561, '/home/mi_rafi/project_file/pmc/tmp/175625627828074.mp4', '2018-01-11 05:35:55'),
(436, 1000089215, '/home/mi_rafi/project_file/pmc/tmp/175656252479022.flv', '2018-01-11 05:36:26'),
(437, 1000608900, '/home/mi_rafi/project_file/pmc/tmp/175666133795002.3gp', '2018-01-11 05:36:36'),
(438, 1000759989, '/home/mi_rafi/project_file/pmc/tmp/180237192640792.png', '2018-01-11 06:52:47'),
(439, 1000072341, '/home/mi_rafi/project_file/pmc/tmp/180237192640787.png', '2018-01-11 06:52:47'),
(440, 1000071395, '/home/mi_rafi/project_file/pmc/tmp/180237810619809.png', '2018-01-11 06:52:48'),
(441, 1000794553, '/home/mi_rafi/project_file/pmc/tmp/180252078469730.png', '2018-01-11 06:53:02'),
(442, 1000304476, '/home/mi_rafi/project_file/pmc/tmp/180254125943095.png', '2018-01-11 06:53:04'),
(443, 1000543284, '/home/mi_rafi/project_file/pmc/tmp/180257972963690.png', '2018-01-11 06:53:08'),
(444, 1000283910, '/home/mi_rafi/project_file/pmc/tmp/180257984927102.png', '2018-01-11 06:53:08'),
(445, 1000358132, '/home/mi_rafi/project_file/pmc/tmp/180264550556111.png', '2018-01-11 06:53:14'),
(446, 1000301222, '/home/mi_rafi/project_file/pmc/tmp/180264557003798.png', '2018-01-11 06:53:14'),
(447, 1000382243, '/home/mi_rafi/project_file/pmc/tmp/180264782871109.png', '2018-01-11 06:53:15'),
(448, 1000565341, '/home/mi_rafi/project_file/pmc/tmp/180271700570832.png', '2018-01-11 06:53:22'),
(449, 1000255082, '/home/mi_rafi/project_file/pmc/tmp/180276497385959.webm', '2018-01-11 06:53:26'),
(451, 1000679113, '/home/mi_rafi/project_file/pmc/tmp/180308539567883.png', '2018-01-11 06:53:58'),
(452, 1000470258, '/home/mi_rafi/project_file/pmc/tmp/180314112592599.ogv', '2018-01-11 06:54:04'),
(453, 1000227883, '/home/mi_rafi/project_file/pmc/tmp/180317937261213.mp4', '2018-01-11 06:54:08'),
(454, 1000606499, '/home/mi_rafi/project_file/pmc/tmp/180322070135158.png', '2018-01-11 06:54:12'),
(455, 1000464119, '/home/mi_rafi/project_file/pmc/tmp/186976490126627.webm', '2018-01-11 08:45:06'),
(456, 1000828406, '/home/mi_rafi/project_file/pmc/tmp/187142651214657.mp4', '2018-01-11 08:47:53'),
(457, 1000723192, '/home/mi_rafi/project_file/pmc/tmp/187217612575935.webm', '2018-01-11 08:49:07'),
(458, 1000556528, '/home/mi_rafi/project_file/pmc/tmp/188083496756476.png', '2018-01-11 09:03:33'),
(459, 1000068985, '/home/mi_rafi/project_file/pmc/tmp/188083496765312.png', '2018-01-11 09:03:33'),
(460, 1000087785, '/home/mi_rafi/project_file/pmc/tmp/188083968901120.png', '2018-01-11 09:03:34'),
(461, 1000655547, '/home/mi_rafi/project_file/pmc/tmp/188104344233116.png', '2018-01-11 09:03:54'),
(462, 1000045115, '/home/mi_rafi/project_file/pmc/tmp/188107659060021.png', '2018-01-11 09:03:57'),
(463, 1000783790, '/home/mi_rafi/project_file/pmc/tmp/188112221236657.png', '2018-01-11 09:04:02'),
(464, 1000499306, '/home/mi_rafi/project_file/pmc/tmp/188112212987733.png', '2018-01-11 09:04:02'),
(465, 1000335672, '/home/mi_rafi/project_file/pmc/tmp/188112391267153.png', '2018-01-11 09:04:02'),
(466, 1000760621, '/home/mi_rafi/project_file/pmc/tmp/188117362953558.png', '2018-01-11 09:04:07'),
(467, 1000794281, '/home/mi_rafi/project_file/pmc/tmp/188117370512175.png', '2018-01-11 09:04:07'),
(468, 1000183782, '/home/mi_rafi/project_file/pmc/tmp/188117541441223.png', '2018-01-11 09:04:07'),
(469, 1000385046, '/home/mi_rafi/project_file/pmc/tmp/188121676190927.png', '2018-01-11 09:04:11'),
(470, 1000491425, '/home/mi_rafi/project_file/pmc/tmp/188127023311529.webm', '2018-01-11 09:04:17'),
(471, 1000222270, '/home/mi_rafi/project_file/pmc/tmp/188137296302440.ogv', '2018-01-11 09:04:27'),
(472, 1000883568, '/home/mi_rafi/project_file/pmc/tmp/188142022991700.png', '2018-01-11 09:04:32'),
(473, 1000787170, '/home/mi_rafi/project_file/pmc/tmp/188151176825991.mp4', '2018-01-11 09:04:41'),
(474, 1000305856, '/home/mi_rafi/project_file/pmc/tmp/188156200146741.png', '2018-01-11 09:04:46'),
(475, 1000134222, '/home/mi_rafi/project_file/pmc/tmp/188888053545192.png', '2018-01-11 09:16:58'),
(476, 1000632612, '/home/mi_rafi/project_file/pmc/tmp/188891945358247.png', '2018-01-11 09:17:02'),
(477, 1000410482, '/home/mi_rafi/project_file/pmc/tmp/188891949616600.png', '2018-01-11 09:17:02'),
(478, 1000154951, '/home/mi_rafi/project_file/pmc/tmp/188892108373112.png', '2018-01-11 09:17:02'),
(479, 1000072049, '/home/mi_rafi/project_file/pmc/tmp/188894286014157.png', '2018-01-11 09:17:04'),
(480, 1000795175, '/home/mi_rafi/project_file/pmc/tmp/188896285477950.png', '2018-01-11 09:17:06'),
(481, 1000557543, '/home/mi_rafi/project_file/pmc/tmp/188899071388818.png', '2018-01-11 09:17:09'),
(482, 1000700190, '/home/mi_rafi/project_file/pmc/tmp/188904371037767.webm', '2018-01-11 09:17:14'),
(483, 1000759730, '/home/mi_rafi/project_file/pmc/tmp/188909940584144.ogv', '2018-01-11 09:17:20'),
(484, 1000640589, '/home/mi_rafi/project_file/pmc/tmp/188912897739819.mp4', '2018-01-11 09:17:23'),
(485, 1000780461, '/home/mi_rafi/project_file/pmc/tmp/188918509774437.png', '2018-01-11 09:17:28'),
(486, 1000376681, '/home/mi_rafi/project_file/pmc/tmp/188920986725591.png', '2018-01-11 09:17:31'),
(487, 1000631409, '/home/mi_rafi/project_file/pmc/tmp/189059236158922.png', '2018-01-11 09:19:49'),
(488, 1000578125, '/home/mi_rafi/project_file/pmc/tmp/6116282660464.png', '2018-01-11 12:17:24'),
(489, 1000197190, '/home/mi_rafi/project_file/pmc/tmp/6118133901170.png', '2018-01-11 12:17:25'),
(490, 1000871646, '/home/mi_rafi/project_file/pmc/tmp/89067432448380.png', '2018-01-12 11:19:55'),
(491, 1000587593, '/home/mi_rafi/project_file/pmc/tmp/89071749785760.png', '2018-01-12 11:19:59'),
(492, 1000681971, '/home/mi_rafi/project_file/pmc/tmp/89075475817275.png', '2018-01-12 11:20:03'),
(493, 1000216298, '/home/mi_rafi/project_file/pmc/tmp/99180266512725.png', '2018-01-12 14:08:28'),
(494, 1000497450, '/home/mi_rafi/project_file/pmc/tmp/99399805075468.jpg', '2018-01-12 14:12:08'),
(495, 1000530511, '/home/mi_rafi/project_file/pmc/tmp/4101017069972.jpg', '2018-01-15 06:27:46'),
(496, 1000457442, '/home/mi_rafi/project_file/pmc/tmp/4133700040871.jpg', '2018-01-15 06:28:19'),
(497, 1000666479, '/home/mi_rafi/project_file/pmc/tmp/4613666577974.jpeg', '2018-01-15 06:36:19'),
(498, 1000721913, '/home/mi_rafi/project_file/pmc/tmp/4762591896555.jpeg', '2018-01-15 06:38:48'),
(499, 1000895027, '/home/mi_rafi/project_file/pmc/tmp/5044984895095.jpeg', '2018-01-15 06:43:30'),
(500, 1000162004, '/home/mi_rafi/project_file/pmc/tmp/5185232393155.png', '2018-01-15 06:45:51'),
(502, 1000389357, '/home/mi_rafi/project_file/pmc/tmp/6406954794992.jpeg', '2018-01-15 07:06:12'),
(503, 1000448302, '/home/mi_rafi/project_file/pmc/tmp/6409830754522.jpg', '2018-01-15 07:06:15'),
(504, 1000277346, '/home/mi_rafi/project_file/pmc/tmp/6414818483204.jpg', '2018-01-15 07:06:20'),
(505, 1000705318, '/home/mi_rafi/project_file/pmc/tmp/6481755777189.jpeg', '2018-01-15 07:07:27'),
(506, 1000567693, '/home/mi_rafi/project_file/pmc/tmp/6486244303931.jpg', '2018-01-15 07:07:32'),
(508, 1000583401, '/home/mi_rafi/project_file/pmc/tmp/6539006446523.png', '2018-01-15 07:08:24'),
(509, 1000460411, '/home/mi_rafi/project_file/pmc/tmp/6545946453065.jpg', '2018-01-15 07:08:31'),
(510, 1000361816, '/home/mi_rafi/project_file/pmc/tmp/6549268310774.jpg', '2018-01-15 07:08:35'),
(511, 1000546996, '/home/mi_rafi/project_file/pmc/tmp/6557744392709.jpg', '2018-01-15 07:08:43'),
(512, 1000122341, '/home/mi_rafi/project_file/pmc/tmp/6560604138188.jpg', '2018-01-15 07:08:46'),
(513, 1000497658, '/home/mi_rafi/project_file/pmc/tmp/6565462410093.png', '2018-01-15 07:08:51'),
(514, 1000804636, '/home/mi_rafi/project_file/pmc/tmp/6568639968780.jpg', '2018-01-15 07:08:54'),
(515, 1000231846, '/home/mi_rafi/project_file/pmc/tmp/6627406338298.png', '2018-01-15 07:09:53'),
(516, 1000012515, '/home/mi_rafi/project_file/pmc/tmp/6632211693877.jpg', '2018-01-15 07:09:58'),
(517, 1000709697, '/home/mi_rafi/project_file/pmc/tmp/6774618593983.png', '2018-01-15 07:12:20'),
(518, 1000642398, '/home/mi_rafi/project_file/pmc/tmp/6778185146236.png', '2018-01-15 07:12:24'),
(519, 1000054008, '/home/mi_rafi/project_file/pmc/tmp/6785018740306.png', '2018-01-15 07:12:30'),
(520, 1000238885, '/home/mi_rafi/project_file/pmc/tmp/7510792762375.jpg', '2018-01-15 07:24:36'),
(521, 1000327949, '/home/mi_rafi/project_file/pmc/tmp/7513241748580.jpg', '2018-01-15 07:24:39'),
(522, 1000281083, '/home/mi_rafi/project_file/pmc/tmp/7517247198750.jpeg', '2018-01-15 07:24:43'),
(523, 1000496852, '/home/mi_rafi/project_file/pmc/tmp/7519660960494.png', '2018-01-15 07:24:45'),
(524, 1000671426, '/home/mi_rafi/project_file/pmc/tmp/7761019467078.jpg', '2018-01-15 07:28:46'),
(525, 1000655572, '/home/mi_rafi/project_file/pmc/tmp/7943988272497.mp4', '2018-01-15 07:31:49'),
(526, 1000638296, '/home/mi_rafi/project_file/pmc/tmp/7966203502035.mp4', '2018-01-15 07:32:12'),
(527, 1000613356, '/home/mi_rafi/project_file/pmc/tmp/7978797269163.png', '2018-01-15 07:32:24'),
(528, 1000505773, '/home/mi_rafi/project_file/pmc/tmp/7978918759982.jpg', '2018-01-15 07:32:24'),
(529, 1000747062, '/home/mi_rafi/project_file/pmc/tmp/7988205210105.png', '2018-01-15 07:32:34'),
(530, 1000064098, '/home/mi_rafi/project_file/pmc/tmp/7990624133944.png', '2018-01-15 07:32:36'),
(531, 1000261383, '/home/mi_rafi/project_file/pmc/tmp/7999784081649.png', '2018-01-15 07:32:45'),
(532, 1000889714, '/home/mi_rafi/project_file/pmc/tmp/8005583815727.png', '2018-01-15 07:32:51'),
(533, 1000201334, '/home/mi_rafi/project_file/pmc/tmp/8032916798792.jpeg', '2018-01-15 07:33:18'),
(534, 1000272973, '/home/mi_rafi/project_file/pmc/tmp/8035610380762.jpg', '2018-01-15 07:33:21'),
(535, 1000789662, '/home/mi_rafi/project_file/pmc/tmp/8039835199913.png', '2018-01-15 07:33:25'),
(536, 1000100074, '/home/mi_rafi/project_file/pmc/tmp/8043323510338.jpg', '2018-01-15 07:33:29'),
(537, 1000355098, '/home/mi_rafi/project_file/pmc/tmp/8046447983246.png', '2018-01-15 07:33:32'),
(538, 1000102623, '/home/mi_rafi/project_file/pmc/tmp/8050464747774.png', '2018-01-15 07:33:36'),
(539, 1000074084, '/home/mi_rafi/project_file/pmc/tmp/8054115552054.jpg', '2018-01-15 07:33:39'),
(540, 1000318864, '/home/mi_rafi/project_file/pmc/tmp/8082638247902.png', '2018-01-15 07:34:08'),
(541, 1000208007, '/home/mi_rafi/project_file/pmc/tmp/11123719658885.jpg', '2018-01-15 08:24:49'),
(542, 1000547035, '/home/mi_rafi/project_file/pmc/tmp/11586600947071.jpg', '2018-01-15 08:32:32'),
(543, 1000481786, '/home/mi_rafi/project_file/pmc/tmp/11589449279152.jpg', '2018-01-15 08:32:35'),
(544, 1000217877, '/home/mi_rafi/project_file/pmc/tmp/11592802304896.jpg', '2018-01-15 08:32:38'),
(545, 1000350365, '/home/mi_rafi/project_file/pmc/tmp/11882980666602.jpg', '2018-01-15 08:37:28'),
(546, 1000420188, '/home/mi_rafi/project_file/pmc/tmp/11902215220127.jpg', '2018-01-15 08:37:48'),
(547, 1000897451, '/home/mi_rafi/project_file/pmc/tmp/11904229861648.jpg', '2018-01-15 08:37:50'),
(548, 1000494566, '/home/mi_rafi/project_file/pmc/tmp/12146138008302.jpg', '2018-01-15 08:41:52'),
(549, 1000760453, '/home/mi_rafi/project_file/pmc/tmp/12147848592779.jpg', '2018-01-15 08:41:53'),
(550, 1000117892, '/home/mi_rafi/project_file/pmc/tmp/12256353649087.jpg', '2018-01-15 08:43:42'),
(551, 1000572925, '/home/mi_rafi/project_file/pmc/tmp/12270097168072.mp4', '2018-01-15 08:43:55'),
(552, 1000016067, '/home/mi_rafi/project_file/pmc/tmp/12290608112207.jpg', '2018-01-15 08:44:16'),
(553, 1000386802, '/home/mi_rafi/project_file/pmc/tmp/12493192482093.jpg', '2018-01-15 08:47:39'),
(554, 1000409078, '/home/mi_rafi/project_file/pmc/tmp/12935259995491.jpg', '2018-01-15 08:55:01'),
(555, 1000329227, '/home/mi_rafi/project_file/pmc/tmp/12989008916779.jpg', '2018-01-15 08:55:54'),
(556, 1000575395, '/home/mi_rafi/project_file/pmc/tmp/13040503855418.jpg', '2018-01-15 08:56:46'),
(557, 1000745793, '/home/mi_rafi/project_file/pmc/tmp/13086231538204.jpg', '2018-01-15 08:57:32'),
(558, 1000170447, '/home/mi_rafi/project_file/pmc/tmp/13133613647953.jpg', '2018-01-15 08:58:19'),
(559, 1000757181, '/home/mi_rafi/project_file/pmc/tmp/13341083906113.jpg', '2018-01-15 09:01:46'),
(561, 1000553467, '/home/mi_rafi/project_file/pmc/tmp/13530421711359.jpg', '2018-01-15 09:04:56'),
(562, 1000245398, '/home/mi_rafi/project_file/pmc/tmp/13535566911563.mp4', '2018-01-15 09:05:01'),
(563, 1000072606, '/home/mi_rafi/project_file/pmc/tmp/14068937142491.png', '2018-01-15 09:13:54'),
(564, 1000120660, '/home/mi_rafi/project_file/pmc/tmp/14087818879552.mp4', '2018-01-15 09:14:13'),
(565, 1000364623, '/home/mi_rafi/project_file/pmc/tmp/14120112083923.ogv', '2018-01-15 09:14:45'),
(566, 1000698875, '/home/mi_rafi/project_file/pmc/tmp/14249907052450.png', '2018-01-15 09:16:55'),
(567, 1000473683, '/home/mi_rafi/project_file/pmc/tmp/14254543269731.png', '2018-01-15 09:17:00'),
(568, 1000698589, '/home/mi_rafi/project_file/pmc/tmp/14261105271426.ogv', '2018-01-15 09:17:06'),
(569, 1000597195, '/home/mi_rafi/project_file/pmc/tmp/14264103978791.ogv', '2018-01-15 09:17:09');

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
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `location_id` (`location_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`id`, `name`, `location_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Venue 1', 1, NULL, NULL, NULL),
(2, 'Venue 2', 1, NULL, NULL, NULL),
(3, 'ddf1', 2, '2018-01-10 13:51:52', '2018-01-10 13:52:27', 132),
(4, 'kkk', 4, '2018-01-10 13:52:39', '2018-01-10 13:52:39', 132),
(5, 'test', 3, '2018-01-10 14:01:42', '2018-01-10 14:01:42', 132),
(6, 'New Venu1', 5, '2018-01-15 12:33:20', '2018-01-15 12:33:35', 132),
(7, '123', 2, '2018-01-15 12:34:18', '2018-01-15 12:34:18', 132);

-- --------------------------------------------------------

--
-- Table structure for table `watermarks`
--

CREATE TABLE IF NOT EXISTS `watermarks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('image','text') COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `watermarks`
--

INSERT INTO `watermarks` (`id`, `type`, `logo_name`, `logo_image`, `size`, `fade`, `watermark_text`, `font_id`, `placement`, `color`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'image', 'dfg', '26126512604901.png', 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 18:05:08', '2018-01-09 18:05:08', NULL),
(2, 'image', 'Roy', '92296838259199.jpg', 'small', 6, '', NULL, 'br', '', '2018-01-10 12:27:10', '2018-01-10 12:27:10', NULL),
(3, 'text', 'Roy', '', NULL, NULL, 'Roy', NULL, NULL, 'B3FF57', '2018-01-10 12:28:11', '2018-01-10 12:28:11', NULL),
(4, 'image', 'Rainbow', '5044984895095.jpeg', 'medium', 29, '', NULL, 'bl', '', '2018-01-15 12:36:39', '2018-01-15 12:43:34', NULL),
(5, 'text', 'New 1', '', NULL, NULL, 'New 1', NULL, NULL, 'FFFFFF', '2018-01-15 12:45:14', '2018-01-15 12:46:05', NULL);

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
  ADD CONSTRAINT `event_images_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `event_images_ibfk_2` FOREIGN KEY (`watermark_id`) REFERENCES `watermarks` (`id`);

--
-- Constraints for table `event_photographers`
--
ALTER TABLE `event_photographers`
  ADD CONSTRAINT `event_photographers_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `event_photographers_ibfk_2` FOREIGN KEY (`photographer_id`) REFERENCES `event_photographers` (`id`);

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
