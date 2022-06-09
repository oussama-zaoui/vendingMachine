package com.oussama.vendingmachine.controllers;


import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.services.ProductService;
import com.oussama.vendingmachine.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable long product_id) {
        Product product = productService.getProduct(product_id);
        if (product != null) {
            return ResponseEntity.of(Optional.of(product));
        }

        return ResponseEntity.status(Constant.NOT_FOUND).build();
    }


    @PostMapping("/newProduct/{seller}")
    public ResponseEntity<?> newProduct(@RequestBody Product product, @PathVariable String seller) {
        if (product != null) {
            product.setUser(new User(seller));
            if (productService.newProduct(product) == Constant.OK) {
                return ResponseEntity.status(Constant.OK).build();
            }

        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        if (product != null) {
            int status = productService.updateProduct(product);
            if (status == Constant.OK) {
                return ResponseEntity.status(Constant.OK).build();
            } else if (status == Constant.FORBIDDEN) {
                return ResponseEntity.status(Constant.FORBIDDEN).build();
            }

        }
        return ResponseEntity.status(Constant.NOT_FOUND).build();
    }


    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long product_id) {
        int status = productService.deleteProductById(product_id);
        if (status == Constant.OK) {
            return ResponseEntity.status(Constant.OK).body("success");
        } else if (status == Constant.FORBIDDEN) return ResponseEntity.status(Constant.FORBIDDEN).build();

        return ResponseEntity.status(Constant.OK).build();
    }

}
