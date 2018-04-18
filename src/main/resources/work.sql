
--课程
create table lesson (
	lno bigint not null auto_increment, --课程id
	lname varchar(255), 		    --课程名
	tno varchar(255),		    --教工账号
	primary key (lno)
) engine=MyISAM

--学生
create table student (
	id bigint not null auto_increment,  -- id
	sclass varchar(255),                -- 班级
	name varchar(255),                  -- 姓名
	password varchar(255),              -- 密码
	sno varchar(255),                   -- 学生账号
	primary key (id)
) engine=MyISAM


--学生选课表
create table student_select_lesson (
	id bigint not null auto_increment,  --id
	lno bigint,                         --课程id
	sno varchar(255),       	    --学生账号
	primary key (id)
) engine=MyISAM

--学生作业表
create table student_work (
	id integer not null auto_increment,  --id
	answer varchar(255), 		     --回答
	grade bigint,   		     --成绩
	sno varchar(255),                    --教工id
	wno bigint,                          --作业id
	primary key (id)
) engine=MyISAM

--作业表
create table tbl_work (
	wno bigint not null auto_increment,   --作业id
	work_desc varchar(255), 	      --作业内容
	tno varchar(255),                     --教工号
	primary key (wno)
) engine=MyISAM

--教师表
create table teacher (
	id bigint not null auto_increment,    --id
	password varchar(255),                --密码
	name varchar(255),                    --姓名
	tno varchar(255),                     --教师账号
	primary key (id)
) engine=MyISAM
