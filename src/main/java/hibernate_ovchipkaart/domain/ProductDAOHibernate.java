package hibernate_ovchipkaart.domain;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate implements ProductDAOH {
    private final SessionFactory sessionFactory;

    public ProductDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(ProductH product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(product);
            for (OV_Chipkaart ovChipkaart : product.getOvchipkaarten()) {
                session.saveOrUpdate(ovChipkaart);  // Zorg ervoor dat de relatie wordt opgeslagen
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(ProductH product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            for (OV_Chipkaart ovChipkaart : product.getOvchipkaarten()) {
                ovChipkaart.getProducten().remove(product);
                session.saveOrUpdate(ovChipkaart);
            }
            session.delete(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ProductH product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            for (OV_Chipkaart ovChipkaart : product.getOvchipkaarten()) {
                session.saveOrUpdate(ovChipkaart);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductH> findByOVChipkaart(OV_Chipkaart ovChipkaart) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ProductH> cq = cb.createQuery(ProductH.class);
            Root<ProductH> root = cq.from(ProductH.class);

            cq.select(root).where(cb.isMember(ovChipkaart, root.get("ovchipkaarten")));
            Query<ProductH> query = session.createQuery(cq);  // cq als parameter
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();  // Return lege lijst in geval van fout
        }
    }

    public List<ProductH> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder(); // Gebruik de juiste getCriteriaBuilder-methode
            CriteriaQuery<ProductH> cq = cb.createQuery(ProductH.class);
            Root<ProductH> root = cq.from(ProductH.class);
            cq.select(root);  // Stel de selectie in

            // Voer de query uit met de correcte createQuery-methode
            Query<ProductH> query = session.createQuery(cq);  // Gebruik cq als parameter, geen string
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();  // Return lege lijst in geval van fout
        }
    }
}
