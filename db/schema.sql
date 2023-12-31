-- Run using MySQL command

CREATE SCHEMA `file_handling_example`;

USE `file_handling_example`;

/* ********************************************************************************************************* */
/* ********************************************************************************************************* */

CREATE TABLE `user_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `national_id` varchar(14) NOT NULL,
  `cell_phone` char(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mailing_address` varchar(250) NOT NULL,
  `personal_image` mediumblob, -- 16MB appx.
  `version` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `national_id_UQ` (`national_id`),
  UNIQUE KEY `cell_phone_UQ` (`cell_phone`),
  UNIQUE KEY `email_UQ` (`email`)
);