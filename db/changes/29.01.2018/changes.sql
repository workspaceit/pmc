ALTER TABLE  `section` CHANGE  `price`  `price` FLOAT( 12, 2 ) NULL ;
ALTER TABLE  `section` CHANGE  `quantity`  `quantity` INT( 5 ) NULL ,
CHANGE  `rotation`  `rotation` ENUM(  'STATIC',  'ROTATE' ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL ;
ALTER TABLE  `advertisers` ADD  `deleted` TINYINT( 1 ) NOT NULL AFTER  `active` ;