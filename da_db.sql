-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2017 at 05:36 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `da_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `apply_history`
--

CREATE TABLE `apply_history` (
  `id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `recruitment_id` int(11) NOT NULL,
  `cv_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `datetime` datetime NOT NULL,
  `approved` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apply_history`
--

INSERT INTO `apply_history` (`id`, `employee_id`, `recruitment_id`, `cv_path`, `datetime`, `approved`) VALUES
(1, 1, 5, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.90442797939504030.8080\\files\\vonhutrong@gmail.com\\the_oxford_3000.pdf', '2017-04-20 13:12:44', 1),
(2, 1, 4, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.90442797939504030.8080\\files\\vonhutrong@gmail.com\\the_oxford_3000.pdf', '2017-04-20 13:55:08', NULL),
(3, 1, 5, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.8920246925717073146.8080\\files\\vonhutrong@gmail.com\\PL03_PhieuKiemSoatTienDo.pdf', '2017-04-29 20:41:51', 1),
(4, 1, 5, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.8611366272113043881.8080\\files\\vonhutrong@gmail.com\\DS THU N14.04.2017_dot 2.pdf', '2017-04-29 21:22:06', 0),
(5, 2, 5, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.3095206199855477473.8080\\files\\nguoidung1@gmail.com\\VO_NHU_TRONG_82.doc', '2017-05-03 15:28:49', 0),
(6, 1, 6, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.7379655780589198864.8080\\files\\vonhutrong@gmail.com\\BiaThuyetMinh_VoNhuTrong_12T4.pdf', '2017-05-10 12:05:35', 1),
(7, 1, 7, 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.7279815913476952736.8080\\files\\vonhutrong@gmail.com\\BiaThuyetMinh_VoNhuTrong_12T4.pdf', '2017-05-12 09:21:21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(7, 'Bán hàng'),
(8, 'Bán lẻ/ Bán sỉ'),
(9, 'Báo chí/ Biên tập viên/ Xuất bản'),
(10, 'Bảo hiểm'),
(11, 'Bất động sản'),
(12, 'Biên phiên dịch/ Thông dịch viên'),
(13, 'Chăm sóc sức khỏe/ Y tế'),
(14, 'CNTT - Phần cứng/ Mạng'),
(15, 'CNTT - Phần mềm'),
(16, 'Dầu khí/ Khoáng sản'),
(17, 'Dệt may/ Da giày'),
(18, 'Dịch vụ khách hàng'),
(19, 'Dược/ Sinh học'),
(20, 'Điện/ Điện tử'),
(21, 'Giáo dục/ Đào tạo/ Thư viện'),
(22, 'Hàng gia dụng'),
(23, 'Hóa chất/ Sinh hóa/ Thực phẩm'),
(24, 'Kế toán/ Tài chính/ Kiểm toán'),
(25, 'Khách sạn/ Du lịch'),
(26, 'Kiến trúc'),
(27, 'Kỹ thuật ứng dụng/ Cơ khí'),
(28, 'Lao động phổ thông'),
(29, 'Môi trường/ Xử lý chất thải'),
(30, 'Mới tốt nghiệp/ Thực tập'),
(31, 'Ngân hàng/ Chứng khoán/ Đầu tư'),
(32, 'Nghệ thuật/ Thiết kế/ Giải trí'),
(33, 'Người nước ngoài'),
(34, 'Nhà hàng/ Dịch vụ ăn uống'),
(35, 'Nhân sự'),
(36, 'Nông nghiệp/ Lâm nghiệp'),
(37, 'Ô tô'),
(38, 'Pháp lý/ Luật'),
(39, 'Phi chính phủ/ Phi lợi nhuận'),
(40, 'Quản lý chất lượng (QA/ QC)'),
(41, 'Quản lý điều hành'),
(42, 'Quảng cáo/ Khuyến mãi/ Đối ngoại'),
(43, 'Sản xuất/ Vận hành sản xuất'),
(44, 'Thư ký/ Hành chánh'),
(45, 'Tiếp thị'),
(46, 'Tư vấn'),
(47, 'Vận chuyển/ Giao thông/ Kho bãi'),
(48, 'Vật tư/ Mua hàng'),
(49, 'Viễn Thông'),
(50, 'Xây dựng'),
(51, 'Xuất nhập khẩu/ Ngoại thương'),
(52, 'Khác');

-- --------------------------------------------------------

--
-- Table structure for table `educational_level`
--

CREATE TABLE `educational_level` (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `educational_level`
--

INSERT INTO `educational_level` (`id`, `name`) VALUES
(1, 'Trung học phổ thông'),
(2, 'Trung cấp'),
(3, 'Cao Đẳng'),
(4, 'Đại Học'),
(5, 'Cao Học');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `user_id`, `name`) VALUES
(1, 17, 'vo nhu trong'),
(2, 27, 'Người dùng 1');

-- --------------------------------------------------------

--
-- Table structure for table `employer`
--

CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(10000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `contact_email` varchar(255) NOT NULL,
  `logo_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employer`
--

INSERT INTO `employer` (`id`, `user_id`, `name`, `description`, `address`, `phone_number`, `contact_email`, `logo_path`) VALUES
(3, 25, 'Công Ty Cổ Phần Chứng Khoán Ngân Hàng Đầu Tư Và Phát Triển Việt Nam (BSC)', 'Được cấp phép thành lập ngày 26/11/1999, với tên giao dịch: Công ty TNHH Chứng khoán Ngân hàng Đầu tư và Phát triển Việt Nam (BSC), Công ty vinh dự trở thành Công ty chứng khoán đầu tiên trong ngành ngân hàng tham gia kinh doanh trong lĩnh vực chứng khoán và cũng là một trong hai công ty chứng khoán đầu tiên tại Việt Nam.\r\n\r\nKế thừa và phát huy những kinh nghiệm quý báu trong hơn 50 năm qua của hệ thống Ngân hàng Đầu tư và Phát triển Việt Nam (BIDV)- Ngân hàng Thương mại quốc doanh được Uỷ ban Chứng khoán Nhà Nước chỉ định làm ngân hàng thanh toán cho thị trường chứng khoán - sự khai trương và đi vào hoạt động với tư cách là một định chế tài chính trung gian hoạt động đa năng của BSC cũng đánh dấu cho sự khởi đầu cho ngành chứng khoán nói chung và nghề môi giới, đầu tư và tư vấn đầu tư chứng khoán tại Việt Nam nói riêng.\r\n\r\nCuối năm 2010, với định hướng phát triển của BIDV, đồng thời để đáp ứng được nhu cầu và đòi hỏi của thị trường, BSC tiến hành cổ phần hóa và đấu giá thành công 10.195.570 cổ phần. Ngày 01/01/2011, Công ty chính thức đổi tên thành Công ty Cổ phần Chứng khoán Ngân hàng Đầu tư và Phát triển Việt Nam với số vốn điều lệ là 865 tỷ đồng. Hiện BSC có một trụ sở chính tại Hà Nội, một chi nhánh tại Thành phố Hồ Chí Minh, mạng lưới đại lý giao dịch trên toàn quốc, với hơn 200 nhân viên làm việc trong cả khối hỗ trợ và khối nghiệp vụ.\r\n\r\nHơn 10 năm qua, với sự hậu thuẫn toàn diện, mạnh mẽ và có hiệu quả của BIDV, bằng nỗ lực tự thân của đội ngũ cán bộ nhân viên, BSC đã không ngừng vươn lên với mục tiêu trở thành một trong những Công ty chứng khoán hàng đầu tại Việt Nam.', 'Tầng: 10,11-Tháp BIDV-35 Hàng Vôi,Hà Nội', '0439264660', 'bsc@gmail.com', 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.3859274278121829682.8080\\files\\bsc@gmail.com\\logo.png'),
(4, 26, 'Công Ty CP Đầu tư Bizman ', '\r\n\r\nCông ty Cổ phần Đầu tư Bizman – Thương hiệu Bizman được thành lập và hoạt động từ năm 2003, tiền thân là đơn vị chuyên khai thác và kinh doanh bảng quảng cáo ngoài trời, sau hơn 13 năm hoạt động không ngừng và liên tục phát triển cả về quy mô cũng như mở rộng lĩnh vực kinh doanh.\r\nĐến nay Bizman đã đầu tư và sở hữu một hệ thống bảng quảng cáo tấm lớn trên hầu khắp các trục quốc lộ quan trọng của cả nước, các cửa ngõ đường bộ, đường hàng không hướng vào trung tâm các thành phố lớn Hà Nội, Hồ Chí Minh, Cần Thơ,… Đặc biệt Bizman đã đầu tư một hệ thống Xe đẩy hành lý trong các sân bay lớn như; Sân bay quốc tế Nội Bài, Sân bay quốc tế Tân Sơn Nhất,… và khai thác quảng cáo trên hệ thống Xe đẩy này. Bên cạnh đó Bizman còn là đối tác tin cậy trong việc Tư vấn xây dựng và phát triển thương hiệu, tổ chức sự kiện, sản xuất phim quảng cáo, thiết kế và in ấn, đã sát cánh với nhiều thương hiệu mạnh hiện nay trên thị trường Việt Nam.\r\nVới đội ngũ nhân viên được đào tạo chuyên sâu trong lĩnh vực hoạt động, Bizman đã và đang nỗ lực không ngừng để cống hiến mọi khả năng của mình cho sự phát triển của Quý khách hàng. Hơn nữa Bizman xin gửi lời tri ân tới tất cả Quý khách hàng truyền thống đã sát cánh và tin tưởng chúng tôi trong suốt nhiều năm qua.', 'Số nhà 09, đường Sông Thao, phường 2, Q Tân Bình, TP HCM', '0949869898', 'info.sg@bizman.com.vn', 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.3149423279947159517.8080\\files\\bizman@gmail.com\\bizman.JPG'),
(5, 29, 'Công Ty TNHH Phát Triển Công Nghệ Minh Hà', 'Công ty TNHH Phát Triển Công Nghệ Minh Hà tiền thân là Công ty TNHH Công Nghệ Tin Học Minh Hà có đội ngũ nhân viên năng động, sáng tạo, nhiều kinh nghiệm trong ngành công nghệ thông tin. Hoạt động dựa trên nền tảng công nghệ, kỹ thuật cao và quy trình quản lý chất lượng nghiêm khắc.Minh Hà chú trọng chất lượng từng sản phẩm bán ra và đặc biệt quan tâm đến phần dịch vụ và hậu mãi nhằm đem đến cho khách hàng sự yên tâm, hài lòng cao nhất khi chọn các sản phẩm và dịch vụ của Minh Hà.', 'C3/T13 Phạm Hùng, P. Bình Hưng, Bình Chánh, Tp.HCM (gần cầu Nguyễn Tri Phương, Quận 5)', '0949869898', 'congngheminhha@gmail.com', 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.7673116167406097761.8080\\files\\congngheminhha@gmail.com\\minhha.png'),
(6, 30, '  PIVOTAL MARKETING VN', '\r\n\r\nWelcome to Pivotal Vietnam. Pivotal is one of Vietnam’s leading call center, direct mailing & database generation facilities, with the capability to support to companies across the Country. Established in August 2005, Pivotal has facilities in both Ho Chi Minh City and Hanoi, with plans to expand into other urban centers such in the future. Pivotal was established in response to increasing demand for a range of different telecommunication and mailing services. As a direct result of Vietnam’s increased economic and social development, consumers are becoming increasingly mature and confident in their purchasing power and decision making.\r\n\r\nThis in turn places more emphasis on companies operating here to maximise their sales opportunities, as well as allowing them to forge links with their consumer bases, (both current and potential), in ways that simply weren’t possible five or ten years ago.\r\n\r\nPivotal’s capabilities can be employed by companies from all parts of the spectrum of industry. Initially established to focus on supporting the marketing strategies of some of the biggest companies in the Region, a diversified portfolio of services now allows for a much greater variety of partnership possibilities, the full extent of which is detailed across this site.', 'Hồ Chí Minh, Việt Nam', '0949869898', 'pivotal@gmail.com', 'C:\\Users\\trongvn13\\AppData\\Local\\Temp\\tomcat.7379655780589198864.8080\\files\\pivotal@gmail.com\\pivotal.png');

-- --------------------------------------------------------

--
-- Table structure for table `recruitment`
--

CREATE TABLE `recruitment` (
  `id` int(11) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `department_id` int(11) NOT NULL,
  `required_female` tinyint(1) DEFAULT NULL,
  `educational_level_id` int(11) NOT NULL,
  `average_age` int(11) NOT NULL,
  `years_of_experience` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `created_datetime` datetime NOT NULL,
  `lowest_salary` double NOT NULL,
  `highest_salary` double NOT NULL,
  `deadline_for_submission` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recruitment`
--

INSERT INTO `recruitment` (`id`, `title`, `content`, `department_id`, `required_female`, `educational_level_id`, `average_age`, `years_of_experience`, `employer_id`, `created_datetime`, `lowest_salary`, `highest_salary`, `deadline_for_submission`) VALUES
(4, 'CHUYÊN VIÊN TIẾNG NHẬT', '- Chăm sóc khách hàng: Hướng dẫn khách mở tài khoản và cách thức giao dịch, giải đáp các câu hỏi của khách hàng, hỗ trợ các thủ tục cần thiết trong quá trình đầu tư, cung cấp cho khách hàng các thông tin về chính sách của BSC,…\r\n\r\n- Dịch bản tin, báo cáo sang tiếng Nhật: Các tin tức chính thức từ Website, của Sở giao dịch, các tin tức liên quan đến tài chính, ngân hàng, chứng khoán từ các nguồn như caféF, stox, Vietstock…, các tin thống kê giao dịch trong tuần, các báo cáo phân tích của Phòng phân tích Hội sở.', 7, 1, 4, 22, 0, 3, '2017-04-17 18:35:53', 5000000, 7000000, '2017-04-21'),
(5, 'NHÂN VIÊN KINH DOANH – BÁN HÀNG', '- Lập kế hoạch kinh doanh tuần/tháng/năm\r\n- Tìm kiếm và thiết lập danh sách khách hàng tiềm năng.\r\n- Xây dựng quan hệ với khách hàng.\r\n- Triển khai kế hoạch xúc tiến bán các sản phẩm, dịch vụ quảng cáo, truyền thông của công ty.\r\n- Đàm phán, ký kết hợp đồng.\r\n- Giải đáp thắc mắc, hỗ trợ khách hàng trong quá trình thực hiện hợp đồng.\r\n- Quản lý, theo dõi hợp đồng và các công việc chăm sóc khách hàng trước, trong và sau hợp đồng.\r\n- Đảm bảo mục tiêu doanh số của cá nhân.\r\n- Thực hiện các công việc theo yêu cầu của Cấp trên.\r\n- Công việc cụ thể trao đổi thêm khi phỏng vấn.', 7, 1, 3, 20, 0, 4, '2017-04-18 15:23:17', 500000, 8000000, '2017-04-26'),
(6, 'NHÂN VIÊN KỸ THUẬT PHẦN CỨNG VÀ MẠNG', '- Lắp ráp, cài đặt, sửa chữa máy vi tính các loại\r\n- Sửa các lỗi thông thường của máy in, máy fax, xử lý sự cố mạng, phần mềm, phần cứng…\r\n- Lắp đặt, đi dây mạng, cấu hình mạng nội bộ, mạng điện thoại có tổng đài, mạng camera quan sát, thay binh điện cho UPS…\r\n- Chi tiết trao đổi trong buổi phỏng vấn.', 14, NULL, 4, 22, 2, 5, '2017-05-10 11:58:57', 5000000, 7000000, '2017-05-21'),
(7, 'Nhân Viên Chăm Sóc Khách Hàng', '- Chăm sóc khách hàng từng giai đoạn theo Call Script của công ty\r\n- Giải đáp thắc mắc các vấn đề liên quan đến sản phẩm\r\n- Tư vấn và giới thiệu sản phẩm Sữa dinh dưỡng', 13, 1, 1, 30, 1, 6, '2017-05-10 12:15:35', 7000000, 10000000, '2017-05-22');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_EMPLOYEE'),
(2, 'ROLE_EMPLOYER'),
(3, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(17, 'vonhutrong@gmail.com', '$2a$10$E.0PUBo3is/vGhiLl1UhFuJmh.J0GemFIdAKDRLnSicG7pnTK2uSy'),
(20, 'tronglitu@yahoo.com.vn', '$2a$10$cGdofJz7nksOIGsqfRTJHerBSPMYpuR4kAiVvVaN26knnEIaDhOq.'),
(25, 'bsc@gmail.com', '$2a$10$1aQhOf6iJi2IiXfMPsTn1.kLA.Z09A8tXwONpsCc8eRlqGatfvO0u'),
(26, 'bizman@gmail.com', '$2a$10$e0rAyRVkt9eKi.Sm.dPcy.JEHPLsimQ2gGlCM9Pb/wSgzE6z8UJEG'),
(27, 'nguoidung1@gmail.com', '$2a$10$MGOH4qTvWjoSvTOf/tExMeEZZDX/plnvHCFsEg4k4WBNy7JsQ0J6a'),
(29, 'congngheminhha@gmail.com', '$2a$10$Q04ouSaXBx8gdQlnc3Tfne2Ks/ltpewkF17NnZGMU6bTQeJgq443e'),
(30, 'pivotal@gmail.com', '$2a$10$sRL.oMDUVIhVfErEZhpWAuTyjGHPXwx1Impd/kOrsNgHdeEah.SKu');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(17, 1),
(20, 2),
(25, 2),
(26, 2),
(27, 1),
(29, 2),
(30, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apply_history`
--
ALTER TABLE `apply_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKti8xysrmpjdcrpg5chr0equhv` (`employee_id`),
  ADD KEY `FKokk9tkilnanq63343gx0xr2vd` (`recruitment_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `educational_level`
--
ALTER TABLE `educational_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK81pr23jf16cs0wxoh94yj2nug` (`user_id`);

--
-- Indexes for table `recruitment`
--
ALTER TABLE `recruitment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrmbdxp7unhhx0uahlmbevnc3h` (`department_id`),
  ADD KEY `FKkkj8gvjogclskj1ow6l9yu19u` (`educational_level_id`),
  ADD KEY `FKrqjjy58isiw1iu4p8xycv7qi5` (`employer_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apply_history`
--
ALTER TABLE `apply_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `educational_level`
--
ALTER TABLE `educational_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `employer`
--
ALTER TABLE `employer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `recruitment`
--
ALTER TABLE `recruitment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `apply_history`
--
ALTER TABLE `apply_history`
  ADD CONSTRAINT `FKokk9tkilnanq63343gx0xr2vd` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`),
  ADD CONSTRAINT `FKti8xysrmpjdcrpg5chr0equhv` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `apply_history_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `apply_history_ibfk_2` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK6lk0xml9r7okjdq0onka4ytju` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `employer`
--
ALTER TABLE `employer`
  ADD CONSTRAINT `FK81pr23jf16cs0wxoh94yj2nug` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `recruitment`
--
ALTER TABLE `recruitment`
  ADD CONSTRAINT `FKkkj8gvjogclskj1ow6l9yu19u` FOREIGN KEY (`educational_level_id`) REFERENCES `educational_level` (`id`),
  ADD CONSTRAINT `FKrmbdxp7unhhx0uahlmbevnc3h` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKrqjjy58isiw1iu4p8xycv7qi5` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `recruitment_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `recruitment_ibfk_2` FOREIGN KEY (`educational_level_id`) REFERENCES `educational_level` (`id`),
  ADD CONSTRAINT `recruitment_ibfk_3` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
