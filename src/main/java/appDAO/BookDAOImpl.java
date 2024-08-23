package appDAO;

import jakarta.transaction.Transactional;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${author.update_access}")
    private boolean updateAccess;

    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(book);
        tx1.commit();
        session.close();
    }

    @Override
    public Book getByID(int id) {
        return sessionFactory.openSession().get(Book.class, id);
    }

    @Override
    public void update(Book book) {
        try {
            if(updateAccess) {
                Session session = sessionFactory.openSession();
                Transaction tx1 = session.beginTransaction();
                session.merge(book);
                tx1.commit();
                session.close();
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalStateException e) {
            System.out.println("The access to update is closed");
        }
    }

    @Override
    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(book);
        tx1.commit();
        session.close();
    }
}
