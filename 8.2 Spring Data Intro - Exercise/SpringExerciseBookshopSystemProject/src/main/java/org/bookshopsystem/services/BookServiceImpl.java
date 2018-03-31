package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Book;
import org.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<Book> findAllByDateTimeAfter(Date date) {
        return this.bookRepository.findAllByDateTimeAfter(date);
    }

    @Override
    public List<Book> findAllByAuthorOrderByDateTimeDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorOrderByDateTimeDescTitleAsc(firstName, lastName);
    }

    @Override
    public Set<Book> findAllByDateTimeBefore(Date date) {
        return this.bookRepository.findAllByDateTimeBefore(date);
    }
}
