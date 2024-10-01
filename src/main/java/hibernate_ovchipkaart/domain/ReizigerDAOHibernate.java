package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAOH {

    private final SessionFactory sessionFactory;

    public ReizigerDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(ReizigerH reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ReizigerH reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ReizigerH reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ReizigerH findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ReizigerH.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReizigerH> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ReizigerH", ReizigerH.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
