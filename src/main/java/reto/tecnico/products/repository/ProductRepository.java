package reto.tecnico.products.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import reto.tecnico.products.beans.Product;
import reto.tecnico.products.response.ProductResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public ProductResponse insertAndListProducts(Product product) {
    return jdbcTemplate.execute((connection) -> {
      CallableStatement cs = connection.prepareCall("{call sp_insertAndListProducts(?, ?, ?, ?, ?, ?)}");
      cs.setInt(1, product.getId());
      cs.setString(2, product.getName());
      cs.setDate(3, new java.sql.Date(product.getRegistrationDate().getTime()));
      cs.registerOutParameter(4, Types.REF_CURSOR);
      cs.registerOutParameter(5, Types.INTEGER);
      cs.registerOutParameter(6, Types.VARCHAR);
      return cs;
    }, (CallableStatement cs) -> {
      cs.execute();
      int responseCode = cs.getInt(5);
      String responseMessage = cs.getString(6);

      List<Product> products = new ArrayList<>();
      if (responseCode == 0) {
        try (ResultSet rs = (ResultSet) cs.getObject(4)) {
          while (rs.next()) {
            products.add(new Product(rs.getInt("id_producto"), rs.getString("nombre"), rs.getDate("fec_registro")));
          }
        }
      }

      return new ProductResponse(responseCode, responseMessage, products);
    });
  }
}