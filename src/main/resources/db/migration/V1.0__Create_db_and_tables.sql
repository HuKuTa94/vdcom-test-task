CREATE DATABASE IF NOT EXISTS `vdcom-test-task` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE `vdcom-test-task`;

CREATE TABLE IF NOT EXISTS `todo_list` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `text` VARCHAR(255) NOT NULL,
    `done` BIT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;