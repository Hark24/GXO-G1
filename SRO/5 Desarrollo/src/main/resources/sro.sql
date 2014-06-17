CREATE DATABASE  IF NOT EXISTS `sro`;
USE `sro`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: sro
-- ------------------------------------------------------
-- Server version	5.5.28

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
CREATE TABLE `paciente` (
  `idPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `HistClinica` varchar(30) DEFAULT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Edad` int(11) DEFAULT NULL,
  `FechNac` date DEFAULT NULL,
  `Eliminacion` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPaciente`),
  UNIQUE KEY `HistClinica_UNIQUE` (`HistClinica`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `procedimiento`
--

DROP TABLE IF EXISTS `procedimiento`;
CREATE TABLE `procedimiento` (
  `idProcedimiento` int(11) NOT NULL AUTO_INCREMENT,
  `CIE` varchar(30) DEFAULT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Eliminacion` tinyint(1) NOT NULL,
  PRIMARY KEY (`idProcedimiento`),
  UNIQUE KEY `CIE_UNIQUE` (`CIE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
CREATE TABLE `sala` (
  `idSala` int(11) NOT NULL AUTO_INCREMENT,
  `CodSala` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `Eliminacion` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `profesional`
--

DROP TABLE IF EXISTS `profesional`;
CREATE TABLE `profesional` (
  `idProfesional` int(11) NOT NULL AUTO_INCREMENT,
  `Colegio` varchar(10) NOT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Tipo` varchar(30) NOT NULL,
  `Eliminacion` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idProfesional`),
  UNIQUE KEY `Colegio_UNIQUE` (`Colegio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL AUTO_INCREMENT,
  `FechAviso` date NOT NULL,
  `FechaIni` datetime NOT NULL,
  `FechaFin` datetime NOT NULL,
  `DuracionReal` datetime DEFAULT NULL,
  `DuracionEst` datetime NOT NULL,
  `Aprobado` tinyint(1) DEFAULT NULL,
  `Cancelado` tinyint(1) DEFAULT NULL,
  `Realizado` tinyint(1) DEFAULT NULL,
  `Observacion` varchar(45) DEFAULT NULL,
  `Compania` varchar(45) DEFAULT NULL,
  `Ubicacion` varchar(45) DEFAULT NULL,
  `Vigente` tinyint(1) NOT NULL,
  `Sala_idSala` int(11) NOT NULL,
  `paciente_idPaciente` int(11) NOT NULL,
  `procedimiento_idProcedimiento` int(11) NOT NULL,
  `profesional_idProfesional` int(11) NOT NULL,
  `profesional_idProfesional1` int(11) NOT NULL,
  `profesional_idProfesional2` int(11) NOT NULL,
  `reserva_idReserva` int(11) DEFAULT NULL,
  `Eliminacion` tinyint(1) NOT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `fk_Reserva_Sala1_idx` (`Sala_idSala`),
  KEY `fk_reserva_reserva1_idx` (`reserva_idReserva`),
  KEY `fk_reserva_profesional1_idx` (`profesional_idProfesional`),
  KEY `fk_reserva_profesional2_idx` (`profesional_idProfesional1`),
  KEY `fk_reserva_profesional3_idx` (`profesional_idProfesional2`),
  KEY `fk_reserva_procedimiento1_idx` (`procedimiento_idProcedimiento`),
  KEY `fk_reserva_paciente1_idx` (`paciente_idPaciente`),
  CONSTRAINT `fk_Reserva_Sala1` FOREIGN KEY (`Sala_idSala`) REFERENCES `sala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_reserva1` FOREIGN KEY (`reserva_idReserva`) REFERENCES `reserva` (`idReserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_profesional1` FOREIGN KEY (`profesional_idProfesional`) REFERENCES `profesional` (`idProfesional`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_profesional2` FOREIGN KEY (`profesional_idProfesional1`) REFERENCES `profesional` (`idProfesional`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_profesional3` FOREIGN KEY (`profesional_idProfesional2`) REFERENCES `profesional` (`idProfesional`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_procedimiento1` FOREIGN KEY (`procedimiento_idProcedimiento`) REFERENCES `procedimiento` (`idProcedimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_paciente1` FOREIGN KEY (`paciente_idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


