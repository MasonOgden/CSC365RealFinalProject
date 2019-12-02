create table if not exists Book (
	id integer auto_increment primary key,
    title varchar(60) not null,
    author varchar(40) not null,
    numCopies int not null,
    category varchar(35) not null
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
    startDate date not null,
    dayReturned date default null,
    dueBack date not null,
    ddExtended boolean not null default false,
    foreign key (studentId) references Student (id),
    foreign key (bookId) references Book (id)
);

create table if not exists Reservation (
	studentId int not null,
    bookId int not null,
    startDate date not null,
    endDate date not null,
    fulfilled boolean not null default false,
    foreign key (studentId) references Student (id),
    foreign key (bookId) references Book (id)
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

insert into Checkout (studentId, bookId, startDate, dayReturned, dueBack) values (1, 1, "2019-12-01", null, "2019-12-15");
insert into Reservation (studentId, bookId, startDate, endDate) values (1, 1, "2019-12-01", "2019-12-08");

drop table StudentInfo;
drop table Checkout;
drop table Reservation;
drop table Student;
drop table Book;



# Test insert statements
insert into Book (title, author, numCopies, category) values ("test title", "test author", 3, "fiction");
insert into Student (studentType, firstName, lastName) values ("u", "Mason", "Ogden");