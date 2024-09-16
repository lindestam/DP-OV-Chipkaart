import sd.project.dp_ovchipkaart.domain.adres.Adres;
import sd.project.dp_ovchipkaart.domain.adres.AdresDAO;
import sd.project.dp_ovchipkaart.domain.adres.AdresDAOPsql;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;
import sd.project.dp_ovchipkaart.domain.reiziger.ReizigerDAO;
import sd.project.dp_ovchipkaart.domain.reiziger.ReizigerDAOPsql;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Maak een verbinding met de database
            Connection myCon = DriverManager.getConnection("jdbc:postgresql://localhost:5433/ovchip", "postgres", "lindestam");

            // Maak de DAO-objecten aan
            AdresDAOPsql adresDAO = new AdresDAOPsql(myCon);
            ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(myCon, adresDAO);

            // Test de DAO-methoden
            testReizigerDAO(reizigerDAO, adresDAO);

            // SQL-query om reizigers en hun adressen op te halen met een LEFT JOIN
            String query = "SELECT r.voorletters, r.tussenvoegsel, r.achternaam, r.geboortedatum, " +
                    "a.adres_id, a.postcode, a.huisnummer, a.straat, a.woonplaats " +
                    "FROM reiziger r " +
                    "LEFT JOIN adres a ON r.reiziger_id = a.reiziger_id";  // LEFT JOIN zodat reizigers zonder adres ook worden opgehaald

            // Voer de query uit
            Statement stmt = myCon.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Alle reizigers en hun adressen:\n");
            while (rs.next()) {
                // Haal reiziger gegevens op
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                java.sql.Date geboortedatum = rs.getDate("geboortedatum");

                // Haal adresgegevens op (misschien null als de reiziger geen adres heeft)
                int adresId = rs.getInt("adres_id");
                String postcode = rs.getString("postcode");
                String huisnummer = rs.getString("huisnummer");
                String straat = rs.getString("straat");
                String woonplaats = rs.getString("woonplaats");

                // Toon de reiziger en het adres
                System.out.print(voorletters + ". " + (tussenvoegsel != null ? tussenvoegsel + " " : "") + achternaam +
                        " (" + geboortedatum + ")");

                if (postcode != null) {  // Controleer of er een adres is
                    System.out.println(" - Adres: " + straat + " " + huisnummer + ", " + postcode + ", " + woonplaats);
                } else {
                    System.out.println(" - Geen adres gevonden.");
                }
            }

            // Sluit de resources correct
            rs.close();
            stmt.close();
            myCon.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Test alle CRUD-methoden van de ReizigerDAO
    private static void testReizigerDAO(ReizigerDAO rdao, AdresDAO adao) throws Exception {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en sla deze op in de database
        java.sql.Date gbdatum = java.sql.Date.valueOf("2000-03-14");
        Reiziger sietske = new Reiziger(8, "K", "van", "Janne", gbdatum);
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        boolean saved = rdao.save(sietske);
        if (saved) {
            reizigers = rdao.findAll();
            System.out.println(reizigers.size() + " reizigers (na toevoeging van Sietske)\n");
        } else {
            System.out.println("De reiziger kon niet worden opgeslagen.\n");
            return;  // Stop verdere tests als de reiziger niet is opgeslagen
        }

        // Test het updaten van een reiziger
        System.out.println("[Test] ReizigerDAO.update() - Wijzig de achternaam van Sietske naar 'Janssen'.");
        sietske.setAchternaam("Janssen");
        boolean updated = rdao.update(sietske);
        if (updated) {
            Reiziger updatedReiziger = rdao.findById(8);  // Het ID van de toegevoegde reiziger
            System.out.println("[Test] Geüpdatete reiziger: " + updatedReiziger + "\n");
        } else {
            System.out.println("[Test] Het updaten van de reiziger is mislukt.\n");
        }

        // Maak een adres voor de reiziger en sla deze op in de database
        Adres sietskeAdres = new Adres(7, "1234AB", "56", "Kerkstraat", "Utrecht", 7);
        boolean adresSaved = adao.Save(sietskeAdres);
        if (adresSaved) {
            System.out.println("[Test] Adres opgeslagen voor Sietske: " + sietskeAdres + "\n");
        } else {
            System.out.println("[Test] Het adres kon niet worden opgeslagen.\n");
        }

        // Update ook het adres van Sietske
        sietskeAdres.setWoonplaats("Amsterdam");
        boolean adresUpdated = adao.Update(sietskeAdres);
        if (adresUpdated) {
            Adres updatedAdres = adao.FindByReiziger(sietske);
            System.out.println("[Test] Geüpdatet adres: " + updatedAdres + "\n");
        } else {
            System.out.println("[Test] Het updaten van het adres is mislukt.\n");
        }

        // Test het verwijderen van een adres
        System.out.println("[Test] Verwijder het adres van Sietske.");
        boolean adresDeleted = adao.Delete(sietskeAdres);
        if (adresDeleted) {
            System.out.println("[Test] Het adres van Sietske is verwijderd.\n");
        } else {
            System.out.println("[Test] Het adres kon niet worden verwijderd.\n");
        }

        // Test het verwijderen van een reiziger
        System.out.println("[Test] ReizigerDAO.delete() - Verwijder de reiziger met ID 8.");
        boolean deleted = rdao.delete(sietske);
        if (deleted) {
            reizigers = rdao.findAll();
            System.out.println("[Test] Na ReizigerDAO.delete() zijn er " + reizigers.size() + " reizigers.\n");
        } else {
            System.out.println("[Test] Het verwijderen van de reiziger is mislukt.\n");
        }

        // Test opnieuw de findAll() methoden voor reizigers en adressen
        System.out.println("[Test] ReizigerDAO.findAll() geeft nu de volgende reizigers:");
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
    }
}