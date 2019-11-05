-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 02 nov. 2019 à 14:04
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_tdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id_emp` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `nb_heure` double NOT NULL,
  `FK_id_ind` int(255) NOT NULL,
  `sexe` varchar(255) NOT NULL,
  PRIMARY KEY (`id_emp`),
  KEY `id_ind` (`FK_id_ind`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id_emp`, `nom`, `prenom`, `statut`, `nb_heure`, `FK_id_ind`, `sexe`) VALUES
(1, 'ACINA', 'Vick', 'Stagiaire', 35, 1, 'M'),
(3, 'LABEYE', 'Loïc', 'Stagiaire', 40, 1, 'M'),
(4, 'VERHEGGEN', 'Nour', 'Stagiaire', 35, 1, 'M'),
(5, 'FRANCOIS', 'Lisa', 'Cadre', 50, 2, 'F'),
(6, 'MEDJEBEUR', 'David', 'Employe', 40, 2, 'M');

-- --------------------------------------------------------

--
-- Structure de la table `industrie`
--

DROP TABLE IF EXISTS `industrie`;
CREATE TABLE IF NOT EXISTS `industrie` (
  `id_ind` int(11) NOT NULL AUTO_INCREMENT,
  `nom_ind` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ind`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `industrie`
--

INSERT INTO `industrie` (`id_ind`, `nom_ind`) VALUES
(1, 'ECAM'),
(2, 'Dassault');

-- --------------------------------------------------------

--
-- Structure de la table `intermediaire`
--

DROP TABLE IF EXISTS `intermediaire`;
CREATE TABLE IF NOT EXISTS `intermediaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FK_id_emp` int(255) NOT NULL,
  `FK_id_projet` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id_emp` (`FK_id_emp`),
  KEY `FK_id_projet` (`FK_id_projet`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `intermediaire`
--

INSERT INTO `intermediaire` (`id`, `FK_id_emp`, `FK_id_projet`) VALUES
(1, 1, 1),
(2, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

DROP TABLE IF EXISTS `projet`;
CREATE TABLE IF NOT EXISTS `projet` (
  `id_projet` int(255) NOT NULL AUTO_INCREMENT,
  `nom_projet` varchar(255) NOT NULL,
  `FK_id_ind` int(11) NOT NULL,
  PRIMARY KEY (`id_projet`),
  KEY `id_ind` (`FK_id_ind`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`id_projet`, `nom_projet`, `FK_id_ind`) VALUES
(1, 'Jenkins', 1),
(2, 'Symfony', 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
