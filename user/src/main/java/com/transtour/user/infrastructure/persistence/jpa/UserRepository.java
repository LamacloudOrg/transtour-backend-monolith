package com.transtour.user.infrastructure.persistence.jpa;


import com.transtour.user.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("userRepo")
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    Optional<User> findByDniAndPassword(String dni, String password);

    Optional<User> findByDni(String dni);

    @Override
    <S extends User> S save(S entity);


}
