USE admission;

-- insert admin
INSERT INTO users (username, password, role_name, created_date, last_modified_date)
VALUES ("admin","$2a$10$5ZaGoa6gAbwqDgn9gA4tcuqDNniDbx3RwTH6rEWl1Ak2GY/FZr5ya", "ROLE_ADMIN", now(), now());

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

-- insert data major detail
INSERT INTO major_details (id, amount_student_received, bench_mark, status, created_date, last_modified_date, major_id) VALUES
(1, 280, 21.05, "PUBLIC", "2016-08-12 00:00:00", "2016-08-12 00:00:00", 23),
(2, 210, 23, "PUBLIC", "2017-08-12 00:00:00", "2017-08-12 00:00:00", 23),
(3, 390, 20.4, "PUBLIC", "2018-08-12 00:00:00", "2018-08-12 00:00:00", 23),
(4, 390, 22.8, "PUBLIC", "2019-08-12 00:00:00", "2019-08-12 00:00:00", 23),
(5, 390, 25.6, "PUBLIC", "2020-08-12 00:00:00", "2020-08-12 00:00:00", 23),
(6, 370, 26.05, "PUBLIC", "2021-08-12 00:00:00", "2021-08-12 00:00:00", 23),
(7, 295, 26.15, "PUBLIC", "2022-08-12 00:00:00", "2022-08-12 00:00:00", 23);