use webdb;

drop table if exists member;
create table member(
	no int not null auto_increment,
    email varchar(200) not null,
    password varchar(100) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);
    
desc member;
show create table member;
alter table member add column juminbunho char(13) not null after no;
alter table member drop column juminbunho;
alter table member change column department dept varchar(100);
alter table member add self_intro text;