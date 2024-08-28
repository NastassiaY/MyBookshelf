package repository;

import model.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(Book book);
    Book findByID(Long id);
    void delete(Book book);
}
