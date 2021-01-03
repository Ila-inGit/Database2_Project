DELIMITER \\
CREATE DEFINER=`root`@`localhost` TRIGGER `answers_AFTER_DELETE` AFTER DELETE ON `answers` FOR EACH ROW BEGIN
	DECLARE V INTEGER;
	SELECT DISTINCT Q.prodId into V
	FROM answers as A JOIN questions as Q ON A.quetionID = Q.id
	WHERE Q.id = old.quetionID;
    
    DELETE FROM score as S
    WHERE S.userId = old.userId and S.prodID = V and S.points = 1
    ORDER BY S.userId, S.prodID DESC
    LIMIT 1;
END
DELIMITER ;