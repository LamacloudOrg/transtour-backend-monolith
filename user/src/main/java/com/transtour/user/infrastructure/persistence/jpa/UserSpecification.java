package com.transtour.user.infrastructure.persistence.jpa;

import com.transtour.user.domain.Role;
import com.transtour.user.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class UserSpecification {

    public static Specification<User> findAllUserByRol(String rol) {
        return (root, query, criteriaBuilder) -> {
            Join<User, Role> drivers = root.join("roles");
            return criteriaBuilder.equal(drivers.get("name"), rol);
        };
    }
}
