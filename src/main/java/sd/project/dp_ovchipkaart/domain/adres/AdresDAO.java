package sd.project.dp_ovchipkaart.domain.adres;

import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    public boolean Save(Adres adres) throws SQLException;
    public boolean Update(Adres adres) throws SQLException;
    public boolean Delete(Adres adres) throws SQLException;
    public Adres FindByReiziger(Reiziger r) throws SQLException;
    public List<Adres> findAll() throws SQLException;
}
