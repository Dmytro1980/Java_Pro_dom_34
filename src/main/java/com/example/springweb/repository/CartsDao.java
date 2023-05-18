package com.example.springweb.repository;

import com.example.springweb.entity.Cart;
import com.example.springweb.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartsDao {

    private final JdbcTemplate jdbcTemplate;

    public void addProductToCartById(Integer cart_id, Integer product_id) {


        String sql1 = "SELECT \"name\" FROM \"Products\" WHERE \"id\" = " + product_id;
        String product_name = jdbcTemplate.queryForObject(
                sql1,
                String.class);

        String sql2 = "SELECT \"price\" FROM \"Products\" WHERE \"id\" = " + product_id;
        Integer product_price = jdbcTemplate.queryForObject(
                sql2,
                Integer.class);

        String sql = "INSERT INTO public.\"Carts\" (cart_id, product_id, product_name, product_price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, cart_id, product_id, product_name, product_price);
    }

    public void deleteById(Integer cart_id) {

        String sql = "DELETE FROM \"Carts\" WHERE \"cart_id\" = ?";
        Object[] args = new Object[]{cart_id};
        this.jdbcTemplate.update(sql, args);
    }

    public Optional<List<Product>> findById(Integer cart_id) {

        try {

            String sql = "SELECT product_id, product_name, product_price " +
                    "FROM public.\"Carts\" WHERE cart_id = " + cart_id;
            List<Product> products = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) -> new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price")
                    ));

            return Optional.of(products);


        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    //================= use Cart ===================================
    public Optional<List<Cart>> findById2(Integer cart_id) {

        List<Cart> productsInCart = new ArrayList<>();
        try {

            String sql = "SELECT product_id, product_name, product_price " +
                    "FROM public.\"Carts\" WHERE cart_id = " + cart_id;
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

            for(Map m: list){
                Cart cart = new Cart(cart_id,new ArrayList<>(
                       m.values()
                ));
                productsInCart.add(cart);
            }

            return Optional.of(productsInCart);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
