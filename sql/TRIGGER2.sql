DELIMITER //
CREATE trigger updateScores2
AFTER INSERT ON statisticanswers
FOR EACH ROW
BEGIN
	INSERT INTO score(userId,prodID,points)
    VALUES (new.userId,new.prodID,2);
END //

DELIMITER ;