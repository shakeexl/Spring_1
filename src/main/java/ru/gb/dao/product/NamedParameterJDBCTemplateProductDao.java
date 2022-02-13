package ru.gb.dao.product;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.dao.manufacturer.ManufacturerDao;
import ru.gb.entity.Manufacturer;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//@Component
@RequiredArgsConstructor
public class NamedParameterJDBCTemplateProductDao implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Product> findAll() {
        String sql = "SELECT * FROM product";
        return namedParameterJdbcTemplate.query(sql, (rs, rn) ->
                Product.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("name"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build());
    }

    @Override
    public Product findById(Long id) {
        String sql = "select p.id, title as product_name, cost, manufacture_date, m.name as manufacturer_name, manufacturer_id\n" +
                "FROM product p JOIN manufacturer m on p.manufacturer_id = m.id  WHERE p.id = :id;";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new ProductWithManufacturerExtractor());
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT title FROM product where id = :id";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public void insert(Product product) {
    }

    @Override
    public void update(Product product) {
    }

    @Override
    public void deleteById(Long id) {

    }

    private static class ProductWithManufacturerExtractor implements ResultSetExtractor<Product> {

        @Override
        public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
            Product product = null;

            while (rs.next()) {
                long id = rs.getLong("id");
                if (product == null) {
                    product = Product.builder()
                            .id(id)
                            .title(rs.getString("product_name"))
                            .cost(rs.getBigDecimal("cost"))
                            .date(rs.getDate("manufacture_date").toLocalDate())
                            .build();
                }
                final Manufacturer manufacturer = Manufacturer.builder()
                        .id(rs.getLong("manufacturer_id"))
                        .name(rs.getString("manufacturer_name"))
                        .build();
                product.addManufacturer(manufacturer);
            }
            return product;
        }
    }

}
