import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private final SessionFactory sessionFactory;

    public OVChipkaartDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(OV_chipkaart ovChipkaart) {
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

    public boolean update(OV_chipkaart ovChipkaart) {
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

    public boolean delete(OV_chipkaart ovChipkaart) {
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

    public OV_chipkaart findByKaartNummer(int kaartNummer) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(OV_chipkaart.class, kaartNummer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OV_chipkaart> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM OV_chipkaart", OV_chipkaart.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OV_chipkaart> findByReiziger(Reiziger reiziger) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM OV_chipkaart WHERE reiziger = :reiziger", OV_chipkaart.class)
                    .setParameter("reiziger", reiziger)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
