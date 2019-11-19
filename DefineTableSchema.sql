create table if not exists Book (
	id integer auto_increment primary key,
    image_link varchar(100),
    title varchar(60) not null,
    author varchar(40) not null,
    numCopies int not null,
    category varchar(35) not null
);

create table if not exists Student (
	gradStudent boolean not null default false,
    id int auto_increment primary key,
    firstName varchar(15) not null,
    lastName varchar(25) not null
);