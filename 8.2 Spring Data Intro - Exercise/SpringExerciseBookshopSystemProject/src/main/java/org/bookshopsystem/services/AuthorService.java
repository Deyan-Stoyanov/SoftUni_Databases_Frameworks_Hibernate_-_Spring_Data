package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Author;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void saveAuthorToDb(Author author);
    List<Author> getAll();
    Optional<Author> getAuthorById(long id);
    List<Author> findAuthorByAuthorId(Long id);
    List<Author> findAllByBooksOrderByBooksSizeDesc();
}
