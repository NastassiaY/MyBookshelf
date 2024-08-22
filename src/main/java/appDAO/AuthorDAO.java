package appDAO;

import model.Author;

public interface AuthorDAO {
    void save(Author author);
    Author getByID(int id);
    void update(Author author);
    void delete(Author author);
}
