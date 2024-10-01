package sd.project.dp_ovchipkaart.domain.product;

import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ProductDAO {
    boolean save(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public List<Product> findByOVChipkaart(OV_chipkaart ovChipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
