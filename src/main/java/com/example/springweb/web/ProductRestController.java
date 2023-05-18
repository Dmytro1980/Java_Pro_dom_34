package com.example.springweb.web;

import com.example.springweb.entity.Product;
import com.example.springweb.root.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    //    "/all"
    @GetMapping("/all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    //    "/id/{id}"
    @GetMapping("/{id}")
    public Product getByIdPathParam(@PathVariable("id") Integer id) {
        return productService
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no product with id = " + id));
    }

    //    "/"
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {

        productService.addProduct(product);

    }

    //    "/delete/{id}"
    @GetMapping("/delete/{id}")
    public void deleteByIdPathParam(@PathVariable("id") Integer id) {
        productService.deleteById(id);
    }
}
