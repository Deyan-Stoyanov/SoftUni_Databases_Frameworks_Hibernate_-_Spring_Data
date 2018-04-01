package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Author;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorService {
    void saveAuthorToDb(Author author);
    List<Author> getAll();
    Optional<Author> getAuthorById(long id);
    List<Author> findAllByFirstNameEndsWith(String ending);
    List<Object[]> findAllByBooksCopiesOrderByCopiesDesc();
}
