USE db2_project;

-------------------------------------------------------------------------------------------
-- Intitialize score to 0 for new registered users

DELIMITER //
CREATE trigger onUserRegister
AFTER INSERT ON Users
FOR EACH ROW
BEGIN
	INSERT INTO score(userId, points)
    VALUES (new.id, 0);
END //

DELIMITER ;

-------------------------------------------------------------------------------------------
-- 

DELIMITER //
CREATE trigger onMarketingAnswerInsert
AFTER INSERT ON Answers
FOR EACH ROW
BEGIN
	update score set points = points + 1 where userId = new.userId;
END //

DELIMITER ;


-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE trigger onStatisticAnswerInsert
AFTER INSERT ON statisticanswers
FOR EACH ROW
BEGIN
    DECLARE NUM INTEGER DEFAULT 0;
    
	IF new.gender IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	IF new.age IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	IF new.expLvl IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	update score set points = points + 2 * NUM where userId = new.userId;
END //

DELIMITER ;

-------------------------------------------------------------------------------------------
-- 



DELIMITER //
CREATE TRIGGER onQuestionDelete
BEFORE DELETE ON Questions
FOR EACH ROW
BEGIN
	delete from answers where questionId = old.id;
END //
DELIMITER ;

-------------------------------------------------------------------------------------------
-- 



DELIMITER //
CREATE TRIGGER onMarketingAnswerDelete
AFTER DELETE ON answers 
FOR EACH ROW
BEGIN
	update score set points = points - 1 where userId = old.userId;
END //
DELIMITER ;

-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE TRIGGER onStatisticAnswerDelete
AFTER DELETE ON statisticanswers 
FOR EACH ROW 
BEGIN
    DECLARE NUM INTEGER DEFAULT 0;
    
	IF old.gender IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	IF old.age IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	IF old.expLvl IS NOT NULL THEN
		set NUM := NUM + 1;
    END IF;
    
	update score set points = points - 2 * NUM where userId = old.userId;
END //

DELIMITER ;
