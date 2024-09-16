package sd.project.dp_ovchipkaart.domain.adres;

import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private final Connection conn;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean Save(Adres adres) throws SQLException {
        String insertQuery = "INSERT INTO adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {
            statement.setInt(1, adres.getId());
            statement.setString(2, adres.getPostcode());
            statement.setString(3, adres.getHuisnummer());
            statement.setString(4, adres.getStraat());
            statement.setString(5, adres.getWoonplaats());
            statement.setInt(6, adres.getReizigerId());  // Zorg ervoor dat het Adres object de juiste reiziger_id bevat

            return statement.executeUpdate() > 0;
        }
    }

    public boolean Update(Adres adres) throws SQLException {
        String updateQuery = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(updateQuery)) {
            statement.setString(1, adres.getPostcode());
            statement.setString(2, adres.getHuisnummer());
            statement.setString(3, adres.getStraat());
            statement.setString(4, adres.getWoonplaats());
            statement.setInt(5, adres.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean Delete(Adres adres) throws SQLException {
        String deleteQuery = "DELETE FROM adres WHERE adres_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(deleteQuery)) {
            statement.setInt(1, adres.getId());
            return statement.executeUpdate() > 0;
        }
    }


    public Adres FindByReiziger(Reiziger reiziger) throws SQLException {
        String selectQuery = "SELECT * FROM adres WHERE reiziger_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(selectQuery)) {
            statement.setInt(1, reiziger.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Adres adres = new Adres();
                adres.setId(resultSet.getInt("adres_id"));
                adres.setPostcode(resultSet.getString("postcode"));
                adres.setHuisnummer(resultSet.getString("huisnummer"));
                adres.setStraat(resultSet.getString("straat"));
                adres.setWoonplaats(resultSet.getString("woonplaats"));
                adres.setReizigerId(resultSet.getInt("reiziger_id"));

                return adres;
            } else {
                return null; // Geen adres gevonden voor deze reiziger_id
            }
        }
    }

    @Override
    public List<Adres> findAll() throws SQLException{
        List<Adres> adresList = new ArrayList<>();
        String selectQuery = "SELECT * FROM adres";
        PreparedStatement statement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Adres adres = new Adres(
                    resultSet.getInt("adres_id"),
                    resultSet.getString("postcode"),
                    resultSet.getString("huisnummer"),
                    resultSet.getString("straat"),
                    resultSet.getString("woonplaats"),
                    resultSet.getInt("reiziger_id")
            );
            adresList.add(adres);

        }
        return adresList;

    }
}
