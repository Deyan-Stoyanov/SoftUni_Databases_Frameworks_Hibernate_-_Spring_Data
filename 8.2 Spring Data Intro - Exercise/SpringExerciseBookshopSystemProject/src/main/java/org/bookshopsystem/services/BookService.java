package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Book;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookService {
    void saveBookToDb(Book book);
    void getAll();
    List<Book> findAllByDateTimeAfter(Date date);
    List<Book> findAllByAuthorOrderByDateTimeDescTitleAsc(String firstName, String lastName);
    Set<Book> findAllByDateTimeBefore(Date date);
}

