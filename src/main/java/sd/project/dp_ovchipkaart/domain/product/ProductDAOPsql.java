package sd.project.dp_ovchipkaart.domain.product;

import hibernate_ovchipkaart.domain.ProductH;
import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private Product product;
    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean save(Product product) throws SQLException {
        String query = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, product.getProduct_nummer());
        preparedStatement.setString(2, product.getNaam());
        preparedStatement.setString(3, product.getBeschrijving());
        preparedStatement.setDouble(4, product.getPrijs());
        preparedStatement.execute();

        for(OV_chipkaart ovChipkaart: product.getOvchipkaarten()) {
            preparedStatement = conn.prepareStatement("insert into OV_Chipkaart_Product(kaart_nummer, product_nummer) values(?,?)");
            preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
            preparedStatement.setInt(2, product.getProduct_nummer());
            preparedStatement.execute();
        }
        preparedStatement.close();
        return true;
    }
    public boolean delete(Product product) throws SQLException {
        String deleteRelationshipQuery = "DELETE FROM ov_chipkaart_product WHERE product_nummer = ?";
        PreparedStatement deleteRelationshipStatement = conn.prepareStatement(deleteRelationshipQuery);
        deleteRelationshipStatement.setInt(1, product.getProduct_nummer());
        deleteRelationshipStatement.execute();

        String query = "delete from product where product_nummer=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, product.getProduct_nummer());
        preparedStatement.execute();
        preparedStatement.close();
        return true;
    }
    public boolean update(Product product) throws SQLException {
        String deleteQuery = "update ov_chipkaart_product WHERE product_nummer = ?";
        PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, product.getProduct_nummer());
        deleteStatement.execute();

// Voeg nieuwe relaties toe
        for(OV_chipkaart ovChipkaart : product.getOvchipkaarten()) {
            PreparedStatement insertStatement = conn.prepareStatement("update ov_chipkaart_product (kaart_nummer, product_nummer) VALUES (?, ?)");
            insertStatement.setInt(1, ovChipkaart.getKaart_nummer());
            insertStatement.setInt(2, product.getProduct_nummer());
            insertStatement.execute();
        }
        return true;
    }
    public List<Product> findByOVChipkaart(OV_chipkaart ovChipkaart) throws SQLException {
        String query = "SELECT p.product_nummer, p.naam, p.beschrijving, p.prijs FROM product p" +
                "INNER JOIN ocp ON p.product_nummer = ocp.product_nummer " +
                "WHERE ocp.kaart_nummer = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setProduct_nummer(resultSet.getInt("product_nummer"));
            product.setNaam(resultSet.getString("naam"));
            product.setBeschrijving(resultSet.getString("beschrijving"));
            product.setPrijs(resultSet.getDouble("prijs"));
            products.add(product);
        }
        return products;
    }
    public List<ProductH> findAll() throws SQLException {
        String query = "select * from product";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setProduct_nummer(resultSet.getInt("product_nummer"));
            product.setNaam(resultSet.getString("naam"));
            product.setBeschrijving(resultSet.getString("beschrijving"));
            product.setPrijs(resultSet.getDouble("prijs"));
            products.add(product);
        }
        return List.of();
    }

}
