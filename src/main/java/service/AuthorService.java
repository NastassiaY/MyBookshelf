package service;

import model.Author;
import appDAO.AuthorDAOImpl;

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
}
