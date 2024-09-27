package sd.project.dp_ovchipkaart.domain.reiziger;

import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OVChipkaartDAOPsql;
import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;
import sd.project.dp_ovchipkaart.domain.adres.Adres;
import sd.project.dp_ovchipkaart.domain.adres.AdresDAOPsql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private final Connection con;
    private final AdresDAOPsql adresDAOPsql;
    private final OVChipkaartDAOPsql ovChipkaartDAOPsql;

    public ReizigerDAOPsql(Connection con, AdresDAOPsql adresDAOPsql, OVChipkaartDAOPsql ovChipkaartDAOPsql) {
        this.con = con;
        this.adresDAOPsql = adresDAOPsql;
        this.ovChipkaartDAOPsql = ovChipkaartDAOPsql;
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        String insertReizigerQuery = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(insertReizigerQuery)) {
            statement.setInt(1, reiziger.getId());
            statement.setString(2, reiziger.getVoorletters());
            statement.setString(3, reiziger.getTussenvoegsels());
            statement.setString(4, reiziger.getAchternaam());
            statement.setDate(5, reiziger.getGeboortedatum());
            statement.execute();

            // Als de reiziger een adres heeft, roep de save-methode van AdresDAOPsql aan
            if (reiziger.getAdres() != null) {
                adresDAOPsql.Save(reiziger.getAdres());  // Reiziger wordt doorgegeven aan AdresDAO
            }
            if (reiziger.getOvchipkaarten() != null) {
                for (OV_chipkaart ovChipkaart : reiziger.getOvchipkaarten()) {
                    ovChipkaartDAOPsql.save(ovChipkaart);
                }
            }
        }
            return true;
        }

    public boolean update(Reiziger reiziger) throws SQLException {
        String updateQuery = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
        try (PreparedStatement statement = con.prepareStatement(updateQuery)) {
            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsels());
            statement.setString(3, reiziger.getAchternaam());
            statement.setDate(4, reiziger.getGeboortedatum());
            statement.setInt(5, reiziger.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(Reiziger reiziger) throws SQLException {
        if (reiziger.getAdres() != null) {
            adresDAOPsql.Delete(reiziger.getAdres());
        }
        String deleteQuery = "DELETE FROM reiziger WHERE reiziger_id = ?";
        try (PreparedStatement statement = con.prepareStatement(deleteQuery)) {
            statement.setInt(1, reiziger.getId());
            return statement.executeUpdate() > 0;
        }
    }


    public Reiziger findById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM reiziger WHERE reiziger_id = ?";
        try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Reiziger reiziger = new Reiziger();
                    reiziger.setId(rs.getInt("reiziger_id"));
                    reiziger.setVoorletters(rs.getString("voorletters"));
                    reiziger.setTussenvoegsels(rs.getString("tussenvoegsel"));
                    reiziger.setAchternaam(rs.getString("achternaam"));
                    reiziger.setGeboortedatum(rs.getDate("geboortedatum"));

                    List<OV_chipkaart> ovChipkaarten = ovChipkaartDAOPsql.findByReiziger(reiziger);
                    reiziger.setOvchipkaart(ovChipkaarten);

                    return reiziger;
                } else {
                    return null; // Geen reiziger gevonden met dit ID
                }
            }
        }
    }


    public List<Reiziger> findByGbDatum(Date date) {
        return List.of();
    }


    public List<Reiziger> findByGbDatum(java.sql.Date date) throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
        String selectQuery = "SELECT * FROM reiziger WHERE geboortedatum = ?";
        try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
            statement.setDate(1, date);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Reiziger reiziger = new Reiziger(
                            rs.getInt("reiziger_id"),
                            rs.getString("voorletters"),
                            rs.getString("tussenvoegsel"),
                            rs.getString("achternaam"),
                            rs.getDate("geboortedatum")
                    );
                    reizigers.add(reiziger);
                }
            }
        }
        return reizigers;
    }


    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
        String selectQuery = "SELECT * FROM reiziger";
        try (PreparedStatement statement = con.prepareStatement(selectQuery);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Reiziger reiziger = new Reiziger(
                        rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum")
                );
                Adres adres = adresDAOPsql.FindByReiziger(reiziger);
                reiziger.setAdres(adres);

                List<OV_chipkaart> ovChipkaarten = ovChipkaartDAOPsql.findByReiziger(reiziger);
                reiziger.setOvchipkaart(ovChipkaarten);

                reizigers.add(reiziger);
            }
        }
        return reizigers;
    }
}
