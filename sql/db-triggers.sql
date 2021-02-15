USE db2_project;


-------------------------------------------------------------------------------------------
-- 

DELIMITER //
CREATE trigger onMarketingAnswerInsert
AFTER INSERT ON Answers
FOR EACH ROW
BEGIN
	insert into score (userId, marketQuid, points) values (new.userId, new.id, 1);
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
    
    insert into score (userId, statsQuid, points) values (new.userId, new.id, 2 * NUM);
END //

DELIMITER ;

