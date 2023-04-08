package com.transtour.travel.application.create;

import com.transtour.travel.application.create.command.CreationCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TravelCreationUC {
    private static final Logger logger = LoggerFactory.getLogger(TravelCreationUC.class);
    private final TravelRepository repository;

    public TravelCreationUC(@Qualifier("JpaRepository") TravelRepository repository) {
        this.repository = repository;
    }

    public void createTravel(CreationCommand command) {
        Travel travel = repository.save(Travel.create(command));
        logger.info("Travel Created " + travel.getOrderNumber());
    }

}
