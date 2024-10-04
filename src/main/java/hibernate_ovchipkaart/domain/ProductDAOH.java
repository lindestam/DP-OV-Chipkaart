package hibernate_ovchipkaart.domain;

import sd.project.dp_ovchipkaart.domain.product.Product;

import java.util.List;

public interface ProductDAOH {
    boolean save(ProductH product);
    boolean update(ProductH product);
    boolean delete(ProductH product);
    List<ProductH> findByOVChipkaart(OV_Chipkaart ovChipkaart);
    List<ProductH> findAll();
}
