-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 09, 2018 at 05:24 PM
-- Server version: 5.7.20-0ubuntu0.16.04.1
-- PHP Version: 7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pmc`
--

-- --------------------------------------------------------

--
-- Table structure for table `watermarks`
--

CREATE TABLE `watermarks` (
  `id` int(11) NOT NULL,
  `type` enum('image','text') COLLATE utf8_unicode_ci NOT NULL,
  `logo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `logo_image` text COLLATE utf8_unicode_ci,
  `size` enum('thumb','small','medium','large','x_large') COLLATE utf8_unicode_ci DEFAULT NULL,
  `fade` double DEFAULT NULL,
  `watermark_text` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `font_id` int(11) DEFAULT NULL,
  `placement` enum('tl','tc','tr','cl','cc','cr','bl','bc','br') COLLATE utf8_unicode_ci DEFAULT NULL,
  `color` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `watermarks`
--

INSERT INTO `watermarks` (`id`, `type`, `logo_name`, `logo_image`, `size`, `fade`, `watermark_text`, `font_id`, `placement`, `color`, `created_at`, `updated_at`, `created_by`) VALUES
(1, 'image', 'test', NULL, NULL, 37, NULL, NULL, NULL, NULL, '2018-01-08 21:57:08', '2018-01-08 21:57:08', NULL),
(2, 'image', 'test2', NULL, NULL, 22, NULL, NULL, NULL, NULL, '2018-01-08 21:57:33', '2018-01-08 21:57:33', NULL),
(3, 'text', 'test3', NULL, NULL, NULL, 'text3', NULL, NULL, '9787FF', '2018-01-08 21:57:56', '2018-01-08 21:57:56', NULL),
(4, 'image', 'asdsadasdsad', NULL, 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 13:41:53', '2018-01-09 13:41:53', NULL),
(5, 'text', 'text', NULL, NULL, NULL, 'asd', NULL, NULL, 'BDC6FF', '2018-01-09 13:44:32', '2018-01-09 13:44:32', NULL),
(6, 'image', 'image', NULL, 'medium', 30, '', NULL, 'tr', '', '2018-01-09 13:48:22', '2018-01-09 13:48:22', NULL),
(7, 'image', 'asdasdas acascas', NULL, 'medium', 25, '', NULL, 'br', '', '2018-01-09 13:59:14', '2018-01-09 13:59:14', NULL),
(8, 'image', 'asd', NULL, 'x_large', 42, '', NULL, 'bl', '', '2018-01-09 13:59:59', '2018-01-09 13:59:59', NULL),
(9, 'image', 'asdsadasdadsadsad', NULL, 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 14:06:41', '2018-01-09 14:06:41', NULL),
(10, 'image', 'asdasdasddadavsavdav', NULL, 'large', 32, '', NULL, 'tr', '', '2018-01-09 14:56:23', '2018-01-09 14:56:23', NULL),
(11, 'image', 'werwer', '', 'medium', 25, '', NULL, 'tc', '', '2018-01-09 15:32:42', '2018-01-09 15:32:42', NULL),
(12, 'image', 'fsdffs', '9459954922289.jpg', 'small', 25, '', NULL, 'tr', '', '2018-01-09 15:44:15', '2018-01-09 15:44:15', NULL),
(13, 'image', 'aasdads', '11691104925394.jpg', 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 16:21:22', '2018-01-09 16:21:22', NULL),
(14, 'text', 'asdad', '', NULL, NULL, 'asdasd', NULL, NULL, 'FFFFFF', '2018-01-09 16:21:39', '2018-01-09 16:21:39', NULL),
(15, 'image', 'dadsa', '', 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 16:33:32', '2018-01-09 16:33:32', NULL),
(16, 'image', 'asdad', '12882587456606.jpg', 'thumb', 25, '', NULL, 'tl', '', '2018-01-09 16:41:15', '2018-01-09 16:41:15', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `watermarks`
--
ALTER TABLE `watermarks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `created_by` (`created_by`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `watermarks`
--
ALTER TABLE `watermarks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
