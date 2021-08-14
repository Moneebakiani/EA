-- alter table Course
--     add constraint fk_course foreign key (student_id) references Student (studentid);
insert into student (studentid, firstname, lastname) values (11334, 'Frank', 'Brown');
insert into course (coursenumber, `name`, grade, student_id) values (1101, 'Java', 'A', 11334);
insert into course (coursenumber, `name`, grade, student_id) values (1102, 'Math', 'B-', 11334);
