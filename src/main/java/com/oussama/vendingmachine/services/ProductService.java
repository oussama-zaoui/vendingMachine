package com.oussama.vendingmachine.services;


import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(long product_id){
        if (productRepository.findById(product_id).isPresent()){
            return productRepository.findById(product_id).get();
        }

        return null;
    }

    public boolean newProduct(Product product){
        if (!productRepository.findById(product.getProductId()).isPresent()) {
            productRepository.save(product);
            return true;
        }else
            return false;

    }

    public boolean updateProduct(Product product) {
        if (productRepository.findById(product.getProductId()).isPresent()) {
            productRepository.save(product);
            return true;
        }else
            return false;
    }

    public void deleteProductById(long product_id) {
        Product product = getProduct(product_id);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    public Product getProductByProductName(Product product){
       Product product1= productRepository.getProductByProductName(product.getProductName());
       if (product1==null){
           return product1;
       }
       else return null;
    }
}
