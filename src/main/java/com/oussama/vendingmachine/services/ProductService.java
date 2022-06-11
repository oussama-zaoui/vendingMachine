package com.oussama.vendingmachine.services;


import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.repositorys.ProductRepository;
import com.oussama.vendingmachine.utils.Constant;
import com.oussama.vendingmachine.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(long product_id) {
        if (productRepository.findById(product_id).isPresent()) {
            Product product=productRepository.findById(product_id).get();
            product.setUser(null);
            return product;
        }

        return null;
    }

    public int newProduct(Product product) {
        if (!productRepository.findByName(product.getProductName()).isPresent()) {
            productRepository.save(product);
            return Constant.OK;
        }
        return Constant.FORBIDDEN;
    }

    public int updateProduct(Product product) {
        if(productRepository.findById(product.getProductId()).isPresent()){
            if (isProductOwner(product.getProductId())) {
                product.setUser(productRepository.findById(product.getProductId()).get().getUser());
                productRepository.save(product);
               return Constant.OK;
            }else return Constant.FORBIDDEN;
        }

        return Constant.NOT_FOUND;

    }

    public int deleteProductById(long product_id) {
        Product product = getProduct(product_id);
        if (product != null) {
            if (isProductOwner(product_id)) {
                productRepository.delete(product);
                return Constant.OK;
            } else {
                return Constant.FORBIDDEN;
            }

        }
        return Constant.NOT_FOUND;


    }

    public boolean isProductOwner(long product_id) {
        String owner = productRepository.getProductOwner(product_id);
        return owner.equals(CurrentUser.getCurrentLoggedUser());
    }

}
