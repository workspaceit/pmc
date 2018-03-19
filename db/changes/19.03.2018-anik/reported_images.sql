

CREATE TABLE IF NOT EXISTS `reported_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) NOT NULL,
  `reported_at` datetime NOT NULL,
  `action_taken` tinyint(1) NOT NULL DEFAULT '0',
  `action_taken_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `image_id` (`image_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;



ALTER TABLE `reported_images`
  ADD CONSTRAINT `reported_images_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `event_images` (`id`);

