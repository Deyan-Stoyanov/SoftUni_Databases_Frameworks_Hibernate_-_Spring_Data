package org.bookshopsystem.services;

import org.bookshopsystem.dto.books.BookReleaseDto;
import org.bookshopsystem.models.entities.Book;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookService {
    void saveBookToDb(Book book);
    void getAll();
    List<Book> findAllByAgeRestriction(String ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(String type, Integer copies);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);
    List<Book> findAllByDateTimeBeforeOrDateTimeAfter(Date firstDate, Date secondDate);
    List<Book> findAllByDateTimeBefore(Date date);
    List<Book> findAllByTitleContainsIgnoreCase(String str);
    List<Book> findAllByAuthorLastNameStartsWithOrderByAuthorLastName(String beginning);
    Integer countAllByTitleLongerThan(@Param("length")int length);
    BookReleaseDto findBookByTitleEquals(String title);
    void updateBooksIncreaseCopies(@Param("number")Integer number, @Param("date")Date date);
    Integer countAllByDateTimeAfter(Date date);
    Integer deleteBooksByCopiesLessThan(Integer number);
    Integer getTotalBooksOfAuthor(@Param("first_name") String first_name, @Param("last_name")String last_name);
}

