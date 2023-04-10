package com.transtour.user.infrastructure.persistence.jpa;

import com.transtour.user.domain.Driver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Qualifier("DriverRepo")
public interface DriverRepository extends JpaRepository<Driver, String>, JpaSpecificationExecutor<Driver> {
}
