package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sd.project.dp_ovchipkaart.domain.adres.Adres;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;


import java.util.List;
public class AdresDAOHibernate implements adresDAO {
    private final SessionFactory sessionFactory;

    public AdresDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(adres adres) {
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
    public void update(adres adres) {
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
    public void delete(adres adres) {
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
    public adres findByReiziger(reiziger reiziger) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres WHERE reiziger = :reiziger", adres.class)
                    .setParameter("reiziger", reiziger)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Adres adres) {

    }

    @Override
    public void delete(Adres adres) {

    }

    @Override
    public void update(Adres adres) {

    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<adres> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres", adres.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
