package com.example.springweb.root;

import com.example.springweb.entity.Cart;
import com.example.springweb.entity.Product;
import com.example.springweb.repository.CartsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartsService {

    private final CartsDao cartsDao;

    public void addProductToCartById(Integer cart_id, Integer product_id) {
        this.cartsDao.addProductToCartById(cart_id, product_id);
    }

    public void deleteById(Integer cart_id) {
        this.cartsDao.deleteById(cart_id);
    }

    public Optional<List<Product>> findById(Integer cart_id) {
        return this.cartsDao.findById(cart_id);
    }

    //================= use Cart2 ===================================
    public Optional<List<Cart>> findById2(Integer cart_id) {
        return this.cartsDao.findById2(cart_id);
    }
}
