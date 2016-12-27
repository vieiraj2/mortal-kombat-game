-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mortalkombat
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mortalkombat` ;

-- -----------------------------------------------------
-- Schema mortalkombat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mortalkombat` DEFAULT CHARACTER SET utf8 ;
USE `mortalkombat` ;

-- -----------------------------------------------------
-- Table `mortalkombat`.`alinhamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`alinhamento` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`alinhamento` (
  `idAlinhamento` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Ali` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idAlinhamento`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`arma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`arma` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`arma` (
  `idArma` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Arma` VARCHAR(45) NOT NULL COMMENT '',
  `Ataque` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idArma`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`classificacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`classificacao` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`classificacao` (
  `idClassificacao` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Cla` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idClassificacao`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`estiloluta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`estiloluta` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`estiloluta` (
  `idEstilo` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Estilo` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idEstilo`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`mundo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`mundo` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`mundo` (
  `idMundo` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Mundo` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idMundo`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`personagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`personagem` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`personagem` (
  `idPersonagem` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Nome_Per` VARCHAR(45) NOT NULL COMMENT '',
  `idMundoP` INT(11) NULL DEFAULT NULL COMMENT '',
  `idClassificacaoP` INT(11) NULL DEFAULT NULL COMMENT '',
  `idArmaP` INT(11) NULL DEFAULT NULL COMMENT '',
  `idEstiloP` INT(11) NULL DEFAULT NULL COMMENT '',
  `idAlinhamentoP` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idPersonagem`)  COMMENT '',
  INDEX `fk_idMundo_idx` (`idMundoP` ASC)  COMMENT '',
  INDEX `fk_idClassificacao_idx` (`idClassificacaoP` ASC)  COMMENT '',
  INDEX `fk_idArma_idx` (`idArmaP` ASC)  COMMENT '',
  INDEX `fk_idEstilo_idx` (`idEstiloP` ASC)  COMMENT '',
  INDEX `fk_idAlinhamento_idx` (`idAlinhamentoP` ASC)  COMMENT '',
  CONSTRAINT `fk_idAlinhamento`
    FOREIGN KEY (`idAlinhamentoP`)
    REFERENCES `mortalkombat`.`alinhamento` (`idAlinhamento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idArma`
    FOREIGN KEY (`idArmaP`)
    REFERENCES `mortalkombat`.`arma` (`idArma`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idClassificacao`
    FOREIGN KEY (`idClassificacaoP`)
    REFERENCES `mortalkombat`.`classificacao` (`idClassificacao`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idEstilo`
    FOREIGN KEY (`idEstiloP`)
    REFERENCES `mortalkombat`.`estiloluta` (`idEstilo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idMundo`
    FOREIGN KEY (`idMundoP`)
    REFERENCES `mortalkombat`.`mundo` (`idMundo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mortalkombat`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mortalkombat`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mortalkombat`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(45) NOT NULL COMMENT '',
  `senha` VARCHAR(45) NOT NULL COMMENT '',
  `acesso` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idUsuario`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `usuario` (`login`, `senha`, `acesso`) VALUES 
 ('admin', 'admin', 'administrador'),
 ('usuario', 'usuario', 'usuario');

INSERT INTO `mundo` (`Nome_Mundo`) VALUES
 ("Nenhum"),
 ("Outworld"),
 ("Earthrealm"),
 ("Arnyek"),
 ("Zaterra"),
 ("Edenia"),
 ("Heaven");
 
INSERT INTO `classificacao` (`Nome_Cla`) VALUES
 ("Nenhum"),
 ("Tarkatan"),
 ("Outworlder"),
 ("Humano"),
 ("Kytinn"),
 ("Metade Edeniano Metade Deus"),
 ("Fusão de Almas"),
 ("Dupla de Lutadores"),
 ("Guerreiro"),
 ("Deus"),
 ("Edeniana"),
 ("Zumbi"),
 ("Osh-Tekk"),
 ("Ninja");
 
INSERT INTO `estiloluta` (`Nome_Estilo`) VALUES
 ("Nenhum"),
 ("Silat"),
 ("Drunken Fist"),
 ("Fu Jow Pai"),
 ("Hua Chuan"),
 ("Lui He"),
 ("Shokan"),
 ("Muay Thai"),
 ("Fan Zi"),
 ("Karate"),
 ("Aikido"),
 ("Judo"),
 ("Eagle Claw"),
 ("Judo"),
 ("Mantis"),
 ("Jun Fan"),
 ("Mian Chuan"),
 ("Crab"),
 ("Hapkido"),
 ("Ninjitsu"),
 ("Kuo Shou"),
 ("Kenpo"),
 ("Shotokan"),
 ("Yue Chuan"),
 ("Gold Dragon");
 
INSERT INTO `alinhamento` (`Nome_Ali`) VALUES
 ("Bem"),
 ("Mal"),
 ("Neutro");
 
INSERT INTO `arma` (`Nome_Arma`, `Ataque`) VALUES
 ("Razor Cane", 21),
 ("Jojutsu", 29),
 ("Nightstick", 19),
 ("Stingers", 14),
 ("Drakesword", 37),
 ("Axe", 40),
 ("Revolver", 58),
 ("Fist", 44),
 ("Plasma Crossbow", 43),
 ("Dragon Fangs", 52),
 ("Bionic Arm", 26),
 ("Powered Gauntlets", 60),
 ("Cyclone Throwers", 67),
 ("Machete", 13),
 ("Nunchaku", 20),
 ("Butterfly Knives", 51),
 ("Katana", 71),
 ("Steel Fan", 31),
 ("Macuahuitl", 17),
 ("Bow", 70),
 ("Broadsword", 33),
 ("Long Sword", 64),
 ("Pistola Laser", 63),
 ("Straight Sword", 72),
 ("Bone Spear", 57),
 ("Wind Blades", 54),
 ("Kori Blader", 74),
 ("Pulse Blades", 72),
 ("Boomerang", 12);