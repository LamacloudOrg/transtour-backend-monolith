package com.transtour.travel.infrastructure.persistence.postgres;

import com.transtour.travel.domain.Travel;
import com.transtour.kernel.domain.travel.TravelStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Qualifier("JpaRepository")
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByOrderNumberAndStatus(Long id, TravelStatus status);


    Page<Travel> findByCreatedAt(LocalDateTime dateCreated, Pageable pageable);

    Page<Travel> findByCreatedAtAndStatus(LocalDateTime dateCreated, TravelStatus status, Pageable pageable);

    Page<Travel> findByCreatedAtAndCarDriver(LocalDateTime dateCreated, String cardDriver, String driver, Pageable pageable);

    Page<Travel> findByCarDriverAndStatus(String cardDriver, TravelStatus status, Pageable pageable);

    Page<Travel> findByCreatedAtAndStatusAndCarDriver(LocalDateTime dateCreated, TravelStatus status, String cardDriver, Pageable pageable);

    Page<Travel> findByCarDriver(String cardDriver, Pageable pageable);

    Page<Travel> findByStatus(TravelStatus travelStatus, Pageable pageable);
}



