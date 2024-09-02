package service;

import configuration.ThisIsMyFirstConditionalBean;
import repository.AuthorRepository;
import repository.BookRepository;
import model.Author;
import model.Book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ThisIsMyFirstConditionalBean timfcb;

    @Transactional
    public Author saveAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("The object can't be null");
        }

        if (findAuthor(author.getId()).isPresent())
        {
            throw new IllegalArgumentException
                    ("The object with Id " + author.getId() + " already exists in database");
        }

        authorRepository.save(author);

        return author;
    }

    public Optional<Author> findAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public void deleteAuthor(Author author) {
        if (findAuthor(author.getId()).isEmpty())
        {
            throw new IllegalArgumentException
                    ("No object with Id " + author.getId() + " in database");
        }

        authorRepository.delete(author);
    }

    @Transactional
    public void updateAuthor(Author author) {
        if (timfcb == null) {
            return;
        }

        if (findAuthor(author.getId()).isEmpty()) {
            throw new IllegalArgumentException
                    ("No object with Id " + author.getId() + " in database");
        }
        
        authorRepository.save(author);
    }

    @Transactional
    public void updateCountry(Author author, String country,
                              int shelfNumber, BookRepository bookRepository) {
        author.setCountry(country);
        this.updateAuthor(author);
        for (Book book : author.getBooks()) {
            book.setShelfNumber(shelfNumber);
            bookRepository.save(book);
        }
    }
}