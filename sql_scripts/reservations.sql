-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysqldb
-- Generation Time: Aug 27, 2024 at 12:01 PM
-- Server version: 8.0.29
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `guest_count` int NOT NULL,
  `has_notify` bit(1) NOT NULL,
  `customer_id` bigint NOT NULL,
  `reservation_id` bigint NOT NULL,
  `timeslot` datetime(6) NOT NULL,
  `notify_method` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`guest_count`, `has_notify`, `customer_id`, `reservation_id`, `timeslot`, `notify_method`) VALUES
(10, b'0', 1, 1, '2024-08-30 15:00:00.000000', 'email'),
(5, b'0', 1, 3, '2024-09-03 10:00:00.000000', 'sms'),
(10, b'0', 2, 4, '2024-08-28 11:00:00.000000', 'email'),
(2, b'0', 3, 5, '2024-08-28 19:30:00.000000', 'email'),
(4, b'0', 4, 6, '2024-08-28 20:45:00.000000', 'sms'),
(4, b'0', 4, 7, '2024-09-15 20:45:00.000000', 'sms'),
(4, b'0', 5, 8, '2024-09-15 21:15:00.000000', 'sms');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` bigint NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
