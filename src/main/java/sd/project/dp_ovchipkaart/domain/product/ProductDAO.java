package sd.project.dp_ovchipkaart.domain.product;

import hibernate_ovchipkaart.domain.ProductH;
import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    boolean save(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public List<Product> findByOVChipkaart(OV_chipkaart ovChipkaart) throws SQLException;
    public List<ProductH> findAll() throws SQLException;
}
