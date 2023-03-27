package com.transtour.kernel.repository;


import com.transtour.kernel.domain.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("userRepo")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDniAndPassword(String dni, String password);

    Optional<User> findByDni(String dni);

    @Override
    <S extends User> S save(S entity);
}
