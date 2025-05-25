-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema trackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `trackerdb` ;

-- -----------------------------------------------------
-- Schema trackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trackerdb` DEFAULT CHARACTER SET utf8 ;
USE `trackerdb` ;

-- -----------------------------------------------------
-- Table `target`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `target` ;

CREATE TABLE IF NOT EXISTS `target` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `species` VARCHAR(100) NULL,
  `wanted_level` INT NOT NULL,
  `planet_name` VARCHAR(100) NULL,
  `last_seen` VARCHAR(100) NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hunter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hunter` ;

CREATE TABLE IF NOT EXISTS `hunter` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `rank` VARCHAR(45) NOT NULL,
  `reputaion` INT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bounty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bounty` ;

CREATE TABLE IF NOT EXISTS `bounty` (
  `id` INT NOT NULL,
  `amount` INT NOT NULL,
  `status` TINYINT NULL,
  `description` VARCHAR(2000) NULL,
  `danger_level` INT NULL,
  `issue_date` VARCHAR(45) NULL,
  `claimed_date` VARCHAR(45) NULL,
  `hunter_id` INT NOT NULL,
  `target_id` INT NOT NULL,
  `image_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`, `amount`),
  INDEX `fk_bounty_hunter_idx` (`hunter_id` ASC) VISIBLE,
  INDEX `fk_bounty_target1_idx` (`target_id` ASC) VISIBLE,
  CONSTRAINT `fk_bounty_hunter`
    FOREIGN KEY (`hunter_id`)
    REFERENCES `hunter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bounty_target1`
    FOREIGN KEY (`target_id`)
    REFERENCES `target` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS tracker@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'tracker'@'localhost' IDENTIFIED BY 'tracker';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tracker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `target`
-- -----------------------------------------------------
START TRANSACTION;
USE `trackerdb`;
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (1, 'Lucarius Sylvinate', 'Half-Serpinian ', 4, 'S\'kalium', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hunter`
-- -----------------------------------------------------
START TRANSACTION;
USE `trackerdb`;
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputaion`, `image_url`) VALUES (1, 'Hog Durkins', 'Rim Scout II', 863, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bounty`
-- -----------------------------------------------------
START TRANSACTION;
USE `trackerdb`;
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (1, 50000, 1, NULL, 3, NULL, NULL, 1, 1, NULL);

COMMIT;

