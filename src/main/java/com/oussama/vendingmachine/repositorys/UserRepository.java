package com.oussama.vendingmachine.repositorys;

import com.oussama.vendingmachine.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Override
    Optional<User> findById(String username);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);
}
