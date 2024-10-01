import nl.hu.dp.ovchip.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

        testAdresDAO(adresDAO, reizigerDAO); // Test adresDAO
        testOVChipkaartDAO(ovChipkaartDAO, reizigerDAO); // Test OVChipkaartDAO

        factory.close();  // Sluit de factory aan het einde van de applicatie
    }

    private static void testAdresDAO(AdresDAO adresDAO, ReizigerDAO reizigerDAO) {
        System.out.println("\n---------- Test AdresDAO -------------");
        List<Reiziger> reizigers = reizigerDAO.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        reizigerDAO.save(sietske);
        reizigers = reizigerDAO.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Maak een adres aan en koppel het aan de reiziger
        Adres adres = new Adres();
        adres.setPostcode("1234AB");
        adres.setHuisnummer("1");
        adres.setStraat("Hoofdstraat");
        adres.setWoonplaats("Amsterdam");
        sietske.setAdres(adres);

        // Sla de reiziger (met adres) op
        reizigerDAO.save(sietske);
        System.out.println("Reiziger met adres opgeslagen: " + sietske);

        // Haal het adres op via de reiziger
        Adres gevondenAdres = adresDAO.findByReiziger(sietske);
        System.out.println("Gevonden adres: " + gevondenAdres);

        // Update het adres
        gevondenAdres.setWoonplaats("Utrecht");
        adresDAO.update(gevondenAdres);
        System.out.println("Adres geüpdatet: " + gevondenAdres);

        // Verwijder het adres
        adresDAO.delete(gevondenAdres);
        System.out.println("Adres verwijderd. Huidige adressen: " + adresDAO.findAll());
    }

    private static void testOVChipkaartDAO(OVChipkaartDAO ovChipkaartDAO, ReizigerDAO reizigerDAO) throws SQLException {
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        // Haal reizigers op
        List<Reiziger> reizigers = reizigerDAO.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Voeg een nieuwe reiziger toe
        String gbdatum = "1990-06-21";
        Reiziger jan = new Reiziger(88, "J", "", "Jansen", java.sql.Date.valueOf(gbdatum));
        reizigerDAO.save(jan);

        // Maak een OV-chipkaart aan voor Jan
        OV_chipkaart ovChipkaart = new OV_chipkaart(12345, java.sql.Date.valueOf("2025-12-31"), 2, 50.0, jan);
        ovChipkaartDAO.save(ovChipkaart);

        System.out.println("OV-chipkaart opgeslagen: " + ovChipkaart);

        // Haal OV-chipkaarten op via de reiziger
        List<OV_chipkaart> ovChipkaarten = ovChipkaartDAO.findByReiziger(jan);
        System.out.println("OV-chipkaarten van Jan: " + ovChipkaarten);

        // Update de OV-chipkaart
        ovChipkaart.setSaldo(100.0);
        ovChipkaartDAO.update(ovChipkaart);
        System.out.println("OV-chipkaart geüpdatet: " + ovChipkaart);

        // Verwijder de OV-chipkaart
        ovChipkaartDAO.delete(ovChipkaart);
        System.out.println("OV-chipkaart verwijderd. Huidige OV-chipkaarten: " + ovChipkaartDAO.findAll());
    }
}

