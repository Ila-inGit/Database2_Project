USE db2_project;

-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE trigger updateScores
AFTER INSERT ON Answers
FOR EACH ROW
BEGIN
	DECLARE V INTEGER;
	SELECT DISTINCT Q.prodId into V
	FROM Answers as A JOIN Questions as Q ON A.questionId = Q.id
	WHERE Q.id = new.questionId;

	INSERT INTO score(userId,prodId,points)
    VALUES (new.userId,V,1);
END //

DELIMITER ;


-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE trigger updateScores2
AFTER INSERT ON statisticanswers
FOR EACH ROW
BEGIN
	INSERT INTO score(userId,prodId,points)
    VALUES (new.userId,new.prodId,2);
END //

DELIMITER ;

-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE TRIGGER answers_AFTER_DELETE AFTER DELETE ON answers FOR EACH ROW BEGIN
	DECLARE V INTEGER;
	SELECT DISTINCT Q.prodId into V
	FROM answers as A JOIN questions as Q ON A.questionId = Q.id
	WHERE Q.id = old.questionId;
    
    DELETE FROM score as S
    WHERE S.userId = old.userId and S.prodId = V and S.points = 1
    ORDER BY S.userId, S.prodId DESC
    LIMIT 1;
END //
DELIMITER ;

-------------------------------------------------------------------------------------------
-- 


DELIMITER //
CREATE  TRIGGER statisticanswers_AFTER_DELETE AFTER DELETE ON statisticanswers FOR EACH ROW BEGIN
	DELETE FROM score as S
    WHERE S.userId = old.userId and S.prodId = old.prodId and S.points = 2
    ORDER BY S.userId, S.prodId DESC
    LIMIT 1;
END //

DELIMITER ;
