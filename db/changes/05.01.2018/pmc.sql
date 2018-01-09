-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 05, 2018 at 08:37 PM
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
  `password` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=133 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `email`, `password`, `created_at`, `created_by`) VALUES
(132, 'Anik S.', 'aniksarker17@gmail.com', '$2a$10$6C3HCNuSOm52lli4CK43wuGMoi4MvPXzxPpsSEbe/P5DHA4YlhSgC', '2017-12-19 18:08:02', NULL);

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
-- Table structure for table `advertisement_settings`
--

CREATE TABLE IF NOT EXISTS `advertisement_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `advertiser_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `created_by` int(11) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `other_image` text COLLATE utf8_unicode_ci,
  `runtime_starts` date NOT NULL,
  `runtime_ends` date NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `state_id` (`state_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `advertisers`
--

INSERT INTO `advertisers` (`id`, `name`, `address`, `city_id`, `state_id`, `zip`, `phone`, `website`, `other_image`, `runtime_starts`, `runtime_ends`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'sdf', 'sdf', 1, 1, 'sdf', 'sdf', 'sdf', NULL, '2018-01-05', '2018-01-05', '2018-01-05 18:56:14', '2018-01-05 18:56:14', 132);

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
-- Table structure for table `bottom_banner_images`
--

CREATE TABLE IF NOT EXISTS `bottom_banner_images` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  `top_banner_expiry_date` date NOT NULL,
  `bottom_banner_expiry_date` date NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `gallery_ads`
--

INSERT INTO `gallery_ads` (`id`, `advertiser_id`, `top_banner_expiry_date`, `bottom_banner_expiry_date`, `created_at`, `updated_at`, `created_by`) VALUES
(1, NULL, '2018-01-05', '2018-01-05', '2018-01-05 20:36:17', '2018-01-05 20:36:17', 132);

-- --------------------------------------------------------

--
-- Table structure for table `gallery_banner_images`
--

CREATE TABLE IF NOT EXISTS `gallery_banner_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_type` enum('LOGO','BACKGRAOUND','TOP_BANNER','BOTTOM_BANNER') COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `gallery_ad_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `slideshow_id` (`gallery_ad_id`,`created_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `gallery_banner_images`
--

INSERT INTO `gallery_banner_images` (`id`, `image_type`, `image`, `gallery_ad_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'LOGO', '25797591509870.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL),
(2, 'BACKGRAOUND', '25823987910644.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL),
(3, 'TOP_BANNER', '25804678808643.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL),
(4, 'TOP_BANNER', '25807616472171.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL),
(5, 'BOTTOM_BANNER', '25810968364153.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL),
(6, 'BOTTOM_BANNER', '25813732537533.png', 1, '2018-01-05 20:36:17', '2018-01-05 20:36:17', NULL);

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
(3, 'Location 3', '15 Ghost Town, NY  ', 1, '1245', '12345678', '29509776336830.jpg', 1, 3, 13, 3, 4, '2018-01-02 20:40:58', '2018-01-03 19:44:18', NULL),
(4, 'Location 2', 'asd', 1, 'asd', 'asdasd', '99587924720066.png', 1, 5, 2, 2, 3, '2018-01-02 22:00:27', '2018-01-03 19:44:01', NULL),
(5, 'Location 1', 'Ny', 3, '123', '12123', '109596060107824.jpg', 1, 1, 2, 1, 3, '2018-01-03 17:55:56', '2018-01-03 19:43:44', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=26 ;

--
-- Dumping data for table `location_bg_images`
--

INSERT INTO `location_bg_images` (`id`, `image`, `location_id`, `created_at`, `created_by`) VALUES
(9, '29523316176027.png', 3, '2018-01-02 20:40:58', NULL),
(10, '29523349415554.png', 3, '2018-01-02 20:40:58', NULL),
(11, '29523467252186.png', 3, '2018-01-02 20:40:58', NULL),
(14, '34299032741232.png', 4, '2018-01-02 22:00:27', NULL),
(17, '98974713056925.png', 4, '2018-01-03 15:58:27', NULL),
(22, '105986355945201.png', 5, '2018-01-03 17:55:56', NULL),
(24, '107127822805245.png', 5, '2018-01-03 18:14:18', NULL),
(25, '11269368608786.png', 5, '2018-01-04 15:24:39', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

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
(10, 'asdfg345345', '3423', 'sdf012', 'asd@asdf.comqwe', '$2a$10$zzwAVYjQfa9fW3OMuk7WCOgmZHjP3dCKQ62O2TQ/LEdVIeLb.zVOG', '35697004643068.png', '2017-12-29 12:44:35', '2018-01-02 17:10:46', 132);

-- --------------------------------------------------------

--
-- Table structure for table `popup_ads`
--

CREATE TABLE IF NOT EXISTS `popup_ads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('sms','email','','') COLLATE utf8_unicode_ci NOT NULL,
  `ad_type` enum('banner','video','','') COLLATE utf8_unicode_ci NOT NULL,
  `duration` int(11) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `video` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  `video_duration` int(11) NOT NULL,
  `banner_duration` int(11) NOT NULL,
  `video_expiry_date` datetime NOT NULL,
  `banner_expiry_date` datetime NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  KEY `advertiser_id` (`advertiser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=215 ;

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
(214, 1000250955, '/home/mi_rafi/project_file/pmc/tmp/25823987910644.png', '2018-01-05 13:51:20');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`id`, `name`, `location_id`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'Venue 1', 3, NULL, NULL, NULL),
(2, 'Venue 2', 3, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `watermarks`
--

CREATE TABLE IF NOT EXISTS `watermarks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('image','text','','') COLLATE utf8_unicode_ci NOT NULL,
  `logo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `logo_image` text COLLATE utf8_unicode_ci NOT NULL,
  `size` enum('thumb','small','medium','large','x-large') COLLATE utf8_unicode_ci NOT NULL,
  `fade` double NOT NULL,
  `watermark_text` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `font_id` int(11) NOT NULL,
  `placement` enum('tl','tc','tr','cl','cc','cr','bl','bc','br') COLLATE utf8_unicode_ci NOT NULL,
  `color` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
-- Constraints for table `locations`
--
ALTER TABLE `locations`
  ADD CONSTRAINT `locations_ibfk_3` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`);

--
-- Constraints for table `location_bg_images`
--
ALTER TABLE `location_bg_images`
  ADD CONSTRAINT `location_bg_images_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`);

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

--
-- Constraints for table `venues`
--
ALTER TABLE `venues`
  ADD CONSTRAINT `venues_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
