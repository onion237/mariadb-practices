-- ddl
-- MySQL Workbench Forward Engineering
drop table book;
drop table author;

CREATE TABLE IF NOT EXISTS `author` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `book` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `status` ENUM('대여중', '대여가능') NOT NULL DEFAULT '대여가능',
  `author_no` INT NOT NULL,
  PRIMARY KEY (`no`),
  CONSTRAINT `fk_book_author`
    FOREIGN KEY (`author_no`)
    REFERENCES `bookshop`.`author` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- scheme 확인

desc book;
desc author;
select * from book;

