use bookmall;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `order_book`;
drop table if exists `order_seq`;
SET FOREIGN_KEY_CHECKS = 1;

create table category(
	`no` int not null auto_increment,
    `name` varchar(100) not null,
    `description` varchar(1000) null,
    
    primary key(no),
    unique(name)
);

create table book(
	`no` int not null auto_increment,
    `title` varchar(100) not null,
    `price` int not null,
    `category_no` int not null,
    
    primary key(no),
    foreign key(category_no) references category(no) on delete cascade
);

create table member(
	`no` int not null auto_increment,
    `email` varchar(100) not null,
    `password` varchar(100) not null,
    `name` varchar(50) not null,
    `phone_no` varchar(20) not null,    
    
    primary key(no),
    unique(email)
);

create table cart(
    `member_no` int not null,
    `book_no` int not null,
    `qty` int not null,
    
    primary key(member_no, book_no),
    foreign key(member_no) references member(no) on delete cascade,
    foreign key(book_no) references book(no) on delete cascade
);
select sysdate();
create table `order`(
	`no` int not null auto_increment,
    `order_no` varchar(16) null,
    `member_no` int not null,
    `pay_amount` int not null,
    `ship_addr` varchar(200) not null,
    `order_date` datetime not null default now(),
    `status` enum('배송준비중', '배송중', '배송완료') default '배송준비중',
    
    primary key(no),
    foreign key(member_no) references member(no) on delete cascade
);


create table `order_book`(
    `order_no` int not null,
    `book_no` int not null,
    `qty` int not null,
    `price` int not null,
    
    primary key(order_no, book_no),
    foreign key(order_no) references `order`(no) on delete cascade,
    foreign key(book_no) references book(no) on delete cascade
);

create table `order_seq`(
	`order_date` date not null,
    `seq` int not null,

    primary key(order_date, seq),
    unique(order_date)
);