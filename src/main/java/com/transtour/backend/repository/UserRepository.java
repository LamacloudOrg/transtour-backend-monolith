package com.transtour.backend.repository;


import com.transtour.backend.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("userRepo")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDniAndPassword(String dni, String password);

    Optional<User> findByDni(String dni);

}
