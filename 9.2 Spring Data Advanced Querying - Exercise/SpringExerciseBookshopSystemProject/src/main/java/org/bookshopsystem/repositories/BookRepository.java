package org.bookshopsystem.repositories;

import org.bookshopsystem.dto.books.BookReleaseDto;
import org.bookshopsystem.models.entities.Author;
import org.bookshopsystem.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedStoredProcedureQuery;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findAllByAgeRestriction(String ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(String type, Integer copies);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);
    List<Book> findAllByDateTimeBeforeOrDateTimeAfter(Date firstDate, Date secondDate);
    List<Book> findAllByDateTimeBefore(Date date);
    List<Book> findAllByTitleContainsIgnoreCase(String str);
    List<Book> findAllByAuthorLastNameStartsWithOrderByAuthorLastName(String beginning);
    @Query(value = "select count(b) FROM Book AS b WHERE length(b.title)>:length")
    Integer countAllByTitleLongerThan(@Param("length")int length);
    @Query(value = "SELECT new org.bookshopsystem.dto.books.BookReleaseDto(b.title, b.editionType, b.ageRestriction, b.price) FROM Book AS b WHERE b.title LIKE concat('%', :title, '%') ")
    BookReleaseDto findBookByTitleEquals(@Param("title") String title);
    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies+:number WHERE b.dateTime>:date")
    void updateBooksIncreaseCopies(@Param("number")Integer number, @Param("date")Date date);
    Integer countAllByDateTimeAfter(Date date);
    @Modifying
    Integer deleteBooksByCopiesLessThan(Integer number);
    @Procedure(name = "usp_total_books")
    Integer getTotalBooksOfAuthor(@Param("first_name") String first_name, @Param("last_name")String last_name);

}
