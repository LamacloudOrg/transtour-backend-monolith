package com.transtour.user.infrastructure.persistence.jpa;

import com.transtour.user.domain.Role;
import com.transtour.user.domain.User;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Join;
import java.util.Optional;

@Qualifier("userRepo")
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByDniAndPassword(String dni, String password);

    Optional<User> findByDni(String dni);

    @Override
    <S extends User> S save(S entity);

    /*
    Query query = em.createQuery(
            "select u.* from user u inner join user_rol ur inner join rol r where u.id = user_rol.user_id and rol.id= user.rol_id and r.name = " + rol);
    */

}
