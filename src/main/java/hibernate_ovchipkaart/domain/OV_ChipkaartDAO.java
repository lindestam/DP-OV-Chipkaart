package hibernate_ovchipkaart.domain;

import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OV_ChipkaartDAO {
    boolean save(OV_chipkaart ovChipkaart) throws SQLException;
    boolean update(OV_chipkaart ovChipkaart) throws SQLException;
    boolean delete(OV_chipkaart ovChipkaart) throws SQLException;
    OV_chipkaart findByKaartNummer(int kaartNummer) throws SQLException;
    List<OV_chipkaart> findAll() throws SQLException;
    List<OV_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
}
