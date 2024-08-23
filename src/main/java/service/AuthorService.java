package service;

import appDAO.BookDAOImpl;
import model.Author;
import appDAO.AuthorDAOImpl;
import model.Book;
import org.springframework.transaction.annotation.Transactional;

public class AuthorService {

    private AuthorDAOImpl authorDAO;

    public AuthorService(AuthorDAOImpl authorDAO)  {
        this.authorDAO = authorDAO;
    }

    public Author getAuthor(int id) {
        return authorDAO.getByID(id);
    }

    public void saveAuthor(Author author) {
        authorDAO.save(author);
    }

    public void deleteAuthor(Author author) {
        authorDAO.delete(author);
    }

    public void updateAuthor(Author author) {
        authorDAO.update(author);
    }

    @Transactional
    public void updateCountry(Author author, String country,
                              int shelfNumber, BookDAOImpl bookDAO) {
        author.setCountry(country);
        authorDAO.update(author);
        for (Book book : author.getBooks()) {
            book.setShelfNumber(shelfNumber);
            bookDAO.save(book);
        }
    }
}