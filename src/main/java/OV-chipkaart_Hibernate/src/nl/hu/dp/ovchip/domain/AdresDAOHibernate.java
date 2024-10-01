import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
public class AdresDAOHibernate implements AdresDAO {
    private final SessionFactory sessionFactory;

    public AdresDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(adres);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(adres);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(adres);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres WHERE reiziger = :reiziger", Adres.class)
                    .setParameter("reiziger", reiziger)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres", Adres.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
