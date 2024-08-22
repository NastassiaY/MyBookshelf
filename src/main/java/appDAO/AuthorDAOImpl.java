package appDAO;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import model.Author;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:application.properties")
public class AuthorDAOImpl implements AuthorDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${author.update_access}")
    private String updateAccess;

    public AuthorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(author);
        tx1.commit();
        session.close();
    }

    @Override
    public Author getByID(int id) {
        return sessionFactory.openSession().get(Author.class, id);
    }

    @Override
    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(author);
        tx1.commit();
        session.close();
    }

    @Transactional
    public void updateCountry(Author author, String country, int shelfNumber) {
        try {
            if (updateAccess.equals("ON")) {
                author.setCountry(country);
                Session session = sessionFactory.openSession();
                session.merge(author);
                for (Book book : author.getBooks()) {
                    book.setShelfNumber(shelfNumber);
                    session.merge(book);
                }
            } else if (updateAccess.equals("OFF")) {
                throw new IllegalStateException();
            }
        } catch(IllegalStateException e) {
            System.out.println("The access to update is denied");
        }
    }

    @Override
    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(author);
        tx1.commit();
        session.close();
    }
}
