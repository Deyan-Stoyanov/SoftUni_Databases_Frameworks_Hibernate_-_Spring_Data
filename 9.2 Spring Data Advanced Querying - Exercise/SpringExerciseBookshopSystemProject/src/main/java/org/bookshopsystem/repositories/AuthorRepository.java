package org.bookshopsystem.repositories;

import org.bookshopsystem.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstNameEndsWith(String ending);
    @Query(value = "SELECT \n" +
            "    concat(a.firstName, ' ', a.lastName) AS fullName, sum(b.copies) AS copies_count\n" +
            "FROM\n" +
            "    Author AS a\n" +
            "        INNER JOIN\n" +
            "    a.books AS b\n" +
            "GROUP BY a.authorId\n" +
            "ORDER BY copies_count DESC")
    List<Object[]> findAllByBooksCopiesOrderByCopiesDesc();
}
