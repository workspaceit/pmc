ALTER TABLE  `section` CHANGE  `section_type`  `section_type` ENUM(  'LOGO',  'BACKGROUND',  'BANNER',  'TOP_BANNER',  'BOTTOM_BANNER',  'VIDEO' ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ;