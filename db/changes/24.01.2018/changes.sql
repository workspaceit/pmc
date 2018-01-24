ALTER TABLE  `advertiser_transaction` ADD  `total_paid` FLOAT( 12, 2 ) NOT NULL AFTER  `total` ;
ALTER TABLE  `advertiser_transaction` ADD  `total_due` FLOAT( 12, 2 ) NOT NULL AFTER  `total_paid` ;