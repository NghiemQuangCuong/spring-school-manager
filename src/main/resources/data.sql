INSERT INTO class_type (id, name) VALUES (1, 'Maths');
INSERT INTO class_type (id, name) VALUES (2, 'Physics');
INSERT INTO class_type (id, name) VALUES (3, 'Chemistry');
INSERT INTO class_type (id, name) VALUES (4, 'Biology');
INSERT INTO class_type (id, name) VALUES (5, 'Geography');
INSERT INTO class_type (id, name) VALUES (6, 'Literature');
INSERT INTO class_type (id, name) VALUES (7, 'IT');

/* Insert school year */
INSERT INTO school_year (id, first_year, second_year) VALUES (1, 2020, 2021);

/* Insert classes */
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (1, 'M01', 1, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (2, 'M02', 1, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (3, 'P01', 2, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (4, 'C01', 3, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (5, 'B01', 4, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (6, 'G01', 5, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (7, 'L01', 6, 1);
INSERT INTO class_room (id, name, class_type_id, school_year_id) VALUES (8, 'I01', 7, 1);

/* Insert Student for class I01 */
INSERT INTO student (id, first_name, last_name, address, city, phone1, phone2, class_room_id)
    VALUES (1, 'Truong', 'Nguyen', '222/34 Nguyen Trai', 'Ho Chi Minh City', '0327491724', '', 8);
INSERT INTO student (id, first_name, last_name, address, city, phone1, phone2, class_room_id)
    VALUES (2, 'Khanh', 'Tran', '152 Nguyen Thi Minh Khai', 'Ho Chi Minh City', '0975387892', '0645787565', 8);
INSERT INTO student (id, first_name, last_name, address, city, phone1, phone2, class_room_id)
    VALUES (3, 'Phong', 'Nguyen', '73/28/4 Tran Phu', 'Nghe An', '0987678542', '0989687127', 8);

/* Insert Teacher */
INSERT INTO teacher (id, first_name, last_name, address, city, phone1, phone2)
    VALUES (1, 'Trong', 'Phan', '357 Cach Mang Thang 8', 'Ho Chi Minh City', '0361879264', '02846103826');
INSERT INTO teacher (id, first_name, last_name, address, city, phone1, phone2)
    VALUES (2, 'Phu', 'Le', '737 Ly Thuong Kiet', 'Ho Chi Minh City', '0271937235', '');
INSERT INTO teacher (id, first_name, last_name, address, city, phone1, phone2)
    VALUES (3, 'Thanh', 'Dinh', '22/312/35 Nam Ki Khoi Nghia', 'Ho Chi Minh City', '0972937123', '');

/* insert into teacher _ class relationship */
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (1, 1);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (1, 3);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (2, 2);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (2, 3);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (3, 1);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (3, 2);
INSERT INTO classes_teachers (class_room_id, teacher_id) VALUES (3, 3);