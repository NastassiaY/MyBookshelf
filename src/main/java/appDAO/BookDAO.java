package appDAO;

import model.Book;

public interface BookDAO {
    void save(Book book);
    Book getByID(int id);
    void update(Book book);
    void delete(Book book);
}
