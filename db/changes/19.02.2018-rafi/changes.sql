ALTER TABLE  `temp_file` ADD  `file_name` VARCHAR( 500 ) NOT NULL AFTER  `path` ;
ALTER TABLE  `watermarks` ADD  `sample_image_name` VARCHAR( 500 ) NOT NULL AFTER  `logo_name` ;