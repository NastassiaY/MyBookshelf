package appDAO;

import model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

@Repository
@PropertySource("classpath:application.properties")
public class AuthorDAOImpl implements AuthorDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${author.update_access}")
    private boolean updateAccess;

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
        try {
            if(updateAccess) {
                Session session = sessionFactory.openSession();
                Transaction tx1 = session.beginTransaction();
                session.merge(author);
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
    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(author);
        tx1.commit();
        session.close();
    }

}
