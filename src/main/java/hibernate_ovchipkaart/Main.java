package hibernate_ovchipkaart;

import hibernate_ovchipkaart.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {

    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;



    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        AdresDAOHibernate adresDAO = new AdresDAOHibernate(factory);
        ReizigerDAOHibernate reizigerDAO = new ReizigerDAOHibernate(factory);
        OVChipkaartDAOHibernate ovChipkaartDAO = new OVChipkaartDAOHibernate(factory);
        ProductDAOHibernate productDAO = new ProductDAOHibernate(factory);

        testAdresDAO(adresDAO, reizigerDAO); // Test adresDAO
        testOVChipkaartDAO(ovChipkaartDAO, reizigerDAO); // Test OVChipkaartDAO
        testProduct(ovChipkaartDAO, productDAO);// OVChipkaartDAO

        factory.close();  // Sluit de factory aan het einde van de applicatie
    }

    private static void testAdresDAO(AdresDAOH adresDAO, ReizigerDAOH reizigerDAO) {
        System.out.println("\n---------- Test AdresDAO -------------");
        List<ReizigerH> reizigers = reizigerDAO.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (ReizigerH r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        ReizigerH sietske = new ReizigerH(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        reizigerDAO.save(sietske);
        reizigers = reizigerDAO.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Maak een adres aan en koppel het aan de reiziger
        AdresH a = new AdresH();
        a.setPostcode("1234AB");
        a.setHuisnummer("1");
        a.setStraat("Hoofdstraat");
        a.setWoonplaats("Amsterdam");
        sietske.setAdres(a);

        // Sla de reiziger (met adres) op
        reizigerDAO.save(sietske);
        System.out.println("Reiziger met adres opgeslagen: " + sietske);

        // Haal het adres op via de reiziger
        AdresH gevondenAdres = adresDAO.findByReiziger(sietske);
        System.out.println("Gevonden adres: " + gevondenAdres);

        // Update het adres
        gevondenAdres.setWoonplaats("Utrecht");
        adresDAO.update(gevondenAdres);
        System.out.println("Adres geüpdatet: " + gevondenAdres);

        // Verwijder het adres
        adresDAO.delete(gevondenAdres);
        System.out.println("Adres verwijderd. Huidige adressen: " + adresDAO.findAll());
    }

    private static void testOVChipkaartDAO(OV_ChipkaartDAO ovChipkaartDAO, ReizigerDAOH reizigerDAO) throws SQLException {
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        // Haal reizigers op
        List<ReizigerH> reizigers = reizigerDAO.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (ReizigerH r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Voeg een nieuwe reiziger toe
        String gbdatum = "1990-06-21";
        ReizigerH jan = new ReizigerH(88, "J", "", "Jansen", java.sql.Date.valueOf(gbdatum));
        reizigerDAO.save(jan);

        // Maak een OV-chipkaart aan voor Jan
        OV_Chipkaart ovChipkaart = new OV_Chipkaart(12345, java.sql.Date.valueOf("2025-12-31"), 2, 50.0, jan);
        ovChipkaartDAO.save(ovChipkaart);

        System.out.println("OV-chipkaart opgeslagen: " + ovChipkaart);

        // Haal OV-chipkaarten op via de reiziger
        List<OV_Chipkaart> ovChipkaarten = ovChipkaartDAO.findByReiziger(jan);
        System.out.println("OV-chipkaarten van Jan: " + ovChipkaarten);

        // Update de OV-chipkaart
        ovChipkaart.setSaldo(100.0);
        ovChipkaartDAO.update(ovChipkaart);
        System.out.println("OV-chipkaart geüpdatet: " + ovChipkaart);

        // Verwijder de OV-chipkaart
        ovChipkaartDAO.delete(ovChipkaart);
        System.out.println("OV-chipkaart verwijderd. Huidige OV-chipkaarten: " + ovChipkaartDAO.findAll());
    }
    private static void testProduct(OV_ChipkaartDAO ovChipkaartDAO, ProductDAOH productDAO) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");
        List<ProductH> products = productDAO.findAll();
        for (ProductH p : products) {
            System.out.println(p);
        }

        // Maak een nieuwe reiziger aan
        String gbdatum = "1990-06-21";
        ReizigerH reiziger = new ReizigerH(88, "J", "", "Jansen", java.sql.Date.valueOf(gbdatum));
        OV_Chipkaart ovChipkaart = new OV_Chipkaart(88, java.sql.Date.valueOf(gbdatum), 10, 20.00, reiziger);
        ovChipkaartDAO.save(ovChipkaart);

        // Maak een product aan
        ProductH product = new ProductH("Beschrijving", "ProductNaam", 12345, 19.99);

        // Voeg de OV-chipkaart toe aan het product
        product.voegOVChipkaartToe(ovChipkaart);

        // Sla het product op
        productDAO.save(product);
        System.out.println("Product opgeslagen: " + product);

        // Test het ophalen van producten geassocieerd met de OV

    }
}

