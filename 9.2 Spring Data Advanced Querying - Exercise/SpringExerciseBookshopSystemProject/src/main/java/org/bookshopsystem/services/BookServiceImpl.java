package org.bookshopsystem.services;

import org.bookshopsystem.dto.books.BookReleaseDto;
import org.bookshopsystem.models.entities.Book;
import org.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBookToDb(Book book) {
        this.bookRepository.saveAndFlush(book);
    }

    @Override
    public void getAll() {
        this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(String type, Integer copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(type, copies);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(low, high);
    }

    @Override
    public List<Book> findAllByDateTimeBeforeOrDateTimeAfter(Date firstDate, Date secondDate) {
        return this.bookRepository.findAllByDateTimeBeforeOrDateTimeAfter(firstDate, secondDate);
    }

    @Override
    public List<Book> findAllByDateTimeBefore(Date date) {
        return this.bookRepository.findAllByDateTimeBefore(date);
    }

    @Override
    public List<Book> findAllByTitleContainsIgnoreCase(String str) {
        return this.bookRepository.findAllByTitleContainsIgnoreCase(str);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartsWithOrderByAuthorLastName(String beginning) {
        return this.bookRepository.findAllByAuthorLastNameStartsWithOrderByAuthorLastName(beginning);
    }

    @Override
    public Integer countAllByTitleLongerThan(int length) {
        return this.bookRepository.countAllByTitleLongerThan(length);
    }

    @Override
    public BookReleaseDto findBookByTitleEquals(String title) {
        return this.bookRepository.findBookByTitleEquals(title);
    }

    @Override
    public void updateBooksIncreaseCopies(Integer number, Date date) {
        this.bookRepository.updateBooksIncreaseCopies(number, date);
    }

    @Override
    public Integer countAllByDateTimeAfter(Date date) {
        return this.bookRepository.countAllByDateTimeAfter(date);
    }

    @Override
    public Integer deleteBooksByCopiesLessThan(Integer number) {
        return this.bookRepository.deleteBooksByCopiesLessThan(number);
    }

    @Override
    public Integer getTotalBooksOfAuthor(String first_name, String last_name) {
        return this.bookRepository.getTotalBooksOfAuthor(first_name, last_name);
    }


}
