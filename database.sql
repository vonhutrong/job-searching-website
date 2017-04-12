CREATE TABLE `da_db`.`role` ( `id` INT NOT NULL AUTO_INCREMENT ,  `name` VARCHAR(50) NOT NULL ,    PRIMARY KEY  (`id`)) ENGINE = InnoDB;

INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_EMPLOYEE');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_EMPLOYER');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_ADMIN');

CREATE TABLE `da_db`.`user` ( `id` INT NOT NULL AUTO_INCREMENT ,  `email` VARCHAR(255) NOT NULL ,  `password` VARCHAR(255) NOT NULL ,    PRIMARY KEY  (`id`)) ENGINE = InnoDB;
ALTER TABLE `user` ADD UNIQUE(`email`);

INSERT INTO `user` (`id`, `email`, `password`) VALUES (NULL, 'user1@gmail.com', '123456');
INSERT INTO `user` (`id`, `email`, `password`) VALUES (NULL, 'employer@gmail.com', '123456');
INSERT INTO `user` (`id`, `email`, `password`) VALUES (NULL, 'admin@gmail.com', '123456');

CREATE TABLE `da_db`.`user_role` ( `user_id` INT NOT NULL ,  `role_id` INT NOT NULL ) ENGINE = InnoDB;
ALTER TABLE `user_role` ADD PRIMARY KEY( `user_id`, `role_id`);
ALTER TABLE `user_role` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `user_role` ADD FOREIGN KEY (`role_id`) REFERENCES `role`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `da_db`.`employee` ( `id` INT NOT NULL AUTO_INCREMENT ,  `user_id` INT NOT NULL ,  `name` VARCHAR(100) NOT NULL ,    PRIMARY KEY  (`id`)) ENGINE = InnoDB;
ALTER TABLE `employee` ADD INDEX(`user_id`);
ALTER TABLE `employee` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `da_db`.`employer` ( `id` INT NOT NULL AUTO_INCREMENT , `user_id` INT NOT NULL , `name` VARCHAR(255) NOT NULL , `description` VARCHAR(2000) NOT NULL , `address` VARCHAR(255) NOT NULL , `phone_number` VARCHAR(20) NOT NULL , `email` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
ALTER TABLE `employer` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `employer` CHANGE `email` `contact_email` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

CREATE TABLE `da_db`.`department` ( `id` INT NOT NULL AUTO_INCREMENT ,  `name` VARCHAR(100) NOT NULL ,    PRIMARY KEY  (`id`)) ENGINE = InnoDB;
CREATE TABLE `da_db`.`educational_level` ( `id` INT NOT NULL AUTO_INCREMENT ,  `name` VARCHAR(100) NOT NULL ,    PRIMARY KEY  (`id`)) ENGINE = InnoDB;
CREATE TABLE `da_db`.`cruitment` ( 
	`id` INT NOT NULL AUTO_INCREMENT ,  
	`title` VARCHAR(100) NOT NULL ,  
	`content` VARCHAR(2000) NOT NULL ,  
	`department_id` INT NOT NULL ,  
	`required_female` BOOLEAN NOT NULL ,  
	`educational_level_id` INT NOT NULL ,  
	`lowest_age` INT NOT NULL ,  
	`average_age` INT NOT NULL ,  
	`years_of_experience` INT NOT NULL ,  
	`employer_id` INT NOT NULL ,  
	`created_datetime` DATETIME NOT NULL ,    
	PRIMARY KEY  (`id`)
) ENGINE = InnoDB;
ALTER TABLE `cruitment` 
ADD FOREIGN KEY (`department_id`) REFERENCES `department`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT; 
ALTER TABLE `cruitment` 
ADD FOREIGN KEY (`educational_level_id`) REFERENCES `educational_level`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT; 
ALTER TABLE `cruitment` 
ADD FOREIGN KEY (`employer_id`) REFERENCES `employer`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `cruitment`  ADD `lowest_salary` DOUBLE NOT NULL  AFTER `created_datetime`,  ADD `highest_salary` DOUBLE NOT NULL  AFTER `lowest_salary`;
ALTER TABLE `cruitment` DROP `lowest_age`;
ALTER TABLE `cruitment` ADD `deadline_for_submission` DATETIME NOT NULL AFTER `highest_salary`;

