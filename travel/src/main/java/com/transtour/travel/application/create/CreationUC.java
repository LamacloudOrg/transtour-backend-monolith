package com.transtour.travel.application.create;

import com.transtour.travel.application.create.command.CreationCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreationUC {

    private final TravelRepository repository;

    public CreationUC(TravelRepository repository) {
        this.repository = repository;
    }

    public void createTravel (CreationCommand command){
        repository.save(Travel.create(command));
    }

}
