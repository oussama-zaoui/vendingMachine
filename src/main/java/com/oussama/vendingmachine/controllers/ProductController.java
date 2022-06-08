package com.oussama.vendingmachine.controllers;


import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/product" ,produces = MediaType.APPLICATION_JSON_VALUE )
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{product_id}")
    public ResponseEntity<Object> getProduct(@PathVariable long product_id){
        Product product= productService.getProduct(product_id);
        if(product!=null){
            return ResponseEntity.of(Optional.of(product));
        }

        return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
    }


    @PostMapping("/newProduct/{seller}")
    public ResponseEntity<String> newProduct(@RequestBody Product product,@PathVariable String seller) {
        if (product != null) {
            product.setUser(new User(seller));
            if(productService.newProduct(product)){
                return new ResponseEntity<String>("product created succefully", HttpStatus.OK);
            }

        }
        return new ResponseEntity<String>("product already exist", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        if (product != null) {
            if(productService.updateProduct(product)){
                return new ResponseEntity<String>("product updated succefully",HttpStatus.OK);
            }

        }
        return new ResponseEntity<String>("product not found",HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{product_id}")
    public void deleteProduct(@PathVariable long product_id) {
        productService.deleteProductById(product_id);
    }

}
