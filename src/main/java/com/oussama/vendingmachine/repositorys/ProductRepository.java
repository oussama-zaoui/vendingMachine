package com.oussama.vendingmachine.repositorys;

import com.oussama.vendingmachine.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    <S extends Product> S save(S entity);

    @Query(value = "SELECT '*' FROM Product WHERE 'product_name'=productName")
    Product getProductByProductName(String productName);
}
