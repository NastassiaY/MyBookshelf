package repository;

import model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author save(Author author);
    Author findByID(Long id);
    void delete(Author author);
}
