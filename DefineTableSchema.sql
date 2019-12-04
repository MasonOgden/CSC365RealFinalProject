create table if not exists Book (
	id integer not null,
    copyNum integer not null,
    title varchar(60) not null,
    author varchar(40) not null,
    category varchar(35) not null,
    primary key (id, copyNum)
);

create table if not exists Student (
	studentType enum('u', 'g') not null,
    id int auto_increment primary key,
    firstName varchar(15) not null,
    lastName varchar(25) not null
);

create table if not exists StudentInfo (
	sType enum('u', 'g') not null,
    sName varchar(13) not null,
    numDays int not null,
    maxNumBooks int not null
);

create table if not exists Checkout (
	studentId int not null,
    bookId int not null,
    copyNum int not null,
    startDate date not null,
    dayReturned date default null,
    dueBack date not null,
    ddExtended boolean not null default false,
    foreign key (studentId) references Student (id),
    foreign key (bookId, copyNum) references Book (id, copyNum)
);

create table if not exists Reservation (
	studentId int not null,
    bookId int not null,
    copyNum int not null,
    startDate date not null,
    endDate date not null,
    fulfilled boolean not null default false,
    foreign key (studentId) references Student (id),
    foreign key (bookId, copyNum) references Book (id, copyNum)
);

insert into StudentInfo(sType, sName, numDays, maxNumBooks) values ('u', "undergraduate", 14, 3);
insert into StudentInfo(sType, sName, numDays, maxNumBooks) values ('g', "graduate", 7, 5);
select * from StudentInfo;

use group06;
show tables;

select * from Reservation;
select * from Checkout;
select * from Student;
select * from Book;

# Test insert statements
insert into Book (copyNum, title, author, category) values (1, "test title", "test author", "fiction");
insert into Book (copyNum, title, author, category) values (2, "test title", "test author", "fiction");
insert into Book (copyNum, title, author, category) values (3, "test title", "test author", "fiction");
insert into Student (studentType, firstName, lastName) values ("u", "Mason", "Ogden");

insert into Checkout (studentId, bookId, copyNum, startDate, dayReturned, dueBack) values (1, 1, 1, "2019-12-01", null, "2019-12-15");
insert into Reservation (studentId, bookId, copyNum, startDate, endDate) values (1, 1, 1, "2019-12-01", "2019-12-08");

drop table StudentInfo;
drop table Checkout;
drop table Reservation;
drop table Student;
drop table Book;

