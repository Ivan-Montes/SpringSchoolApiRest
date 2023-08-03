INSERT INTO TEACHERS(name,surname)
VALUES('Satoru', 'Gojo'),
('Nikola', 'Tesla'),
('Hipatia', 'De Alejandria');

INSERT INTO SUBJECTS(name, teacher_id)
VALUES('Infinito 101', 1),
('Ingenieria 101', 2),
('Astronomia 101', 3);

INSERT INTO STUDENTS(name, surname)
VALUES('Yuji','Itadori'),
('Dukita','Madic'),
('Orestes','De Egipto'),
('Sinesio', 'De Cirene');

INSERT INTO SUBJECTS_STUDENTS(sub_id,stu_id,avegare_grade)
VALUES(1,1,4.3),
(2,2,9.2),
(3,3,7),
(3,4,8.1);