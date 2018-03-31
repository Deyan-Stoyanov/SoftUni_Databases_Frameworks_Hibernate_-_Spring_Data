package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Author;
import org.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthorToDb(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(long id){
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAuthorByAuthorId(Long id) {
        return  this.authorRepository.findAuthorByAuthorId(id);
    }

    @Override
    public List<Author> findAllByBooksOrderByBooksSizeDesc() {
        return this.authorRepository.findAllByBooksOrderByBooksSizeDesc();
    }
}
