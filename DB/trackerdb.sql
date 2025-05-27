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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `rank` VARCHAR(45) NOT NULL,
  `reputation` INT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bounty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bounty` ;

CREATE TABLE IF NOT EXISTS `bounty` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (2, 'Vrix Nol\'tar', 'Zelurian', 4, 'Karnyx Prime', 'Docking Bay 12', NULL);
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (3, 'Juno Breaker', 'Augmented Human', 5, 'Velatrax 9', 'Neon District, Sector 8', NULL);
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (4, 'Meleth Sol', 'Draxian', 3, 'Ossirius', 'Hover Market near Blackspire Temple', NULL);
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (5, 'Echo Delta-7', 'Rogue AI Unit', 5, 'Nuvian Reach', ' Abandoned Relay Station Theta-21', NULL);
INSERT INTO `target` (`id`, `name`, `species`, `wanted_level`, `planet_name`, `last_seen`, `image_url`) VALUES (6, 'Skrumm Cryxol', 'Gragskin', 2, 'Brakka IV', 'Waste Wastes', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hunter`
-- -----------------------------------------------------
START TRANSACTION;
USE `trackerdb`;
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (1, 'Hog Durkins', 'Rim Scout II', 863, NULL);
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (2, 'Dale “Doitup” Drayko', 'Rad Ranger', 1386, NULL);
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (3, 'Nyra Vox', 'Prime Seeker', 1523, NULL);
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (4, 'Brick Talon', 'Rim Scout I', 443, NULL);
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (5, 'Zee “The Ghost” Korran', 'Orbital Operative', 1774, NULL);
INSERT INTO `hunter` (`id`, `name`, `rank`, `reputation`, `image_url`) VALUES (6, 'Mako Driggs', 'Snipe Tracker', 240, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bounty`
-- -----------------------------------------------------
START TRANSACTION;
USE `trackerdb`;
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (1, 50000, 1, 'Bring in alive. No disintegrations. The bounty is on a rogue smuggler with deep ties to a black-market tech syndicate. Known for evading planetary security systems and disabling trackers mid-chase. High priority due to intel leaks.', 3, '2025-02-12T12:23:12', NULL, 1, 1, NULL);
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (2, 25000, 1, 'Suspected of multiple identity thefts across three systems. Excellent in disguise, with a flair for charming their way out of tight spots. Last seen on Narion Prime impersonating a customs officer.', 2, '2025-02-12T12:23:12', NULL, 1, 2, NULL);
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (3, 30000, 1, 'Dead or Alive. Former weapons scientist turned rogue mercenary. Possesses critical knowledge of volatile tech. Known to travel with drones and homemade traps. Extremely dangerous in confined environments.', 2, '2025-02-12T12:23:12', NULL, 1, 3, NULL);
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (4, 75000, 1, 'DO NOT ENGAGE SOLO. Leader of the infamous Blood Eclipse crew. Wanted for war crimes, ship sabotage, and civilian mass displacement. Highly tactical, extremely dangerous, and likely armed with stolen military-grade tech. Coordination required.', 5, '2025-02-12T12:23:12', NULL, 2, 4, NULL);
INSERT INTO `bounty` (`id`, `amount`, `status`, `description`, `danger_level`, `issue_date`, `claimed_date`, `hunter_id`, `target_id`, `image_url`) VALUES (5, 10000, 0, 'Famous for a string of flashy heists. Leaves taunting messages for planetary law enforcement. Not violent, but evades capture with style. Crowd favorite, but the bounty still stands.', 1, '2025-02-12T12:23:12', '2025-07-03T12:12:12', 3, 5, NULL);

COMMIT;

