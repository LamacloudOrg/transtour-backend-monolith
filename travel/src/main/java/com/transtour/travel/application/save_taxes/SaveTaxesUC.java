package com.transtour.travel.application.save_taxes;

import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.save_taxes.command.TravelSaveTaxesCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelNotFoundException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaveTaxesUC {

    private final TravelRepository travelRepository;

    public SaveTaxesUC(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @SneakyThrows
    public void save(TravelSaveTaxesCommand command) {

        Travel travel = travelRepository.findById(command.getId()).orElseThrow(() -> new TravelNotFoundException("No exist"));

        if (travel.getStatus().equals(TravelStatus.SIGNED)) travel.setStatus(TravelStatus.READY);
        travel.getPayload().setWhitingTime(command.getWhitingTime());
        travel.getPayload().setToll(command.getToll());
        travel.getPayload().setParkingAmount(command.getParkingAmount());
        travel.getPayload().setTaxForReturn(command.getTaxForReturn());
        travelRepository.save(travel);
    }
}
