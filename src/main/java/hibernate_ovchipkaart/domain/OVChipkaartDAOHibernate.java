package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OVChipkaartDAOHibernate implements OV_ChipkaartDAO {
    private final SessionFactory sessionFactory;

    public OVChipkaartDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(OV_Chipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(OV_Chipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(OV_Chipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public OV_Chipkaart findByKaartNummer(int kaartNummer) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(OV_Chipkaart.class, kaartNummer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OV_Chipkaart> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<OV_Chipkaart> cq = cb.createQuery(OV_Chipkaart.class);
            Root<OV_Chipkaart> root = cq.from(OV_Chipkaart.class);
            cq.select(root);

            Query<OV_Chipkaart> query = session.createQuery(cq.toString());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OV_Chipkaart> findByReiziger(ReizigerH reiziger) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<OV_Chipkaart> cq = cb.createQuery(OV_Chipkaart.class);
            Root<OV_Chipkaart> root = cq.from(OV_Chipkaart.class);

            cq.select(root).where(cb.equal(root.get("reiziger"), reiziger));
            Query<OV_Chipkaart> query = session.createQuery(cq.toString());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

