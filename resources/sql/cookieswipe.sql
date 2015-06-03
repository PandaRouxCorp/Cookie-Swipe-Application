-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 03 Juin 2015 à 11:51
-- Version du serveur :  5.6.17-log
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `cookieswipe`
--

CREATE DATABASE IF NOT EXISTS `cookieswipe`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci ;


USE `cookieswipe`;

-- --------------------------------------------------------

--
-- Structure de la table `domain`
--

CREATE TABLE IF NOT EXISTS `domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `adresse` varchar(45) NOT NULL,
  `popaddr` varchar(255) NOT NULL,
  `smtpaddr` varchar(255) NOT NULL,
  `portin` int(11) NOT NULL,
  `portout` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `domain`
--

INSERT INTO `domain` (`id`, `name`, `adresse`, `popaddr`, `smtpaddr`, `portin`, `portout`) VALUES
(1, 'Yahoo', 'yahoo.fr', 'pop.mail.yahoo.fr', 'smtp.mail.yahoo.fr', 995, 465),
(2, 'Microsoft', 'hotmail.com / hotmail.fr', 'pop3.live.com', 'smtp.live.com', 995, 587),
(3, 'Orange', 'orange.fr / wanadoo.fr', 'imap.free.fr', 'smtp.orange.fr', 995, 465),
(4, 'Google', 'gmail.com', 'pop.gmail.com', 'smtp.gmail.com', 993, 465);

-- --------------------------------------------------------

--
-- Structure de la table `mailaccount`
--

CREATE TABLE IF NOT EXISTS `mailaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `cookieswipename` varchar(45) NOT NULL,
  `domain` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `lastsync` datetime DEFAULT NULL,
  `mailsignature` varchar(255) DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_idx` (`user_id`),
  KEY `domain_mailaccount_idx` (`domain`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `mailaccount`
--

INSERT INTO `mailaccount` (`id`, `user_id`, `adresse`, `cookieswipename`, `domain`, `password`, `lastsync`, `mailsignature`, `color`) VALUES
(7, 1, 'panda.roux.corp@gmail.com', 'panda.roux.corp@gmail.com', 4, 'm65ZYlpIK6VKM9ZEBJZNdA==', NULL, NULL, 'Jaune'),
(9, 1, 'lucas.girardin@outlook.com', 'lucas.girardin', 2, 'FKU6zn6Riws0ijpkYWAXDg==', NULL, NULL, 'Rouge'),
(10, 1, 'girardin.lucas@gmail.com', 'girardin.lucas', 4, 'FBeHLRY40qCD6EDNdU5Q3Q==', NULL, NULL, 'Bleu');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `backupAdr` varchar(255) DEFAULT NULL,
  `blacklist` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `backupAdr`, `blacklist`) VALUES
(1, 'panda.roux.corp@gmail.com', 'ftkQGFdCHrYow/XnlXFK1Q==', 'lucas.girardin@outlooK.com', '');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `mailaccount`
--
ALTER TABLE `mailaccount`
  ADD CONSTRAINT `domain_mailaccount` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_mailaccount` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
