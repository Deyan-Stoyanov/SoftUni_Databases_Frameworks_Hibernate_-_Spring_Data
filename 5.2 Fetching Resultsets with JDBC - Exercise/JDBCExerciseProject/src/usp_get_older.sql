CREATE PROCEDURE usp_get_older(minion_id INT)
  BEGIN
    START TRANSACTION;
    IF minion_id NOT IN (SELECT m.id FROM minions AS m)
    THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid Id';
      ROLLBACK;
    ELSE UPDATE minions AS m SET m.age = m.age + 1 WHERE m.id = minion_id;
      COMMIT;
    END IF;
  END;