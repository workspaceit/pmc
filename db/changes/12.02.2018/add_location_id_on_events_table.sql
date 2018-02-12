ALTER TABLE  `events` ADD  `location_id` INT NOT NULL AFTER  `is_private` ,
ADD INDEX (  `location_id` ) ;