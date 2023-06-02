USE admission;

-- insert students
INSERT INTO students (id,created_date,last_modified_date,address,avatar,birthDay,citizen_identity_issue_date,citizen_identity_issued_by,citizen_identity_number,email,ethnic,first_name,gender,last_name,order_number,phone_number) VALUES
(1,'2020-05-31 16:30:54','2020-05-31 16:30:54','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/1.jpg','2002-06-21','2019-05-09','Cục CS ĐKQL và DLQG','1','cuongnv@gmail.com','Kinh','Cường','Nam','Nguyễn Văn','435354','0384934734'),
(2,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/2.jpg','2002-08-16','2019-06-14','Cục CS ĐKQL và DLQG','2','dungnv@gmail.com','Kinh','Dũng','Nam','Nguyễn Văn','345346','0388327823'),
(3,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/3.jpg','2002-03-26','2019-06-14','Cục CS ĐKQL và DLQG','3','linhlv@gmail.com','Kinh','Linh','Nam','Lê Văn','546445','0345425345'),
(4,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/4.jpg','2002-02-06','2019-06-14','Cục CS ĐKQL và DLQG','4','manhbv@gmail.com','Kinh','Mạnh','Nam','Bùi Văn','756756','0373583342'),
(5,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/5.jpg','2002-01-15','2019-06-14','Cục CS ĐKQL và DLQG','5','huyenpt@gmail.com','Kinh','Huyền','Nữ','Phạm Thị','245534','0936734722'),
(6,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/6.jpg','2002-07-28','2019-06-14','Cục CS ĐKQL và DLQG','6','lanpt@gmail.com','Kinh','Lan','Nữ','Phạm Thị','575673','0834532224'),
(7,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/7.jpg','2002-06-19','2019-06-14','Cục CS ĐKQL và DLQG','7','thuydt@gmail.com','Kinh','Thúy','Nữ','Đoàn Thị','445346','0925345233'),
(8,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/8.jpg','2002-05-30','2019-06-14','Cục CS ĐKQL và DLQG','8','huydv@gmail.com','Kinh','Huy','Nam','Doãn Văn','845634','0344356634'),
(9,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/9.jpg','2002-04-12','2019-06-14','Cục CS ĐKQL và DLQG','9','cuongpv@gmail.com','Kinh','Cương','Nam','Phạm Văn','134554','0374544224'),
(10,'2020-06-01 10:48:39','2020-06-01 10:48:39','Thọ Ngọc, Triệu Sơn, Thanh Hóa','upload/avatar/10.jpg','2002-09-14','2019-06-14','Cục CS ĐKQL và DLQG','10','minhnt@gmail.com','Kinh','Minh','Nữ','Nguyễn Thị','563244','0954737332');

-- insert users
INSERT INTO users (created_date, last_modified_date, is_locked, password, role_name, username, student_id) VALUES
('2020-05-31 16:23:33','2020-05-31 16:23:33',0,'$2a$10$5ZaGoa6gAbwqDgn9gA4tcuqDNniDbx3RwTH6rEWl1Ak2GY/FZr5ya','ROLE_ADMIN','admin',NULL),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','1',1),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','2',2),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','3',3),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','4',4),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','5',5),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','6',6),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','7',7),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','8',8),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','9',9),
('2020-05-31 16:30:55','2020-05-31 16:30:55',0,'$2a$10$FswKqr6pH6uFODwuXcyEyOxJFV5xa0AH0.cbqMWDSACApOi.p3Tja','ROLE_USER','10',10);

-- insert data majors
INSERT INTO majors (id, code, name, created_date, last_modified_date) VALUES 
(1, "7210404", "Thiết kế thời trang", now(), now()),
(2, "7220201", "Ngôn ngữ Anh", now(), now()),
(3, "7220204", "Ngôn ngữ Trung Quốc", now(), now()),
(4, "7220209", "Ngôn ngữ Nhật", now(), now()),
(5, "7220210", "Ngôn ngữ Hàn Quốc", now(), now()),
(6, "7229020", "Ngôn ngữ học", now(), now()),
(7, "7310104", "Kinh tế đầu tư", now(), now()),
(8, "7310612", "Trung Quốc học", now(), now()),
(9, "7320113", "Công nghệ đa phương tiện", now(), now()),
(10, "7340101", "Quản trị kinh doanh", now(), now()),
(11, "7340115", "Marketing", now(), now()),
(12, "7340125", "Phân tích dữ liệu kinh doanh", now(), now()),
(13, "7340201", "Tài chính – Ngân hàng", now(), now()),
(14, "7340301", "Kế toán", now(), now()),
(15, "7340302", "Kiểm toán", now(), now()),
(16, "7340404", "Quản trị nhân lực", now(), now()),
(17, "7340406", "Quản trị văn phòng", now(), now()),
(18, "7480101", "Khoa học máy tính", now(), now()),
(19, "7480102", "Mạng máy tính và truyền thông dữ liệu", now(), now()),
(20, "7480103", "Kỹ thuật phần mềm", now(), now()),
(21, "7480104", "Hệ thống thông tin", now(), now()),
(22, "7480108", "Công nghệ kỹ thuật máy tính", now(), now()),
(23, "7480201", "Công nghệ thông tin", now(), now()),
(24, "7510201", "Công nghệ kỹ thuật cơ khí", now(), now()),
(25, "7510203", "Công nghệ kỹ thuật cơ điện tử", now(), now()),
(26, "7510205", "Công nghệ kỹ thuật ô tô", now(), now()),
(27, "7510206", "Công nghệ kỹ thuật nhiệt", now(), now()),
(28, "7510209", "Robot và trí tuệ nhân tạo", now(), now()),
(29, "7510301", "Công nghệ kỹ thuật điện, điện tử", now(), now()),
(30, "7519007", "Năng lượng tái tạo", now(), now()),
(31, "7510302", "Công nghệ kỹ thuật điện tử - Viễn thông", now(), now()),
(32, "75103021", "Công nghệ kỹ thuật điện tử y sinh", now(), now()),
(33, "7510303", "Công nghệ kỹ thuật điều khiển và TĐH", now(), now()),
(34, "75103031", "Kỹ thuật sản xuất thông minh", now(), now()),
(35, "7510401", "Công nghệ kỹ thuật hoá học", now(), now()),
(36, "7510406", "Công nghệ kỹ thuật môi trường", now(), now()),
(37, "7510605", "Logistics và quản lý chuỗi cung ứng", now(), now()),
(38, "7519003", "Công nghệ kỹ thuật khuôn mẫu", now(), now()),
(39, "7510213", "Thiết kế cơ khí và kiểu dáng công nghiệp", now(), now()),
(40, "7510204", "Công nghệ kỹ thuật cơ điện tử ô tô", now(), now()),
(41, "7520118", "Kỹ thuật hệ thống công nghiệp", now(), now()),
(42, "7520116", "Kỹ thuật cơ khí động lực", now(), now()),
(43, "7540101", "Công nghệ thực phẩm", now(), now()),
(44, "7540203", "Công nghệ vật liệu dệt, may", now(), now()),
(45, "7720203", "Hóa dược", now(), now()),
(46, "7810101", "Du lịch", now(), now()),
(47, "7810103", "Quản trị dịch vụ du lịch và lữ hành", now(), now()),
(48, "7810201", "Quản trị khách sạn", now(), now()),
(49, "7810202", "Quản trị nhà hàng và dịch vụ ăn uống", now(), now());

-- insert data blocks
INSERT INTO blocks (id, code, created_date, last_modified_date) VALUES
(1, "A00", now(), now()),
(2, "A01", now(), now()),
(3, "B00", now(), now()),
(4, "C00", now(), now()),
(5, "D01", now(), now()),
(6, "D04", now(), now()),
(7, "D06", now(), now()),
(8, "D07", now(), now()),
(9, "D14", now(), now()),
(10, "DD2", now(), now());

-- insert data majors block
INSERT INTO major_block (major_id, block_id) VALUES
(1, 1),
(1, 2),
(1, 5),
(1, 9);

INSERT INTO major_block (major_id, block_id) VALUES
(2, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(3, 5),
(3, 6);

INSERT INTO major_block (major_id, block_id) VALUES
(4, 5),
(4, 7);

INSERT INTO major_block (major_id, block_id) VALUES
(5, 5),
(5, 10);

INSERT INTO major_block (major_id, block_id) VALUES
(6, 4),
(6, 5),
(6, 9);

INSERT INTO major_block (major_id, block_id) VALUES
(7, 1),
(7, 2),
(7, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(8, 5),
(8, 6);

INSERT INTO major_block (major_id, block_id) VALUES
(9, 1),
(9, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(10, 1),
(10, 2),
(10, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(11, 1),
(11, 2),
(11, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(12, 1),
(12, 2),
(12, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(13, 1),
(13, 2),
(13, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(14, 1),
(14, 2),
(14, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(15, 1),
(15, 2),
(15, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(16, 1),
(16, 2),
(16, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(17, 1),
(17, 2),
(17, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(18, 1),
(18, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(19, 1),
(19, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(20, 1),
(20, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(21, 1),
(21, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(22, 1),
(22, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(23, 1),
(23, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(24, 1),
(24, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(25, 1),
(25, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(26, 1),
(26, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(27, 1),
(27, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(28, 1),
(28, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(29, 1),
(29, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(30, 1),
(30, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(31, 1),
(31, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(32, 1),
(32, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(33, 1),
(33, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(34, 1),
(34, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(35, 1),
(35, 3),
(35, 8);

INSERT INTO major_block (major_id, block_id) VALUES
(36, 1),
(36, 3),
(36, 8);

INSERT INTO major_block (major_id, block_id) VALUES
(37, 1),
(37, 2),
(37, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(38, 1),
(38, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(39, 1),
(39, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(40, 1),
(40, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(41, 1),
(41, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(42, 1),
(42, 2);

INSERT INTO major_block (major_id, block_id) VALUES
(43, 1),
(43, 3),
(43, 8);

INSERT INTO major_block (major_id, block_id) VALUES
(44, 1),
(44, 2),
(44, 5);

INSERT INTO major_block (major_id, block_id) VALUES
(45, 1),
(45, 3),
(45, 8);

INSERT INTO major_block (major_id, block_id) VALUES
(46, 4),
(46, 5),
(46, 9);

INSERT INTO major_block (major_id, block_id) VALUES
(47, 1),
(47, 5),
(47, 9);

INSERT INTO major_block (major_id, block_id) VALUES
(48, 1),
(48, 5),
(48, 9);

INSERT INTO major_block (major_id, block_id) VALUES
(49, 1),
(49, 5),
(49, 9);

-- insert subject
INSERT INTO subjects (id, `name`, created_date, last_modified_date) VALUES
(1, "Toán", now(), now()),
(2, "Lý", now(), now()),
(3, "Hóa", now(), now()),
(4, "Văn", now(), now()),
(5, "Anh", now(), now()),
(6, "Sinh", now(), now()),
(7, "Sử", now(), now()),
(8, "Địa", now(), now()),
(9, "Tiếng Trung", now(), now()),
(10, "Tiếng Nhật", now(), now()),
(11, "Tiếng Nga", now(), now());

-- insert data block subject
INSERT INTO block_subject (block_id, subject_id) VALUES
(1, 1),
(1, 2),
(1, 3);

INSERT INTO block_subject (block_id, subject_id) VALUES
(2, 1),
(2, 2),
(2, 5);

INSERT INTO block_subject (block_id, subject_id) VALUES
(3, 1),
(3, 3),
(3, 6);

INSERT INTO block_subject (block_id, subject_id) VALUES
(4, 1),
(4, 3),
(4, 6);

INSERT INTO block_subject (block_id, subject_id) VALUES
(5, 1),
(5, 4),
(5, 5);

INSERT INTO block_subject (block_id, subject_id) VALUES
(6, 1),
(6, 4),
(6, 9);

INSERT INTO block_subject (block_id, subject_id) VALUES
(7, 1),
(7, 4),
(7, 10);

INSERT INTO block_subject (block_id, subject_id) VALUES
(8, 1),
(8, 3),
(8, 5);

INSERT INTO block_subject (block_id, subject_id) VALUES
(9, 4),
(9, 7),
(9, 5);

INSERT INTO block_subject (block_id, subject_id) VALUES
(10, 1),
(10, 4),
(10, 11);

-- insert data major detail
INSERT INTO major_details (created_date,last_modified_date,amount_student_received,bench_mark,status,major_id) VALUES
('2016-08-12 00:00:00','2016-08-12 00:00:00',280,21.05,'PUBLIC',23),
('2017-08-12 00:00:00','2017-08-12 00:00:00',210,23.0,'PUBLIC',23),
('2018-08-12 00:00:00','2018-08-12 00:00:00',390,20.4,'PUBLIC',23),
('2019-08-12 00:00:00','2019-08-12 00:00:00',390,22.8,'PUBLIC',23),
('2021-08-12 00:00:00','2021-08-12 00:00:00',370,26.05,'PUBLIC',23),
('2022-08-12 00:00:00','2022-08-12 00:00:00',295,26.15,'PUBLIC',23),
('2020-08-12 00:00:00','2020-08-12 00:00:00',40,22.8,'PUBLIC',1),
('2020-08-12 00:00:00','2020-08-12 00:00:00',180,22.73,'PUBLIC',2),
('2020-08-12 00:00:00','2020-08-12 00:00:00',100,23.29,'PUBLIC',3),
('2020-08-12 00:00:00','2020-08-12 00:00:00',70,22.4,'PUBLIC',4).
('2020-08-12 00:00:00','2020-08-12 00:00:00',70,23.44,'PUBLIC',5),
('2020-08-12 00:00:00','2020-08-12 00:00:00',310,22.6,'PUBLIC',7),
('2020-08-12 00:00:00','2020-08-12 00:00:00',390,23.55,'PUBLIC',10),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,24.9,'PUBLIC',11),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,23.45,'PUBLIC',13),
('2020-08-12 00:00:00','2020-08-12 00:00:00',770,22.75,'PUBLIC',14),
('2020-08-12 00:00:00','2020-08-12 00:00:00',130,22.3,'PUBLIC',15),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,24.2,'PUBLIC',16),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,22.2,'PUBLIC',17),
('2020-08-12 00:00:00','2020-08-12 00:00:00',130,24.7,'PUBLIC',18),
('2020-08-12 00:00:00','2020-08-12 00:00:00',70,23.1,'PUBLIC',19),
('2020-08-12 00:00:00','2020-08-12 00:00:00',250,24.3,'PUBLIC',20),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,23.5,'PUBLIC',21),
('2020-08-12 00:00:00','2020-08-12 00:00:00',130,24.0,'PUBLIC',22),
('2020-08-12 00:00:00','2020-08-12 00:00:00',390,25.6,'PUBLIC',23),
('2020-08-12 00:00:00','2020-08-12 00:00:00',480,23.9,'PUBLIC',24),
('2020-08-12 00:00:00','2020-08-12 00:00:00',280,25.3,'PUBLIC',25),
('2020-08-12 00:00:00','2020-08-12 00:00:00',460,25.1,'PUBLIC',26),
('2020-08-12 00:00:00','2020-08-12 00:00:00',140,22.45,'PUBLIC',27),
('2020-08-12 00:00:00','2020-08-12 00:00:00',520,24.1,'PUBLIC',29),
('2020-08-12 00:00:00','2020-08-12 00:00:00',470,23.2,'PUBLIC',31),
('2020-08-12 00:00:00','2020-08-12 00:00:00',280,26.0,'PUBLIC',33),
('2020-08-12 00:00:00','2020-08-12 00:00:00',140,18.0,'PUBLIC',35),
('2020-08-12 00:00:00','2020-08-12 00:00:00',50,18.05,'PUBLIC',36),
('2020-08-12 00:00:00','2020-08-12 00:00:00',60,24.4,'PUBLIC',37),
('2020-08-12 00:00:00','2020-08-12 00:00:00',50,21.5,'PUBLIC',38),
('2020-08-12 00:00:00','2020-08-12 00:00:00',40,21.95,'PUBLIC',41),
('2020-08-12 00:00:00','2020-08-12 00:00:00',70,21.05,'PUBLIC',43),
('2020-08-12 00:00:00','2020-08-12 00:00:00',40,18.5,'PUBLIC',44),
('2020-08-12 00:00:00','2020-08-12 00:00:00',140,24.25,'PUBLIC',46),
('2020-08-12 00:00:00','2020-08-12 00:00:00',180,23.0,'PUBLIC',47),
('2020-08-12 00:00:00','2020-08-12 00:00:00',120,23.75,'PUBLIC',48);

-- insert admission
INSERT INTO admission.admissions (created_date,last_modified_date,orders,status,total_score,block_id,major_id,student_id) VALUES
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,23.0,1,10,1),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,3,23.0,1,13,1),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,2,23.0,1,14,1),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,2,24.0,2,7,2),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,4,24.0,2,10,2),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,4,24.0,2,11,2),
('2020-06-02 08:35:35','2020-06-02 08:35:35',4,4,24.0,2,13,2),
('2020-06-02 08:35:35','2020-06-02 08:35:35',5,4,24.0,2,14,2),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,2,23.5,5,7,3),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,4,23.5,5,3,3);
INSERT INTO admission.admissions (created_date,last_modified_date,orders,status,total_score,block_id,major_id,student_id) VALUES
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,4,23.5,5,2,3),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,24.0,2,16,4),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,2,25.5,1,18,4),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,2,26.0,2,33,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,4,25.0,1,29,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,4,25.0,1,37,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',4,4,25.0,1,38,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',5,4,25.0,1,41,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',6,4,25.0,1,48,5),
('2020-06-02 08:35:35','2020-06-02 08:35:35',7,4,26.0,2,21,5);
INSERT INTO admission.admissions (created_date,last_modified_date,orders,status,total_score,block_id,major_id,student_id) VALUES
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,24.5,2,26,6),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,3,24.5,2,23,6),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,2,24.5,2,22,6),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,24.0,5,46,7),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,2,24.0,5,47,7),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,4,24.0,5,48,7),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,25.0,1,23,8),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,3,24.5,2,25,8),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,3,23.0,5,16,8),
('2020-06-02 08:35:35','2020-06-02 08:35:35',4,2,25.0,1,22,8);
INSERT INTO admission.admissions (created_date,last_modified_date,orders,status,total_score,block_id,major_id,student_id) VALUES
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,2,23.0,5,4,9),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,3,23.0,5,5,9),
('2020-06-02 08:35:35','2020-06-02 08:35:35',1,3,18.0,3,43,10),
('2020-06-02 08:35:35','2020-06-02 08:35:35',2,3,18.0,3,36,10),
('2020-06-02 08:35:35','2020-06-02 08:35:35',3,2,18.0,3,35,10);

-- insert data setting
INSERT INTO settings (`setting_key`, `setting_value`) VALUES 
("start_time_admission", "2023-06-01 00:00:00"),
("end_time_admission", "2023-08-01 00:00:00")

