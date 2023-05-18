package com.example.springweb.web;

import com.example.springweb.entity.Cart;
import com.example.springweb.entity.Product;
import com.example.springweb.root.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartsRestController {

    private final CartsService cartsService;



    //    "/carts/add/{cart_id}/{product_id}"    - add by id
    // добавление в Carts продукта из Products с добавлением соответствующего cart_id
    @PostMapping("/add/{cart_id}/{product_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addById(
            @PathVariable("cart_id") Integer cart_id,
            @PathVariable("product_id") Integer product_id) {

        cartsService.addProductToCartById(cart_id, product_id);

    }

    //    "/carts/delete/{cart_id}"    - delete by id
    // удаление из Carts всех продуктов, "принадлежащих" переданному cart_id
    @GetMapping("/delete/{cart_id}")
    public void deleteById(@PathVariable("cart_id") Integer cart_id) {
        cartsService.deleteById(cart_id);
    }

    //    "/carts/{cart_id}"    - find by id -
    //    поиск в таблице Carts продуктов принадлежащих корзине с заданныи id
    @GetMapping("/{cart_id}")
    public List<Product> findById(@PathVariable("cart_id") Integer cart_id) {
        return cartsService
                .findById(cart_id)
                .orElseThrow(() -> new IllegalArgumentException("no product in Cart with id = " + cart_id));
    }


    //================= use Cart ===================================
    //    "/carts2/test/{cart_id}"    - find by id -
    //    поиск в таблице Carts продуктов принадлежащих корзине с заданныи id
    // возвращает список объектов Cart
    @GetMapping("/use_carts/{cart_id}")
    public List<Cart> findById_test(@PathVariable("cart_id") Integer cart_id) {
        return cartsService
                .findById2(cart_id)
                .orElseThrow(() -> new IllegalArgumentException("no product in Cart with id = " + cart_id));
    }
}
