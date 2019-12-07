use group06;
show tables;

create table if not exists Book (
	id integer not null,
    copyNum integer not null,
    title varchar(150) not null,
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
    endDate date as (date_add(startDate, interval 1 day)),
    fulfilled boolean not null default false,
    expired boolean not null default false,
    foreign key (studentId) references Student (id),
    foreign key (bookId, copyNum) references Book (id, copyNum)
);