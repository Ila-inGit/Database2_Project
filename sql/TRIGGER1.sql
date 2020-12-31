DELIMITER //
CREATE trigger updateScores
AFTER INSERT ON answers
FOR EACH ROW
BEGIN
	DECLARE V INTEGER;
	SELECT DISTINCT Q.prodId into V
	FROM aswer as A JOIN questions as Q ON A.questionID = Q.id
	WHERE Q.id = new.questionID;

	INSERT INTO score(userId,prodID,points)
    VALUES (new.userId,V,1);
END //

DELIMITER ;



