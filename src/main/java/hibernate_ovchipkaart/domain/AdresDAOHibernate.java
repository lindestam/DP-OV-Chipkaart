package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;
public class AdresDAOHibernate implements AdresDAOH {
    private final SessionFactory sessionFactory;

    public AdresDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(AdresH adres) {
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


    public void update(AdresH adres) {
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


    public void delete(AdresH adres) {
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


    public AdresH findByReiziger(ReizigerH reiziger) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM AdresH WHERE reiziger = :reiziger", AdresH.class)
                    .setParameter("reiziger", reiziger)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AdresH> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM AdresH", AdresH.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
