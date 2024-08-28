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
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Optional<Author> findAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    @Transactional
    public void updateAuthor(Author author) {
        try {
            if (timfcb != null) {
                authorRepository.save(author);
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalStateException e) {
            System.out.println("The access to update is closed");
            e.printStackTrace();
        }
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