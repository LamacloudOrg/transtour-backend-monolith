package com.transtour.travel.application.change_status;

import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.change_status.command.ChangeStatusCommand;
import com.transtour.travel.application.create.TravelCreationUC;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelNotFoundException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;



@Service
@Transactional
public class ChangeStatusUC {

    private static final Logger logger = LoggerFactory.getLogger(TravelCreationUC.class);

    private final String travelId;

    private final TravelRepository repository;

    public ChangeStatusUC(String travelId, TravelRepository repository) {
        this.travelId = travelId;
        this.repository = repository;
    }

    @SneakyThrows
    public void changeStatus(ChangeStatusCommand command) throws IOException {

        try {
            Travel travel = repository.findById(Long.parseLong(command.getTravelId())).orElseThrow(() -> new TravelNotFoundException(command.getTravelId().toString()));
            travel.setStatus(TravelStatus.ERROR);
            repository.save(travel);
            logger.info("Cambiando el status a ERROR del travel:" + command.getTravelId());
        } catch (Exception e) {
            logger.info("Error al querer cambiar el status del travel: " + command.getTravelId() + " Con este error: " + e.getMessage());
        }
    }

}
