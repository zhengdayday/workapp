create table lesson (lno bigint not null auto_increment, lesson_desc varchar(255), lname varchar(255), tno varchar(255), primary key (lno)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table student_select_lesson (id bigint not null auto_increment, lno bigint, sno varchar(255), primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table student_work (id integer not null auto_increment, answer varchar(255), grade varchar(255), sno varchar(255), wno bigint, work_read integer, work_status varchar(255), primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table tbl_work (wno bigint not null auto_increment, work_desc varchar(255), lno bigint, tno varchar(255), work_info varchar(255), work_name varchar(255), primary key (wno)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table user (id integer not null auto_increment, email varchar(255), level integer, name varchar(255), number varchar(255), password varchar(255), primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
