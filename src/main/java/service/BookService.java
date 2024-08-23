package service;

import model.Book;
import appDAO.BookDAOImpl;

public class BookService {

    private BookDAOImpl bookDAO;

    public BookService(BookDAOImpl bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book getBook(int id) {
        return bookDAO.getByID(id);
    }

    public void saveBook(Book book) {
        bookDAO.save(book);
    }

    public void deleteBook(Book book) {
        bookDAO.delete(book);
    }

    public void updateBook(Book book) {
        bookDAO.update(book);
    }
}
