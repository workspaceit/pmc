ALTER TABLE  `advertisers` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `events` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `locations` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `photographers` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `venues` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `watermarks` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;
ALTER TABLE  `admins` ADD  `active` BOOLEAN NOT NULL DEFAULT TRUE AFTER  `created_by`;