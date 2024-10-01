package sd.project.dp_ovchipkaart.domain.OV_chipkaart;

import sd.project.dp_ovchipkaart.domain.product.Product;
import sd.project.dp_ovchipkaart.domain.product.ProductDAO;
import sd.project.dp_ovchipkaart.domain.product.ProductDAOPsql;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{
    private Connection conn;
    private ProductDAOPsql productDAOPsql;
    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(OV_chipkaart ovChipkaart) throws SQLException {
        String insertQuer = "INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertQuer);
        ps.setInt(1, ovChipkaart.getKaart_nummer());
        ps.setDate(2, ovChipkaart.getGeldig_tot());
        ps.setInt(3, ovChipkaart.getKlasse());
        ps.setInt(4, ovChipkaart.getSaldo());
        ps.setInt(5, ovChipkaart.getReiziger().getId());
        ps.execute();
        ps.close();
        if(productDAOPsql != null) {
            for(Product product: ovChipkaart.getProducten()) {

            }
        }
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(OV_chipkaart ovChipkaart) throws SQLException {
        String query = "UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setDate(1, ovChipkaart.getGeldig_tot());
        pst.setInt(2, ovChipkaart.getKlasse());
        pst.setDouble(3, ovChipkaart.getSaldo());
        pst.setInt(4, ovChipkaart.getReiziger().getId());  // Verwijzing naar de reiziger
        pst.setInt(5, ovChipkaart.getKaart_nummer());
        return pst.executeUpdate() > 0;
    }

    @Override
    public boolean delete(OV_chipkaart ovChipkaart) throws SQLException {
        String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, ovChipkaart.getKaart_nummer());
        return pst.executeUpdate() > 0;
    }
    public OV_chipkaart findByKaartNummer(int kaartNummer) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, kaartNummer);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            OV_chipkaart ovChipkaart = new OV_chipkaart();
            ovChipkaart.setKaart_nummer(rs.getInt("kaart_nummer"));
            ovChipkaart.setGeldig_tot(rs.getDate("geldig_tot"));
            ovChipkaart.setKlasse(rs.getInt("klasse"));
            ovChipkaart.setSaldo(rs.getInt("saldo"));
            return ovChipkaart;
        }
        return null;
    }
    public List<OV_chipkaart> findAll() throws SQLException {
        List<OV_chipkaart> ovChipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Reiziger reiziger = new Reiziger();
            OV_chipkaart ovChipkaart = new OV_chipkaart(
                    rs.getInt("kaart_nummer"),
                    rs.getDate("geldig_tot"),
                    rs.getInt("klasse"),
                    rs.getInt("saldo"),
                    reiziger
            );
            ovChipkaarten.add(ovChipkaart);
        }
        return ovChipkaarten;
    }
    public List<OV_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        List<OV_chipkaart> ovChipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, reiziger.getId());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OV_chipkaart ovChipkaart = new OV_chipkaart();
            ovChipkaart.setKaart_nummer(rs.getInt("kaart_nummer"));
            ovChipkaart.setGeldig_tot(rs.getDate("geldig_tot"));
            ovChipkaart.setKlasse(rs.getInt("klasse"));
            ovChipkaart.setSaldo(rs.getInt("saldo"));
            ovChipkaart.setReiziger(reiziger);
            ovChipkaarten.add(ovChipkaart);
        }
        return ovChipkaarten;
    }
}
