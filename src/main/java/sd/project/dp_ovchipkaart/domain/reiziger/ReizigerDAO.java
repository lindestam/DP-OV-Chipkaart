package sd.project.dp_ovchipkaart.domain.reiziger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ReizigerDAO {
    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger) throws SQLException;
    public Reiziger findById(int id) throws SQLException;
    public List<Reiziger> findByGbDatum(Date date);
    public List<Reiziger> findAll() throws SQLException;
}
