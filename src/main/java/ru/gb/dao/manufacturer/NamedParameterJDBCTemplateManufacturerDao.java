package ru.gb.dao.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.gb.entity.Manufacturer;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//@Component
@RequiredArgsConstructor
public class NamedParameterJDBCTemplateManufacturerDao implements ManufacturerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Manufacturer> findAll() {
        String sql = "SELECT * FROM manufacturer";
        return namedParameterJdbcTemplate.query(sql, (rs, rn) ->
                Manufacturer.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build());
    }

    @Override
    public Manufacturer findById(Long id) {
        String sql = "SELECT name, p.id as product_id, title, cost, manufacture_date, manufacturer_id FROM manufacturer m\n" +
                "JOIN product p on m.id = p.manufacturer_id\n" +
                "WHERE m.id = :manufacturerId;";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturerId", id);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new ManufacturerWithProductsExtractor());
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT name FROM manufacturer where id = :manufacturerId";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public void insert(Manufacturer manufacturer) {

    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public void deleteById(Long id) {

    }

    private static class ManufacturerMapper implements RowMapper<Manufacturer> {

        @Override
        public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Manufacturer.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }

    private static class ManufacturerWithProductsExtractor implements ResultSetExtractor<Manufacturer> {

        @Override
        public Manufacturer extractData(ResultSet rs) throws SQLException, DataAccessException {
            Manufacturer manufacturer = null;

            while (rs.next()) {
                long id = rs.getLong("manufacturer_id");
                if (manufacturer == null) {
                    manufacturer = Manufacturer.builder()
                            .id(id)
                            .name(rs.getString("name"))
                            .build();
                }
                final Product product = Product.builder()
                        .id(rs.getLong("product_id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build();
                manufacturer.addProduct(product);
            }
            return manufacturer;
        }
    }

}
