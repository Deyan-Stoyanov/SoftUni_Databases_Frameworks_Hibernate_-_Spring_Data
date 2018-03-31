package org.bookshopsystem.repositories;

import org.bookshopsystem.models.entities.Author;
import org.bookshopsystem.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findAllByDateTimeAfter(Date date);

    @Query(value = "SELECT b FROM Book AS b WHERE b.author.firstName = :firstName AND b.author.lastName = :lastName")
    List<Book> findAllByAuthorOrderByDateTimeDescTitleAsc(@Param(value = "firstName")String firstName, @Param(value = "lastName")String lastName);

    Set<Book> findAllByDateTimeBefore(Date date);
}
