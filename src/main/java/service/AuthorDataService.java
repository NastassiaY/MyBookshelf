package service;

import configuration.ThisIsMyFirstConditionalBean;
import repository.AuthorRepository;
import repository.BookRepository;
import model.Author;
import model.Book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorDataService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ThisIsMyFirstConditionalBean timfcb;

    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author findAuthor(Long id) {
        return authorRepository.findByID(id);
    }

    @Transactional
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    @Transactional
    public void updateAuthor(Author author) {
        try {
            if (timfcb.isUpdateAuthorised()) {
                authorRepository.save(author);
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalStateException e) {
            System.out.println("The access to update is closed");
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