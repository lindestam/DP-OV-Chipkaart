package hibernate_ovchipkaart.domain;

import java.sql.SQLException;
import java.util.List;

public interface OV_ChipkaartDAO {
    boolean save(OV_Chipkaart ovChipkaart) throws SQLException;
    boolean update(OV_Chipkaart ovChipkaart) throws SQLException;
    boolean delete(OV_Chipkaart ovChipkaart) throws SQLException;
    OV_Chipkaart findByKaartNummer(int kaartNummer) throws SQLException;
    List<OV_Chipkaart> findAll() throws SQLException;
    List<OV_Chipkaart> findByReiziger(ReizigerH reiziger) throws SQLException;
}
