package com.oussama.vendingmachine.repositorys;

import com.oussama.vendingmachine.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);
}
