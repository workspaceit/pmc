ALTER TABLE  `photographers` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;
ALTER TABLE  `locations` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;
ALTER TABLE  `watermarks` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;
ALTER TABLE  `venues` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;
ALTER TABLE  `events` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;
ALTER TABLE  `section` ADD  `duration` INT( 5 ) NULL AFTER  `quantity` ;
ALTER TABLE  `admins` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;