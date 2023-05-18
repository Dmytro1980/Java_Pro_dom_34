package com.example.springweb.repository;

import com.example.springweb.entity.Product;
import com.example.springweb.config.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Product> findById(Integer id) {
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "SELECT * FROM \"Products\" WHERE \"id\" = ?",
                    new Mapper(),
                    id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM public.\"Products\"",
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"))
        );
    }

    public void addProduct(Product product) {

        String sql = "INSERT INTO public.\"Products\" (name, price) VALUES ( ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    public void deleteById(Integer id) {

            String sql = "DELETE FROM \"Products\" WHERE \"id\" = ?";
            Object[] args = new Object[]{id};
        this.jdbcTemplate.update(sql, args);
    }
}
