create table if not exists Book (
	id integer auto_increment primary key,
    title varchar(60) not null,
    author varchar(40) not null,
    numCopies int not null,
    category varchar(35) not null
);

create table if not exists Student (
	studentType char(1) not null,
    id int auto_increment primary key,
    firstName varchar(15) not null,
    lastName varchar(25) not null
);

drop table Book;
drop table Student;

# Test insert statements
insert into Book (title, author, numCopies, category) values ("test title", "test author", 3, "fiction");
insert into Student (studentType, firstName, lastName) values ("u", "Mason", "Ogden");