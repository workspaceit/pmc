ALTER TABLE  `events` ADD  `location_id` INT NOT NULL AFTER  `is_private` ,
ADD INDEX (  `location_id` ) ;
ALTER TABLE  `events` CHANGE  `venue_id`  `venue_id` INT( 11 ) NULL ;