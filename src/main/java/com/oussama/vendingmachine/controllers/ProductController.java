package com.oussama.vendingmachine.controllers;


import com.oussama.vendingmachine.exceptions.ResourceNotFoundException;
import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.services.ProductService;
import com.oussama.vendingmachine.utils.Constant;
import com.oussama.vendingmachine.utils.CurrentUser;
import net.bytebuddy.implementation.bytecode.Throw;
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


    @PostMapping("/newProduct")
    public ResponseEntity<?> newProduct(@RequestBody Product product) {
        if (product != null) {
            product.setUser(new User(CurrentUser.getCurrentLoggedUser()));
            return ResponseEntity.status(productService.newProduct(product)).build();

        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        if (product != null && product.getProductId()!=0) {
                ResponseEntity.status(productService.updateProduct(product)).build();
        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }


    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long product_id) {
        return ResponseEntity.status(productService.deleteProductById(product_id)).build();
    }

}
