package com.transtour.travel.infrastructure.persistence.postgres;

import com.transtour.travel.domain.Travel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("JpaRepository")
public interface TravelRepository extends JpaRepository<Travel, Long> {
    @Override
    Optional<Travel> findById(Long aLong);
}



