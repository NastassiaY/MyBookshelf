package appDAO;

import jakarta.transaction.Transactional;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO{

    @Autowired
    private SessionFactory sessionFactory;

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
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(book);
        tx1.commit();
        session.close();
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
