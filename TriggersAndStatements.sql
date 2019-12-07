DELIMITER $
CREATE TRIGGER `check_student_books_and_availability_checkout` BEFORE
	INSERT ON `Checkout`
    # Need to check that student is allowed to check out this book
    # Then need to check that it's not reserved
    FOR EACH ROW
    BEGIN
		# Finding how many books the student already has checked out. 
		SET @studentNumCheckedOut = (SELECT count(*) 
									 FROM Checkout c 
                                     WHERE c.studentId = NEW.studentId
                                     AND dayReturned = NULL); # If it hasn't been returned
		SET @studentNumReserved = (SELECT count(*)
								   FROM Reservation r
                                   WHERE r.studentId = NEW.studentId
                                   AND r.fulfilled = false);
		SET @isReserved = (SELECT count(*)
						   FROM Reservation r
                           WHERE r.bookId = NEW.bookId
                           AND r.copyNum = NEW.copyNum
                           AND r.fulfilled = false
                           AND expired = false);
		SET @isReservedByMe = (SELECT count(*)
							   FROM Reservation r
                               WHERE r.studentId = NEW.studentId
                               AND r.bookId = NEW.bookId
                               AND r.copyNum = NEW.copyNum
                               AND r.fulfilled = false
                               AND expired = false);
		# Number of reservations + Number of checkouts should not exceed the max
        # number defined in the StudentInfo table. 
        IF  @studentNumCheckedOut + @studentNumReserved + 1 > (SELECT maxNumBooks 
										 FROM StudentInfo
										 WHERE sType = 
											(SELECT studentType
                                             FROM Student s 
                                             WHERE s.id = NEW.studentId))
		THEN
			signal sqlstate '12345' set message_text = "Too many books checked out or reserved already";
		END IF;
        IF @isReserved > 1 AND @isReservedByMe = 0
        THEN
			signal sqlstate '12345' set message_text = "This copy is reserved";
		END IF;
	END$
DELIMITER ;

DELIMITER $
CREATE TRIGGER `check_out_a_book` AFTER
    INSERT ON `Checkout`
    FOR EACH ROW
    BEGIN
		# Check if this checkout is associated with a reservation:
        IF (SELECT count(*) 
			FROM Reservation 
            WHERE bookId = NEW.bookId
			AND copyNum = NEW.copyNum
			AND studentId  = NEW.studentId
			AND fulfilled = false
			AND expired = false) > 0
        THEN
			# If it is, then set that reservation to fulfilled
			UPDATE Reservation SET fulfilled = true 
				WHERE bookId = NEW.bookId
                AND copyNum = NEW.copyNum
				AND studentId = NEW.studentId
                AND expired = false;
		END IF;
    END$
DELIMITER ;

DELIMITER $
CREATE TRIGGER `check_student_books_and_availability_reservation` BEFORE
	INSERT ON `Reservation`
    FOR EACH ROW
    BEGIN
		# Finding how many books the student already has checked out. 
		SET @studentNumCheckedOut = (SELECT count(*) 
									 FROM Checkout c 
                                     WHERE c.studentId = NEW.studentId
                                     AND dayReturned = NULL); # If it hasn't been returned
		# Finding out how many books the student already has reserved.
		SET @studentNumReserved = (SELECT count(*)
								   FROM Reservation r
                                   WHERE r.studentId = NEW.studentId
                                   AND r.fulfilled = false
                                   AND r.expired = false);
		# Finding out how many copies of the book are available to be reserved
		SET @isReserved = (SELECT count(*)
						 FROM Reservation r
                         WHERE r.bookId = NEW.bookId
                         AND r.copyNum = NEW.copyNum
                         AND r.fulfilled = false
                         AND expired = false);
		# Number of reservations + Number of checkouts should not exceed the max. 
        IF  @studentNumCheckedOut + @studentNumReserved + 1 > (SELECT maxNumBooks 
										 FROM StudentInfo
										 WHERE sType = 
											(SELECT studentType
                                             FROM Student s 
                                             WHERE s.id = NEW.studentId))
		THEN
			signal sqlstate '12345' set message_text = "Too many books checked out or reserved already";
		END IF;
        IF @isReserved > 0
        THEN
			signal sqlstate '12345' set message_text = "Copy is already reserved";
		END IF;
	END$
DELIMITER ;

DELIMITER $
CREATE TRIGGER `check_reserved_before_extension` BEFORE
	UPDATE ON `Checkout`
    FOR EACH ROW
    BEGIN
		# Before I extend a due date, I need to check if someone has a reservation on that
        # book. If they do, I can't extend the due date. 
        SET @isReserved = (SELECT count(*)
						 FROM Reservation r
                         WHERE r.bookId = NEW.bookId
                         AND r.copyNum = NEW.copyNum
                         AND r.fulfilled = false
                         AND expired = false);
		IF @isReserved > 0
        THEN 
			signal sqlstate '12345' set message_text = "Cannot extend because someone has a reservation on this book";
		END IF;
	END$
DELIMITER ;