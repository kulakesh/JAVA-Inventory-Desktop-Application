-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 18, 2024 at 07:31 AM
-- Server version: 5.7.39
-- PHP Version: 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_22`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `remarks` varchar(200) NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `remarks`, `del`) VALUES
('C1000', 'Default', '', 0),
('C1001', 'Water', '', 0),
('C1002', 'Vial', '', 0),
('C1003', 'Transcap', '', 0),
('C1004', 'Toothpaste', '', 0),
('C1005', 'Test Cagrd', '', 0),
('C1006', 'Tarnscap', '', 0),
('C1007', 'Tabs ', '', 0),
('C1008', 'Tab', '', 0),
('C1009', 'Syringe', '', 0),
('C1010', 'Syp', '', 0),
('C1011', 'Sy', '', 0),
('C1012', 'Suspention', '', 0),
('C1013', 'Susp', '', 0),
('C1014', 'Sus', '', 0),
('C1015', 'Spray', '', 0),
('C1016', 'Solution', '', 0),
('C1017', 'Soap', '', 0),
('C1018', 'Shampoo', '', 0),
('C1019', 'Salaine', '', 0),
('C1020', 'Rotacap ', '', 0),
('C1021', 'Powder', '', 0),
('C1022', 'Plaster', '', 0),
('C1023', 'ORS Powder', '', 0),
('C1024', 'Oint', '', 0),
('C1025', 'oil', '', 0),
('C1026', 'Needle', '', 0),
('C1027', 'Nasal Spray ', '', 0),
('C1028', 'nasal drop', '', 0),
('C1029', 'N/Drop', '', 0),
('C1030', 'Mouth wash', '', 0),
('C1031', 'Mouth Paint', '', 0),
('C1032', 'M/Wash', '', 0),
('C1033', 'M/W', '', 0),
('C1034', 'Lotion', '', 0),
('C1035', 'Liquid', '', 0),
('C1036', 'LIQ', '', 0),
('C1037', 'IV', '', 0),
('C1038', 'Inj', '', 0),
('C1039', 'Inhaler ', '', 0),
('C1040', 'Honey', '', 0),
('C1041', 'Gel', '', 0),
('C1042', 'Flex Pen', '', 0),
('C1043', 'Feeding Nipple', '', 0),
('C1044', 'Eye/ear drop', '', 0),
('C1045', 'Eye drop', '', 0),
('C1046', 'Expt', '', 0),
('C1047', 'Exp', '', 0),
('C1048', 'Ear drop', '', 0),
('C1049', 'E/drop', '', 0),
('C1050', 'Dry Syp', '', 0),
('C1051', 'Drop', '', 0),
('C1052', 'Diaper', '', 0),
('C1053', 'Cream', '', 0),
('C1054', 'Cough Syp', '', 0),
('C1055', 'Cotton', '', 0),
('C1056', 'Condom', '', 0),
('C1057', 'Cartridge', '', 0),
('C1058', 'Cap', '', 0),
('C1059', 'Cannula', '', 0),
('C1060', 'Candy', '', 0),
('C1061', 'Belt', '', 0),
('C1062', 'Bag', '', 0),
('C1063', 'Pen Fill', '', 0),
('C1064', 'Bum', '', 0),
('C1065', 'Test Strip', '', 0),
('C1066', 'Vapocap', '', 0),
('C1067', 'Face Wash', '', 0),
('C1068', 'Mouth Spray', '', 0),
('C1069', 'Tharmomiter', '', 0),
('C1070', 'Musk', '', 0),
('C1071', 'Pragnancy Test Card', '', 0),
('C1072', 'Tap', '', 0),
('C1073', 'Sanitary Pad', '', 0),
('C1074', 'Kit', '', 0),
('C1075', 'Nutrition Powder', '', 0),
('C1076', 'Suppliment', '', 0),
('C1077', 'Suppy', '', 0),
('C1078', 'Swing Gum', '', 0),
('C1079', 'Buds', '', 0),
('C1080', 'Enema', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` varchar(10) NOT NULL,
  `cid` varchar(20) NOT NULL,
  `category` varchar(30) NOT NULL,
  `division` varchar(30) NOT NULL,
  `name` varchar(200) NOT NULL,
  `reg_no` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `pin` varchar(20) NOT NULL,
  `phone_o` varchar(20) NOT NULL,
  `phone_r` varchar(20) NOT NULL,
  `fax` varchar(20) NOT NULL,
  `website` varchar(100) NOT NULL,
  `email_c` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cst` varchar(30) NOT NULL,
  `vat` varchar(30) NOT NULL,
  `pan` varchar(30) NOT NULL,
  `service` varchar(30) NOT NULL,
  `exice` varchar(30) NOT NULL,
  `bank` varchar(100) NOT NULL,
  `branch` varchar(100) NOT NULL,
  `ifsc` varchar(20) NOT NULL,
  `mirc` varchar(30) NOT NULL,
  `ac` varchar(30) NOT NULL,
  `ac_type` varchar(20) NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `cid`, `category`, `division`, `name`, `reg_no`, `address`, `city`, `state`, `country`, `pin`, `phone_o`, `phone_r`, `fax`, `website`, `email_c`, `email`, `cst`, `vat`, `pan`, `service`, `exice`, `bank`, `branch`, `ifsc`, `mirc`, `ac`, `ac_type`, `del`) VALUES
('C1000', '123', ' ', ' ', 'CASH', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ' ', 0);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` varchar(10) NOT NULL,
  `category_id` varchar(10) NOT NULL,
  `category` varchar(300) NOT NULL,
  `sap_code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `category_id`, `category`, `sap_code`, `name`, `amount`, `del`) VALUES
('I3390', '', 'M7 HT-SPARE', '437.67', 'CURRENT COLLECTOR RING FOR MOTOR 2071', 3500, 0),
('I3391', '', 'M7 HT-SPARE', '437.670', 'CURRENT COLLECTOR RING FOR MOTOR 2071277', 3500, 0),
('I3392', '', 'COOLING FAN ', '131087008', 'COOLING FAN PP D132-146 Q703 PP BORE', 189, 0),
('I3393', '', 'SHRNK RTR ', 'S-29656521', 'SHRNK RTR Q106X4TF 127 MM', 2375, 0),
('I3394', '', 'SHRNK RTR ', 'S-29683421', 'SHRNK RTR Q106X4TF 100 MM CMDA', 1950, 0),
('I3395', '', ' ROTOR+ SHAFT ', '6148262001', '4P/ND225S/200 RTR+SHAFT RD 200-4', 21954, 0),
('I3396', '', 'ROTOR + SHAFT', '6124312011', '4P/ND180L/215 RTR+SHAFT RD 160-4', 22950, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rpt_stock`
--

CREATE TABLE `rpt_stock` (
  `id` int(11) NOT NULL,
  `item_id` varchar(10) NOT NULL,
  `rdate` date NOT NULL,
  `opening_stock` bigint(20) NOT NULL,
  `receive` bigint(20) NOT NULL,
  `sale` bigint(20) NOT NULL,
  `return` bigint(20) NOT NULL,
  `closing_stock` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rpt_stock`
--

INSERT INTO `rpt_stock` (`id`, `item_id`, `rdate`, `opening_stock`, `receive`, `sale`, `return`, `closing_stock`) VALUES
(46, 'I3392', '2016-04-07', 0, 25, 0, 0, 25);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` varchar(10) NOT NULL,
  `ref_no` varchar(20) NOT NULL,
  `client_id` varchar(10) NOT NULL,
  `invoice_date` date NOT NULL,
  `invoice_time` time NOT NULL,
  `amount` double NOT NULL,
  `tax` double NOT NULL,
  `tax_p` double NOT NULL,
  `discount` double NOT NULL,
  `discount_p` double NOT NULL,
  `handling` double NOT NULL,
  `handling_p` double NOT NULL,
  `tax_type` varchar(10) NOT NULL,
  `is_c_req` tinyint(4) NOT NULL,
  `is_c_rec` tinyint(4) NOT NULL,
  `paid` double NOT NULL,
  `carrier` varchar(200) NOT NULL,
  `shipment_no` varchar(100) NOT NULL,
  `weight` varchar(20) NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sales_item`
--

CREATE TABLE `sales_item` (
  `sales_id` varchar(10) NOT NULL,
  `item_id` varchar(10) NOT NULL,
  `rate` double NOT NULL,
  `quantity` double NOT NULL,
  `del` tinyint(4) NOT NULL,
  `unique` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `name` varchar(100) NOT NULL,
  `address1` varchar(150) NOT NULL,
  `address2` varchar(150) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `fax` varchar(30) NOT NULL,
  `website` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `is_tax` tinyint(4) NOT NULL,
  `tax_name` varchar(20) NOT NULL,
  `tax_rate` double NOT NULL,
  `tin` varchar(20) NOT NULL,
  `cst` varchar(20) NOT NULL,
  `declaration` text NOT NULL,
  `img_path` varchar(600) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`name`, `address1`, `address2`, `phone`, `fax`, `website`, `email`, `is_tax`, `tax_name`, `tax_rate`, `tin`, `cst`, `declaration`, `img_path`) VALUES
('', '', '', '', '', '', '', 1, 'VAT', 5, '88888888888888', '77777777777777', 'Declaration\nI hereby certify that my registration certificate under the \nAssam Value Added Tax Act, 2003 is in force on the date on\nwhich the sale of goods specified in this retail invoice is made\nby me and that the transaction of sale covered by this retail invoice\nhas been effected by me.\n1) Goods once sold, will  not be taken back\n2) Warranty as per responcive menufacturer policy.', '');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` varchar(10) NOT NULL,
  `rdate` date NOT NULL,
  `invoice_no` varchar(50) NOT NULL,
  `invoice_date` varchar(20) NOT NULL,
  `invoice_amount` double NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stock_item`
--

CREATE TABLE `stock_item` (
  `stock_id` varchar(10) NOT NULL,
  `item_id` varchar(10) NOT NULL,
  `qnty` int(11) NOT NULL,
  `gd` int(11) NOT NULL,
  `bd` int(11) NOT NULL,
  `reck` varchar(30) NOT NULL,
  `del` tinyint(4) NOT NULL,
  `unique` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` varchar(10) NOT NULL,
  `name` varchar(75) NOT NULL,
  `contact_person` varchar(50) NOT NULL,
  `address1` varchar(150) NOT NULL,
  `address2` varchar(150) NOT NULL,
  `contact_no` varchar(50) NOT NULL,
  `remarks` varchar(200) NOT NULL,
  `del` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sys_moss`
--

CREATE TABLE `sys_moss` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_moss`
--

INSERT INTO `sys_moss` (`username`, `password`) VALUES
('a', 'a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rpt_stock`
--
ALTER TABLE `rpt_stock`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales_item`
--
ALTER TABLE `sales_item`
  ADD PRIMARY KEY (`unique`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_item`
--
ALTER TABLE `stock_item`
  ADD PRIMARY KEY (`unique`),
  ADD UNIQUE KEY `unique` (`unique`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_moss`
--
ALTER TABLE `sys_moss`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales_item`
--
ALTER TABLE `sales_item`
  MODIFY `unique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stock_item`
--
ALTER TABLE `stock_item`
  MODIFY `unique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
