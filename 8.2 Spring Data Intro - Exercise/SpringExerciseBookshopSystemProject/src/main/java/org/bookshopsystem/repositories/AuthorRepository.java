package org.bookshopsystem.repositories;

import org.bookshopsystem.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT a FROM Author as a WHERE a.authorId = :id")
    List<Author> findAuthorByAuthorId(@Param("id") Long id);

    @Query(value = "select a FROM Author AS a ORDER BY SIZE(a.books) DESC ")
    List<Author> findAllByBooksOrderByBooksSizeDesc();
}
