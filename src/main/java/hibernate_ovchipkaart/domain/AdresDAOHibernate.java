package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<AdresH> cq = cb.createQuery(AdresH.class);
            Root<AdresH> root = cq.from(AdresH.class);

            // Voeg de conditie toe voor de reiziger
            cq.select(root).where(cb.equal(root.get("reiziger"), reiziger));

            Query<AdresH> query = session.createQuery(cq.toString()); // Corrigeer dit deel
            return query.getSingleResult(); // Gebruik getSingleResult() als er maar één adres wordt verwacht
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<AdresH> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<AdresH> cq = cb.createQuery(AdresH.class);
            Root<AdresH> root = cq.from(AdresH.class);
            cq.select(root);

            Query<AdresH> query = session.createQuery(cq.toString());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
