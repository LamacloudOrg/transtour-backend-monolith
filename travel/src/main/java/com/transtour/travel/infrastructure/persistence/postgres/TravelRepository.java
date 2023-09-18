package com.transtour.travel.infrastructure.persistence.postgres;

import com.transtour.travel.domain.Travel;
import com.transtour.kernel.domain.travel.TravelStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Qualifier("JpaRepository")
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByOrderNumberAndStatus(Long id, TravelStatus status);


    Page<Travel> findByDateCreated(LocalDate dateCreated, Pageable pageable);

    Page<Travel> findByDateCreatedAndStatus(LocalDate dateCreated, TravelStatus status, Pageable pageable);

    Page<Travel> findByDateCreatedAndCarDriver(LocalDate dateCreated, String cardDriver, Pageable pageable);

    Page<Travel> findByCarDriverAndStatus(String cardDriver, TravelStatus status, Pageable pageable);

    Page<Travel> findByDateCreatedAndStatusAndCarDriver(LocalDate dateCreated, TravelStatus status, String cardDriver, Pageable pageable);

    Page<Travel> findByCarDriver(String cardDriver, Pageable pageable);

    Page<Travel> findByStatus(TravelStatus travelStatus, Pageable pageable);
}



