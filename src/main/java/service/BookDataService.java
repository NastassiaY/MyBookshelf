package service;

import configuration.ThisIsMyFirstConditionalBean;
import model.Book;
import repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDataService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ThisIsMyFirstConditionalBean timfcb;

    public Book findBook(Long id) {
        return bookRepository.findByID(id);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    public void updateBook(Book book) {
        try {
            if (timfcb.isUpdateAuthorised()) {
            bookRepository.save(book);
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalStateException e) {
            System.out.println("The access to update is closed");
        }
    }
}
