CREATE PROCEDURE usp_total_books(IN first_name VARCHAR(255), IN last_name VARCHAR(255), OUT number_of_books INT)
BEGIN
SET number_of_books=(SELECT
    COUNT(*) AS total_books
FROM
    books AS b
        JOIN
    authors AS a ON a.author_id = b.author_id
WHERE
    a.first_name = first_name
        AND a.last_name = last_name);
END;