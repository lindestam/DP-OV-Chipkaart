package hibernate_ovchipkaart.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
            return session.createQuery("FROM OV_Chipkaart", OV_Chipkaart.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OV_Chipkaart> findByReiziger(ReizigerH r) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM OV_Chipkaart WHERE reiziger = :reiziger", OV_Chipkaart.class)
                    .setParameter("reiziger", r)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
