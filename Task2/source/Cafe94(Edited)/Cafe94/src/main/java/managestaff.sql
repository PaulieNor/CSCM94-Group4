-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2022 at 06:43 PM
-- Server version: 10.6.4-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `staffdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `managestaff`
--

CREATE TABLE `ManageStaff` (
  `StaffUsername` varchar(255) NOT NULL,
  `StaffType` varchar(255) NOT NULL,
  `First_Name` varchar(255) NOT NULL,
  `Last_Name` varchar(255) NOT NULL,
  `StaffPassword` varchar(255) NOT NULL,
  `HoursToWork` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `managestaff`
--

INSERT INTO `ManageStaff`(`StaffUsername`, `StaffType`, `First_Name`,
`Last_Name`, `StaffPassword`, `HoursToWork`) VALUES
('BB', 'Manager', 'Big', 'Boss', 'Test', '10'),
('JS', 'Cook', 'Jessica', 'Smith', 'Password', '8'),
('KD', 'Cook', 'Kierra', 'Davies', 'Password', '8'),
('RP', 'Cook', 'Ronnie', 'Peck', 'Password', '7'),
('JO', 'Waiter', 'Jenkins', 'Oldman', 'Password', '6'),
('CP', 'Waiter', 'Cory', 'Peterson', 'Password', '6'),
('LS', 'Waiter', 'Laika', 'Sputnik', 'Password', '6'),
('CH', 'Waiter', 'Chloe', 'Hines', 'Password', '5'),
('MW', 'Driver', 'Marlon', 'Warren', 'Password', '5'),
('CW', 'Driver', 'Carmelo', 'Woodward', 'Password', '4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `managestaff`
--
ALTER TABLE `managestaff`
  ADD PRIMARY KEY (`StaffUsername`);

--
-- AUTO_INCREMENT for dumped tables
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;