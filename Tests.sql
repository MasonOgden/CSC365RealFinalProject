insert into StudentInfo(sType, sName, numDays, maxNumBooks) values ('u', "undergraduate", 14, 3);
insert into StudentInfo(sType, sName, numDays, maxNumBooks) values ('g', "graduate", 7, 5);
select * from StudentInfo;

use group06;
show tables;

# Test insert statements
insert into Student (studentType, firstName, lastName) values ("u", "Mason", "Ogden"); # 1
insert into Student (studentType, firstName, lastName) values ("u", "Adonculitis", "Bird Boy"); # 2
insert into Student (studentType, firstName, lastName) values ("u", "Pokus", "Busheaux"); # 3
insert into Student (studentType, firstName, lastName) values ("u", "Joel", "Kreamples"); # 4
insert into Student (studentType, firstName, lastName) values ("u", "Rat", "Man"); # 5
insert into Student (studentType, firstName, lastName) values ("u", "Easy", "Moo"); # 6
insert into Student (studentType, firstName, lastName) values ("g", "Denny", "The God"); # 7
insert into Student (studentType, firstName, lastName) values ("u", "ur", "mum"); # 8
insert into Student (studentType, firstName, lastName) values ("u", "deez", "nutz"); # 9
insert into Student (studentType, firstName, lastName) values ("g", "Julie", "Workman"); # 10
insert into Student (studentType, firstName, lastName) values ("g", "Toshi", "Kuboi"); # 11
insert into Student (studentType, firstName, lastName) values ("g", "Matthew", "Carlton"); # 12
insert into Student (studentType, firstName, lastName) values ("g", "Kevin", "Ross"); # 13

# I want each book to be checked out at least once in each month

insert into Checkout (studentId, bookId, copyNum, startDate, dayReturned, dueBack) values 
	(1, 1, 1, "2019-01-01", null, "2019-01-15"),
    (1, 1, 2, "2019-01-01", null, "2019-01-15"),
    (1, 1, 3, "2019-01-01", null, "2019-01-15"),
    (2, 2, 1, "2019-02-01", null, "2019-02-15"),
    (2, 2, 2, "2019-02-01", null, "2019-02-15"),
    (2, 2, 3, "2019-02-01", null, "2019-02-15"),
    (3, 3, 1, "2019-03-01", null, "2019-03-15"),
    (3, 3, 2, "2019-03-01", null, "2019-03-15"),
    (3, 3, 3, "2019-03-01", null, "2019-03-15"),
    (4, 4, 1, "2019-04-01", null, "2019-04-15"),
    (4, 4, 2, "2019-04-01", null, "2019-04-15"),
    (4, 4, 3, "2019-04-01", null, "2019-04-15"),
    (5, 5, 1, "2019-05-01", null, "2019-05-15"),
    (5, 5, 2, "2019-05-01", null, "2019-05-15"),
    (5, 5, 3, "2019-05-01", null, "2019-05-15"),
    (6, 6, 1, "2019-06-01", null, "2019-06-15"),
    (6, 6, 2, "2019-06-01", null, "2019-06-15"),
    (6, 6, 3, "2019-06-01", null, "2019-06-15"),
    (7, 7, 1, "2019-07-01", null, "2019-07-15"),
    (7, 7, 2, "2019-07-01", null, "2019-07-15"),
    (7, 7, 3, "2019-07-01", null, "2019-07-15"),
    (8, 8, 1, "2019-08-01", null, "2019-08-15"),
    (8, 8, 2, "2019-08-01", null, "2019-08-15"),
    (8, 8, 3, "2019-08-01", null, "2019-08-15"),
    (9, 9, 1, "2019-09-01", null, "2019-09-15"),
    (9, 9, 2, "2019-09-01", null, "2019-09-15"),
    (9, 9, 3, "2019-09-01", null, "2019-09-15"),
    (10, 10, 1, "2019-01-01", null, "2019-01-15"),
    (10, 10, 2, "2019-02-01", null, "2019-02-15"),
    (10, 10, 3, "2019-03-01", null, "2019-03-15"),
    (11, 11, 1, "2019-04-01", null, "2019-04-15"),
    (11, 11, 2, "2019-05-01", null, "2019-05-15"),
    (11, 11, 3, "2019-06-01", null, "2019-06-15"),
    (12, 12, 1, "2019-07-01", null, "2019-07-15"),
    (12, 12, 2, "2019-08-01", null, "2019-08-15"),
    (12, 12, 3, "2019-09-01", null, "2019-09-15"),
    (13, 13, 1, "2019-10-01", null, "2019-10-15"),
    (13, 13, 2, "2019-11-01", null, "2019-11-15"),
    (13, 13, 3, "2019-12-01", null, "2019-12-15");




# Run triggers before inserting these
#insert into Reservation (studentId, bookId, copyNum, startDate) values (1, 1, 1, "2019-12-01");
#insert into Checkout (studentId, bookId, copyNum, startDate, dayReturned, dueBack) values (1, 1, 1, "2019-12-01", null, "2019-12-15");
#insert into Reservation (studentId, bookId, copyNum, startDate) values (2, 1, 1, "2019-12-02");
#insert into Reservation (studentId, bookId, copyNum, startDate) values (2, 1, 2, "2019-01-02");

UPDATE Checkout SET dueBack = 
	DATE_ADD(dueBack, INTERVAL 3 DAY), ddExtended = 1 
    WHERE studentId=1 
    AND bookId = 1
    AND copyNum = 1;

select * from Reservation;
select * from Checkout;
select * from Student;
select * from Book;

truncate table StudentInfo;
truncate table Checkout;
truncate table Reservation;
truncate table Student;
truncate table Book;

select id, Checkout.copyNum, title, author, category from Checkout join Book on dueBack < '2019-12-09' and dayReturned is null and id = bookId and Checkout.copyNum = Book.copyNum;

    
create temporary table if not exists usages2 
	select *, (January + February + March + April + May + June + July + 
		     August + September + October + November + December) as bookTotal 
	from (select bookId,
		max( if(monthNum = 1, numCheckouts, 0)) as January,
        max( if(monthNum = 2, numCheckouts, 0)) as February,
        max( if(monthNum = 3, numCheckouts, 0)) as March,
        max( if(monthNum = 4, numCheckouts, 0)) as April,
        max( if(monthNum = 5, numCheckouts, 0)) as May,
        max( if(monthNum = 6, numCheckouts, 0)) as June,
        max( if(monthNum = 7, numCheckouts, 0)) as July,
        max( if(monthNum = 8, numCheckouts, 0)) as August,
        max( if(monthNum = 9, numCheckouts, 0)) as September,
        max( if(monthNum = 10, numCheckouts, 0)) as October,
        max( if(monthNum = 11, numCheckouts, 0)) as November,
        max( if(monthNum = 12, numCheckouts, 0)) as December
    from (select bookId, month(startDate) as monthNum, count(*) as numCheckouts from Checkout
		  where year(startDate) = 2019
		  group by bookId, month(startDate)) as bookMonthlyCheckoutCounts
    group by bookId) as usages;
    
    
select * from usages2;
create temporary table if not exists usagesCopy select * from usages2;

select sum(January) as januaryTotal,
	   sum(February) as februaryTotal,
       sum(March) as marchTotal,
       sum(April) as aprilTotal,
       sum(May) as mayTotal,
       sum(June) as juneTotal,
       sum(July) as julyTotal,
       sum(August) as augustTotal,
       sum(September) as septemberTotal,
       sum(October) as octoberTotal,
       sum(November) as novemberTotal,
       sum(December) as decemberTotal
    from usages2;
    
insert into usages2 (bookId, January, February, March, April, May, June, July, August, September, October,
					 November, December) values 
	(null,
     (select sum(January) from usagesCopy),
     (select sum(February) from usagesCopy),
     (select sum(March) from usagesCopy), 
     (select sum(April) from usagesCopy),
     (select sum(May) from usagesCopy),
     (select sum(June) from usagesCopy),
     (select sum(July) from usagesCopy),
     (select sum(August) from usageCopy),
     (select sum(September) from usagesCopy),
     (select sum(October) from usagesCopy),
     (select sum(November) from usagesCopy),
     (select sum(December) from usagesCopy)
     );
     