package com.example.springweb.config.util.mapper;

import com.example.springweb.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Mapper implements RowMapper<Product> {

    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"));
    }
}
