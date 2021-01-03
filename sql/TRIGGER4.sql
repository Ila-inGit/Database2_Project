DELIMITER \\
CREATE DEFINER=`root`@`localhost` TRIGGER `statisticanswers_AFTER_DELETE` AFTER DELETE ON `statisticanswers` FOR EACH ROW BEGIN
	DELETE FROM score as S
    WHERE S.userId = old.userId and S.prodID = old.prodID and S.points = 2
    ORDER BY S.userId, S.prodID DESC
    LIMIT 1;
END

DELIMITER ;