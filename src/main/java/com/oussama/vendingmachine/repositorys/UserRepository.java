package com.oussama.vendingmachine.repositorys;

import com.oussama.vendingmachine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User getReferenceById(Long aLong);
}
